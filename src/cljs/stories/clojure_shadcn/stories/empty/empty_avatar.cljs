(ns clojure-shadcn.stories.empty.empty-avatar
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:class "p-6"}
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {:variant :default}
      [avatar/avatar {:size :lg}
       [avatar/avatar-image {:src "https://github.com/shadcn.png"
                             :class "grayscale"}]
       [avatar/avatar-fallback {}
        "LR"]]]
     [empty/empty-title {}
      "User Offline"]
     [empty/empty-description {}
      "This user is currently offline. You can leave a message or try again later."]]
    [empty/empty-content {}
     (button/button {:size :sm} "Leave Message")]]])
