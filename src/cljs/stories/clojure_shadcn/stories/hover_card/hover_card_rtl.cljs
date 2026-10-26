(ns clojure-shadcn.stories.hover-card.hover-card-rtl
  (:require
   [clojure-shadcn.ui.components.hover-card :as hover-card]))

(defn example
  []
  [:div {:class "p-16 text-right"
         :dir "rtl"}
   [hover-card/hover-card {}
    [hover-card/hover-card-trigger {:as-child true}
     [:a {:href "https://github.com/shadcn-ui/ui"
          :class "font-medium underline underline-offset-4"}
      "معاينة المشروع"]]
    [hover-card/hover-card-content {:align :start
                                    :dir "rtl"}
     [:div {:class "space-y-2 text-right"}
      [:h4 {:class "font-semibold"}
       "واجهة المستخدم"]
      [:p {:class "text-sm text-muted-foreground"}
       "مكوّنات مصممة بعناية يمكنك نسخها وتخصيصها."]]]]])
