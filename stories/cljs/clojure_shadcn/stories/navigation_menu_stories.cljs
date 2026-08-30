(ns clojure-shadcn.stories.navigation-menu-stories
  (:require
   [clojure-shadcn.stories.helpers               :as helpers]
   [clojure-shadcn.ui.components.navigation-menu :as sut]
   [reagent.core                                 :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Navigation Menu"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Responsive navigation with portaled viewport content."
                        :npm-install "npm install @radix-ui/react-navigation-menu lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.navigation_menu")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/navigation_menu.cljs"
                        :filename "navigation_menu.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "navigation-menu"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/navigation-menu"
             :label "Radix Navigation Menu Docs"}
      :description
      "Radix Root wrapper that manages focus, keyboard navigation, and content state. By default it appends one navigation-menu-viewport after supplied children."
      :props [{:name ":viewport"
               :type "boolean"
               :default "true"
               :description
               "When false, content is positioned directly and no automatic viewport is appended."}
              {:name ":class / Root props"
               :type "string / map entries"
               :default nil
               :description
               "Classes are merged and all remaining normalized Radix Root props forwarded."}]}]
    [helpers/api-component-card
     {:component-name "navigation-menu-list / navigation-menu-item"
      :description
      "Radix List and Item structural wrappers. List must be inside Root; triggers, links, and content belong within Items."
      :props [{:name ":class / primitive props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to the corresponding Radix primitive."}]}]
    [helpers/api-component-card
     {:component-name "navigation-menu-trigger / navigation-menu-content / navigation-menu-link"
      :description
      "Accessible trigger with automatic decorative chevron, associated content panel, and navigation link. Radix owns trigger/content ARIA and keyboard behavior."
      :props
      [{:name ":class / primitive props"
        :type "string / map entries"
        :default nil
        :description
        "Merged and forwarded. Link accepts Radix Link props such as :active and :as-child plus anchor props."}]}]
    [helpers/api-component-card
     {:component-name "navigation-menu-viewport / navigation-menu-indicator"
      :description
      "Animated content viewport and optional active-item indicator. Root creates the viewport automatically unless :viewport is false; avoid adding a second one."
      :props [{:name ":class / primitive props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to Viewport or Indicator."}]}]
    [helpers/api-component-card
     {:component-name "navigation-menu-trigger-style"
      :description "Public style helper for links that should visually match triggers."
      :props [{:name "classes"
               :type "variadic class values"
               :default nil
               :description
               "Merged after the default trigger classes; returns a class string."}]}]])))

(defstory NavigationMenuDemo
          []
          (r/as-element
           (helpers/wrap-component
            [sut/navigation-menu {}
             [sut/navigation-menu-list {}
              [sut/navigation-menu-item {}
               [sut/navigation-menu-trigger {}
                "Getting started"]
               [sut/navigation-menu-content {}
                [:ul {:class "grid w-[400px] gap-2 p-2"}
                 [:li
                  [sut/navigation-menu-link {:href "#"}
                   [:div {:class "font-medium"}
                    "Introduction"]
                   [:p {:class "text-muted-foreground"}
                    "Reusable data-oriented Reagent components."]]]
                 [:li
                  [sut/navigation-menu-link {:href "#"}
                   [:div {:class "font-medium"}
                    "Installation"]
                   [:p {:class "text-muted-foreground"}
                    "Add only the modules your application needs."]]]]]]
              [sut/navigation-menu-item {}
               [sut/navigation-menu-link {:href "#"
                                          :class (sut/navigation-menu-trigger-style)}
                "Documentation"]]
              [sut/navigation-menu-item {}
               [sut/navigation-menu-trigger {}
                "Components"]
               [sut/navigation-menu-content {}
                [:ul {:class "grid w-[500px] grid-cols-2 gap-2 p-2"}
                 (for [label ["Button" "Card" "Dialog" "Menu"]]
                   ^{:key label}
                   [:li
                    [sut/navigation-menu-link {:href "#"}
                     label]])]]]]])))

(defstory NavigationMenuWithoutViewport
          []
          (r/as-element (helpers/wrap-component [sut/navigation-menu {:viewport false}
                                                 [sut/navigation-menu-list {}
                                                  [sut/navigation-menu-item {}
                                                   [sut/navigation-menu-trigger {}
                                                    "Direct panel"]
                                                   [sut/navigation-menu-content {:class "w-64"}
                                                    [sut/navigation-menu-link {:href "#"}
                                                     "A non-viewport menu link"]]]]])))
