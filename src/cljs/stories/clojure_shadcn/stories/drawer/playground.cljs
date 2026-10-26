(ns clojure-shadcn.stories.drawer.playground
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]))

(defn example
  ([]
   (example ((fn [{:keys [direction]
                   :as args}]
               (cond-> args
                 direction (update :direction keyword)))
             {:direction "bottom"})))
  ([args]
   [:div {:class "p-6"}
    [drawer/drawer
     (select-keys args [:direction])
     [drawer/drawer-trigger {:as-child true}
      [button/button {:variant :outline}
       "Open drawer"]]
     [drawer/drawer-content {}
      [drawer/drawer-header {}
       [drawer/drawer-title {}
        "Move goal"]
       [drawer/drawer-description {}
        "Set your daily activity goal."]]]]]))
