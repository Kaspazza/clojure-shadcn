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

(defstory ApiReference []
  (r/as-element (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card {:component-name "direction-provider" :link {:href "https://www.radix-ui.com/primitives/docs/utilities/direction-provider" :label "Radix Direction Provider Docs"} :description "Supplies writing direction to descendant Radix primitives. It is a context provider, not a DOM element; set a matching HTML dir attribute where native text/layout direction is also required." :props [{:name ":direction / :dir" :type ":ltr | :rtl" :default ":ltr" :description ":direction takes precedence; the selected value is converted to a string."} {:name "additional props" :type "map entries" :default nil :description "Forwarded to Radix DirectionProvider."}]}]
     [helpers/api-component-card {:component-name "use-direction" :description "React hook returning the resolved direction as :ltr or :rtl. Call only from a Reagent/React function component and obey the Rules of Hooks." :props [{:name "direction" :type "keyword, optional positional argument" :default nil :description "Local direction passed to Radix useDirection; without it, reads context or Radix's default."}]}]])))

(defstory RightToLeft
  []
  (r/as-element
   (helpers/wrap-component
    [sut/direction-provider {:direction :rtl}
     [:div {:dir "rtl" :class "w-80 rounded-lg border p-6 text-right"}
      [:h3 {:class "font-semibold"} "واجهة من اليمين إلى اليسار"]
      [:p {:class "text-muted-foreground"}
       "يحافظ المزود على اتجاه مكونات Radix المتداخلة."]]])))
