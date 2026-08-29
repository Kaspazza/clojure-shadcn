(ns clojure-shadcn.stories.macros
  "Compile-time macros for Storybook documentation.

  Ported from mateuszmazurczak.portfolio.macros."
  (:require
   [clojure.string :as str]))

(defmacro embed-source
  "Reads source file at compile time and returns as string.

  Args:
  - namespace-symbol-or-string: The namespace to read source for (symbol or string)

  Example:
  (embed-source clojure-shadcn.ui.components.button)
  (embed-source 'clojure-shadcn.ui.components.button)

  Returns the full source code of the file as a string."
  [namespace-symbol-or-string]
  (let [ns-str (cond
                 (symbol? namespace-symbol-or-string) (str namespace-symbol-or-string)
                 (string? namespace-symbol-or-string) namespace-symbol-or-string
                 :else (throw (ex-info "embed-source expects a namespace symbol or string"
                                       {:argument namespace-symbol-or-string})))
        file-path (-> ns-str
                      (str/replace "." "/")
                      (str/replace "-" "_")
                      (str ".cljs"))
        full-path (str "src/cljs/" file-path)]
    (try (slurp full-path)
         (catch Exception e
           (throw (ex-info (str "Failed to read source file: " full-path)
                           {:namespace namespace-symbol-or-string
                            :file-path full-path
                            :error (.getMessage e)}
                           e))))))

;; ── embed-body internals (compile-time source extraction) ─────────────────
;;
;; These functions run at macro-expansion time (on the JVM). They locate a
;; `(defn ^:export StoryName ...)` form inside the calling namespace's source
;; file and return only its body — dedented — so stories can ship their own
;; source alongside the demo. The scanner is string- and comment-aware, in
;; the same style as scripts/port_scenes.clj.

(defn- token-end
  "Index one past the end of the token starting at idx (whitespace or a
   delimiter terminates the token)."
  [s idx]
  (let [end (or (some (fn [i] (let [c (nth s i)]
                                (when (or (Character/isWhitespace ^char c)
                                          (#{\( \) \[ \] \{ \} \"} c))
                                  i)))
                      (iterate inc idx))
                (count s))]
    end))

(defn- skip-ws
  [s idx]
  (loop [idx idx]
    (if (and (<= idx (count s))
             (Character/isWhitespace ^char (nth s idx)))
      (recur (inc idx))
      idx)))

(defn- form-end
  "Index one past the closing paren of the form opening at idx."
  [s idx]
  (loop [i (inc idx) depth 1 in-str false escaped false in-comment false]
    (if (>= i (count s))
      i
      (let [c (nth s i)]
        (cond
          in-comment (recur (inc i) depth in-str false (not= c \newline))
          in-str     (cond escaped   (recur (inc i) depth true  false false)
                           (= c \\)  (recur (inc i) depth true  true  false)
                           (= c \")  (recur (inc i) depth false false false)
                           :else     (recur (inc i) depth true  false false))
          (= c \;)   (recur (inc i) depth false false true)
          (= c \")   (recur (inc i) depth true  false false)
          (= c \()   (recur (inc i) (inc depth) in-str escaped in-comment)
          (= c \))   (if (= 1 depth)
                       (inc i)
                       (recur (inc i) (dec depth) in-str escaped in-comment))
          :else      (recur (inc i) depth in-str escaped in-comment))))))

(defn- top-level-forms
  "Vector of [start end] spans for every top-level (paren) form in s."
  [s]
  (loop [i 0 spans []]
    (if (>= i (count s))
      spans
      (if (= (nth s i) \()
        (let [e (form-end s i)]
          (recur e (conj spans [i e])))
        (recur (inc i) spans)))))

(defn- find-defn-form
  "Locates the top-level (defn ... name ...) form whose symbol name matches;
   tolerates ^metadata between defn and the name. Returns [start end]."
  [s name]
  (some (fn [[st e]]
          (let [c (subs s st e)]
            (when (str/starts-with? c "(defn ")
              (let [t1-end  (token-end c 6)
                    tok1    (subs c 6 t1-end)
                    nm      (if (str/starts-with? tok1 "^")
                              (let [i2 (skip-ws c t1-end)]
                                (subs c i2 (token-end c i2)))
                              tok1)]
                (when (= name nm)
                  [st e])))))
        (top-level-forms s)))

(defn- string-literal?
  "True when the char at idx opens a string literal."
  [s idx]
  (= (nth s idx) \"))

(defn- string-literal-end
  "Index one past the closing quote of the string literal opening at idx."
  [s idx]
  (loop [j (inc idx) escaped false]
    (cond
      (>= j (count s)) j
      escaped          (recur (inc j) false)
      (= (nth s j) \\) (recur (inc j) true)
      (= (nth s j) \") (inc j)
      :else            (recur (inc j) false))))

(defn- bracket-end
  "Index one past the matching closing bracket for the opener at idx."
  [s idx open close]
  (loop [j (inc idx) depth 1 in-str false escaped false]
    (if (or (>= j (count s)) (zero? depth))
      j
      (let [c (nth s j)]
        (cond
          in-str
          (cond
            escaped   (recur (inc j) depth true  false)
            (= c \\)  (recur (inc j) depth true  true)
            (= c \")  (recur (inc j) depth false false)
            :else     (recur (inc j) depth true  false))

          (= c \")    (recur (inc j) depth true  false)
          (= c open)  (recur (inc j) (inc depth) in-str false)
          (= c close) (recur (inc j) (dec depth) in-str false)
                    :else      (recur (inc j) depth       in-str false))))))
          
          (defn- dedent
  "Remove the common leading-whitespace prefix from all non-blank lines;
   blank lines and lines containing only whitespace are left empty."
  [s]
  (let [lines      (str/split s #"\n")
        min-indent (->> lines
                        (remove str/blank?)
                        (map #(count (re-find #"^ *" %)))
                        (reduce #(min %1 %2) Integer/MAX_VALUE))]
    (->> lines
         (map (fn [ln]
                (if (or (str/blank? ln) (zero? min-indent))
                  ln
                  (subs ln (min (count ln) min-indent)))))
         (str/join "\n"))))

(defn extract-body-source
  "Given full file source `s` and defn-name `name`, returns the body of
   `(defn ... name ...)`: everything after the optional docstring and the
   arglist, dedented, minus the trailing close-paren."
  [s name]
  (let [span (find-defn-form s name)]
    (when-not span
      (throw (ex-info (str "extract-body-source: defn not found: " name)
                      {:name name})))
    (let [[fs fe] span
          form     (subs s fs fe)
          t1-end   (token-end form 6)
          tok1     (subs form 6 t1-end)
          ;; with metadata like ^:export present, skip to the next token
          name-end (if (str/starts-with? tok1 "^")
                     (let [i2 (skip-ws form t1-end)]
                       (token-end form i2))
                     t1-end)]
      (let [i           (skip-ws form name-end)
            ;; skip an optional docstring before the arglist
            docstring?  (string-literal? form i)
            i'          (if docstring? (string-literal-end form i) i)
            i2          (skip-ws form i')
            _           (when-not (= (nth form i2) \[)
                          (throw (ex-info (str "extract-body-source: expected arglist for " name)
                                          {:name name})))
            body-start  (bracket-end form i2 \[ \])]
        (-> (subs form body-start (dec (count form)))
            (str/replace #"\)\s*$" "")
            dedent
            str/trim)))))

(defmacro embed-body
  "Reads the calling namespace's own source file at compile time and returns
   the body of the named story defn as a string, excluding the defn wrapper,
   defn name, docstring, and arglist.

  Example:
  (embed-body AvatarDemo)

  The macro resolves the calling namespace from `&env`, so no namespace
  argument is needed. The :cljs-dev alias must put stories/cljs on the
  classpath for the macro to `slurp` the file."
  [story-name]
  (let [ns-sym  (or (:name (:ns &env))
                    (throw (ex-info "embed-body: no :ns in macro expansion env" {})))
        ns-str  (str ns-sym)
        file-path (-> ns-str
                      (str/replace "." "/")
                      (str/replace "-" "_")
                      (str ".cljs"))
        full-path (str "stories/cljs/" file-path)
        source    (try (slurp full-path)
                       (catch Exception e
                         (throw (ex-info (str "embed-body: failed to read " full-path)
                                         {:ns ns-str :story story-name}
                                         e))))]
    (extract-body-source source (str story-name))))
