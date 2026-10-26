(ns clojure-shadcn.stories.message-scroller-stories
  (:require
   [clojure-shadcn.stories.helpers                                                      :as helpers]
   [clojure-shadcn.stories.message-scroller.conversation
    :as conversation]
   [clojure-shadcn.stories.message-scroller.message-scroller-anchors-and-open-positions
    :as message-scroller-anchors-and-open-positions]
   [clojure-shadcn.stories.message-scroller.message-scroller-commands-and-visibility
    :as message-scroller-commands-and-visibility]
   [clojure-shadcn.stories.message-scroller.message-scroller-group-chat
    :as message-scroller-group-chat]
   [clojure-shadcn.stories.message-scroller.message-scroller-prepend-history
    :as message-scroller-prepend-history]
   [clojure-shadcn.stories.message-scroller.message-scroller-streaming
    :as message-scroller-streaming]
   [reagent.core                                                                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Message Scroller"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Auto-following, virtualizable message viewport from the current shadcn/ui registry."
           :npm-install "npm install @shadcn/react lucide-react"
           :source-code (embed-source "clojure-shadcn.ui.components.message_scroller")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/message_scroller.cljs"
           :filename "message_scroller.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-4xl space-y-4 p-6"}
    [helpers/api-component-card
     {:component-name "message-scroller-provider / message-scroller / viewport / content"
      :link {:href "https://ui.shadcn.com/docs/components/message-scroller"
             :label "shadcn/ui Message Scroller"}
      :description
      "Provider and structural primitives for an auto-following vertical message viewport. All primitive props are forwarded."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged on root, viewport, or content."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Forwarded to the matching @shadcn/react primitive."}]}]
    [helpers/api-component-card
     {:component-name "message-scroller-item / message-scroller-button"
      :description "Virtualization-friendly item and visibility-aware start/end navigation button."
      :props [{:name ":scroll-anchor"
               :type "boolean"
               :default "false"
               :description "Marks an item as a scroll anchor."}
              {:name ":direction"
               :type "\"start\" | \"end\""
               :default "\"end\""
               :description "Target edge for the button."}
              {:name ":variant / :size"
               :type "Button keywords"
               :default ":secondary / :icon-sm"
               :description "Delegated Button appearance."}
              {:name ":render"
               :type "React element"
               :default nil
               :description "Overrides the default Button render element."}]}]
    [helpers/api-component-card
     {:component-name
      "use-message-scroller / use-message-scroller-scrollable / use-message-scroller-visibility"
      :description
      "Direct aliases of the canonical @shadcn/react hooks; call only from React/Reagent function components under the provider."
      :props []}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "⚠️ Important Notes"]
     [:p {:class "text-xs text-muted-foreground"}
      "The root needs a bounded height. Keep the canonical provider/root/viewport/content hierarchy intact."]]])))

(defstory Conversation
          "Interactive message-scroller playground."
          {:source-code (embed-source "clojure-shadcn.stories.message-scroller.conversation")
           :args {:scroll-anchor true
                  :direction "end"}
           :arg-types {:scroll-anchor {:name "Anchor last message"
                                       :control {:type "boolean"}}
                       :direction {:name "Direction"
                                   :control {:type "select"}
                                   :options ["start" "end"]}}
           :parameters
           {:playground
            {:description
             "Explore message scroller anchor last message and direction with a live preview."}
            :controls {:expanded false
                       :exclude ["class" "render"]}}}
          [args]
          (r/as-element (helpers/wrap-component [conversation/example args])))

(defstory
 MessageScrollerGroupChat
 "A grouped multi-participant transcript uses stable message IDs, turn anchors, sender headers, and end alignment."
 {:source-code (embed-source "clojure-shadcn.stories.message-scroller.message-scroller-group-chat")}
 []
 (r/as-element (helpers/wrap-component [message-scroller-group-chat/example])))



(defstory
 MessageScrollerAnchorsAndOpenPositions
 "Turn anchors preserve previous context; compare supported initial start, end, and last-anchor positions."
 {:source-code
  (embed-source
   "clojure-shadcn.stories.message-scroller.message-scroller-anchors-and-open-positions")}
 []
 (r/as-element (helpers/wrap-component [message-scroller-anchors-and-open-positions/example])))

(defstory
 MessageScrollerPrependHistory
 "Prepending older rows keeps the first visible item stable with the viewport’s public preservation prop."
 {:source-code (embed-source
                "clojure-shadcn.stories.message-scroller.message-scroller-prepend-history")}
 []
 (r/as-element (helpers/wrap-component [message-scroller-prepend-history/example])))

(defstory MessageScrollerStreaming
          "Auto-scroll follows a growing live response only while the reader remains at the end."
          {:source-code (embed-source
                         "clojure-shadcn.stories.message-scroller.message-scroller-streaming")}
          []
          (r/as-element (helpers/wrap-component [message-scroller-streaming/example])))

(defstory
 MessageScrollerCommandsAndVisibility
 "Public hooks drive commands and expose current anchor, visible IDs, and scrollable edges."
 {:source-code (embed-source
                "clojure-shadcn.stories.message-scroller.message-scroller-commands-and-visibility")}
 []
 (r/as-element (helpers/wrap-component [message-scroller-commands-and-visibility/example])))
