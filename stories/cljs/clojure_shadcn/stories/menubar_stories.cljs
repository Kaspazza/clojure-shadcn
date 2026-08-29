(ns clojure-shadcn.stories.menubar-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.menubar :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Menubar" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Accessible application menubar with nested and selectable items." :npm-install "npm install @radix-ui/react-menubar lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.menubar") :namespace-path "src/cljs/clojure_shadcn/ui/components/menubar.cljs" :filename "menubar.cljs"}]))
(defstory MenubarDemo []
  (r/as-element (helpers/wrap-component
    [sut/menubar {}
     [sut/menubar-menu {}
      [sut/menubar-trigger {} "File"]
      [sut/menubar-content {}
       [sut/menubar-item {} "New tab" [sut/menubar-shortcut {} "⌘T"]]
       [sut/menubar-item {} "New window" [sut/menubar-shortcut {} "⌘N"]]
       [sut/menubar-separator {}]
       [sut/menubar-sub {} [sut/menubar-sub-trigger {} "Share"] [sut/menubar-sub-content {} [sut/menubar-item {} "Email"] [sut/menubar-item {} "Messages"]]]
       [sut/menubar-item {:variant :destructive} "Close"]]]
     [sut/menubar-menu {}
      [sut/menubar-trigger {} "Edit"]
      [sut/menubar-content {} [sut/menubar-item {} "Undo" [sut/menubar-shortcut {} "⌘Z"]] [sut/menubar-item {:disabled true} "Redo"]]]])))
(defstory MenubarSelections []
  (r/as-element [(fn [] (let [bookmarks? (r/atom true) profile (r/atom "benoit")]
    (fn [] (helpers/wrap-component
      [sut/menubar {} [sut/menubar-menu {} [sut/menubar-trigger {} "View"]
       [sut/menubar-content {} [sut/menubar-checkbox-item {:checked @bookmarks? :on-checked-change #(reset! bookmarks? %)} "Bookmarks bar"]]]
       [sut/menubar-menu {} [sut/menubar-trigger {} "Profiles"] [sut/menubar-content {}
        [sut/menubar-radio-group {:value @profile :on-value-change #(reset! profile %)} [sut/menubar-radio-item {:value "andy"} "Andy"] [sut/menubar-radio-item {:value "benoit"} "Benoit"]]]]]))))]))
