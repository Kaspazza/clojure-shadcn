(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-checkboxes
  (:require
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [reagent.core                               :as r]))

(defn example
  []
  [(fn []
     (let [show-status? (r/atom true)
           show-activity? (r/atom false)
           show-panel? (r/atom false)]
       (fn [] [:div {:class "p-6"}
               [dropdown-menu/dropdown-menu {}
                [dropdown-menu/dropdown-menu-trigger {:as-child true}
                 (button/button {:variant :outline} "Appearance")]
                [dropdown-menu/dropdown-menu-content {:class "w-56"}
                 [dropdown-menu/dropdown-menu-label {}
                  "Appearance"]
                 [dropdown-menu/dropdown-menu-separator {}]
                 [dropdown-menu/dropdown-menu-checkbox-item {:checked @show-status?
                                                             :on-checked-change
                                                             (fn* [%1] (reset! show-status? %1))}
                  "Status Bar"]
                 [dropdown-menu/dropdown-menu-checkbox-item {:checked @show-activity?
                                                             :disabled true
                                                             :on-checked-change
                                                             (fn* [%1] (reset! show-activity? %1))}
                  "Activity Bar"]
                 [dropdown-menu/dropdown-menu-checkbox-item {:checked @show-panel?
                                                             :on-checked-change
                                                             (fn* [%1] (reset! show-panel? %1))}
                  "Panel"]]]])))])
