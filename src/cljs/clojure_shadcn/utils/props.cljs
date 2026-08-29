(ns clojure-shadcn.utils.props
  "Props normalization utilities.

  Component props in this library accept both kebab-case (`:on-checked-change`,
  `:as-child`) and camelCase (`:onCheckedChange`, `:asChild`) keyword keys.
  `normalize-props` canonicalizes every camelCase keyword key to its kebab-case
  form so that a single destructuring/lookup works for either dialect.

  Non-keyword keys and string keys are left untouched."
  (:require
   [clojure.string :as str]))

(defn camel-case-key?
  "True for keyword keys whose name contains an uppercase letter,
  e.g. :onCheckedChange, :asChild, :showCloseButton."
  [k]
  (and (keyword? k)
       (boolean (re-find #"[A-Z]" (name k)))))

(defn camel->kebab-key
  "Converts a camelCase keyword into a kebab-case keyword.
  :onCheckedChange -> :on-checked-change
  :asChild -> :as-child"
  [k]
  (let [s (-> (name k)
              (str/replace #"[A-Z]" #(str "-" (str/lower-case %)))
              (str/replace #"^-" ""))]
    (if-let [ns (namespace k)]
      (keyword ns s)
      (keyword s))))

(defn normalize-props
  "Returns a copy of `props` where every camelCase keyword key is moved to its
  kebab-case equivalent. Destructuring kebab names afterwards then works for
  both dialects.

  If the same map contains both spellings, the explicit kebab-case entry wins
  and the camelCase duplicate is dropped (deterministic conflict resolution)."
  [props]
  (reduce-kv
   (fn [m k v]
     (if (camel-case-key? k)
       (let [kn (camel->kebab-key k)]
         (if (contains? props kn)
           m
           (assoc m kn v)))
       (assoc m k v)))
   {}
   props))
