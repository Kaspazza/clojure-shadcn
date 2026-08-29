(ns clojure-shadcn.stories.calendar-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.calendar :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Calendar"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Accessible date and range picker."
     :npm-install "npm install react-day-picker date-fns"
     :source-code (embed-source "clojure-shadcn.ui.components.calendar")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/calendar.cljs"
     :filename "calendar.cljs"}]))

(defn- single-date-example []
  (r/with-let [selected (r/atom nil)]
    [sut/calendar {:mode "single"
                   :selected @selected
                   :on-select #(reset! selected %)}]))

(defstory SingleDate
  []
  (r/as-element
   (helpers/wrap-component
    [single-date-example])))

(defn- date-range-example []
  (r/with-let [selected (r/atom nil)]
    [sut/calendar {:mode "range"
                   :number-of-months 2
                   :selected @selected
                   :on-select #(reset! selected %)}]))

(defstory DateRange
  []
  (r/as-element
   (helpers/wrap-component
    [date-range-example])))
