(ns clojure-shadcn.stories.sheet.playground
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  ([]
   (example ((fn [{:keys [side]
                   :as args}]
               (cond-> args
                 side (update :side keyword)))
             {:side "right"})))
  ([args]
   [:div {:class "p-6"}
    [sheet/sheet {}
     [sheet/sheet-trigger {:as-child true}
      [button/button {:variant :outline}
       "Open sheet"]]
     [sheet/sheet-content
      (select-keys args [:side])
      [sheet/sheet-header {}
       [sheet/sheet-title {}
        "Edit profile"]
       [sheet/sheet-description {}
        "Make changes to your profile here."]]]]]))
