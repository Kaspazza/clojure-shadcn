(ns clojure-shadcn.stories.toggle.toggle-states
  (:require
   ["lucide-react"                      :refer [Bold Italic]]
   [clojure-shadcn.ui.components.toggle :as toggle]))

(defn example
  []
  [:div {:class "flex flex-wrap items-center gap-3 p-6"}
   [toggle/toggle {:default-pressed true
                   :aria-label "Bold enabled"}
    [:> Bold]]
   [toggle/toggle {:disabled true
                   :aria-label "Italic unavailable"}
    [:> Italic]]
   [toggle/toggle {:size :sm
                   :variant :outline}
    "Small"]
   [toggle/toggle {:size :lg
                   :variant :outline}
    "Large"]])
