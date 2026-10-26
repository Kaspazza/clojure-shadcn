(ns clojure-shadcn.stories.textarea.playground
  (:require
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  ([]
   (example ((fn [{:keys [auto-size]
                   :as args}]
               (assoc (dissoc args :auto-size) :auto-size? auto-size))
             {:placeholder "Type your message…"
              :default-value ""
              :rows 4
              :disabled false
              :auto-size true})))
  ([args]
   [:div {:class "max-w-sm p-6"}
    [textarea/textarea args]]))
