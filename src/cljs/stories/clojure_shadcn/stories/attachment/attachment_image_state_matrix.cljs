(ns clojure-shadcn.stories.attachment.attachment-image-state-matrix
  (:require
   ["lucide-react" :refer [X]]
   [clojure-shadcn.ui.components.attachment :as attachment]))

(defn example []
  [:div {:class "grid gap-4 sm:grid-cols-2 lg:grid-cols-5"}
   (for [state [:idle :uploading :processing :error :done]]
     ^{:key state}
     [attachment/attachment {:state state :orientation :vertical}
      [attachment/attachment-media {:variant :image}
       [:img {:src "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=160&h=160&fit=crop"
              :alt "Mountain landscape"}]]
      [attachment/attachment-content {}
       [attachment/attachment-title {} "landscape.jpg"]
       [attachment/attachment-description {} (name state)]]
      [attachment/attachment-actions {}
       [attachment/attachment-action {:aria-label "Remove landscape.jpg"} [:> X]]]])])
