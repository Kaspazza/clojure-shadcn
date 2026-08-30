(ns clojure-shadcn.stories.message-scroller-stories
  (:require
   [clojure-shadcn.stories.helpers                :as helpers]
   [clojure-shadcn.ui.components.message-scroller :as sut]
   [reagent.core                                  :as r])
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
          "Bounded conversation with canonical item composition and an end button."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "h-80 max-w-xl rounded-lg border"}
                          [sut/message-scroller-provider {}
                           [sut/message-scroller {}
                            [sut/message-scroller-viewport {}
                             [sut/message-scroller-content {:class "p-4"}
                              (for [n (range 1 9)]
                                ^{:key n}
                                [sut/message-scroller-item {:scroll-anchor (= n 8)}
                                 [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                                  (str "Conversation message " n)]])]]
                            [sut/message-scroller-button {}]]]])))
