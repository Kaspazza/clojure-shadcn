(ns clojure-shadcn.stories.scroll-button-stories
  "Storybook stories for the Scroll Button component. Ported from mateuszmazurczak.portfolio.ui-components.scroll_button."
  (:require
   [clojure-shadcn.stories.helpers                                :as helpers]
   [clojure-shadcn.stories.scroll-button.scroll-button-chat       :as example-scroll-button-chat]
   [clojure-shadcn.stories.scroll-button.scroll-button-custom     :as example-scroll-button-custom]
   [clojure-shadcn.stories.scroll-button.scroll-button-standalone :as
                                                                  example-scroll-button-standalone]
   [reagent.core                                                  :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Scroll Button"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Scroll-to-bottom button that appears when not at the bottom of a scrollable container."
           :npm-install "npm install lucide-react use-stick-to-bottom"
           :source-code (embed-source "clojure-shadcn.ui.components.scroll_button")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/scroll_button.cljs"
           :filename "scroll_button.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "scroll-button"
       :link {:href "https://www.prompt-kit.com/docs/scroll-button"
              :label "Prompt Kit Scroll Button Docs"}
       :description
       "Floating action button that appears when chat content is not pinned to the bottom. Integrates with use-stick-to-bottom context and scrolls smoothly to the newest message."
       :props [{:name ":variant"
                :type "keyword"
                :default ":outline"
                :description "Button variant forwarded to button component."}
               {:name ":size"
                :type "keyword"
                :default ":sm"
                :description "Button size forwarded to button component."}
               {:name ":class"
                :type "string"
                :default nil
                :description
                "Additional Tailwind classes merged with default visibility/position classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to the underlying button component."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Must be rendered inside chat-container-root; otherwise useStickToBottom context is unavailable."]
       [:li
        "Include chat-container-scroll-anchor as the last child of chat-container-content for correct bottom detection."]
       [:li
        "Visibility is managed automatically via transform/opacity classes based on scroll position."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[chat-container-root {:class \"relative h-64\"}\n  [chat-container-content {}\n    ;; messages\n    [chat-container-scroll-anchor {}]]\n  [scroll-button {:class \"absolute bottom-4 right-4\"}]]"]]]]])))

(defstory ScrollButtonChat
          "Interactive scroll-button playground."
          {:args {:variant "outline"
                  :size "sm"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "outline" "secondary" "ghost"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["xs" "sm" "default" "lg" "icon" "icon-sm"]}}
           :parameters {:playground {:description
                                     "Explore scroll button variant and size with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "on-click"]}}
           :decode-args (fn [{:keys [variant size]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            size (update :size keyword)))
           :source-code (embed-source "clojure-shadcn.stories.scroll-button.scroll-button-chat")}
          [args]
          (r/as-element (helpers/wrap-component [example-scroll-button-chat/example args])))

(defstory ScrollButtonCustom
          "Scroll button with custom styling.
  Use class overrides for position or style changes."
          {:source-code (embed-source "clojure-shadcn.stories.scroll-button.scroll-button-custom")}
          []
          (r/as-element (helpers/wrap-component [example-scroll-button-custom/example])))

(defstory ScrollButtonStandalone
          "Scroll button placement in custom layout.
  Place inside any stick-to-bottom container."
          {:source-code (embed-source
                         "clojure-shadcn.stories.scroll-button.scroll-button-standalone")}
          []
          (r/as-element (helpers/wrap-component [example-scroll-button-standalone/example])))
