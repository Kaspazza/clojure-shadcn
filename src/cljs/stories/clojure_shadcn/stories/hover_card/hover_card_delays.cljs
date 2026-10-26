(ns clojure-shadcn.stories.hover-card.hover-card-delays
  (:require
   [clojure-shadcn.ui.components.hover-card :as hover-card]))

(defn- project-preview
  []
  [:div {:class "space-y-2"}
   [:h4 {:class "font-semibold"}
    "shadcn/ui"]
   [:p {:class "text-sm text-muted-foreground"}
    "Beautifully designed components you can copy and own."]])

(defn example
  []
  [:div {:class "p-16"}
   [hover-card/hover-card {:open-delay 100
                           :close-delay 600}
    [hover-card/hover-card-trigger {:as-child true}
     [:a {:href "https://github.com/shadcn-ui/ui"
          :class "font-medium underline underline-offset-4"}
      "Preview with custom delays"]]
    [hover-card/hover-card-content {}
     [project-preview]]]])
