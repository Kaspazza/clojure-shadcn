(ns clojure-shadcn.stories.toggle-group.toggle-group-patterns
  (:require
   ["lucide-react"                            :refer [AlignCenter AlignLeft AlignRight Bold Italic]]
   [clojure-shadcn.ui.components.toggle-group :as toggle-group]))

(defn example
  []
  [:div {:class "grid gap-6 p-6"}
   [toggle-group/toggle-group {:type "multiple"
                               :variant :outline
                               :disabled true
                               :default-value ["bold"]}
    [toggle-group/toggle-group-item {:value "bold"}
     [:> Bold]]
    [toggle-group/toggle-group-item {:value "italic"}
     [:> Italic]]]
   [toggle-group/toggle-group
    {:type "single"
     :default-value "serif"
     :variant :outline
     :class
     "gap-2 [&_[data-slot=toggle-group-item]]:rounded-md [&_[data-slot=toggle-group-item]]:border-l"}
    [toggle-group/toggle-group-item {:value "sans"
                                     :class "font-sans"}
     "Sans"]
    [toggle-group/toggle-group-item {:value "serif"
                                     :class "font-serif"}
     "Serif"]
    [toggle-group/toggle-group-item {:value "mono"
                                     :class "font-mono"}
     "Mono"]]
   [toggle-group/toggle-group
    {:type "single"
     :orientation "vertical"
     :default-value "center"
     :class "flex-col items-stretch [&_[data-slot=toggle-group-item]]:rounded-md"}
    [toggle-group/toggle-group-item {:value "left"}
     [:> AlignLeft]
     "Left"]
    [toggle-group/toggle-group-item {:value "center"}
     [:> AlignCenter]
     "Center"]
    [toggle-group/toggle-group-item {:value "right"}
     [:> AlignRight]
     "Right"]]
   [:div {:dir "rtl"}
    [toggle-group/toggle-group {:type "single"
                                :dir "rtl"
                                :default-value "right"
                                :variant :outline
                                :aria-label "محاذاة النص"}
     [toggle-group/toggle-group-item {:value "right"
                                      :aria-label "محاذاة لليمين"}
      [:> AlignRight]]
     [toggle-group/toggle-group-item {:value "center"
                                      :aria-label "توسيط"}
      [:> AlignCenter]]
     [toggle-group/toggle-group-item {:value "left"
                                      :aria-label "محاذاة لليسار"}
      [:> AlignLeft]]]]])
