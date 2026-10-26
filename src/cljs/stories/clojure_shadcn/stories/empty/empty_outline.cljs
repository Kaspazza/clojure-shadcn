(ns clojure-shadcn.stories.empty.empty-outline
  (:require
   ["lucide-react"                      :refer [Bell]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:class "p-6"}
   [empty/empty {:class "border border-dashed"}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Bell]]
     [empty/empty-title {}
      "Cloud Storage Empty"]
     [empty/empty-description {}
      "Upload files to your cloud storage to access them anywhere."]]
    [empty/empty-content {}
     (button/button {:variant :outline
                     :size :sm}
                    "Upload Files")]]])
