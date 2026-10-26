(ns clojure-shadcn.stories.progress.progress-timed
  (:require
   [clojure-shadcn.ui.components.progress :as progress]
   [reagent.core                          :as r]))

(defn example
  []
  (let [timer-id (atom nil)]
    [(r/create-class
      {:display-name "timed-progress-story"
       :get-initial-state (fn [_] #js {:value 13})
       :component-did-mount (fn [^js component]
                              (reset! timer-id (js/setTimeout #(.setState component #js {:value 66})
                                                              500)))
       :component-will-unmount (fn [_] (when-let [id @timer-id] (js/clearTimeout id)))
       :reagent-render (fn []
                         (let [^js this (r/current-component)
                               value (.. this -state -value)]
                           [:div {:class "w-80 space-y-2"}
                            [:div {:class "flex justify-between text-sm"}
                             [:span "Installing update"]
                             [:span (str value "%")]]
                            [progress/progress {:value value
                                                :aria-label "Installing update"}]]))})]))
