(ns clojure-shadcn.stories.spinner.spinner-input-group
  (:require
   [clojure-shadcn.ui.components.input-group :as input-group]
   [clojure-shadcn.ui.components.spinner     :as spinner]))

(defn example
  []
  [:div {:role "status"
         :class "max-w-sm"}
   [input-group/input-group {}
    [input-group/input-group-addon {}
     [spinner/spinner {}]]
    [input-group/input-group-input {:disabled true
                                    :value "Searching repositories…"
                                    :aria-label "Repository search"}]]
   [:span {:class "sr-only"}
    "Searching repositories"]])
