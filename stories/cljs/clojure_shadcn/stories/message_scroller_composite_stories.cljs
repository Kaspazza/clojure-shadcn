(ns clojure-shadcn.stories.message-scroller-composite-stories
  (:require
   ["lucide-react"                                :refer [ArrowUpIcon
                                                          GlobeIcon
                                                          ImageIcon
                                                          MessageCircleDashedIcon
                                                          PaperclipIcon
                                                          PlusIcon
                                                          RotateCwIcon
                                                          TelescopeIcon]]
   [clojure-shadcn.stories.helpers                :as helpers]
   [clojure-shadcn.ui.components.bubble           :as bubble]
   [clojure-shadcn.ui.components.button           :as button]
   [clojure-shadcn.ui.components.card             :as card]
   [clojure-shadcn.ui.components.dropdown-menu    :as dropdown-menu]
   [clojure-shadcn.ui.components.empty            :as empty]
   [clojure-shadcn.ui.components.input-group      :as input-group]
   [clojure-shadcn.ui.components.message          :as message]
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]
   [clojure-shadcn.ui.components.tooltip          :as tooltip]
   [reagent.core                                  :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [defstory]]))

(def ^:export default
  #js {:title "Composites/Message Scroller"
       :parameters #js {:layout "centered"}})

(def ^:private queued-messages
  [{:id 1
    :role :user
    :text
    "I'm building a chat for our app and the scroll behavior is driving me nuts. Every time the AI streams a reply, the whole thread jumps around."}
   {:id 2
    :role :assistant
    :text
    "That's the classic streaming scroll problem. Message Scroller follows new content only while the reader is already at the bottom. The moment they scroll up, auto-scroll backs off and preserves their position."}
   {:id 3
    :role :user
    :text
    "Okay, but when someone sends a new message the view still feels jarring — like the whole conversation reloads from the top."}
   {:id 4
    :role :assistant
    :text
    "Message Scroller Item fixes that with turn anchoring. Mark the user turn as the scroll anchor and the reply starts in view without blindly snapping the entire document to the bottom."}
   {:id 5
    :role :user
    :text
    "And if they've scrolled up to re-read an older answer? I don't want to yank them back down."}
   {:id 6
    :role :assistant
    :text
    "You won't. When unseen content arrives, Message Scroller Button appears at the bottom. One click returns to the newest message and re-engages auto-scroll — quiet when you're caught up, helpful when you're not."}
   {:id 7
    :role :user
    :text "Last one — does this work with assistive tech?"}
   {:id 8
    :role :assistant
    :text
    "Message Scroller Content is exposed as a live log so screen readers can announce additions. The scroll control is a real button and disappears from the tab order when it isn't needed."}])

(defn- chat-message
  [{:keys [role text]}]
  [message-scroller/message-scroller-item {:scroll-anchor (= role :user)}
   [message/message {:align (if (= role :user) :end :start)}
    [message/message-content {}
     [bubble/bubble {:align (if (= role :user) :end :start)
                     :variant (if (= role :user) :default :muted)}
      [bubble/bubble-content {}
       text]]]]])

(defn- demo
  []
  (let [messages (r/atom [])
        busy? (r/atom false)
        timer (atom nil)]
    (fn []
      (let [next-message (nth queued-messages (count @messages) nil)
            reset-conversation! (fn []
                                  (when-let [timer-id @timer] (js/clearTimeout timer-id))
                                  (reset! timer nil)
                                  (reset! messages [])
                                  (reset! busy? false))
            send-next! (fn [event]
                         (.preventDefault event)
                         (when (and next-message (not @busy?))
                           (reset! busy? true)
                           (reset! timer (js/setTimeout (fn []
                                                          (swap! messages conj next-message)
                                                          (reset! busy? false)
                                                          (reset! timer nil))
                                                        350))))]
        [message-scroller/message-scroller-provider {}
         [:div {:class "relative flex flex-col gap-4"}
          [card/card {:class "mx-auto h-[35rem] w-full max-w-sm gap-0 py-0"}
           [card/card-header {:class "gap-1 border-b py-5"}
            [card/card-title {}
             "New Chat"]
            [card/card-description {}
             "How can I help you today?"]
            [card/card-action {}
             [tooltip/tooltip {:trigger (r/as-element [button/button {:variant :outline
                                                                      :size :icon
                                                                      :aria-label
                                                                      "Reset conversation"
                                                                      :on-click reset-conversation!
                                                                      :disabled @busy?}
                                                       [:> RotateCwIcon]])
                               :content "Reset"
                               :trigger-as-child? true}]]]
           [card/card-content {:class "flex-1 overflow-hidden p-0"}
            (if (empty? @messages)
              [empty/empty {:class "h-full border-0"}
               [empty/empty-header {}
                [empty/empty-media {:variant :icon}
                 [:> MessageCircleDashedIcon]]
                [empty/empty-title {}
                 "Morning, shadcn!"]
                [empty/empty-description {}
                 "What are we working on today? Press send to start the conversation."]]]
              [message-scroller/message-scroller {}
               [message-scroller/message-scroller-viewport {}
                [message-scroller/message-scroller-content {:aria-busy @busy?
                                                            :class "p-6"}
                 (for [item @messages] ^{:key (:id item)} [chat-message item])]]
               [message-scroller/message-scroller-button {}]])]
           [card/card-footer {:class "flex-col gap-2 py-5"}
            [:form {:class "w-full"
                    :on-submit send-next!}
             [input-group/input-group {:class "h-auto flex-col items-stretch"}
              [:div {:class "h-14 w-full px-3 py-2.5 text-sm"}
               [:span {:class (str "line-clamp-2 " (when @busy? "opacity-60"))}
                (if next-message
                  (:text next-message)
                  [:span {:class "text-muted-foreground"}
                   "No messages queued. Reset the conversation."])]]
              [input-group/input-group-addon {:align :block-end
                                              :class "pt-1"}
               [:div {:class "flex w-full items-center gap-2"}
                [dropdown-menu/dropdown-menu {}
                 [dropdown-menu/dropdown-menu-trigger {:as-child true}
                  (button/button {:aria-label "Add files"
                                  :type "button"
                                  :size :xs
                                  :variant :outline
                                  :data-size "icon-sm"
                                  :class "size-8 rounded-full p-0 shadow-none"}
                                 [:> PlusIcon])]
                 [dropdown-menu/dropdown-menu-content {:align "start"
                                                       :side "top"
                                                       :class "w-52"}
                  [dropdown-menu/dropdown-menu-item {}
                   [:> PaperclipIcon]
                   "Add Photos & Files"]
                  [dropdown-menu/dropdown-menu-separator {}]
                  [dropdown-menu/dropdown-menu-item {}
                   [:> ImageIcon]
                   "Create Image"]
                  [dropdown-menu/dropdown-menu-item {}
                   [:> TelescopeIcon]
                   "Deep Research"]
                  [dropdown-menu/dropdown-menu-item {}
                   [:> GlobeIcon]
                   "Web Search"]]]
                [input-group/input-group-button {:type "submit"
                                                 :variant :default
                                                 :size :icon-sm
                                                 :disabled (or (nil? next-message) @busy?)
                                                 :class "ml-auto rounded-full"}
                 [:> ArrowUpIcon]
                 [:span {:class "sr-only"}
                  "Send"]]]]]]]]
          [:div {:class "px-0.5 text-center text-xs text-muted-foreground"}
           "Demo is read only. Press send to send queued messages."]]]))))

(defstory Demo
          "Read-only queued chat demonstrating anchored, intent-aware scrolling."
          []
          (r/as-element (helpers/wrap-component [demo])))
