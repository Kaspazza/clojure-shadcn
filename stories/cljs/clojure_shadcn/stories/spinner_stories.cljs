(ns clojure-shadcn.stories.spinner-stories
  "Storybook stories for the Spinner component. Ported from mateuszmazurczak.portfolio.ui-components.spinner."
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.badge       :as badge]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.empty       :as empty]
   [clojure-shadcn.ui.components.input-group :as input-group]
   [clojure-shadcn.ui.components.spinner     :as sut]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Spinner"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Spinner component for loading states."
                        :npm-install "npm install lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.spinner")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/spinner.cljs"
                        :filename "spinner.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "spinner"
       :description
       "Lucide Loader2-based visual spinner. It is aria-hidden and does not provide status semantics by itself."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional CSS classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to underlying icon element."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Default icon size is size-4; override with :class for larger/smaller spinners."]
       [:li
        "Spinner is aria-hidden and purely presentational; put status semantics and text on a parent or sibling."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"flex items-center gap-2\"}\n [spinner {:class \"size-4\"}]\n [:span \"Loading data...\"]]"]]]]])))

(defstory SpinnerBasic
          "Interactive spinner playground."
          {:args {:class "size-4"}
           :arg-types {:class {:control {:type "select"}
                               :options ["size-3" "size-4" "size-6" "size-8"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/spinner (select-keys args [:class])]])))

(defstory
 SpinnerSize
 "Spinner size variants using class overrides.

  Icon: lucide-react Loader2

  Adjust size via Tailwind size classes."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 flex items-center gap-6"}
                                        [sut/spinner {:class "size-3"}]
                                        [sut/spinner {:class "size-4"}]
                                        [sut/spinner {:class "size-6"}]
                                        [sut/spinner {:class "size-8"}]])))

(defstory
 SpinnerButton
 "Spinner inside disabled buttons.

  Icon: lucide-react Loader2

  Combine with buttons to show in-progress actions."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 flex flex-col items-start gap-4"}
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

(defstory
 SpinnerBadge
 "Spinner embedded in badges.

  Icon: lucide-react Loader2

  Useful for background status updates."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 flex items-center gap-4"}
                                        [badge/badge {}
                                         [sut/spinner {}]
                                         "Syncing"]
                                        [badge/badge {:variant :secondary}
                                         [sut/spinner {}]
                                         "Updating"]
                                        [badge/badge {:variant :outline}
                                         [sut/spinner {}]
                                         "Processing"]])))

(defstory
 SpinnerColor
 "Spinner color variations.

  Icon: lucide-react Loader2

  Color via text utility classes."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 flex items-center gap-6"}
                                        [sut/spinner {:class "size-6 text-red-500"}]
                                        [sut/spinner {:class "size-6 text-green-500"}]
                                        [sut/spinner {:class "size-6 text-blue-500"}]
                                        [sut/spinner {:class "size-6 text-yellow-500"}]
                                        [sut/spinner {:class "size-6 text-purple-500"}]])))

(defstory
 SpinnerDemo
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

(defstory SpinnerInputGroup
          "Input Group — upstream loading field pairs a visual spinner with explicit status text."
          []
          (r/as-element (helpers/wrap-component [:div {:role "status"
                                                       :class "max-w-sm"}
                                                 [input-group/input-group {}
                                                  [input-group/input-group-addon {}
                                                   [sut/spinner {}]]
                                                  [input-group/input-group-input
                                                   {:disabled true
                                                    :value "Searching repositories…"
                                                    :aria-label "Repository search"}]]
                                                 [:span {:class "sr-only"}
                                                  "Searching repositories"]])))

(defstory
 SpinnerEmpty
 "Empty — upstream loading empty-state composition uses explicit copy for the accessible status."
 []
 (r/as-element (helpers/wrap-component [empty/empty {:role "status"
                                                     :class "border"}
                                        [empty/empty-header {}
                                         [empty/empty-media {}
                                          [sut/spinner {:class "size-8"}]]
                                         [empty/empty-title {}
                                          "Loading results"]
                                         [empty/empty-description {}
                                          "This may take a moment."]]])))

(defstory SpinnerRTL
          "RTL — visual spinner accompanies explicit right-to-left loading text."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :role "status"
                                                       :class "flex items-center gap-2 text-sm"}
                                                 [sut/spinner {}]
                                                 [:span "جارٍ تحميل البيانات…"]])))
