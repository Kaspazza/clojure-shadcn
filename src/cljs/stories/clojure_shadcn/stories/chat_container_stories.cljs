(ns clojure-shadcn.stories.chat-container-stories
  "Storybook stories for the Chat Container component. Ported from mateuszmazurczak.portfolio.ui-components.chat_container."
  (:require
   [clojure-shadcn.stories.helpers              :as helpers]
   [clojure-shadcn.stories.chat-container.playground :as playground]
   [clojure-shadcn.stories.chat-container.chat-container-long :as chat-container-long]
   [clojure-shadcn.stories.chat-container.chat-container-composition :as chat-container-composition]
   [clojure-shadcn.stories.chat-container.chat-container-append :as chat-container-append]
   [clojure-shadcn.stories.chat-container.chat-container-streaming :as chat-container-streaming]
   [reagent.core                                :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Chat Container"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Chat container component with auto-scroll-to-bottom functionality."
                        :npm-install "npm install use-stick-to-bottom"
                        :source-code (embed-source "clojure-shadcn.ui.components.chat_container")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/chat_container.cljs"
                        :filename "chat_container.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "chat-container-root"
       :link {:href "https://www.prompt-kit.com/docs/chat-container"
              :label "Prompt Kit Chat Container Docs"}
       :description "Root scroll container powered by use-stick-to-bottom context."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":resize"
                :type "string"
                :default "\"smooth\""
                :description "One of: 'smooth' | 'instant'"}
               {:name ":initial"
                :type "string"
                :default "\"instant\""
                :description "One of: 'instant' | 'smooth'"}]}]
     [helpers/api-component-card {:component-name "chat-container-content"
                                  :description
                                  "Message list/content region within chat-container-root."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "chat-container-scroll-anchor"
       :description "Anchor marker used for stick-to-bottom behavior and scroll targeting."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Always keep chat-container-scroll-anchor as the last child of chat-container-content."]
       [:li
        "scroll-button and other stick-to-bottom consumers must be nested inside chat-container-root."]
       [:li
        "For accessibility, consider setting role=\"log\" and aria-live semantics on content wrappers when needed."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[chat-container-root {:class \"h-64 border rounded-md\"}\n [chat-container-content {:class \"p-4 space-y-2\"}\n  [:div \"Message 1\"]\n  [:div \"Message 2\"]\n  [chat-container-scroll-anchor {}]]\n [scroll-button {:class \"absolute bottom-4 right-4\"}]]"]]]]])))

(defstory Playground
          "Interactive chat-container playground."
          {:source-code (embed-source "clojure-shadcn.stories.chat-container.playground")
           :args {:resize "smooth"
                  :initial "instant"}
           :arg-types {:resize {:name "Resize scroll behavior"
                                :control {:type "select"}
                                :options ["smooth" "instant"]}
                       :initial {:name "Initial scroll behavior"
                                 :control {:type "select"}
                                 :options ["instant" "smooth"]}}
           :parameters {:playground {:description "Explore chat container resize scroll behavior and initial scroll behavior with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory ChatContainerLong
 "Chat container with many messages and scroll-to-bottom button.
  Demonstrates overflow, stick-to-bottom behavior, and interactive recovery when scrolled up."
 {:source-code (embed-source "clojure-shadcn.stories.chat-container.chat-container-long")}
 []
 (r/as-element (helpers/wrap-component [chat-container-long/example])))

(defstory ChatContainerComposition
 "Chat container with header and footer content.
  Use additional elements around the scroll region for composition."
 {:source-code (embed-source "clojure-shadcn.stories.chat-container.chat-container-composition")}
 []
 (r/as-element (helpers/wrap-component [chat-container-composition/example])))

(defstory ChatContainerAppend
 "Append messages while pinned to the live edge; scroll up to inspect history without being pulled back."
 {:source-code (embed-source "clojure-shadcn.stories.chat-container.chat-container-append")}
 []
 (r/as-element (helpers/wrap-component [chat-container-append/example])))

(defstory ChatContainerStreaming
 "A growing final message demonstrates resize-follow behavior during token streaming."
 {:source-code (embed-source "clojure-shadcn.stories.chat-container.chat-container-streaming")}
 []
 (r/as-element (helpers/wrap-component [chat-container-streaming/example])))
