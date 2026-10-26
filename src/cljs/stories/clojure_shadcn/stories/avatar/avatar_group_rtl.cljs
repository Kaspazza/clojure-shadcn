(ns clojure-shadcn.stories.avatar.avatar-group-rtl
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]))

(defn example
  []
  [:div {:class "p-6"
         :dir "rtl"}
   [avatar/avatar-group {:aria-label "فريق المشروع"}
    [avatar/avatar {}
     [avatar/avatar-fallback {}
      "س"]]
    [avatar/avatar {}
     [avatar/avatar-fallback {}
      "م"]]
    [avatar/avatar {}
     [avatar/avatar-fallback {}
      "ل"]]
    [avatar/avatar-group-count {:aria-label "ثلاثة أعضاء آخرون"}
     "+٣"]]])
