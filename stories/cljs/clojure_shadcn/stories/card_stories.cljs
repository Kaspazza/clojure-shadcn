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
