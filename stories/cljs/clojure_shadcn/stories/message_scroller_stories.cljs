(ns clojure-shadcn.stories.message-scroller-stories
  (:require
   [clojure-shadcn.stories.helpers                :as helpers]
   [clojure-shadcn.ui.components.bubble           :as bubble]
   [clojure-shadcn.ui.components.message          :as message]
   [clojure-shadcn.ui.components.message-scroller :as sut]
   [reagent.core                                  :as    r
                                                  :refer [defc]])
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
          {:args {:scroll-anchor true
                  :direction "end"}
           :arg-types {:scroll-anchor {:control {:type "boolean"}}
                       :direction {:control {:type "select"}
                                   :options ["start" "end"]}}
           :parameters {:controls {:exclude ["class" "render"]}}}
          [args]
          (r/as-element (helpers/wrap-component
                         [:div {:class "h-80 max-w-xl rounded-lg border"}
                          [sut/message-scroller-provider {}
                           [sut/message-scroller {}
                            [sut/message-scroller-viewport {}
                             [sut/message-scroller-content {:class "p-4"}
                              (for [n (range 1 9)]
                                ^{:key n}
                                [sut/message-scroller-item {:scroll-anchor
                                                            (and (:scroll-anchor args) (= n 8))}
                                 [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                                  (str "Conversation message " n)]])]]
                            [sut/message-scroller-button (select-keys args [:direction])]]]])))

(defstory
 MessageScrollerGroupChat
 "A grouped multi-participant transcript uses stable message IDs, turn anchors, sender headers, and end alignment."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "h-96 max-w-2xl rounded-lg border"}
    [sut/message-scroller-provider {:defaultScrollPosition "end"
                                    :scrollPreviousItemPeek 56}
     [sut/message-scroller {}
      [sut/message-scroller-viewport {:aria-label "Design team transcript"}
       [sut/message-scroller-content {:class "gap-4 p-4"}
        (for [{:keys [id sender initials align text]}
              [{:id "ava-1"
                :sender "Ava"
                :initials "AV"
                :align :start
                :text "The interaction notes are ready for review."}
               {:id "noah-1"
                :sender "Noah"
                :initials "NO"
                :align :start
                :text "I’ll check keyboard and reduced-motion behavior."}
               {:id "you-1"
                :sender "You"
                :initials "ME"
                :align :end
                :text "Great. I’ll verify the responsive states."}
               {:id "ava-2"
                :sender "Ava"
                :initials "AV"
                :align :start
                :text "Please keep the previous turn visible when opening this reply."}]]
          ^{:key id}
          [sut/message-scroller-item {:message-id id
                                      :scroll-anchor true}
           [message/message {:align align}
            [message/message-avatar {:src ""
                                     :alt sender
                                     :fallback initials}]
            [message/message-content {}
             [message/message-header {}
              sender]
             [bubble/bubble {:align align
                             :variant (if (= align :end) :default :muted)}
              [bubble/bubble-content {}
               text]]]]])]]
      [sut/message-scroller-button {}]
      [sut/message-scroller-button {:direction "start"}]]]])))

(defc scroller-controls
 []
 (let [^js commands (sut/use-message-scroller)
       ^js scrollable (sut/use-message-scroller-scrollable)
       ^js visibility (sut/use-message-scroller-visibility)
       ^js visible (.-visibleMessageIds visibility)]
   [:div {:class "flex flex-wrap items-center gap-2 border-t p-2 text-xs"}
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToStart commands) #js {:behavior "smooth"})}
     "Start"]
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToMessage commands)
                           "turn-3"
                           #js {:align "center"
                                :behavior "smooth"})}
     "Turn 3"]
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToEnd commands) #js {:behavior "smooth"})}
     "End"]
    [:span {:class "text-muted-foreground"}
     (str "scrollable: " (when (.-start scrollable) "start ") (when (.-end scrollable) "end"))]
    [:span {:class "text-muted-foreground"}
     (str "anchor: " (or (.-currentAnchorId visibility) "none")
          " · visible: " (if visible (.join visible ", ") "none"))]]))

