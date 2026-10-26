(ns clojure-shadcn.stories.avatar.empty-avatar-group
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]))

(defn example
  []
  [:div {:class "p-6"}
   [avatar/avatar-group {}
    [avatar/avatar {:size :lg
                    :class "grayscale"}
     [avatar/avatar-image {:src "https://github.com/shadcn.png"
                           :alt "@shadcn"}]
     [avatar/avatar-fallback {}
      "CN"]]
    [avatar/avatar {:size :lg
                    :class "grayscale"}
     [avatar/avatar-image {:src "https://github.com/maxleiter.png"
                           :alt "@maxleiter"}]
     [avatar/avatar-fallback {}
      "ML"]]
    [avatar/avatar {:size :lg
                    :class "grayscale"}
     [avatar/avatar-image {:src "https://github.com/evilrabbit.png"
                           :alt "@evilrabbit"}]
     [avatar/avatar-fallback {}
      "ER"]]
    [avatar/avatar-group-count {:aria-label "5 more members"}
     "+5"]]])
