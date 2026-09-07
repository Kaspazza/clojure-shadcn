(ns clojure-shadcn.stories.calendar-stories
  (:require
   ["date-fns/locale"                     :refer [arSA]]
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.calendar :as sut]
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
       "All remaining normalized props are forwarded, including :mode, :selected, :on-select, :disabled, and :number-of-months. Use the value shapes required by the selected DayPicker mode."}]}])))

(defn- single-date-example
  []
  (r/with-let [selected (r/atom nil)]
              [sut/calendar {:mode "single"
                             :selected @selected
                             :on-select #(reset! selected %)}]))

(defstory SingleDate [] (r/as-element (helpers/wrap-component [single-date-example])))

(defn- date-range-example
  []
  (r/with-let [selected (r/atom nil)]
              [sut/calendar {:mode "range"
                             :number-of-months 2
                             :selected @selected
                             :on-select #(reset! selected %)}]))

(defstory DateRange [] (r/as-element (helpers/wrap-component [date-range-example])))


(defstory CalendarPlayground
          "Interactive calendar playground."
          {:args {:show-outside-days true
                  :caption-layout "label"
                  :number-of-months 1}
           :arg-types {:show-outside-days {:control {:type "boolean"}}
                       :caption-layout {:control {:type "select"}
                                        :options ["label" "dropdown"]}
                       :number-of-months {:control {:type "select"}
                                          :options [1 2]}}
           :parameters {:controls {:exclude ["selected" "on-select" "class" "class-names"]}}
           :decode-args (fn [{:keys [caption-layout]
                              :as args}]
                          (update args :caption-layout keyword))}
          [args]
          (r/as-element (helpers/wrap-component [sut/calendar (assoc args :mode "single")])))

(defn- calendar-patterns-example
  []
  (r/with-let
   [selected (r/atom (js/Date. 2026 2 12)) time (r/atom "09:00")]
   [:div {:class "grid gap-8 lg:grid-cols-2"}
    [:section {:class "space-y-3"}
     [:h3 {:class "font-medium"}
      "Presets and appointment time"]
     [:div {:class "flex flex-wrap gap-2"}
      [button/button {:variant :outline
                      :size :sm
                      :on-click #(reset! selected (js/Date.))}
       "Today"]
      [button/button {:variant :outline
                      :size :sm
                      :on-click #(reset! selected (js/Date. 2026 2 20))}
       "March 20"]]
     [sut/calendar {:mode "single"
                    :selected @selected
                    :on-select #(when % (reset! selected %))
                    :disabled #js [(js/Date. 2026 2 14) (js/Date. 2026 2 18)]
                    :show-week-number true}]
     [:label {:class "grid gap-1 text-sm"}
      "Time"
      [:input {:type "time"
               :value @time
               :on-change #(reset! time (.. % -target -value))
               :class "h-9 rounded-md border bg-background px-3"}]]
     [:p {:class "text-sm text-muted-foreground"}
      "Booked dates are disabled; time remains a separate native control."]]
    [:section {:class "space-y-3"
               :dir "rtl"}
     [:h3 {:class "font-medium"}
      "Arabic locale, RTL, fixed time zone"]
     [sut/calendar {:mode "single"
                    :locale arSA
                    :dir "rtl"
                    :time-zone "Asia/Riyadh"
                    :show-week-number true}]
     [:p {:class "text-sm text-muted-foreground"}
      "Locale, direction, week numbers, and IANA time zone are forwarded to React DayPicker."]]]))

(defstory
 CalendarPatterns
 "Presets, separate date/time controls, booked dates, week numbers, timezone, and RTL locale using React DayPicker props."
 []
 (r/as-element (helpers/wrap-component [calendar-patterns-example])))
