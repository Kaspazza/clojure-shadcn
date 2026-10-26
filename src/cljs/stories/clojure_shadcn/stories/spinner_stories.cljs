(ns clojure-shadcn.stories.spinner-stories
  "Storybook stories for the Spinner component. Ported from mateuszmazurczak.portfolio.ui-components.spinner."
  (:require
   [clojure-shadcn.stories.helpers                     :as helpers]
   [clojure-shadcn.stories.spinner.playground          :as example-playground]
   [clojure-shadcn.stories.spinner.spinner-badge       :as example-spinner-badge]
   [clojure-shadcn.stories.spinner.spinner-button      :as example-spinner-button]
   [clojure-shadcn.stories.spinner.spinner-color       :as example-spinner-color]
   [clojure-shadcn.stories.spinner.spinner-demo        :as example-spinner-demo]
   [clojure-shadcn.stories.spinner.spinner-empty       :as example-spinner-empty]
   [clojure-shadcn.stories.spinner.spinner-input-group :as example-spinner-input-group]
   [clojure-shadcn.stories.spinner.spinner-item        :as example-spinner-item]
   [clojure-shadcn.stories.spinner.spinner-rtl         :as example-spinner-rtl]
   [clojure-shadcn.stories.spinner.spinner-size        :as example-spinner-size]
   [reagent.core                                       :as r])
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

(defstory Playground
          "Interactive spinner playground."
          {:args {:class "size-4"}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "select"}
                               :options ["size-3" "size-4" "size-6" "size-8"]}}
           :parameters {:playground {:description
                                     "Explore spinner css classes with a live preview."}
                        :controls {:expanded false}}
           :source-code (embed-source "clojure-shadcn.stories.spinner.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory
 SpinnerSize
 "Spinner size variants using class overrides.

  Icon: lucide-react Loader2

  Adjust size via Tailwind size classes."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-size")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-size/example])))

(defstory
 SpinnerButton
 "Spinner inside disabled buttons.

  Icon: lucide-react Loader2

  Combine with buttons to show in-progress actions."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-button")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-button/example])))

(defstory
 SpinnerBadge
 "Spinner embedded in badges.

  Icon: lucide-react Loader2

  Useful for background status updates."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-badge")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-badge/example])))

(defstory
 SpinnerColor
 "Spinner color variations.

  Icon: lucide-react Loader2

  Color via text utility classes."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-color")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-color/example])))

(defstory
 SpinnerDemo
 "Spinner inside list item layout.

  Icon: lucide-react Loader2

  Demonstrates inline usage in a row layout."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-demo")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-demo/example])))

(defstory
 SpinnerItem
 "Spinner used as Item media, with status semantics and text owned by the composed item rather than the decorative icon."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-item")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-item/example])))

(defstory SpinnerInputGroup
          "Input Group — upstream loading field pairs a visual spinner with explicit status text."
          {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-input-group")}
          []
          (r/as-element (helpers/wrap-component [example-spinner-input-group/example])))

(defstory
 SpinnerEmpty
 "Empty — upstream loading empty-state composition uses explicit copy for the accessible status."
 {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-empty")}
 []
 (r/as-element (helpers/wrap-component [example-spinner-empty/example])))

(defstory SpinnerRTL
          "RTL — visual spinner accompanies explicit right-to-left loading text."
          {:source-code (embed-source "clojure-shadcn.stories.spinner.spinner-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-spinner-rtl/example])))
