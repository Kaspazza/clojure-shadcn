(ns clojure-shadcn.ui.components.combobox
  "General-purpose controlled single-select combobox.
  Items are data maps (`:value`, `:label`, optional `:keywords` and `:disabled?`), not child components."
  (:require
   ["lucide-react"                       :refer [Check ChevronsUpDown X]]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.command :as command]
   [clojure-shadcn.ui.components.popover :as popover]
   [clojure-shadcn.utils.styles          :refer [merge-classes]]
   [reagent.core                         :as r]))

(defn- item-label [item] (or (:label item) (str (:value item))))

(defn combobox
  [{:keys [items
           value
           on-value-change
           placeholder
           search-placeholder
           empty-text
           class
           content-class
           disabled?
           clearable?
           clear-label
           item-render
           value-render]
    :or {placeholder "Select an option…"
         search-placeholder "Search…"
         empty-text "No option found."
         clear-label "Clear selection"}}]
  (r/with-let
   [open? (r/atom false)]
   (let [selected (some #(when (= value (:value %)) %) items)
         choose!
         (fn [next-value] (when on-value-change (on-value-change next-value)) (reset! open? false))
         rendered-items (for [{item-value :value
                               :as item}
                              items]
                          ^{:key (str item-value)}
                          [command/command-item {:value (item-label item)
                                                 :keywords (clj->js (:keywords item))
                                                 :disabled (:disabled? item)
                                                 :on-select #(choose! item-value)}
                           [:>
                            Check
                            {:className (merge-classes
                                         "size-4"
                                         (if (= value item-value) "opacity-100" "opacity-0"))
                             :aria-hidden true}]
                           (if item-render (item-render item) (item-label item))])]
     [popover/popover {:open @open?
                       :on-open-change #(reset! open? %)}
      [:div {:class "relative inline-flex"}
       [popover/popover-trigger {:as-child true}
        [button/button {:variant :outline
                        :role "combobox"
                        :aria-expanded @open?
                        :disabled disabled?
                        :class (merge-classes
                                "w-[240px] justify-between font-normal"
                                (when (and clearable? selected) "pr-14")
                                class)}
         [:span {:class "truncate"}
          (if selected (if value-render (value-render selected) (item-label selected)) placeholder)]
         [:>
          ChevronsUpDown
          {:className "size-4 opacity-50"
           :aria-hidden true}]]]
       (when (and clearable? selected)
         [button/button {:type "button"
                         :variant :ghost
                         :size :icon-xs
                         :disabled disabled?
                         :aria-label clear-label
                         :class "absolute right-7 top-1/2 -translate-y-1/2"
                         :on-click (fn [event]
                                     (.stopPropagation event)
                                     (choose! nil))}
          [:> X {:className "size-3.5" :aria-hidden true}]])]
      [popover/popover-content {:align "start"
                                :class (merge-classes "w-[240px] p-0" content-class)}
       [command/command {}
        [command/command-input {:placeholder search-placeholder}]
        [command/command-list {}
         [command/command-empty {}
          empty-text]
         (into [command/command-group {}]
               rendered-items)]]]])))
