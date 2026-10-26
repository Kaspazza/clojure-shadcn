(ns clojure-shadcn.stories.skeleton.skeleton-form
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [:div {:class "w-full max-w-md space-y-5 rounded-lg border p-6"
         :aria-busy true
         :aria-label "Loading form"}
   (for [width ["w-24" "w-32" "w-20"]]
     ^{:key width}
     [:div {:class "space-y-2"}
      [skeleton/skeleton {:class (str "h-4 " width)}]
      [skeleton/skeleton {:class "h-9 w-full"}]])
   [skeleton/skeleton {:class "h-9 w-28"}]])
