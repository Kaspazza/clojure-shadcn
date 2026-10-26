(ns clojure-shadcn.stories.calendar-stories
  (:require
   [clojure-shadcn.stories.calendar.calendar-patterns :as calendar-patterns]
   [clojure-shadcn.stories.calendar.date-range :as date-range]
   [clojure-shadcn.stories.calendar.playground :as playground]
   [clojure-shadcn.stories.calendar.single-date :as single-date]
   [clojure-shadcn.stories.helpers :as helpers]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Calendar"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Accessible date and range picker."
                        :npm-install "npm install react-day-picker date-fns"
                        :source-code (embed-source "clojure-shadcn.ui.components.calendar")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/calendar.cljs"
                        :filename "calendar.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "calendar"
     :link {:href "https://daypicker.dev/api/classes/DayPicker"
            :label "React DayPicker Docs"}
     :description
     "Styled react-day-picker DayPicker. Selection values and callbacks use JavaScript Date values at the component boundary; DayPicker supplies calendar semantics and keyboard navigation."
     :props
     [{:name ":show-outside-days"
       :type "boolean"
       :default "true"
       :description "Shows days belonging to adjacent months."}
      {:name ":caption-layout"
       :type "keyword | string"
       :default ":label"
       :description "DayPicker caption layout; converted to its string form."}
      {:name ":class-names"
       :type "map"
       :default nil
       :description
       "Shallow overrides merged over DayPicker's defaults and this component's complete style map."}
      {:name ":class"
       :type "string"
       :default nil
       :description "Classes merged onto the DayPicker root."}
      {:name "DayPicker props"
       :type "map entries"
       :default nil
       :description
       "All remaining normalized props are forwarded, including :mode, :selected, :on-select, :disabled, and :number-of-months. Use the value shapes required by the selected DayPicker mode."}]}
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "Hijri calendar limitation"]
     [:p {:class "text-xs text-muted-foreground"}
      "This project uses react-day-picker 10.0.1, whose installed package exposes no Hijri calendar build or Hijri date adapter. date-fns 4.4.0 provides locale text but not Hijri calendar arithmetic, and no Hijri date dependency is installed. A truthful Hijri story therefore cannot be implemented with the existing dependencies; locale and RTL props only localize the Gregorian calendar."]]])))


(defstory SingleDate
          {:source-code (embed-source "clojure-shadcn.stories.calendar.single-date")}
          []
          (r/as-element (helpers/wrap-component [single-date/example])))


(defstory DateRange
          {:source-code (embed-source "clojure-shadcn.stories.calendar.date-range")}
          []
          (r/as-element (helpers/wrap-component [date-range/example])))


(defstory Playground
          "Interactive calendar playground."
          {:source-code (embed-source "clojure-shadcn.stories.calendar.playground")
           :args {:show-outside-days true
                  :caption-layout "label"
                  :number-of-months 1}
           :arg-types {:show-outside-days {:name "Show outside days"
                                           :control {:type "boolean"}}
                       :caption-layout {:name "Caption layout"
                                        :control {:type "select"}
                                        :options ["label" "dropdown"]}
                       :number-of-months {:name "Number of months"
                                          :control {:type "select"}
                                          :options [1 2]}}
           :parameters {:playground {:description "Explore calendar show outside days, caption layout and number of months with a live preview."}
                        :controls {:expanded false
                                   :exclude ["selected" "on-select" "class" "class-names"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))


(defstory
 CalendarPatterns
 "Presets, separate date/time controls, booked dates, week numbers, timezone, and RTL locale using React DayPicker props."
 {:source-code (embed-source "clojure-shadcn.stories.calendar.calendar-patterns")}
 []
 (r/as-element (helpers/wrap-component [calendar-patterns/example])))
