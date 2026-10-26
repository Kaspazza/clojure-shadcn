(ns clojure-shadcn.stories.input-group.input-group-patterns
  (:require
   ["lucide-react"                           :refer [AtSign Search Send]]
   [clojure-shadcn.ui.components.input-group :as input-group]
   [clojure-shadcn.ui.components.kbd         :as kbd]
   [clojure-shadcn.ui.components.spinner     :as spinner]))

(defn example
  []
  [:div {:class "grid w-[36rem] max-w-full gap-5"}
   [input-group/input-group {:class "flex-wrap"}
    [input-group/input-group-addon {:align :block-start}
     [input-group/input-group-text {}
      "Repository URL"]]
    [input-group/input-group-addon {}
     [:> AtSign {:aria-hidden true}]]
    [input-group/input-group-input {:aria-label "Repository URL"
                                    :placeholder "owner/project"}]
    [input-group/input-group-addon {:align :inline-end}
     [kbd/kbd {}
      "⌘K"]
     [input-group/input-group-button {:size :icon-xs
                                      :aria-label "Submit repository"}
      [:> Send]]]]
   [input-group/input-group {}
    [input-group/input-group-addon {}
     [spinner/spinner {}]
     [input-group/input-group-text {}
      "Checking"]]
    [input-group/input-group-input {:aria-label "Package name"
                                    :default-value "clojure-shadcn"}]]
   [input-group/input-group {:class "flex-wrap"}
    [input-group/input-group-textarea {:aria-label "Message"
                                       :placeholder "Write a message…"}]
    [input-group/input-group-addon {:align :block-end}
     [input-group/input-group-text {}
      "Markdown supported"]
     [input-group/input-group-button {:class "ml-auto"}
      "Send"]]]
   [input-group/input-group {}
    [input-group/input-group-addon {}
     [input-group/input-group-text {}
      "Custom"]]
    [:input {:data-slot "input-group-control"
             :aria-label "Custom numeric control"
             :type "number"
             :class "h-9 min-w-0 flex-1 bg-transparent px-3 outline-none"}]]
   [:div {:dir "rtl"}
    [input-group/input-group {}
     [input-group/input-group-addon {}
      [:> Search {:aria-hidden true}]]
     [input-group/input-group-input {:aria-label "بحث"
                                     :placeholder "بحث…"}]
     [input-group/input-group-addon {:align :inline-end}
      [input-group/input-group-text {}
       "⌘K"]]]]])
