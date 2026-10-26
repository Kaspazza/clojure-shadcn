(ns clojure-shadcn.stories.combobox.backwards-compatible-simple-api
  (:require
   [clojure-shadcn.ui.components.combobox :as combobox]
   [reagent.core                          :as r]))

(defn example
  []
  [(fn []
     (let [runtimes [{:value :clj
                      :label "Clojure"
                      :keywords ["jvm"]}
                     {:value :cljs
                      :label "ClojureScript"
                      :keywords ["browser"]}
                     {:value :bb
                      :label "Babashka"
                      :keywords ["cli"]}]]
       (r/with-let [value (r/atom nil)]
                   [combobox/simple-combobox {:items runtimes
                                              :value @value
                                              :on-value-change (fn* [%1] (reset! value %1))
                                              :placeholder "Select runtime…"
                                              :search-placeholder "Search runtimes…"
                                              :empty-text "No runtime found."
                                              :clearable? true}])))])
