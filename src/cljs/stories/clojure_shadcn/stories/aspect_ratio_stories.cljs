(ns clojure-shadcn.stories.aspect-ratio-stories
  (:require
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.aspect-ratio :as sut]
   [reagent.core                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Aspect Ratio"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keeps media at a requested width-to-height ratio."
                        :npm-install "npm install @radix-ui/react-aspect-ratio"
                        :source-code (embed-source "clojure-shadcn.ui.components.aspect-ratio")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/aspect_ratio.cljs"
                        :filename "aspect_ratio.cljs"}]))

(defstory ApiReference
          []
          (r/as-element (helpers/wrap-component
                         [helpers/api-component-card
                          {:component-name "aspect-ratio"
                           :link {:href
                                  "https://www.radix-ui.com/primitives/docs/components/aspect-ratio"
                                  :label "Radix Aspect Ratio Docs"}
                           :description "Radix AspectRatio.Root with normalized, forwarded props."
                           :props [{:name ":ratio"
                                    :type "number"
                                    :default "1"
                                    :description "Width divided by height."}
                                   {:name ":class"
                                    :type "string"
                                    :default nil
                                    :description "Merged classes."}]}])))

(defstory
 Playground
 "Interactive aspect-ratio playground."
 {:args {:ratio 1.7778}
  :arg-types {:ratio {:name "Ratio"
                               :control {:type "number"}
                      :min 0.5
                      :max 3
                      :step 0.1}}
  :parameters {:playground {:description "Explore aspect ratio ratio with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}}
 [args]
 (r/as-element
  (helpers/wrap-component
   [:div {:class "w-[450px] p-6"}
    [sut/aspect-ratio {:ratio (or (:ratio args) 1.7778)
                       :class "overflow-hidden rounded-lg bg-muted"}
     [:img
      {:src
       "https://images.unsplash.com/photo-1472214103451-9374bd1c798e?auto=format&fit=crop&w=900&q=80"
       :alt "Green landscape"
       :class "size-full object-cover"}]]])))

(defstory AspectRatioSquare
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "w-64 p-6"}
             [sut/aspect-ratio
              {:ratio 1
               :class "grid place-items-center rounded-lg border bg-muted text-muted-foreground"}
              "1:1"]])))

(defstory
 AspectRatioPortrait
 "Portrait — upstream 3:4 media treatment for posters and profile photography."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "w-64"}
    [sut/aspect-ratio {:ratio 0.75
                       :class "overflow-hidden rounded-xl bg-muted"}
     [:img
      {:src
       "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=600&q=80"
       :alt "Mountain landscape in portrait format"
       :class "size-full object-cover"}]]])))

(defstory
 AspectRatioRTL
 "RTL — aspect-ratio media embedded in a right-to-left content card."
 []
 (r/as-element
  (helpers/wrap-component
   [:figure {:dir "rtl"
             :class "w-80 space-y-2 text-right"}
    [sut/aspect-ratio {:ratio 1.7778
                       :class "overflow-hidden rounded-lg bg-muted"}
     [:img
      {:src
       "https://images.unsplash.com/photo-1472214103451-9374bd1c798e?auto=format&fit=crop&w=700&q=80"
       :alt "منظر طبيعي أخضر"
       :class "size-full object-cover"}]]
    [:figcaption {:class "text-sm text-muted-foreground"}
     "صورة بنسبة عرض إلى ارتفاع 16:9"]])))
