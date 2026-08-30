(ns clojure-shadcn.stories.macros
  "Compile-time macros for Storybook documentation.

  Ported from mateuszmazurczak.portfolio.macros."
  (:require
   [clojure.pprint :as pprint]
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

(defn- call-named?
  [form function-name]
  (and (seq? form)
       (symbol? (first form))
       (= function-name (name (first form)))))

(defn- example-forms
  "Removes only the outer Storybook render boundary from a story form.

  A form is unwrapped solely when its exact outer shape is
  `(r/as-element (helpers/wrap-component ...))`. Calls with either name deeper
  in an example are consumer code and therefore remain in the displayed source."
  [form]
  (let [wrapped-form (second form)]
    (if (and (call-named? form "as-element")
             (call-named? wrapped-form "wrap-component"))
      (let [args (next wrapped-form)]
        (if (map? (first args))
          (rest args)
          args))
      [form])))

(defn- story-source
  [body]
  (->> (if (= 1 (count body))
         (example-forms (first body))
         body)
       (map #(with-out-str (pprint/pprint %)))
       (apply str)
       str/trim))

(defmacro defdoc
  "Defines an exported, zero-argument Storybook documentation story without
  embedding its implementation as display source.

  Use this for library documentation pages. Component examples should use
  `defstory`, which publishes consumer-ready source to Storybook Docs."
  [story-name & declaration]
  (let [[docstring declaration] (if (string? (first declaration))
                                  [(first declaration) (next declaration)]
                                  [nil declaration])
        [params & body]         declaration]
    (when-not (symbol? story-name)
      (throw (ex-info "defdoc expects a symbol name"
                      {:story-name story-name})))
    (when-not (= [] params)
      (throw (ex-info "defdoc expects an empty argument vector"
                      {:story-name story-name
                       :params params})))
    (when-not (seq body)
      (throw (ex-info "defdoc expects at least one body form"
                      {:story-name story-name})))
    (let [exported-name (with-meta story-name
                          (assoc (meta story-name) :export true))]
      `(do
         (defn ~exported-name
           ~@(when docstring [docstring])
           []
           ~@body)
         (set! (.-parameters ~story-name)
               (~'js-obj "docs" (~'js-obj "codePanel" false
                                            "canvas" (~'js-obj "sourceState" "none"))))))))

(defmacro defstory
  "Defines an exported, zero-argument Storybook story with consumer-ready
  display source.

  The declaration has the same shape as `defn`: an optional docstring, an
  empty argument vector, and one or more body forms. When a story has the
  standard `r/as-element` / `helpers/wrap-component` render boundary,
  Storybook Docs displays and copies only the wrapped component usage.

  Example:
  (defstory ButtonDemo
    \"Primary button example.\"
    []
    (r/as-element
     (helpers/wrap-component
       [button/button {} \"Click me\"])))"
  [story-name & declaration]
  (let [[docstring declaration] (if (string? (first declaration))
                                  [(first declaration) (next declaration)]
                                  [nil declaration])
        [params & body]         declaration]
    (when-not (symbol? story-name)
      (throw (ex-info "defstory expects a symbol name"
                      {:story-name story-name})))
    (when-not (= [] params)
      (throw (ex-info "defstory expects an empty argument vector"
                      {:story-name story-name
                       :params params})))
    (when-not (seq body)
      (throw (ex-info "defstory expects at least one body form"
                      {:story-name story-name})))
    (let [source         (story-source body)
          api-reference? (= 'ApiReference story-name)
          exported-name  (with-meta story-name
                           (assoc (meta story-name) :export true))]
      `(do
         (defn ~exported-name
           ~@(when docstring [docstring])
           []
           ~@body)
         (set! (.-parameters ~story-name)
               ~(if api-reference?
                  `(~'js-obj "docs" (~'js-obj "codePanel" false
                                             "canvas" (~'js-obj "sourceState" "none")))
                  `(~'js-obj "docs" (~'js-obj "codePanel" true
                                             "source" (~'js-obj "code" ~source
                                                                "language" "clojure")))))))))
