(ns clojure-shadcn.stories.separator.separator-responsive-menu
  (:require
   [clojure-shadcn.ui.components.separator :as separator]))

(defn example
  []
  [:nav
   {:aria-label "Account sections"
    :class
    "flex flex-col rounded-lg border p-4 sm:h-6 sm:flex-row sm:items-center sm:gap-4 sm:border-0 sm:p-0"}
   [:a {:href "#profile"
        :class "py-2 text-sm font-medium sm:py-0"}
    "Profile"]
   [separator/separator {:class "sm:hidden"}]
   [separator/separator {:orientation :vertical
                         :class "hidden sm:block sm:h-full"}]
   [:a {:href "#billing"
        :class "py-2 text-sm font-medium sm:py-0"}
    "Billing"]
   [separator/separator {:class "sm:hidden"}]
   [separator/separator {:orientation :vertical
                         :class "hidden sm:block sm:h-full"}]
   [:a {:href "#team"
        :class "py-2 text-sm font-medium sm:py-0"}
    "Team"]])