(defstory
 MessageScrollerAnchorsAndOpenPositions
 "Turn anchors preserve previous context; compare supported initial start, end, and last-anchor positions."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid gap-4 lg:grid-cols-3"}
    (for [position ["start" "end" "last-anchor"]]
      ^{:key position}
      [:div {:class "space-y-2"}
       [:p {:class "text-sm font-medium"}
        (str "Open at " position)]
       [:div {:class "h-72 rounded-lg border"}
        [sut/message-scroller-provider {:defaultScrollPosition position
                                        :scrollPreviousItemPeek 72}
         [sut/message-scroller {}
          [sut/message-scroller-viewport {}
           [sut/message-scroller-content {:class "gap-3 p-4"}
            (for [n (range 1 7)]
              ^{:key n}
              [sut/message-scroller-item {:message-id (str "position-" position "-" n)
                                          :scroll-anchor (contains? #{2 4 6} n)}
               [:div {:class "space-y-1"}
                (when (contains? #{2 4 6} n)
                  [:p {:class "text-xs font-medium text-primary"}
                   (str "Turn " (/ n 2))])
                [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                 (str (if (odd? n) "Previous assistant context " "User anchor ") n)]]])]]
          [sut/message-scroller-button {}]
          [sut/message-scroller-button {:direction "start"}]]]]])])))

(defstory
 MessageScrollerPrependHistory
 "Prepending older rows keeps the first visible item stable with the viewport’s public preservation prop."
 []
 (r/as-element
  [(fn []
     (let [messages (r/atom (vec (range 11 21)))]
       (fn []
         (helpers/wrap-component
          [:div {:class "max-w-xl space-y-2"}
           [:button {:type "button"
                     :class "rounded-md border px-3 py-2 text-sm"
                     :on-click #(swap! messages (fn [items]
                                                  (into (vec (range (- (first items) 5)
                                                                    (first items)))
                                                        items)))}
            "Load previous messages"]
           [:div {:class "h-72 rounded-lg border"}
            [sut/message-scroller-provider {:defaultScrollPosition "start"}
             [sut/message-scroller {}
              [sut/message-scroller-viewport {:preserveScrollOnPrepend true}
               [sut/message-scroller-content {:class "gap-3 p-4"}
                (for [n @messages]
                  ^{:key n}
                  [sut/message-scroller-item {:message-id (str "history-" n)
                                              :class
                                              "transition-opacity duration-300 starting:opacity-0"}
                   [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                    (str "Message " n)]])]]]]]]))))]))

(defstory
 MessageScrollerStreaming
 "Auto-scroll follows a growing live response only while the reader remains at the end."
 []
 (r/as-element
  [(fn []
     (let [text (r/atom "Generating")
           timer (atom nil)]
       (r/create-class
        {:component-did-mount
         (fn [_]
           (let [chunks
                 [" a" " concise" " release" " summary" " from" " current" " build" " results."]
                 index (atom 0)]
             (reset! timer (js/setInterval #(if (< @index (count chunks))
                                              (do (swap! text str (nth chunks @index))
                                                  (swap! index inc))
                                              (js/clearInterval @timer))
                                           400))))
         :component-will-unmount #(when @timer (js/clearInterval @timer))
         :reagent-render
         (fn []
           (helpers/wrap-component
            [:div {:class "h-72 max-w-xl rounded-lg border"}
             [sut/message-scroller-provider {:autoScroll true}
              [sut/message-scroller {}
               [sut/message-scroller-viewport {}
                [sut/message-scroller-content {:class "gap-3 p-4"}
                 [sut/message-scroller-item {:message-id "question"
                                             :scroll-anchor true}
                  [:div {:class "rounded-lg bg-primary p-3 text-sm text-primary-foreground"}
                   "Summarize this release."]]
                 [sut/message-scroller-item {:message-id "stream"}
                  [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                   @text]]]]
               [sut/message-scroller-button {}]]]]))})))]))

(defstory
 MessageScrollerCommandsAndVisibility
 "Public hooks drive commands and expose current anchor, visible IDs, and scrollable edges."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "h-80 max-w-xl rounded-lg border"}
                 [sut/message-scroller-provider {:scrollMargin 16}
                  [sut/message-scroller {}
                   [sut/message-scroller-viewport {:aria-label "Command demo transcript"}
                    [sut/message-scroller-content {:class "gap-3 p-4"}
                     (for [n (range 1 9)]
                       ^{:key n}
                       [sut/message-scroller-item {:message-id (str "turn-" n)
                                                   :scroll-anchor (odd? n)}
                        [:div {:class "rounded-lg bg-muted p-4 text-sm"}
                         (str "Turn " n)]])]]
                   [sut/message-scroller-button {}]
                   [sut/message-scroller-button {:direction "start"}]]
                  [scroller-controls]]])))
