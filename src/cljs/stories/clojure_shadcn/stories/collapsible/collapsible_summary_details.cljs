(ns clojure-shadcn.stories.collapsible.collapsible-summary-details
  (:require
   [clojure-shadcn.ui.components.collapsible :as collapsible]))

(defn example
  []
  [:div {:class "grid max-w-2xl gap-6 md:grid-cols-2"}
   [:details {:class "rounded-lg border p-4"}
    [:summary {:class "cursor-pointer font-medium"}
     "Native details"]
    [:p {:class "pt-3 text-sm text-muted-foreground"}
     "Use when native disclosure semantics and minimal styling are sufficient."]]
   [collapsible/collapsible {:default-open true
                             :class "rounded-lg border p-4"}
    [collapsible/collapsible-trigger {:class "flex w-full items-center justify-between font-medium"}
     "Radix Collapsible"
     [:span {:aria-hidden true}
      "+"]]
    [collapsible/collapsible-content {:class "pt-3 text-sm text-muted-foreground"}
     "Use when controlled state and Radix behavior are required."]]])
