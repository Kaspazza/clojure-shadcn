(ns clojure-shadcn.stories.spinner-stories
  "Storybook stories for the Spinner component. Ported from mateuszmazurczak.portfolio.ui-components.spinner."
  (:require
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.badge   :as badge]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.spinner :as sut]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source]])
)

(def ^:export default
  #js {:title      "Components/Spinner"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Spinner component for loading states."
              :npm-install "npm install lucide-react"
              :source-code (embed-source "clojure-shadcn.ui.components.spinner")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/spinner.cljs"
              :filename "spinner.cljs"}]))

(defn ^:export ApiReference
  []
  (r/as-element
  (helpers/wrap-component
             [:div {:class "p-6 max-w-4xl"}
              [:div {:class "space-y-4"}
               [helpers/api-component-card
                {:component-name "spinner"
                  :description "Lucide Loader2-based spinner with built-in accessibility defaults."
                  :props [{:name ":class"          :type "string"      :default nil        :description "Additional CSS classes."}
                          {:name ":role"           :type "string"      :default "\"status\"" :description "Accessibility role."}
                          {:name ":aria-label"     :type "string"      :default "\"Loading\"" :description "Screen reader label."}
                          {:name "additional props" :type "map entries" :default nil        :description "Forwarded to underlying icon element."}]}]
                [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
                 [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
                 [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
                  [:li "Default icon size is size-4; override with :class for larger/smaller spinners."]
                  [:li "Spinner is purely presentational—pair with status text in long-running operations."]]]
                [:div {:class "border rounded-lg p-4 bg-muted/50"}
                 [:h4 {:class "text-sm font-semibold mb-2"}
                  "Usage Example"]
                 [:pre {:class "text-xs overflow-x-auto"}
                  [:code "[:div {:class \"flex items-center gap-2\"}\n [spinner {:class \"size-4\"}]\n [:span \"Loading data...\"]]" ]]]]])))

(defn ^:export SpinnerBasic
  "Basic spinner indicator.

  Icon: lucide-react Loader2

  Use for lightweight loading indicators."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [sut/spinner {}]])))

(defn ^:export SpinnerSize
  "Spinner size variants using class overrides.

  Icon: lucide-react Loader2

  Adjust size via Tailwind size classes."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 flex items-center gap-6"}
                                       [sut/spinner {:class "size-3"}]
                                       [sut/spinner {:class "size-4"}]
                                       [sut/spinner {:class "size-6"}]
                                       [sut/spinner {:class "size-8"}]])))

(defn ^:export SpinnerButton
  "Spinner inside disabled buttons.

  Icon: lucide-react Loader2

  Combine with buttons to show in-progress actions."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 flex flex-col items-start gap-4"}
                                       (button/button {:disabled true
                                                       :size :sm}
                                                      [sut/spinner {}]
                                                      "Loading...")
                                       (button/button {:variant :outline
                                                       :disabled true
                                                       :size :sm}
                                                      [sut/spinner {}]
                                                      "Please wait")
                                       (button/button {:variant :secondary
                                                       :disabled true
                                                       :size :sm}
                                                      [sut/spinner {}]
                                                      "Processing")])))

(defn ^:export SpinnerBadge
  "Spinner embedded in badges.

  Icon: lucide-react Loader2

  Useful for background status updates."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 flex items-center gap-4"}
                                       [badge/badge {}
                                        [sut/spinner {}]
                                        "Syncing"]
                                       [badge/badge {:variant :secondary}
                                        [sut/spinner {}]
                                        "Updating"]
                                       [badge/badge {:variant :outline}
                                        [sut/spinner {}]
                                        "Processing"]])))

(defn ^:export SpinnerColor
  "Spinner color variations.

  Icon: lucide-react Loader2

  Color via text utility classes."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 flex items-center gap-6"}
                                       [sut/spinner {:class "size-6 text-red-500"}]
                                       [sut/spinner {:class "size-6 text-green-500"}]
                                       [sut/spinner {:class "size-6 text-blue-500"}]
                                       [sut/spinner {:class "size-6 text-yellow-500"}]
                                       [sut/spinner {:class "size-6 text-purple-500"}]])))

(defn ^:export SpinnerDemo
  "Spinner inside list item layout.

  Icon: lucide-react Loader2

  Demonstrates inline usage in a row layout."
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6"}
     [:div {:class
            "flex w-full max-w-xs items-center justify-between rounded-lg border bg-muted/50 p-4"}
      [:div {:class "flex items-center gap-3"}
       [sut/spinner {}]
       [:div {:class "text-sm font-medium"}
        "Processing payment..."]]
      [:span {:class "text-sm tabular-nums"}
       "$100.00"]]])))
