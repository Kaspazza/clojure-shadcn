(ns clojure-shadcn.stories.dialog.dialog-scrollable-body
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]))

(defn example
  []
  [:div {:class "p-6"}
   [dialog/dialog {}
    [dialog/dialog-trigger {:as-child true}
     (button/button {:variant :outline} "Review changelog")]
    [dialog/dialog-content {:class
                            "max-h-[80vh] grid-rows-[auto_minmax(0,1fr)_auto] overflow-hidden p-0"}
     [dialog/dialog-header {:class "border-b p-6"}
      [dialog/dialog-title {}
       "Release changelog"]
      [dialog/dialog-description {}
       "Review all changes before publishing."]]
     [:div {:class "overflow-y-auto px-6"}
      (for [section (range 1 16)]
        ^{:key section}
        [:p {:class "mb-4 text-sm text-muted-foreground"}
         (str "Change " section " improves reliability, accessibility, and feedback.")])]
     [dialog/dialog-footer {:class "border-t bg-background p-6"}
      [dialog/dialog-close {:as-child true}
       (button/button {:variant :outline} "Cancel review")]
      (button/button {} "Publish release")]]]])
