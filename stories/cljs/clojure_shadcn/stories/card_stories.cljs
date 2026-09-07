(ns clojure-shadcn.stories.card-stories
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.card   :as sut]
   [clojure-shadcn.ui.components.input  :as input]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Card"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Composable content container with header, action, content, and footer regions."
           :npm-install nil
           :source-code (embed-source "clojure-shadcn.ui.components.card")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/card.cljs"
           :filename "card.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "card"
      :description
      "Visual card root rendered as a div. Compose the region components below as needed; the API does not impose heading or landmark semantics."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with card defaults."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded to the div."}]}]
    [helpers/api-component-card
     {:component-name "card-header / card-title / card-description"
      :description
      "Header grid plus unopinionated div elements for title and supporting text. Use an actual heading element inside card-title when document hierarchy requires one."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to each underlying div."}]}]
    [helpers/api-component-card
     {:component-name "card-action / card-content / card-footer"
      :description
      "Optional action, body, and footer layout regions. card-action is positioned by card-header's container grid and should therefore be nested in card-header."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to each underlying div."}]}]])))

(defstory CardDemo
          "Interactive card playground."
          {:args {:title "Create project"
                  :description "Deploy a new project in one click."
                  :action-label "Deploy"}
           :arg-types {:title {:control {:type "text"}}
                       :description {:control {:type "text"}}
                       :action-label {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/card {:class "w-full max-w-sm"}
                                                 [sut/card-header {}
                                                  [sut/card-title {}
                                                   (:title args)]
                                                  [sut/card-description {}
                                                   (:description args)]]
                                                 [sut/card-content {}
                                                  "Configure your project settings."]
                                                 [sut/card-footer {:class "justify-end border-t"}
                                                  [button/button {}
                                                   (or (:action-label args)
                                                       (:actionLabel args))]]])))

(defstory CardSimple
          []
          (r/as-element (helpers/wrap-component [sut/card {:class "max-w-md"}
                                                 [sut/card-header {}
                                                  [sut/card-title {}
                                                   "Notifications"]
                                                  [sut/card-description {}
                                                   "You have three unread messages."]]
                                                 [sut/card-content {}
                                                  "Your latest activity appears here."]])))

(defstory
 CardForm
 "Form — the fuller upstream card recipe with labeled fields, header action, and footer actions."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/card {:class "w-full max-w-md"}
    [sut/card-header {}
     [sut/card-title {}
      [:h2 "Create project"]]
     [sut/card-description {}
      "Deploy a new project to your workspace."]
     [sut/card-action {}
      (button/button {:variant :ghost
                      :size :sm}
                     "Import")]]
    [sut/card-content {:class "space-y-4"}
     [:div {:class "grid gap-2"}
      [:label {:for "project-name"
               :class "text-sm font-medium"}
       "Project name"]
      [input/input {:id "project-name"
                    :placeholder "Acme website"}]]
     [:div {:class "grid gap-2"}
      [:label {:for "project-domain"
               :class "text-sm font-medium"}
       "Domain"]
      [input/input {:id "project-domain"
                    :placeholder "acme.example.com"}]]]
    [sut/card-footer {:class "justify-between border-t"}
     (button/button {:variant :outline} "Cancel")
     (button/button {} "Create project")]])))

(defstory
 CardImage
 "Image — the upstream media card recipe composes a native image with Card regions."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/card {:class "w-full max-w-sm overflow-hidden pt-0"}
    [:img
     {:src
      "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?auto=format&fit=crop&w=800&q=80"
      :alt "Team collaborating around a table"
      :class "aspect-video w-full object-cover"}]
    [sut/card-header {}
     [sut/card-title {}
      [:h2 "Design systems workshop"]]
     [sut/card-description {}
      "A practical session for building a shared component language."]]
    [sut/card-footer {}
     (button/button {:class "w-full"} "Reserve a seat")]])))

(defstory CardRTL
          "RTL — the full card composition follows right-to-left reading order."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:dir "rtl"
                   :class "text-right"}
             [sut/card {:class "w-full max-w-md"}
              [sut/card-header {}
               [sut/card-title {}
                [:h2 "إنشاء مشروع"]]
               [sut/card-description {}
                "أدخل بيانات المشروع الجديد."]]
              [sut/card-content {}
               [:div {:class "grid gap-2"}
                [:label {:for "rtl-project"
                         :class "text-sm font-medium"}
                 "اسم المشروع"]
                [input/input {:id "rtl-project"
                              :placeholder "موقع الشركة"}]]]
              [sut/card-footer {:class "justify-end border-t"}
               (button/button {} "إنشاء")]]])))
