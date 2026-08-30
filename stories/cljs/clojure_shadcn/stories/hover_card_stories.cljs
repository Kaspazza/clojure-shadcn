(ns clojure-shadcn.stories.hover-card-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.hover-card :as sut]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default #js {:title "Components/Hover Card" :parameters #js {:layout "padded"}})

(defdoc Installation []
  (r/as-element [helpers/installation-scene {:description "Preview content revealed on hover or keyboard focus." :npm-install "npm install @radix-ui/react-hover-card" :source-code (embed-source "clojure-shadcn.ui.components.hover-card") :namespace-path "src/cljs/clojure_shadcn/ui/components/hover_card.cljs" :filename "hover_card.cljs"}]))

(defstory ApiReference []
  (r/as-element
   (helpers/wrap-component
    [helpers/api-component-card
     {:component-name "hover-card / hover-card-trigger / hover-card-content"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/hover-card" :label "Radix Hover Card Docs"}
      :description "Accessible Radix wrappers with collision-aware portalled content. Normalized additional props are forwarded to each primitive."
      :props [{:name ":open / :default-open / :on-open-change" :type "boolean | function" :default nil :description "Controlled or uncontrolled visibility."}
              {:name ":open-delay / :close-delay" :type "number" :default nil :description "Pointer timing in milliseconds."}
              {:name ":side / :align / :side-offset" :type "keyword|string|number" :default nil :description "Content positioning."}]}])))

(defstory HoverCardBasic []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "p-16"}
     [sut/hover-card {}
      [sut/hover-card-trigger {:as-child true}
       [:a {:href "https://github.com/shadcn-ui/ui" :class "font-medium underline underline-offset-4"} "@shadcn"]]
      [sut/hover-card-content {:class "w-80"}
       [:div {:class "space-y-2"}
        [:h4 {:class "font-semibold"} "shadcn/ui"]
        [:p {:class "text-sm text-muted-foreground"} "Beautifully designed components you can copy and own."]]]]])))

(defstory HoverCardFastOpen []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "p-16"}
     [sut/hover-card {:open-delay 0}
      [sut/hover-card-trigger {} "Focus or hover immediately"]
      [sut/hover-card-content {} "No opening delay."]]])))
