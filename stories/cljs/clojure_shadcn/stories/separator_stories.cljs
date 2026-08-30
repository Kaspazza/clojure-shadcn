(ns clojure-shadcn.stories.separator-stories
  "Storybook stories for the Separator component. Ported from mateuszmazurczak.portfolio.ui-components.separator."
  (:require
   ["lucide-react"                          :refer [Slash]]
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]
   [clojure-shadcn.ui.components.separator  :as sut]
   [reagent.core                            :as r])
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

(defstory SeparatorDemo
          "Interactive separator playground."
          {:args {:orientation "horizontal"
                  :decorative true}
           :arg-types {:orientation {:control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :decorative {:control {:type "boolean"}}}
           :parameters {:controls {:exclude ["class"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "flex h-16 items-center gap-4"}
                                                 [:span "First"]
                                                 [sut/separator
                                                  (assoc (select-keys args
                                                                      [:orientation :decorative])
                                                         :class
                                                         "data-[orientation=vertical]:h-full")]
                                                 [:span "Second"]])))

(defstory
 BreadcrumbSeparator
 "Separator used inside breadcrumb navigation.

  Radix primitive: @radix-ui/react-separator

  Custom separators can be inserted between breadcrumb items."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [breadcrumb/breadcrumb {}
                                         [breadcrumb/breadcrumb-list {}
                                          [breadcrumb/breadcrumb-item {}
                                           [breadcrumb/breadcrumb-link {:href "#"}
                                            "Home"]]
                                          [breadcrumb/breadcrumb-separator {}
                                           [:> Slash]]
                                          [breadcrumb/breadcrumb-item {}
                                           [breadcrumb/breadcrumb-link {:href "#"}
                                            "Components"]]
                                          [breadcrumb/breadcrumb-separator {}
                                           [:> Slash]]
                                          [breadcrumb/breadcrumb-item {}
                                           [breadcrumb/breadcrumb-page {}
                                            "Separator"]]]]])))

(defstory
 SeparatorCustom
 "Separator with custom styling.

  Radix primitive: @radix-ui/react-separator

  Add classes to adjust thickness or color."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 space-y-3"}
                                        [:p {:class "text-sm"}
                                         "Primary accent"]
                                        [sut/separator {:class "bg-primary h-[2px]"}]
                                        [:p {:class "text-sm"}
                                         "Muted divider"]
                                        [sut/separator {:class "bg-muted h-[2px]"}]])))
