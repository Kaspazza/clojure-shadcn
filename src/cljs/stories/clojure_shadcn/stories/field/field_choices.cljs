(ns clojure-shadcn.stories.field.field-choices
  (:require
   [clojure-shadcn.ui.components.field       :as field]
   [clojure-shadcn.ui.components.radio-group :as radio]
   [clojure-shadcn.ui.components.switch      :as switch]))

(defn example
  []
  [:div {:class "grid max-w-xl gap-8 p-6"}
   [field/field-set {}
    [field/field-legend {:variant :label}
     "Workspace visibility"]
    [field/field-description {}
     "Choose who can discover this workspace."]
    [radio/radio-group {:default-value "team"}
     [field/field {:orientation :horizontal}
      [radio/radio-group-item {:id "visibility-team"
                               :value "team"}]
      [field/field-label {:html-for "visibility-team"
                          :class "font-normal"}
       "Team members"]]
     [field/field {:orientation :horizontal}
      [radio/radio-group-item {:id "visibility-public"
                               :value "public"}]
      [field/field-label {:html-for "visibility-public"
                          :class "font-normal"}
       "Anyone with the link"]]]]
   [field/field {:orientation :horizontal
                 :class "rounded-lg border p-4"}
    [field/field-content {}
     [field/field-label {:html-for "field-switch"}
      "Automatic updates"]
     [field/field-description {}
      "Install stable updates when the app is idle."]]
    [switch/switch {:id "field-switch"
                    :default-checked true}]]
   [radio/radio-group {:default-value "starter"
                       :class "grid sm:grid-cols-2"}
    [field/field-label {:class "cursor-pointer"}
     [field/field {:orientation :horizontal
                   :class "rounded-lg border p-4 has-[[data-state=checked]]:border-primary"}
      [radio/radio-group-item {:value "starter"}]
      [field/field-content {}
       [field/field-title {}
        "Starter"]
       [field/field-description {}
        "For personal projects and prototypes."]]]]
    [field/field-label {:class "cursor-pointer"}
     [field/field {:orientation :horizontal
                   :class "rounded-lg border p-4 has-[[data-state=checked]]:border-primary"}
      [radio/radio-group-item {:value "team"}]
      [field/field-content {}
       [field/field-title {}
        "Team"]
       [field/field-description {}
        "Shared controls for growing teams."]]]]]])
