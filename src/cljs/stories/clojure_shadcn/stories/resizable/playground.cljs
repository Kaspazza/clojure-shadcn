(ns clojure-shadcn.stories.resizable.playground
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  ([]
   (example ((fn [{:keys [orientation]
                   :as args}]
               (cond-> args
                 orientation (update :orientation keyword)))
             {:orientation "horizontal"})))
  ([args]
   [:div {:class "h-48 w-[600px] overflow-hidden rounded-lg border"}
    [resizable/panel-group
     (select-keys args [:orientation])
     [resizable/panel {:defaultSize 40}
      [:div {:class "grid h-full place-items-center"}
       "Navigation"]]
     [resizable/handle {:with-handle? true}]
     [resizable/panel {:defaultSize 60}
      [:div {:class "grid h-full place-items-center"}
       "Content"]]]]))
