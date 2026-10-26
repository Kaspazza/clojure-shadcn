(ns clojure-shadcn.stories.progress.progress-basic
  (:require
   [clojure-shadcn.ui.components.progress :as progress]))

(defn example
  []
  [:div {:class "w-[420px] p-6 space-y-2"}
   [:div {:class "text-sm"}
    "Uploading — 60%"]
   [progress/progress {:value 60}]])
