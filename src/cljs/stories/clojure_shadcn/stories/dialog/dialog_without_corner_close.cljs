(ns clojure-shadcn.stories.dialog.dialog-without-corner-close
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]))

(defn example
  []
  [:div {:class "p-6"}
   [dialog/dialog {}
    [dialog/dialog-trigger {:as-child true}
     (button/button {:variant :outline} "Review invitation")]
    [dialog/dialog-content {:show-close-button false}
     [dialog/dialog-header {}
      [dialog/dialog-title {}
       "Team invitation"]
      [dialog/dialog-description {}
       "Review the invitation before returning to your workspace."]]
     [dialog/dialog-footer {}
      [dialog/dialog-close {:as-child true}
       (button/button {:variant :outline} "Close invitation")]]]]])
