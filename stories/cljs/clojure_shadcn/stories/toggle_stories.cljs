(ns clojure-shadcn.stories.toggle-stories
  (:require
   ["lucide-react" :refer [Bold Italic]]
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.toggle :as sut]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default #js {:title "Components/Toggle" :parameters #js {:layout "padded"}})

(defdoc Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Two-state button with accessible pressed semantics."
     :npm-install "npm install @radix-ui/react-toggle"
     :source-code (embed-source "clojure-shadcn.ui.components.toggle")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/toggle.cljs"
     :filename "toggle.cljs"}]))

(defstory ApiReference []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card
      {:component-name "toggle"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/toggle" :label "Radix Toggle Docs"}
       :description "Styled Radix Toggle.Root preserving keyboard activation and aria-pressed state. Props use kebab-case, are normalized, and additional props are forwarded."
       :props [{:name ":pressed / :default-pressed" :type "boolean" :default nil :description "Controlled or initial pressed state."}
               {:name ":on-pressed-change" :type "function" :default nil :description "Receives the next pressed state."}
               {:name ":variant" :type ":default | :outline" :default ":default" :description "Visual treatment."}
               {:name ":size" :type ":default | :sm | :lg" :default ":default" :description "Control dimensions."}
               {:name ":disabled" :type "boolean" :default nil :description "Prevents interaction."}
               {:name ":class" :type "string" :default nil :description "Classes merged after defaults."}]}]
     [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
      "Provide an accessible name with visible text or :aria-label when rendering only an icon."]])))

(defstory ToggleBasic []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "flex gap-3 p-6"}
     [sut/toggle {:aria-label "Toggle bold"} [:> Bold]]
     [sut/toggle {:variant :outline :aria-label "Toggle italic"} [:> Italic]]])))

(defstory ToggleStates []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "flex flex-wrap items-center gap-3 p-6"}
     [sut/toggle {:default-pressed true :aria-label "Bold enabled"} [:> Bold]]
     [sut/toggle {:disabled true :aria-label "Italic unavailable"} [:> Italic]]
     [sut/toggle {:size :sm :variant :outline} "Small"]
     [sut/toggle {:size :lg :variant :outline} "Large"]])))
