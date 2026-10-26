(ns clojure-shadcn.stories.combobox.multiple-with-chips-and-clear
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
                      :keywords ["cli"]}]
           runtime-label (fn [value]
                           (or (:label (some (fn* [%1] (when (= value (:value %1)) %1)) runtimes))
                               "None"))]
       (r/with-let [values (r/atom [:clj])]
                   [:div {:class "grid w-[320px] gap-2"}
                    [:div {:class "flex min-h-9 flex-wrap gap-1 rounded-md border p-1"}
                     (for [value @values]
                       ^{:key (name value)}
                       [combobox/combobox-chip
                        {:on-remove (fn* [] (swap! values (fn [xs] (vec (remove #{value} xs)))))
                         :remove-label (str "Remove " (runtime-label value))}
                        (runtime-label value)])]
                    [combobox/combobox-root {:multiple? true
                                             :value @values
                                             :on-value-change (fn* [%1] (reset! values %1))}
                     [combobox/combobox-trigger {:class "w-full"}
                      [combobox/combobox-value {:placeholder "Select runtimes…"
                                                :render (fn* [%1] (str (count %1) " selected"))}]
                      [:span {:aria-hidden true}
                       "⌄"]]
                     [combobox/combobox-content {:class "w-[320px]"}
                      [combobox/combobox-collection {}
                       [combobox/combobox-input {:placeholder "Search runtimes…"}]
                       [combobox/combobox-list {}
                        (into [combobox/combobox-group {}]
                              (for [{:keys [value label]} runtimes]
                                ^{:key label}
                                [combobox/combobox-item {:value value
                                                         :text-value label}
                                 label]))]]]
                     [combobox/combobox-clear {:label "Clear all runtimes"}]]])))])
