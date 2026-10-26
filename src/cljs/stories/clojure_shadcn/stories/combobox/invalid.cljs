(ns clojure-shadcn.stories.combobox.invalid
  (:require
   [clojure-shadcn.ui.components.combobox :as combobox]
   [reagent.core                          :as r]))

(defn example
  []
  [(fn [{:keys [disabled? invalid? default-open rtl?]}]
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
       (r/with-let
        [value (r/atom nil)]
        [:div {:dir (when rtl? "rtl")
               :class "grid gap-2"}
         [:label {:class "text-sm font-medium"}
          "Runtime"]
         [combobox/combobox-root {:value @value
                                  :on-value-change (fn* [%1] (reset! value %1))
                                  :default-open default-open
                                  :disabled? disabled?}
          [:div {:class "flex items-center gap-1"}
           [combobox/combobox-trigger {:aria-invalid invalid?}
            [combobox/combobox-value {:placeholder "Select runtime…"
                                      :render runtime-label}]
            [:span {:aria-hidden true}
             "⌄"]]
           (when @value
             [combobox/combobox-clear {}])]
          [combobox/combobox-content {:align (if rtl? "end" "start")}
           [combobox/combobox-collection {}
            [combobox/combobox-input {:placeholder "Search runtimes…"
                                      :aria-label "Search runtimes"}]
            [combobox/combobox-list {}
             [combobox/combobox-empty {}
              "No runtime found."]
             [combobox/combobox-group {:heading "Hosted"}
              [combobox/combobox-item {:value :clj
                                       :text-value "Clojure"
                                       :keywords #js ["jvm"]}
               "Clojure"]
              [combobox/combobox-item {:value :cljs
                                       :text-value "ClojureScript"}
               "ClojureScript"]]
             [combobox/combobox-separator {}]
             [combobox/combobox-group {}
              [combobox/combobox-label {}
               "Tools"]
              [combobox/combobox-item {:value :bb
                                       :text-value "Babashka"}
               "Babashka"]
              [combobox/combobox-item {:value :disabled
                                       :text-value "Unavailable"
                                       :disabled true}
               "Unavailable"]]]]]]
         (when invalid?
           [:p {:class "text-sm text-destructive"}
            "Choose a supported runtime."])])))
   {:invalid? true}])
