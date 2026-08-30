(ns clojure-shadcn.stories.collapsible-stories
  "Storybook stories for the Collapsible component. Ported from mateuszmazurczak.portfolio.ui-components.collapsible."
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as sut]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Collapsible"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Collapsible component for showing and hiding content with animation."
                        :npm-install "npm install @radix-ui/react-collapsible"
                        :source-code (embed-source "clojure-shadcn.ui.components.collapsible")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/collapsible.cljs"
                        :filename "collapsible.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "collapsible"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/collapsible"
              :label "Radix Collapsible Docs"}
       :description
       "Radix Collapsible.Root wrapper that controls open/closed state for collapsible sections. Additional props are forwarded to the Radix root primitive."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback when open state changes: (fn [open?] ...)."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables toggling."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Collapsible.Root."}]}]
     [helpers/api-component-card
      {:component-name "collapsible-trigger"
       :description
       "Toggle control for a collapsible block. Typically wraps a button label or a custom button when using :as-child."
       :props [{:name ":as-child"
                :type "boolean"
                :default nil
                :description "Use child element as trigger via Radix Slot."}
               {:name ":on-click"
                :type "function"
                :default nil
                :description "Additional click handler composed with Radix toggle behavior."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix CollapsibleTrigger."}]}]
     [helpers/api-component-card
      {:component-name "collapsible-content"
       :description
       "Expandable/collapsible content container with Radix state attributes for animation styling."
       :props [{:name ":force-mount"
                :type "boolean"
                :default nil
                :description
                "Forces mounting even when collapsed (useful for animation libraries)."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix CollapsibleContent."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use either controlled (:open + :on-open-change) or uncontrolled (:default-open) mode, not both."]
       [:li
        "Use :as-child on the trigger when its child is a Button — otherwise Radix renders nested <button> elements (invalid HTML)."]
       [:li
        "When using :as-child on trigger, ensure your child element is interactive and keyboard-accessible."]
       [:li
        "Style animations using data-state attributes: data-[state=open] and data-[state=closed]."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [open? (r/atom false)]\n  [collapsible {:open @open?\n                :on-open-change #(reset! open? %)}\n   [collapsible-trigger {:as-child true}\n    (button {:variant :outline} \"Toggle details\")]\n   [collapsible-content {:class \"mt-3\"}\n    [:div {:class \"rounded-md border p-3\"} \"Collapsible content\"]]])"]]]]])))

(defstory
 CollapsibleBasic
 "Collapsible content with toggle.

  Radix primitive: @radix-ui/react-collapsible

  Use to reveal secondary details without leaving the page."
 []
 ;; NOTE: Storybook renders the story's return value directly, so the
 ;; story must return a React element — not a bare render fn. Wrapping the
 ;; Form-2 component in a vector lets Reagent own the component (atom
 ;; created once per mount, reactive re-renders). `(r/as-element (let
 ;; [...] (fn [] ...)))` would return a bare fn, which React rejects as a
 ;; child.
 (r/as-element [(fn []
                  (let [open? (r/atom false)]
                    (fn []
                      (helpers/wrap-component
                       [:div {:class "p-6"}
                        [sut/collapsible {:open @open?
                                          :on-open-change #(reset! open? %)}
                         ;; :as-child avoids nested <button>: trigger behavior is
                         ;; merged onto the child button via Radix Slot.
                         [sut/collapsible-trigger {:as-child true}
                          (button/button {:variant :outline}
                                         (if @open? "Hide details" "Show details"))]
                         [sut/collapsible-content {:class "mt-4"}
                          [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                           "This content expands and collapses."]]]]))))]))

(defstory
 CollapsibleDefaultOpen
 "Collapsible starting in the open state.

  Radix primitive: @radix-ui/react-collapsible

  Use :default-open for uncontrolled open state."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/collapsible {:default-open true}
                  [sut/collapsible-trigger {:as-child true}
                   (button/button {:variant :outline} "Toggle details")]
                  [sut/collapsible-content {:class "mt-4"}
                   [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                    "Starts expanded without external state management."]]]])))

(defstory
 CollapsibleDisabled
 "Disabled collapsible trigger.

  Radix primitive: @radix-ui/react-collapsible

  Use :disabled to lock the collapsible state."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/collapsible {:open true
                                   :disabled true}
                  [sut/collapsible-trigger {:as-child true}
                   (button/button {:variant :outline
                                   :disabled true}
                                  "Locked")]
                  [sut/collapsible-content {:class "mt-4"}
                   [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                    "Disabled collapsible remains open and cannot be toggled."]]]])))

(defstory
 CollapsibleMultiple
 "Multiple collapsibles in a list.

  Radix primitive: @radix-ui/react-collapsible

  Useful for FAQ sections or grouped settings."
 []
 ;; See CollapsibleBasic for why the Form-2 component is wrapped in a
 ;; vector.
 (r/as-element
  [(fn []
     (let [open-ids (r/atom #{:one})
           set-open! (fn [id next-open?]
                       (swap! open-ids (fn [current]
                                         (if next-open? (conj current id) (disj current id)))))]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-6 space-y-4"}
           ;; doall: @open-ids is derefed while realizing the seq; a
           ;; lazy seq would realize outside the reactive context and
           ;; not re-render.
           (doall
            (for [{:keys [id title body]} [{:id :one
                                            :title "Shipping"
                                            :body
                                            "Shipping details and estimated delivery windows."}
                                           {:id :two
                                            :title "Returns"
                                            :body "Return policy and refund timeline."}
                                           {:id :three
                                            :title "Support"
                                            :body "Contact information and support hours."}]]
              ^{:key id}
              [sut/collapsible {:open (contains? @open-ids id)
                                :on-open-change (fn [next-open?] (set-open! id next-open?))}
               [sut/collapsible-trigger {:as-child true}
                (button/button {:variant :outline
                                :class "w-full justify-between"}
                               title)]
               [sut/collapsible-content {:class "mt-2"}
                [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                 body]]]))]))))]))


(defstory CollapsiblePlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:default-open false}
           :arg-types {:default-open {:control {:type "boolean"}}}
           :parameters {:controls {:exclude ["children" "open" "on-open-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/collapsible
                                                 (select-keys args [:default-open])
                                                 [sut/collapsible-trigger
                                                  {:class "rounded-md border px-3 py-2"}
                                                  "Toggle details"]
                                                 [sut/collapsible-content {:class "pt-2 text-sm"}
                                                  "This content can be expanded."]])))
