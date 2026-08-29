(ns clojure-shadcn.stories.navigation-menu-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.navigation-menu :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Navigation Menu" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Responsive navigation with portaled viewport content." :npm-install "npm install @radix-ui/react-navigation-menu lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.navigation_menu") :namespace-path "src/cljs/clojure_shadcn/ui/components/navigation_menu.cljs" :filename "navigation_menu.cljs"}]))
(defstory NavigationMenuDemo []
  (r/as-element (helpers/wrap-component
    [sut/navigation-menu {}
     [sut/navigation-menu-list {}
      [sut/navigation-menu-item {}
       [sut/navigation-menu-trigger {} "Getting started"]
       [sut/navigation-menu-content {}
        [:ul {:class "grid w-[400px] gap-2 p-2"}
         [:li [sut/navigation-menu-link {:href "#"} [:div {:class "font-medium"} "Introduction"] [:p {:class "text-muted-foreground"} "Reusable data-oriented Reagent components."]]]
         [:li [sut/navigation-menu-link {:href "#"} [:div {:class "font-medium"} "Installation"] [:p {:class "text-muted-foreground"} "Add only the modules your application needs."]]]]]]
      [sut/navigation-menu-item {} [sut/navigation-menu-link {:href "#" :class (sut/navigation-menu-trigger-style)} "Documentation"]]
      [sut/navigation-menu-item {} [sut/navigation-menu-trigger {} "Components"] [sut/navigation-menu-content {} [:ul {:class "grid w-[500px] grid-cols-2 gap-2 p-2"} (for [label ["Button" "Card" "Dialog" "Menu"]] ^{:key label} [:li [sut/navigation-menu-link {:href "#"} label]])]]]]])))
(defstory NavigationMenuWithoutViewport []
  (r/as-element (helpers/wrap-component
    [sut/navigation-menu {:viewport false}
     [sut/navigation-menu-list {} [sut/navigation-menu-item {} [sut/navigation-menu-trigger {} "Direct panel"] [sut/navigation-menu-content {:class "w-64"} [sut/navigation-menu-link {:href "#"} "A non-viewport menu link"]]]]])))
