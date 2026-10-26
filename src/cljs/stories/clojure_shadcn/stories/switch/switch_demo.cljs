(ns clojure-shadcn.stories.switch.switch-demo
  (:require
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.switch :as switch]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex items-center gap-2"}
    [switch/switch {:id "airplane-mode"}]
    [label/label {:html-for "airplane-mode"}
     "Airplane Mode"]]])
