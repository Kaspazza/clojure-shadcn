(ns clojure-shadcn.stories.empty.empty-avatar-group
  (:require
   ["lucide-react"                      :refer [Plus]]
   [clojure-shadcn.ui.components.avatar :as avatar]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:class "p-6"}
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {}
      [:div {:class "flex -space-x-2"}
       [avatar/avatar {:size :lg
                       :class "ring-2 ring-background grayscale"}
        [avatar/avatar-image {:src "https://github.com/shadcn.png"
                              :alt "@shadcn"}]
        [avatar/avatar-fallback {}
         "CN"]]
       [avatar/avatar {:size :lg
                       :class "ring-2 ring-background grayscale"}
        [avatar/avatar-image {:src "https://github.com/maxleiter.png"
                              :alt "@maxleiter"}]
        [avatar/avatar-fallback {}
         "LR"]]
       [avatar/avatar {:size :lg
                       :class "ring-2 ring-background grayscale"}
        [avatar/avatar-image {:src "https://github.com/evilrabbit.png"
                              :alt "@evilrabbit"}]
        [avatar/avatar-fallback {}
         "ER"]]]]
     [empty/empty-title {}
      "No Team Members"]
     [empty/empty-description {}
      "Invite your team to collaborate on this project."]]
    [empty/empty-content {}
     (button/button {:size :sm} [:> Plus] "Invite Members")]]])
