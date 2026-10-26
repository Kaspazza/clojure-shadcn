(ns clojure-shadcn.stories.separator.playground
  (:require
   [clojure-shadcn.ui.components.separator :as separator]))

(defn example
  ([]
   (example ((fn [{:keys [orientation]
                   :as args}]
               (cond-> args
                 orientation (update :orientation keyword)))
             {:orientation "horizontal"
              :decorative true})))
  ([args]
   [:div {:class "flex h-16 items-center gap-4"}
    [:span "First"]
    [separator/separator
     (assoc (select-keys args [:orientation :decorative])
            :class
            "data-[orientation=vertical]:h-full")]
    [:span "Second"]]))
