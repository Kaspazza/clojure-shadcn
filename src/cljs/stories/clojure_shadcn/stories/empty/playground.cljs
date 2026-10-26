(ns clojure-shadcn.stories.empty.playground
  (:require
   ["lucide-react"                      :refer [Inbox]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  ([]
   (example {:media-variant :icon
             :title "No Projects Yet"
             :description "You haven't created any projects yet."
             :action-label "Create Project"}))
  ([args]
   [:div {:class "p-6"}
    [empty/empty {}
     [empty/empty-header {}
      [empty/empty-media {:variant (:media-variant args)}
       [:> Inbox]]
      [empty/empty-title {}
       (:title args)]
      [empty/empty-description {}
       (:description args)]]
     [empty/empty-content {}
      [button/button {}
       (:action-label args)]]]]))
