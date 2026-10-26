(ns clojure-shadcn.stories.kbd.kbd-tooltip
  (:require
   ["lucide-react"                       :refer [Command]]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.kbd     :as kbd]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn example
  []
  [:>
   tooltip/tooltip-provider-component
   {}
   [tooltip/tooltip {:trigger (button/button {:variant :outline} "Save")
                     :trigger-as-child? true
                     :content [kbd/kbd-group {}
                               [kbd/kbd {}
                                [:> Command]]
                               [kbd/kbd {}
                                "S"]]}]])
