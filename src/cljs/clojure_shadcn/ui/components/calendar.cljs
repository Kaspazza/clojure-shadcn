(ns clojure-shadcn.ui.components.calendar
  "Accessible calendar backed by react-day-picker. Dates remain JavaScript Date values at the boundary."
  (:require
   ["react-day-picker" :refer [DayPicker getDefaultClassNames]]
   [clojure-shadcn.utils.props :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn calendar [{:keys [class class-names show-outside-days caption-layout] :or {show-outside-days true caption-layout :label} :as raw-props}]
  (let [props (normalize-props raw-props)
        defaults (js->clj (getDefaultClassNames) :keywordize-keys true)
        classes (merge defaults
                       {:root "w-fit"
                        :months "relative flex flex-col gap-4 md:flex-row"
                        :month "flex w-full flex-col gap-4"
                        :nav "absolute inset-x-0 top-0 flex w-full items-center justify-between"
                        :button_previous "size-8 rounded-md p-0 hover:bg-accent aria-disabled:opacity-50"
                        :button_next "size-8 rounded-md p-0 hover:bg-accent aria-disabled:opacity-50"
                        :month_caption "flex h-8 w-full items-center justify-center px-8"
                        :caption_label "text-sm font-medium"
                        :month_grid "w-full border-collapse"
                        :weekdays "flex"
                        :weekday "flex-1 rounded-md text-[0.8rem] font-normal text-muted-foreground"
                        :week "mt-2 flex w-full"
                        :day "relative size-8 p-0 text-center"
                        :day_button "size-8 rounded-md text-sm hover:bg-accent focus-visible:ring-2 focus-visible:ring-ring aria-selected:bg-primary aria-selected:text-primary-foreground"
                        :range_start "rounded-l-md bg-accent"
                        :range_middle "rounded-none bg-accent"
                        :range_end "rounded-r-md bg-accent"
                        :today "rounded-md bg-accent text-accent-foreground"
                        :outside "text-muted-foreground opacity-50"
                        :disabled "text-muted-foreground opacity-50"
                        :hidden "invisible"}
                       class-names)]
    [:> DayPicker (-> props
                      (assoc :data-slot "calendar" :showOutsideDays show-outside-days
                             :captionLayout (name caption-layout)
                             :className (merge-classes "group/calendar bg-background p-3 [--rdp-day-width:2rem] [--rdp-day-height:2rem]" class)
                             :classNames (clj->js classes))
                      (dissoc :class :class-name :class-names :show-outside-days :caption-layout))]))
