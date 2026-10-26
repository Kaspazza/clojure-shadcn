(ns clojure-shadcn.stories.empty.empty-canonical-actions
  (:require
   ["lucide-react"                      :refer [ArrowUpRight Inbox Plus]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:class "grid gap-8 lg:grid-cols-2"}
   [empty/empty {:class "border"}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Inbox]]
     [empty/empty-title {}
      "No projects yet"]
     [empty/empty-description {}
      "Create a project or import one from another workspace."]]
    [empty/empty-content {:class "flex-row"}
     (button/button {} [:> Plus] "Create project")
     (button/button {:variant :outline} "Import")]]
   [empty/empty {:class "border"}
    [empty/empty-header {}
     [empty/empty-title {}
      "Nothing saved"]
     [empty/empty-description {}
      "Bookmarks you save will appear here. "
      [:a {:href "#learn"}
       "Learn how bookmarks work"]]]
    [empty/empty-content {}
     (button/button {:variant :outline} [:> ArrowUpRight] "Browse articles")]]])
