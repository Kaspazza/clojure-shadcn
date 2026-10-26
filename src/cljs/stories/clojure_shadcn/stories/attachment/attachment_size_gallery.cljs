(ns clojure-shadcn.stories.attachment.attachment-size-gallery
  (:require
   ["lucide-react" :refer [FileText X]]
   [clojure-shadcn.ui.components.attachment :as attachment]))

(defn example []
  [:div {:class "grid max-w-2xl gap-4 sm:grid-cols-3"}
   (for [[size label] [[:default "Default"] [:sm "Small"] [:xs "Extra small"]]]
     ^{:key size}
     [:div {:class "space-y-2"}
      [:p {:class "text-xs font-medium text-muted-foreground"} label]
      [attachment/attachment {:size size}
       [attachment/attachment-media {} [:> FileText]]
       [attachment/attachment-content {}
        [attachment/attachment-title {} "roadmap.pdf"]
        [attachment/attachment-description {} "PDF · 840 KB"]]
       [attachment/attachment-actions {}
        [attachment/attachment-action {:aria-label (str "Remove " label " attachment")} [:> X]]]]])])
