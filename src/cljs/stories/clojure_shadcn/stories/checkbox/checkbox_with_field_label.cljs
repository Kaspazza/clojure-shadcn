(ns clojure-shadcn.stories.checkbox.checkbox-with-field-label
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]))

(defn example
  []
  [:div {:class "p-6"}
   [field/field-label {}
    [field/field {:orientation :horizontal}
     [checkbox/checkbox {:id "toggle-checkbox-2"
                         :name "toggle-checkbox-2"}]
     [field/field-content {}
      [field/field-title {}
       "Enable notifications"]
      [field/field-description {}
       "You can enable or disable notifications at any time."]]]]])
