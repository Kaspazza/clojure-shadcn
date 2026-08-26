#!/usr/bin/env bb
;; port_scenes.clj — mechanical port of portfolio scenes → Storybook stories.
;;
;; Transforms:
;;   src/cljs/mateuszmazurczak/ui/components/X.cljs  → src/cljs/clojure_shadcn/ui/components/X.cljs  (ns rename only)
;;   src/cljs/mateuszmazurczak/ui/hooks/X.cljs       → src/cljs/clojure_shadcn/ui/hooks/X.cljs
;;   src/cljs/mateuszmazurczak/portfolio/ui_components/X.cljs
;;     → stories/cljs/clojure_shadcn/stories/X_stories.cljs  (defscene → ^:export defn + r/as-element)
;;
;; Usage: bb scripts/port_scenes.clj

(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(def SRC "/Users/Mati/Projects/personal/mateuszmazurczak")
(def DEST "/Users/Mati/Projects/personal/clojure-shadcn")

;; Website-specific components that stay out of the library.
(def excluded-components
  #{"admin" "app_skeleton" "footer" "header" "image" "navigation"})

;; Chat components get their own sidebar group.
(def chat-components
  #{"chat-container" "message" "prompt-input" "speech-recognition-button" "system-message"})

;; ─── textual renames applied to both components and scene bodies ────────────

(defn rename-text
  "Namespace renames that are safe as plain text replacements."
  [s]
  (-> s
      (str/replace "mm-portfolio-utils/" "helpers/")
      (str/replace "mateuszmazurczak.portfolio.utils" "clojure-shadcn.stories.helpers")
      (str/replace "mateuszmazurczak.portfolio.macros" "clojure-shadcn.stories.macros")
      (str/replace "mateuszmazurczak.ui.components." "clojure-shadcn.ui.components.")
      (str/replace "mateuszmazurczak.ui.hooks." "clojure-shadcn.ui.hooks.")
      (str/replace "mateuszmazurczak.utils." "clojure-shadcn.utils.")
      (str/replace "src/cljs/mateuszmazurczak/" "src/cljs/clojure_shadcn/")))

;; ─── top-level form scanner (paren-aware, string/comment aware) ─────────────

(defn first-offender
  "Scans with a bracket stack; returns index of the first delimiter that
   doesn't match its opener, :missing if EOF is reached with open brackets,
   or nil when the text is balanced."
  [s]
  (loop [i 0 stack () in-str false escaped false in-comment false]
    (if (>= i (count s))
      (when (seq stack) :missing)
      (let [c (nth s i)]
        (cond
          in-comment (recur (inc i) stack in-str false (not= c \newline))
          in-str (cond
                   escaped (recur (inc i) stack true false false)
                   (= c \\) (recur (inc i) stack true true false)
                   (= c \") (recur (inc i) stack false false false)
                   :else (recur (inc i) stack true false false))
          (= c \;) (recur (inc i) stack false false true)
          (= c \") (recur (inc i) stack true false false)
          (or (= c \() (= c \[) (= c \{)) (recur (inc i) (conj stack c) false false false)
          (or (= c \)) (= c \]) (= c \}))
          (let [expected (case c \) \( \] \[ \} \{)]
            (if (= (first stack) expected)
              (recur (inc i) (rest stack) false false false)
              i))
          :else (recur (inc i) stack false false false))))))

(defn- closer-for [open]
  (case open \( \) \[ \] \{ \}))

(defn fix-delimiters
  "Iteratively repairs delimiter runs. When an offending closer is found and
   the rest of its line is a pure run of closers, replaces that run with the
   exact closer sequence the open-bracket stack requires. Falls back to
   single-char removal otherwise. Returns [fixed-text log-entries status]."
  [s]
  (loop [s s log [] n 0]
    (if (>= n 40)
      [s log :gave-up]
      (let [off (first-offender s)]
        (cond
          (nil? off) [s log :clean]
          (= off :missing) [s log :missing-opener]
          :else
          (let [eol (or (str/index-of s "\n" off) (count s))
                run (subs s off eol)
                run-only-closers? (every? #(or (contains? #{\) \] \}} %)
                                           (Character/isWhitespace ^char %))
                                          run)]
            (if run-only-closers?
              ;; compute the stack at `off` to derive the correct closer sequence
              (let [stack-at-off
                    (loop [i 0 stack () in-str false escaped false in-comment false]
                      (if (>= i off)
                        stack
                        (let [c (nth s i)]
                          (cond
                            in-comment (recur (inc i) stack in-str false (not= c \newline))
                            in-str (cond
                                     escaped (recur (inc i) stack true false false)
                                     (= c \\) (recur (inc i) stack true true false)
                                     (= c \") (recur (inc i) stack false false false)
                                     :else (recur (inc i) stack true false false))
                            (= c \;) (recur (inc i) stack false false true)
                            (= c \") (recur (inc i) stack true false false)
                            (or (= c \() (= c \[) (= c \{)) (recur (inc i) (conj stack c) false false false)
                            (or (= c \)) (= c \]) (= c \})) (recur (inc i) (rest stack) false false false)
                            :else (recur (inc i) stack false false false)))))]
                (if (empty? stack-at-off)
                  ;; nothing open — drop the stray run entirely
                  (recur (str (subs s 0 off) (subs s eol))
                         (conj log {:idx off :run run :action :dropped-stray-run})
                         (inc n))
                  (let [correct (apply str (map closer-for stack-at-off))]
                    (recur (str (subs s 0 off) correct (subs s eol))
                           (conj log {:idx off :run run :replaced-with correct :action :replaced-run})
                           (inc n)))))
              ;; fallback: remove the single offending delimiter
              (recur (str (subs s 0 off) (subs s (inc off)))
                     (conj log {:idx off :char (nth s off) :action :removed-char})
                     (inc n)))))))))

(defn top-level-forms
  "Returns [start end] spans of top-level forms in s."
  [s]
  (loop [i 0 depth 0 in-str false escaped false in-comment false spans [] start nil]
    (if (>= i (count s))
      (if start (conj spans [start i]) spans)
      (let [c (nth s i)]
        (cond
          ;; inside line comment — ends at newline
          in-comment
          (recur (inc i) depth in-str false (not= c \newline) spans start)

          ;; inside string literal
          in-str
          (cond
            escaped                     (recur (inc i) depth true false false spans start)
            (= c \\)                    (recur (inc i) depth true true false spans start)
            (= c \")                    (recur (inc i) depth false false false spans start)
            :else                       (recur (inc i) depth true false false spans start))

          ;; comment start
          (= c \;)                     (recur (inc i) depth false false true spans start)
          ;; string start
          (= c \")                     (recur (inc i) depth true false false spans start)

          (= c \()                     (recur (inc i) (inc depth) false false false spans (or start i))
          (= c \))                     (let [d (dec depth)]
                                          (if (and (zero? d) start)
                                            (recur (inc i) d false false false (conj spans [start (inc i)]) nil)
                                            (recur (inc i) d false false false spans start)))

          ;; whitespace between forms
          (and (zero? depth) (nil? start) (Character/isWhitespace ^char c))
          (recur (inc i) depth false false false spans start)

          ;; any other char begins a form (metadata, quote, dispatch...)
          :else                        (recur (inc i) depth false false false spans (or start i)))))))

;; ─── defscene parsing ────────────────────────────────────────────────────────

(defn read-token
  "Read a whitespace-delimited token starting at i; returns [token next-i]."
  [s i]
  (let [end (or (some (fn [j] (when (Character/isWhitespace ^char (nth s j)) j))
                      (iterate inc i))
                (count s))]
    [(subs s i end) end]))

(defn skip-ws [s i]
  (loop [i i]
    (if (and (< i (count s)) (Character/isWhitespace ^char (nth s i)))
      (recur (inc i))
      i)))

(defn read-string-literal
  "Read a string literal starting at the opening quote; returns [content next-i]."
  [s i]
  (loop [j (inc i) chars []]
    (if (>= j (count s))
      [(apply str chars) j]
      (let [c (nth s j)]
        (if (= c \\)
          (recur (+ j 2) (conj chars c (nth s (inc j))))
          (if (= c \")
            [(apply str chars) (inc j)]
            (recur (inc j) (conj chars c))))))))

(defn read-bracketed
  "Read a [...] form starting at the open bracket; returns [content next-i]."
  [s i]
  (loop [j (inc i) depth 1 chars []]
    (if (>= j (count s))
      [(apply str chars) j]
      (let [c (nth s j)]
        (cond
          (= c \[) (recur (inc j) (inc depth) (conj chars c))
          (= c \]) (if (= depth 1)
                     [(apply str chars) (inc j)]
                     (recur (inc j) (dec depth) (conj chars c)))
          :else (recur (inc j) depth (conj chars c)))))))

(defn pascal-name
  "button-default → ButtonDefault"
  [s]
  (->> (str/split s #"-")
       (map #(if (seq %)
               (str (Character/toUpperCase ^char (first %)) (subs % 1))
               %))
       (str/join)))

(defn re-indent-body
  "Add `n` spaces to each line of body, except lines that begin inside
   a string literal (tracked across lines)."
  [body n]
  (let [lines (str/split body #"\n")
        ;; track string state line by line
        stateful (reduce
                  (fn [{:keys [out in-str]} line]
                    (let [in-str' (loop [i 0 in-str in-str]
                                    (if (>= i (count line))
                                      in-str
                                      (let [c (nth line i)]
                                        (cond
                                          (and in-str (= c \\)) (recur (+ i 2) in-str)
                                          (and in-str (= c \")) (recur (inc i) false)
                                          (and (not in-str) (= c \")) (recur (inc i) true)
                                          :else (recur (inc i) in-str)))))]
                      {:out (conj out (if (and (not in-str) (str/starts-with? (str/triml line) "" )
                                               (not (str/blank? line))
                                               (not in-str))
                                        (str (apply str (repeat n \space)) line)
                                        line))
                       :in-str in-str'}))
                  {:out [] :in-str false}
                  lines)]
    (str/join "\n" (:out stateful))))

(defn transform-defscene
  "Transform one (defscene ...) form span into a story defn. Returns string."
  [s [fs fe]]
  (let [form (subs s fs fe)
        i0 (skip-ws form (+ 9 (str/index-of form "(defscene")))
        [name-token i1] (read-token form i0)
        i2 (skip-ws form i1)
        [docstring i3] (if (= (nth form i2) \")
                         (let [[c n] (read-string-literal form i2)]
                           [c n])
                         [nil i2])
        i4 (skip-ws form i3)
        [_args i5] (when (and (< i4 (count form)) (= (nth form i4) \[))
                     (read-bracketed form i4))
        body (subs form i5 (dec (count form)))
        body' (str/triml body)]
    (if (seq (str/trim _args))
      (do (println "  WARN: scene with args, manual port needed:" name-token) nil)
      (str "(defn ^:export " (pascal-name name-token) "\n"
           (if docstring (str "  \"" docstring "\"\n") "")
           "  []\n"
           "  (r/as-element\n"
           (rename-text (re-indent-body body' 2))
           "))\n"))))

;; ─── ns form parsing ─────────────────────────────────────────────────────────

(defn extract-title [s]
  (let [m (re-find #":title\s+\"([^\"]+)\"" s)]
    (second m)))

(defn extract-requires
  "Pull the :require entries (as raw text) out of an ns form.
   Entries may span multiple lines, so scan balanced [...] groups."
  [ns-form]
  (let [m (re-find #"(?s)\(:require\s+(.*?)\)" ns-form)]
    (when m
      (let [block (second m)]
        (loop [i 0 entries []]
          (if (>= i (count block))
            (reverse entries)
            (let [c (nth block i)]
              (cond
                (Character/isWhitespace ^char c) (recur (inc i) entries)
                (= c \[)
                (let [end (loop [j (inc i) depth 1 in-str false escaped false]
                            (if (>= j (count block))
                              j
                              (let [ch (nth block j)]
                                (cond
                                  (and in-str escaped) (recur (inc j) depth true false)
                                  (and in-str (= ch \\)) (recur (inc j) depth true true)
                                  (and in-str (= ch \")) (recur (inc j) depth false false)
                                  (= ch \") (recur (inc j) depth true false)
                                  (= ch \[) (recur (inc j) (inc depth) in-str false)
                                  (= ch \]) (if (= depth 1) (inc j) (recur (inc j) (dec depth) in-str false))
                                  :else (recur (inc j) depth in-str false)))))]
                  (recur end (conj (vec entries) (subs block i end))))
                :else (recur (inc i) entries)))))))))

(defn transform-require-entry
  "Entry includes its brackets: [ns :as alias] or [ns :refer [syms]]."
  [entry]
  (let [entry' (str/trim entry)
        renamed (rename-text entry')
        inner (str/trim (subs renamed 1 (dec (count renamed))))]
    (cond
      (str/starts-with? inner "portfolio.reagent-18") nil
      (str/includes? inner "clojure-shadcn.stories.helpers")
      (str "[" (str/replace inner #"mm-portfolio-utils" "helpers") "]")
      :else renamed)))

(defn build-ns-form
  "Build the new ns form for a story file. Require entries include their brackets."
  [story-ns docstring requires require-macros]
  (str "(ns " story-ns "\n"
       (when docstring (str "  \"" docstring "\"\n"))
       (when (seq requires)
         (str "  (:require\n"
              (str/join "\n" (map #(str "   " %) requires))
              ")\n"))
       (when require-macros
         (str "  (:require-macros " require-macros ")\n"))
       ")"))

;; ─── main ────────────────────────────────────────────────────────────────────

(defn port-component! [f]
  (let [fname (.getName f)
        base (str/replace fname #"\.cljs$" "")
        content (-> (slurp f) rename-text)]
    (io/make-parents (io/file DEST "src/cljs/clojure_shadcn/ui/components" fname))
    (spit (io/file DEST "src/cljs/clojure_shadcn/ui/components" fname) content)
    (println "ported component:" fname)))

(defn port-hook! [f]
  (let [fname (.getName f)
        content (-> (slurp f) rename-text)]
    (io/make-parents (io/file DEST "src/cljs/clojure_shadcn/ui/hooks" fname))
    (spit (io/file DEST "src/cljs/clojure_shadcn/ui/hooks" fname) content)
    (println "ported hook:" fname)))

(defn port-scene! [f]
  (let [fname (.getName f)
        base (str/replace fname #"\.cljs$" "")
        [content fixes fix-status] (fix-delimiters (slurp f))]
    (when (seq fixes)
      (println "  FIXED" fname "—" (count fixes) "stray delimiters removed:"
               (pr-str (map :char fixes)) "status:" fix-status))
    (when (not= fix-status :clean)
      (println "  ERROR: could not fully balance" fname "—" fix-status))
    (let [forms (top-level-forms content)
        ;; find ns form: first form starting with "(ns "
        ns-span (first (filter (fn [[fs _]]
                                 (str/starts-with? (subs content fs (+ fs 4)) "(ns "))
                               forms))
        ns-text (subs content (first ns-span) (second ns-span))
        title (or (extract-title content) (pascal-name base))
        group (if (contains? chat-components base) "Chat" "Components")
        requires (->> (extract-requires ns-text)
                      (map transform-require-entry)
                      (remove nil?)
                      (remove #(str/blank? %)))
        ;; ensure reagent.core :as r present
        requires' (if (some #(str/includes? % "reagent.core") requires)
                    requires
                    (conj (vec requires) "[reagent.core :as r]"))
        ;; require-macros (configure-scenes dropped — that was Portfolio metadata)
        rm (when (str/includes? ns-text ":require-macros")
             (-> (re-find #":require-macros\s+(\[[^\n]+\])" ns-text)
                 second
                 rename-text
                 (str/replace " configure-scenes" "")
                 (str/replace "mateuszmazurczak.portfolio.macros" "clojure-shadcn.stories.macros")))
        story-ns (str "clojure-shadcn.stories." (str/replace base #"_" "-") "-stories")
        ns' (build-ns-form story-ns
                           (str "Storybook stories for the " title " component. "
                                "Ported from mateuszmazurczak.portfolio.ui-components." base ".")
                           (sort requires')
                           rm)
        default-export (str "(def ^:export default\n"
                            "  #js {:title      \"" group "/" title "\"\n"
                            "       :parameters #js {:layout \"padded\"}})\n")
        scene-spans (filter (fn [[fs _]]
                              (str/starts-with? (subs content fs (min (+ fs 9) (count content))) "(defscene"))
                            forms)
        stories (keep #(transform-defscene content %) scene-spans)
        ;; Non-defscene top-level forms (defn/defn-/defc helpers, def data) are
        ;; carried through — scenes often reference them (e.g. slide-card in
        ;; carousel). Without this the port produced undeclared-var warnings.
        helper-forms (into [] (for [[fs fe] forms
                                    :when (not= [fs fe] ns-span)
                                    :when (not (str/starts-with? (subs content fs (min (+ fs 9) (count content))) "(defscene"))
                                    :when (not (str/starts-with? (subs content fs (min (+ fs 19) (count content))) "(configure-scenes"))]
                                (let [form (rename-text (subs content fs fe))]
                                  (if (str/starts-with? form "(defc")
                                    (str/replace-first form "(defc" "(defn")
                                    form))))
        out (str ns' "\n\n"
                 default-export "\n"
                 (when (seq helper-forms) (str (str/join "\n" helper-forms) "\n"))
                 (str/join "\n" stories))]
    (io/make-parents (io/file DEST "stories/cljs/clojure_shadcn/stories" (str (str/replace base #"-" "_") "_stories.cljs")))
    (spit (io/file DEST "stories/cljs/clojure_shadcn/stories" (str (str/replace base #"-" "_") "_stories.cljs"))
          out)
    (println "ported scene:" fname "->" base "_stories.cljs (" (count stories) "stories)"))))

;; components
(doseq [f (->> (file-seq (io/file SRC "src/cljs/mateuszmazurczak/ui/components"))
               (filter #(.isFile %))
               (filter #(str/ends-with? (.getName %) ".cljs")))
        :when (not (contains? excluded-components
                              (str/replace (.getName f) #"\.cljs$" "")))]
  (port-component! f))

;; hooks
(doseq [f (->> (file-seq (io/file SRC "src/cljs/mateuszmazurczak/ui/hooks"))
               (filter #(.isFile %)))]
  (port-hook! f))

;; scenes (skip code_block — doc infra, no scene port needed if exists; skip theme_toggle — manual refactor)
(doseq [f (->> (file-seq (io/file SRC "src/cljs/mateuszmazurczak/portfolio/ui_components"))
               (filter #(.isFile %))
               (filter #(str/ends-with? (.getName %) ".cljs"))
               (remove #(contains? #{"theme_toggle.cljs"} (.getName %))))]
  (port-scene! f))

(println "done.")
