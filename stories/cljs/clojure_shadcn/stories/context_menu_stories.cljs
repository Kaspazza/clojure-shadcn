(ns clojure-shadcn.stories.context-menu-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.context-menu :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default #js {:title "Components/Context Menu" :parameters #js {:layout "padded"}})
(defn ^:export Installation []
  (r/as-element [helpers/installation-scene {:description "Right-click menu with items, submenus, checkbox, and radio choices." :npm-install "npm install @radix-ui/react-context-menu lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.context_menu") :namespace-path "src/cljs/clojure_shadcn/ui/components/context_menu.cljs" :filename "context_menu.cljs"}]))

(defstory ContextMenuDemo []
  (r/as-element (helpers/wrap-component
    [sut/context-menu {}
     [sut/context-menu-trigger {:class "flex h-40 w-72 items-center justify-center rounded-md border border-dashed text-sm"} "Right click here"]
     [sut/context-menu-content {:class "w-56"}
      [sut/context-menu-item {} "Back" [sut/context-menu-shortcut {} "⌘["]]
      [sut/context-menu-item {:disabled true} "Forward"]
      [sut/context-menu-sub {}
       [sut/context-menu-sub-trigger {} "Share"]
       [sut/context-menu-sub-content {:class "w-44"} [sut/context-menu-item {} "Email"] [sut/context-menu-item {} "Messages"]]]
      [sut/context-menu-separator {}]
      [sut/context-menu-item {:variant :destructive} "Delete"]]])))

(defstory ContextMenuChoices []
  (r/as-element [(fn [] (let [checked? (r/atom true) position (r/atom "bottom")]
    (fn [] (helpers/wrap-component
      [sut/context-menu {}
       [sut/context-menu-trigger {:class "flex h-32 w-72 items-center justify-center rounded-md border"} "Right click for preferences"]
       [sut/context-menu-content {:class "w-56"}
        [sut/context-menu-checkbox-item {:checked @checked? :on-checked-change #(reset! checked? %)} "Show toolbar"]
        [sut/context-menu-separator {}]
        [sut/context-menu-label {} "Panel position"]
        [sut/context-menu-radio-group {:value @position :on-value-change #(reset! position %)}
         [sut/context-menu-radio-item {:value "top"} "Top"]
         [sut/context-menu-radio-item {:value "bottom"} "Bottom"]]]]))))]))
