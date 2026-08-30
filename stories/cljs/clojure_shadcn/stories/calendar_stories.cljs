(ns clojure-shadcn.stories.calendar-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.calendar :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Calendar"
       :parameters #js {:layout "centered"}})

(defdoc Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Accessible date and range picker."
     :npm-install "npm install react-day-picker date-fns"
     :source-code (embed-source "clojure-shadcn.ui.components.calendar")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/calendar.cljs"
     :filename "calendar.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
   (helpers/wrap-component
    [helpers/api-component-card
     {:component-name "calendar"
      :link {:href "https://daypicker.dev/api/classes/DayPicker" :label "React DayPicker Docs"}
      :description "Styled react-day-picker DayPicker. Selection values and callbacks use JavaScript Date values at the component boundary; DayPicker supplies calendar semantics and keyboard navigation."
      :props [{:name ":show-outside-days" :type "boolean" :default "true" :description "Shows days belonging to adjacent months."}
              {:name ":caption-layout" :type "keyword | string" :default ":label" :description "DayPicker caption layout; converted to its string form."}
              {:name ":class-names" :type "map" :default nil :description "Shallow overrides merged over DayPicker's defaults and this component's complete style map."}
              {:name ":class" :type "string" :default nil :description "Classes merged onto the DayPicker root."}
              {:name "DayPicker props" :type "map entries" :default nil :description "All remaining normalized props are forwarded, including :mode, :selected, :on-select, :disabled, and :number-of-months. Use the value shapes required by the selected DayPicker mode."}]}])))

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
