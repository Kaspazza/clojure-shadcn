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
 AspectRatioBasic
 "Interactive aspect-ratio playground."
 {:args {:ratio 1.7778}
  :arg-types {:ratio {:control {:type "number"}
                      :min 0.5
                      :max 3
                      :step 0.1}}
  :parameters {:controls {:exclude ["class"]}}}
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
