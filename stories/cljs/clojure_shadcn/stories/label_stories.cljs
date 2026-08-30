(ns clojure-shadcn.stories.label-stories
  "Storybook stories for the Label component. Ported from mateuszmazurczak.portfolio.ui-components.label."
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.input    :as input]
   [clojure-shadcn.ui.components.label    :as sut]
   [clojure-shadcn.ui.components.textarea :as textarea]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Label"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Label component for form fields with accessibility support."
                        :npm-install "npm install @radix-ui/react-label"
                        :source-code (embed-source "clojure-shadcn.ui.components.label")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/label.cljs"
                        :filename "label.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card {:component-name "label"
                                  :description
                                  "Accessible form label wrapper around Radix Label primitive."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name ":html-for"
                                           :type "string"
                                           :default nil
                                           :description "Associates label with control id."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying label element."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "For proper click/focus behavior, pair :html-for with matching input :id."]
       [:li "Labels support nested interactive layouts, but keep text concise for accessibility."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"space-y-2\"}\n [label {:html-for \"email\"} \"Email\"]\n [input {:id \"email\" :type \"email\" :placeholder \"you@example.com\"}]]"]]]]])))

(defstory LabelDemo
          "Interactive label playground."
          {:args {:label "Accept terms and conditions"
                  :disabled false}
           :arg-types {:label {:control {:type "text"}}
                       :disabled {:control {:type "boolean"}}}
           :parameters {:controls {:exclude ["class" "html-for" "id" "on-checked-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [:div {:class "flex items-center gap-2"}
                                                  [checkbox/checkbox {:id "terms"
                                                                      :disabled (:disabled args)}]
                                                  [sut/label {:html-for "terms"}
                                                   (:label args)]]])))

(defstory
 InputWithLabel
 "Label with input field.

  Radix primitive: @radix-ui/react-label

  Keep labels close to inputs for clarity."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [sut/label {:html-for "email"}
                                         "Email"]
                                        [input/input {:id "email"
                                                      :type "email"
                                                      :placeholder "Email"}]])))

(defstory
 TextareaWithLabel
 "Label with textarea for multi-line input.

  Radix primitive: @radix-ui/react-label

  Use labels to describe longer-form fields."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                        [sut/label {:html-for "message"}
                                         "Your message"]
                                        [textarea/textarea {:id "message"
                                                            :placeholder
                                                            "Type your message here."}]])))
