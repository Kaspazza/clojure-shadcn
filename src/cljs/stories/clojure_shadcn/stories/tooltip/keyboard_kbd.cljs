(ns clojure-shadcn.stories.tooltip.keyboard-kbd
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn- compound-tooltip
  [{:keys [trigger content root content-props trigger-props portal arrow-props]}]
  [tooltip/tooltip-root
   root
   [tooltip/tooltip-trigger (merge {:as-child true} trigger-props) trigger]
   [tooltip/tooltip-portal
    portal
    [tooltip/tooltip-content
     content-props
     content
     (when arrow-props [tooltip/tooltip-arrow arrow-props])]]])

(defn example
  []
  [tooltip/tooltip-provider {}
   [:div {:class "flex gap-4 p-6"}
    (for [[label shortcut] [["Save" "⌘ S"] ["Search" "⌘ K"]]]
      ^{:key label}
      [compound-tooltip
       {:trigger (button/button {:variant :outline
                                 :size :sm}
                                label)
        :content
        [:span {:class "flex items-center gap-2"}
         label
         [:kbd {:class "rounded border bg-muted px-1.5 font-mono text-[10px] text-muted-foreground"}
          shortcut]]}])]])
