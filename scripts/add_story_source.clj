#!/usr/bin/env bb
;; add_story_source.clj — annotate each Storybook demo story with
;; {:source (embed-body <Name>) :filename "<file>_stories.cljs"} so the
;; rendered demo shows its own source via helpers/wrap-component.
;;
;; Edits per file:
;;   1. Ensure (:require-macros [clojure-shadcn.stories.macros
;;                                 :refer [embed-source embed-body]])
;;   2. In each (defn ^:export Name ... (helpers/wrap-component ...)),
;;      insert the opts map as the first argument.
;;
;; Idempotent: skips defns already containing "(embed-body".
;; Paren/string/comment-aware.
;;
;; Usage: bb scripts/add_story_source.clj

(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(def STORIES-DIR "stories/cljs/clojure_shadcn/stories")

;; ── scanner utilities (string/comment aware, same style as macros.clj) ────

(defn- skip-ws
  [s idx]
  (loop [idx idx]
    (if (and (< idx (count s)) (Character/isWhitespace ^char (nth s idx)))
      (recur (inc idx))
      idx)))

(defn- token-end
  "Index one past the end of a token: stops at whitespace or a delimiter."
  [s idx]
  (or (some (fn [i]
              (let [c (nth s i)]
                (when (or (Character/isWhitespace ^char c)
                          (#{\( \) \[ \] \{ \} \"} c))
                  i)))
            (iterate inc idx))
      (count s)))

(defn- form-end
  "Index one past the closing paren of the form opening at `start`.
   String- and comment-aware."
  [s start]
  (loop [i (inc start) depth 1 in-str false escaped false in-comment false]
    (if (>= i (count s))
      i
      (let [c (nth s i)]
        (cond
          in-comment
          (recur (inc i) depth in-str false (not= c \newline))

          in-str
          (cond
            escaped   (recur (inc i) depth true  false false)
            (= c \\)  (recur (inc i) depth true  true  false)
            (= c \")  (recur (inc i) depth false false false)
            :else     (recur (inc i) depth true  false false))

          (= c \;) (recur (inc i) depth false false true)
          (= c \") (recur (inc i) depth true  false false)

          (= c \() (recur (inc i) (inc depth) in-str escaped in-comment)
          (= c \)) (if (= 1 depth)
                     (inc i)
                     (recur (inc i) (dec depth) in-str escaped in-comment))
          :else    (recur (inc i) depth in-str escaped in-comment))))))

(defn- find-top-level-forms
  "Vector of [start end] spans for every top-level (paren) form in s."
  [s]
  (loop [i 0 spans [] in-str false escaped false in-comment false]
    (if (>= i (count s))
      spans
      (let [c (nth s i)]
        (cond
          in-comment (recur (inc i) spans in-str false (not= c \newline))
          in-str
          (cond
            escaped   (recur (inc i) spans true  false false)
            (= c \\)  (recur (inc i) spans true  true  false)
            (= c \")  (recur (inc i) spans false false false)
            :else     (recur (inc i) spans true  false false))

          (= c \;) (recur (inc i) spans false false true)
          (= c \") (recur (inc i) spans true  false false)

          (= c \() (let [e (form-end s i)]
                     (recur e (conj spans [i e]) false false false))
          :else    (recur (inc i) spans in-str escaped in-comment))))))

(defn- find-ns-form
  [s]
  (some (fn [[st e]] (when (str/starts-with? (subs s st e) "(ns")
                        [st e]))
        (find-top-level-forms s)))

;; ── ensure require-macros ─────────────────────────────────────────────────

(defn- ensure-require-macros
  "For ns's that already have :require-macros with embed-source referred, this
   ns needs only embed-body; otherwise insert a fresh clause before the ns
   form's closing paren."
  [s]
  (if (str/includes? s "embed-body")
    s
    (let [[st e]   (or (find-ns-form s)
                       (throw (ex-info "ns form not found" {:src s})))
          ns-form  (subs s st e)
          clause   "(:require-macros [clojure-shadcn.stories.macros :refer [embed-body]])"
          _        (when (not= (nth ns-form (dec (count ns-form))) \))
                     (throw (ex-info "find-ns-form mis-scanned" {:ns-form ns-form})))
          ns-form' (str (subs ns-form 0 (dec (count ns-form)))
                        "\n  " clause ")")]
      (str (subs s 0 st) ns-form' (subs s e)))))

;; ── annotate defns ────────────────────────────────────────────────────────

(def WC-CALL "(helpers/wrap-component")

(defn- story-defns
  "[start end name filename] for each (defn ^:export Name ...) containing
   a (helpers/wrap-component call, excluding ones already annotated."
  [s fname]
  (->> (find-top-level-forms s)
       (keep (fn [[st e]]
               (let [form (subs s st e)]
                 (when (and (str/starts-with? form "(defn ^:export ")
                            (str/includes? form WC-CALL)
                            ;; skip if already annotated
                            (not (str/includes? form "(embed-body")))
                   [st e
                    (subs form
                          (count "(defn ^:export ")
                          (token-end form (count "(defn ^:export ")))
                    fname]))))))

(defn- annotate
  [s fname]
  ;; loop until no unannotated story defn remains; recon computed per pass so
  ;; offsets stay valid
  (loop [s s]
    (if-let [[st e name fname'] (first (story-defns s fname))]
      (let [defn-text (subs s st e)
            wc-idx    (str/index-of defn-text WC-CALL)
            insert    (str " {:source (embed-body " name ") :filename \"" fname' "\"}")
            pos       (+ st wc-idx (count WC-CALL))]
        (recur (str (subs s 0 pos) insert (subs s pos))))
      s)))

(defn- rewrite-story-file
  [path]
  (let [f     (io/file path)
        fname (str (.getName f))
        orig  (slurp f)
        s     (-> orig
                  ensure-require-macros
                  (annotate fname))]
    (when (not= s orig)
      (spit f s)
      (println "updated:" path))))

;; ── main ──────────────────────────────────────────────────────────────────

(let [files (->> (.listFiles (io/file STORIES-DIR))
                 (filter #(.isFile %))
                 (filter #(str/ends-with? (.getName %) "_stories.cljs"))
                 (sort-by #(.getName %)))]
  (doseq [f files]
    (rewrite-story-file (.getPath f)))
  (println "done."))
