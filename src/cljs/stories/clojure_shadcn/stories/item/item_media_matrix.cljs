(ns clojure-shadcn.stories.item.item-media-matrix
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]
   [clojure-shadcn.ui.components.item   :as item]))

(defn example
  []
  [item/item-group {:class "max-w-2xl"}
   [item/item {:variant :outline}
    [item/item-media {}
     [avatar/avatar {}
      [avatar/avatar-image {:src "https://github.com/shadcn.png"
                            :alt "@shadcn"}]
      [avatar/avatar-fallback {}
       "CN"]]]
    [item/item-content {}
     [item/item-title {}
      "shadcn"]
     [item/item-description {}
      "Updated the component registry."]]]
   [item/item-separator {}]
   [item/item {:variant :outline}
    [item/item-media {:variant :image}
     [:img
      {:src
       "https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=200&q=80"
       :alt "Modern office"
       :class "size-full object-cover"}]]
    [item/item-content {}
     [item/item-title {}
      "Workspace tour"]
     [item/item-description {}
      "See the new collaboration space."]]]])
