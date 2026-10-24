(ns clojure-shadcn.stories.textarea-stories
  "Storybook stories for the Textarea component. Ported from mateuszmazurczak.portfolio.ui-components.textarea."
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.label    :as label]
   [clojure-shadcn.ui.components.textarea :as sut]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Textarea"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Textarea component for forms."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.textarea")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/textarea.cljs"
                        :filename "textarea.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "textarea"
       :description
       "Styled native textarea with optional autosizing behavior. Additional props are forwarded to underlying <textarea>."
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Controlled value."}
               {:name ":default-value"
                :type "string"
                :default nil
                :description "Uncontrolled initial value."}
               {:name ":placeholder"
                :type "string"
                :default nil
                :description "Placeholder text."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables textarea."}
               {:name ":required"
                :type "boolean"
                :default nil
                :description "Marks textarea as required."}
               {:name ":rows"
                :type "number"
                :default nil
                :description "Visible row count."}
               {:name ":cols"
                :type "number"
                :default nil
                :description "Visible column count."}
               {:name ":on-change"
                :type "function"
                :default nil
                :description "Change handler."}
               {:name ":on-blur"
                :type "function"
                :default nil
                :description "Blur handler."}
               {:name ":on-focus"
                :type "function"
                :default nil
                :description "Focus handler."}
               {:name ":auto-size?"
                :type "boolean"
                :default "true"
                :description "Uses field-sizing-content when true."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to native <textarea>."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Default :auto-size? is true (not false). Disable it when you need manual resize handles."]
       [:li "Use :aria-invalid true to trigger built-in destructive validation styles."]
       [:li "Prefer controlled mode for forms with validation/state sync."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"space-y-2 max-w-sm\"}\n [textarea {:placeholder \"Tell us about your project\"}]\n [textarea {:aria-invalid true\n            :default-value \"too short\"\n            :auto-size? false}]]"]]]]])))

(defstory
 TextareaDemo
 "Basic textarea for multi-line input.

  Native element: <textarea>

  Uses Tailwind styling and supports auto-sizing via :auto-size?."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm"}
                                        [sut/textarea {:placeholder "Type your message here."}]])))

(defstory
 TextareaDisabled
 "Disabled textarea for read-only content.

  Native element: <textarea>

  Disabled state applies muted styling and blocks input."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm"}
                                        [sut/textarea {:placeholder "Type your message here."
                                                       :disabled true}]])))

(defstory
 TextareaWithLabel
 "Textarea with a label for accessibility.

  Native element: <textarea>

  Use labels for longer form inputs and clarity."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [label/label {:html-for "message"}
                                         "Your message"]
                                        [sut/textarea {:id "message"
                                                       :placeholder "Type your message here."}]])))

(defstory
 TextareaInvalid
 "Invalid textarea state with inline error.

  Native element: <textarea>

  Pair :aria-invalid with error messaging for clear validation feedback."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [label/label {:html-for "feedback-invalid"}
                                         "Feedback"]
                                        [sut/textarea {:id "feedback-invalid"
                                                       :aria-invalid true
                                                       :default-value "bad"
                                                       :placeholder "Tell us what happened..."}]
                                        [:p {:class "text-destructive text-sm"}
                                         "Feedback should be at least 10 characters."]])))

(defstory
 TextareaWithButton
 "Textarea with a submit button.

  Native element: <textarea>

  Useful for support forms or quick feedback widgets."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [sut/textarea {:placeholder "Type your message here."}]
                                        (button/button {} "Send message")])))

(defstory
 TextareaWithText
 "Textarea with helper text.

  Native element: <textarea>

  Helper text clarifies what happens after submission."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [label/label {:html-for "message-2"}
                                         "Your message"]
                                        [sut/textarea {:id "message-2"
                                                       :placeholder "Type your message here."}]
                                        [:p {:class "text-muted-foreground text-sm"}
                                         "Your message will be routed to the support team."]])))


(defstory Playground
          "Interactive native textarea playground."
          {:args {:placeholder "Type your message…"
                  :default-value ""
                  :rows 4
                  :disabled false
                  :auto-size true}
           :arg-types {:placeholder {:name "Placeholder"
                                     :control {:type "text"}}
                       :default-value {:name "Initial value"
                                       :control {:type "text"}}
                       :rows {:name "Rows"
                              :control {:type "number"}}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}
                       :auto-size {:name "Auto size"
                                   :control {:type "boolean"}}}
           :parameters {:playground {:description "Explore textarea placeholder, initial value, rows, disabled and auto size with a live preview."}
                        :controls {:expanded false
                                   :exclude ["value" "on-change" "class"]}}
           :decode-args (fn [{:keys [auto-size]
                              :as args}]
                          (assoc (dissoc args :auto-size) :auto-size? auto-size))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "max-w-sm p-6"}
                                                 [sut/textarea args]])))

(defstory TextareaFieldDisabled
          "Disabled textarea inside Field. Based on shadcn/ui Textarea — Field Disabled."
          []
          (r/as-element (helpers/wrap-component [:div {:class "max-w-md p-6"}
                                                 [field/field {:data-disabled true}
                                                  [field/field-label {:html-for "disabled-notes"}
                                                   "Internal notes"]
                                                  [sut/textarea {:id "disabled-notes"
                                                                 :disabled true
                                                                 :default-value
                                                                 "Editing is restricted."}]
                                                  [field/field-description {}
                                                   "Only administrators can edit notes."]]])))

(defstory TextareaRtl
          "Right-to-left textarea field. Based on shadcn/ui Textarea — RTL."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "max-w-md p-6"}
                                                 [field/field {}
                                                  [field/field-label {:html-for "message-rtl"}
                                                   "رسالتك"]
                                                  [sut/textarea {:id "message-rtl"
                                                                 :placeholder "اكتب رسالتك هنا"}]
                                                  [field/field-description {}
                                                   "سنرد خلال يوم عمل."]]])))
