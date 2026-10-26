(ns clojure-shadcn.stories.progress.progress-complete
  (:require
   [clojure-shadcn.ui.components.progress :as progress]))

(defn example
  []
  [:div {:class "w-[420px] p-6"}
   [progress/progress {:value 100
                       :aria-label "Upload complete"}]])
