(ns clojure-shadcn.stories.avatar.playground
  (:require
   [clojure-shadcn.ui.components.avatar :as avatar]))

(defn example
  ([]
   (example {:size :default
             :alt "Shadcn"
             :fallback "CN"}))
  ([args]
  [:div {:class "p-6"}
   [avatar/avatar
    (select-keys args [:size])
    [avatar/avatar-image {:src "https://github.com/shadcn.png"
                          :alt (:alt args)}]
    [avatar/avatar-fallback {}
     (:fallback args)]]]))
