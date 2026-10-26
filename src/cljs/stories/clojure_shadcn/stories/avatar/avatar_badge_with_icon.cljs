(ns clojure-shadcn.stories.avatar.avatar-badge-with-icon
  (:require
   ["lucide-react"                      :refer [Plus]]
   [clojure-shadcn.ui.components.avatar :as avatar]))

(defn example
  []
  [:div {:class "p-6"}
   [avatar/avatar {:class "grayscale"}
    [avatar/avatar-image {:src "https://github.com/pranathip.png"
                          :alt "@pranathip"}]
    [avatar/avatar-fallback {}
     "PP"]
    [avatar/avatar-badge {}
     [:> Plus]]]])
