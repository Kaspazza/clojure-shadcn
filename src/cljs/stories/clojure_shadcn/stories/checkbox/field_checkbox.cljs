(ns clojure-shadcn.stories.checkbox.field-checkbox
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [field/field-group {}
    [field/field-set {}
     [field/field-legend {:variant :label}
      "Show these items on the desktop"]
     [field/field-description {}
      "Select the items you want to show on the desktop."]
     [field/field-group {:class "gap-3"}
      [field/field {:orientation :horizontal}
       [checkbox/checkbox {:id "hard-disks"}]
       [field/field-label {:html-for "hard-disks"
                           :class "font-normal"}
        "Hard disks"]]
      [field/field {:orientation :horizontal}
       [checkbox/checkbox {:id "external-disks"}]
       [field/field-label {:html-for "external-disks"
                           :class "font-normal"}
        "External disks"]]
      [field/field {:orientation :horizontal}
       [checkbox/checkbox {:id "cds"}]
       [field/field-label {:html-for "cds"
                           :class "font-normal"}
        "CDs, DVDs, and iPods"]]
      [field/field {:orientation :horizontal}
       [checkbox/checkbox {:id "servers"}]
       [field/field-label {:html-for "servers"
                           :class "font-normal"}
        "Connected servers"]]]]
    [field/field-separator {}]
    [field/field {:orientation :horizontal}
     [checkbox/checkbox {:id "icloud"
                         :default-checked true}]
     [field/field-content {}
      [field/field-label {:html-for "icloud"}
       "Sync Desktop & Documents folders"]
      [field/field-description {}
       "Your Desktop & Documents folders are being synced with iCloud Drive."]]]]])
