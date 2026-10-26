(ns clojure-shadcn.stories.button-group.button-group-patterns
  (:require
   ["lucide-react"                             :refer [Bold ChevronDown Italic Underline]]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.button-group  :as button-group]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown]
   [clojure-shadcn.ui.components.input         :as input]
   [clojure-shadcn.ui.components.input-group   :as input-group]
   [clojure-shadcn.ui.components.popover       :as popover]
   [clojure-shadcn.ui.components.select        :as select]))

(defn example
  []
  [:div {:class "grid gap-6"}
   [button-group/button-group {:aria-label "Text formatting toolbar"}
    [button/button {:variant :outline
                    :size :icon-sm
                    :aria-label "Bold"}
     [:> Bold]]
    [button/button {:variant :outline
                    :size :icon-sm
                    :aria-label "Italic"}
     [:> Italic]]
    [button/button {:variant :outline
                    :size :icon-sm
                    :aria-label "Underline"}
     [:> Underline]]]
   [:div {:class "flex flex-wrap items-center gap-4"}
    (for [size [:sm :default :lg]]
      ^{:key size}
      [button-group/button-group {}
       [button/button {:variant :outline
                       :size size}
        "Previous"]
       [button/button {:variant :outline
                       :size size}
        "Next"]])]
   [button-group/button-group {}
    [button/button {}
     "Save"]
    [dropdown/dropdown-menu {}
     [dropdown/dropdown-menu-trigger {:as-child true}
      [button/button {:size :icon
                      :aria-label "More save options"}
       [:> ChevronDown]]]
     [dropdown/dropdown-menu-content {:align "end"}
      [dropdown/dropdown-menu-item {}
       "Save as draft"]
      [dropdown/dropdown-menu-item {}
       "Save a copy"]]]]
   [button-group/button-group {:class "w-full"
                               :aria-label "Website address"}
    [button-group/button-group-text {}
     "https://"]
    [input/input {:aria-label "Domain"
                  :placeholder "example.com"}]]
   [button-group/button-group {}
    [input-group/input-group {:class "w-72"}
     [input-group/input-group-addon {}
      "@"]
     [input-group/input-group-input {:aria-label "Username"}]]
    [button/button {:variant :outline}
     "Invite"]]
   [button-group/button-group {}
    [select/select {:default-value "viewer"}
     [select/select-trigger {:aria-label "Role"}
      [select/select-value {}]]
     [select/select-content {}
      [select/select-item {:value "viewer"}
       "Viewer"]
      [select/select-item {:value "editor"}
       "Editor"]]]
    [popover/popover {}
     [popover/popover-trigger {:as-child true}
      [button/button {:variant :outline}
       "Details"]]
     [popover/popover-content {:class "text-sm"}
      "Permissions apply immediately."]]]
   [button-group/button-group {:aria-label "Nested pagination"}
    [button/button {:variant :outline}
     "First"]
    [button-group/button-group {}
     [button/button {:variant :outline}
      "2"]
     [button/button {:variant :outline}
      "3"]]
    [button/button {:variant :outline}
     "Last"]]
   [:div {:dir "rtl"}
    [button-group/button-group {:aria-label "التنقل"}
     [button/button {:variant :outline}
      "السابق"]
     [button/button {:variant :outline}
      "التالي"]]]
   [:p {:class "text-xs text-muted-foreground"}
    "RTL uses semantic content order only; the current component still joins horizontal edges with physical left/right classes."]])
