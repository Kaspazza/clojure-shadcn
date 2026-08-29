(ns clojure-shadcn.stories.direction-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.direction :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Direction"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Writing-direction context for Radix primitives."
     :npm-install "npm install @radix-ui/react-direction"
     :source-code (embed-source "clojure-shadcn.ui.components.direction")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/direction.cljs"
     :filename "direction.cljs"}]))

(defstory RightToLeft
  []
  (r/as-element
   (helpers/wrap-component
    [sut/direction-provider {:direction :rtl}
     [:div {:dir "rtl" :class "w-80 rounded-lg border p-6 text-right"}
      [:h3 {:class "font-semibold"} "واجهة من اليمين إلى اليسار"]
      [:p {:class "text-muted-foreground"}
       "يحافظ المزود على اتجاه مكونات Radix المتداخلة."]]])))
