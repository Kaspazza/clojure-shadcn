(ns clojure-shadcn.stories.navigation-menu-stories
  (:require
   [clojure-shadcn.stories.helpers                                          :as helpers]
   [clojure-shadcn.stories.navigation-menu.navigation-menu-custom-link
    :as navigation-menu-custom-link]
   [clojure-shadcn.stories.navigation-menu.navigation-menu-demo             :as
                                                                            navigation-menu-demo]
   [clojure-shadcn.stories.navigation-menu.navigation-menu-indicator
    :as navigation-menu-indicator]
   [clojure-shadcn.stories.navigation-menu.navigation-menu-rtl              :as navigation-menu-rtl]
   [clojure-shadcn.stories.navigation-menu.navigation-menu-without-viewport
    :as navigation-menu-without-viewport]
   [clojure-shadcn.stories.navigation-menu.playground                       :as playground]
   [reagent.core                                                            :as r])
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
          {:source-code (embed-source
                         "clojure-shadcn.stories.navigation-menu.navigation-menu-demo")}
          []
          (r/as-element (helpers/wrap-component [navigation-menu-demo/example])))

(defstory
 NavigationMenuCustomLink
 "Navigation Menu link composed into a consumer-owned anchor.

  Radix primitive: @radix-ui/react-navigation-menu

  Use :as-child for router links or anchors that own navigation props."
 {:source-code (embed-source "clojure-shadcn.stories.navigation-menu.navigation-menu-custom-link")}
 []
 (r/as-element (helpers/wrap-component [navigation-menu-custom-link/example])))

(defstory
 NavigationMenuIndicator
 "Optional Radix active-trigger indicator with the public wrapper.

  Radix primitive: @radix-ui/react-navigation-menu

  Place one indicator under the list when the design needs a viewport pointer."
 {:source-code (embed-source "clojure-shadcn.stories.navigation-menu.navigation-menu-indicator")}
 []
 (r/as-element (helpers/wrap-component [navigation-menu-indicator/example])))

(defstory
 NavigationMenuRtl
 "Right-to-left Radix Navigation Menu.

  Radix primitive: @radix-ui/react-navigation-menu

  Pass :dir to Root so focus movement and content motion follow reading direction."
 {:source-code (embed-source "clojure-shadcn.stories.navigation-menu.navigation-menu-rtl")}
 []
 (r/as-element (helpers/wrap-component [navigation-menu-rtl/example])))

(defstory NavigationMenuWithoutViewport
          {:source-code (embed-source
                         "clojure-shadcn.stories.navigation-menu.navigation-menu-without-viewport")}
          []
          (r/as-element (helpers/wrap-component [navigation-menu-without-viewport/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.navigation-menu.playground")
           :args {:viewport true}
           :arg-types {:viewport {:name "Viewport"
                                  :control {:type "boolean"}}}
           :parameters
           {:playground {:description "Explore navigation menu viewport with a live preview."}
            :controls {:expanded false
                       :exclude ["children" "value" "default-value" "on-value-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
