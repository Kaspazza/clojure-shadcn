(ns clojure-shadcn.stories.textarea-stories
  "Storybook stories for the Textarea component. Ported from mateuszmazurczak.portfolio.ui-components.textarea."
  (:require
   [clojure-shadcn.stories.helpers                          :as helpers]
   [clojure-shadcn.stories.textarea.playground              :as example-playground]
   [clojure-shadcn.stories.textarea.textarea-demo           :as example-textarea-demo]
   [clojure-shadcn.stories.textarea.textarea-disabled       :as example-textarea-disabled]
   [clojure-shadcn.stories.textarea.textarea-field-disabled :as example-textarea-field-disabled]
   [clojure-shadcn.stories.textarea.textarea-invalid        :as example-textarea-invalid]
   [clojure-shadcn.stories.textarea.textarea-rtl            :as example-textarea-rtl]
   [clojure-shadcn.stories.textarea.textarea-with-button    :as example-textarea-with-button]
   [clojure-shadcn.stories.textarea.textarea-with-label     :as example-textarea-with-label]
   [clojure-shadcn.stories.textarea.textarea-with-text      :as example-textarea-with-text]
   [reagent.core                                            :as r])
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
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-demo")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-demo/example])))

(defstory
 TextareaDisabled
 "Disabled textarea for read-only content.

  Native element: <textarea>

  Disabled state applies muted styling and blocks input."
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-disabled")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-disabled/example])))

(defstory
 TextareaWithLabel
 "Textarea with a label for accessibility.

  Native element: <textarea>

  Use labels for longer form inputs and clarity."
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-with-label")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-with-label/example])))

(defstory
 TextareaInvalid
 "Invalid textarea state with inline error.

  Native element: <textarea>

  Pair :aria-invalid with error messaging for clear validation feedback."
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-invalid")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-invalid/example])))

(defstory
 TextareaWithButton
 "Textarea with a submit button.

  Native element: <textarea>

  Useful for support forms or quick feedback widgets."
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-with-button")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-with-button/example])))

(defstory
 TextareaWithText
 "Textarea with helper text.

  Native element: <textarea>

  Helper text clarifies what happens after submission."
 {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-with-text")}
 []
 (r/as-element (helpers/wrap-component [example-textarea-with-text/example])))


(defstory
 Playground
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
  :parameters
  {:playground
   {:description
    "Explore textarea placeholder, initial value, rows, disabled and auto size with a live preview."}
   :controls {:expanded false
              :exclude ["value" "on-change" "class"]}}
  :decode-args (fn [{:keys [auto-size]
                     :as args}]
                 (assoc (dissoc args :auto-size) :auto-size? auto-size))
  :source-code (embed-source "clojure-shadcn.stories.textarea.playground")}
 [args]
 (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory TextareaFieldDisabled
          "Disabled textarea inside Field. Based on shadcn/ui Textarea — Field Disabled."
          {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-field-disabled")}
          []
          (r/as-element (helpers/wrap-component [example-textarea-field-disabled/example])))

(defstory TextareaRtl
          "Right-to-left textarea field. Based on shadcn/ui Textarea — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.textarea.textarea-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-textarea-rtl/example])))
