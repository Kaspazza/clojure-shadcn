(ns clojure-shadcn.stories.avatar.avatar-custom-sizes
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]))

(defn example
  []
  [:div {:class "p-6 flex items-center gap-4"}
   [avatar/avatar {:size :sm}
    [avatar/avatar-fallback {}
     "SM"]]
   [avatar/avatar {:size :default}
    [avatar/avatar-fallback {}
     "MD"]]
   [avatar/avatar {:size :lg}
    [avatar/avatar-fallback {}
     "LG"]]])
