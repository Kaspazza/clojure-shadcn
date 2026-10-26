(ns clojure-shadcn.stories.separator-stories
  "Storybook stories for the Separator component. Ported from mateuszmazurczak.portfolio.ui-components.separator."
  (:require
   [clojure-shadcn.stories.helpers                             :as helpers]
   [clojure-shadcn.stories.separator.breadcrumb-separator      :as example-breadcrumb-separator]
   [clojure-shadcn.stories.separator.playground                :as example-playground]
   [clojure-shadcn.stories.separator.separator-custom          :as example-separator-custom]
   [clojure-shadcn.stories.separator.separator-list            :as example-separator-list]
   [clojure-shadcn.stories.separator.separator-responsive-menu :as
                                                               example-separator-responsive-menu]
   [clojure-shadcn.stories.separator.separator-rtl             :as example-separator-rtl]
   [reagent.core                                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Separator"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Separator component for visual dividers."
                        :npm-install "npm install @radix-ui/react-separator"
                        :source-code (embed-source "clojure-shadcn.ui.components.separator")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/separator.cljs"
                        :filename "separator.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "separator"
       :description "Separator component"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/separator"
              :label "Radix Separator Docs"}
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":orientation"
                :type "keyword"
                :default ":horizontal"
                :description "One of: :horizontal | :vertical"}
               {:name ":decorative"
                :type "boolean"
                :default "true"
                :description "Decorative vs semantic separator"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Set :decorative false when the separator conveys semantic structure for assistive technologies."]
       [:li "Use vertical separators only in containers with explicit height."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"space-y-3\"}\n [:p \"Section A\"]\n [separator {:orientation :horizontal}]\n [:p \"Section B\"]]"]]]]])))

(defstory Playground
          "Interactive separator playground."
          {:args {:orientation "horizontal"
                  :decorative true}
           :arg-types {:orientation {:name "Orientation"
                                     :control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :decorative {:name "Decorative"
                                    :control {:type "boolean"}}}
           :parameters {:playground
                        {:description
                         "Explore separator orientation and decorative with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))
           :source-code (embed-source "clojure-shadcn.stories.separator.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory
 BreadcrumbSeparator
 "Separator used inside breadcrumb navigation.

  Radix primitive: @radix-ui/react-separator

  Custom separators can be inserted between breadcrumb items."
 {:source-code (embed-source "clojure-shadcn.stories.separator.breadcrumb-separator")}
 []
 (r/as-element (helpers/wrap-component [example-breadcrumb-separator/example])))

(defstory
 SeparatorCustom
 "Separator with custom styling.

  Radix primitive: @radix-ui/react-separator

  Add classes to adjust thickness or color."
 {:source-code (embed-source "clojure-shadcn.stories.separator.separator-custom")}
 []
 (r/as-element (helpers/wrap-component [example-separator-custom/example])))

(defstory
 SeparatorResponsiveMenu
 "Responsive menu — upstream navigation recipe switches between horizontal and vertical dividers."
 {:source-code (embed-source "clojure-shadcn.stories.separator.separator-responsive-menu")}
 []
 (r/as-element (helpers/wrap-component [example-separator-responsive-menu/example])))

(defstory SeparatorList
          "List — semantic separators divide related content rows without becoming list items."
          {:source-code (embed-source "clojure-shadcn.stories.separator.separator-list")}
          []
          (r/as-element (helpers/wrap-component [example-separator-list/example])))

(defstory SeparatorRTL
          "RTL — logical spacing and vertical dividers in right-to-left navigation."
          {:source-code (embed-source "clojure-shadcn.stories.separator.separator-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-separator-rtl/example])))
