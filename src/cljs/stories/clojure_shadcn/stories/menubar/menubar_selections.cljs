(ns clojure-shadcn.stories.menubar.menubar-selections
  (:require
   [clojure-shadcn.ui.components.menubar :as menubar]
   [reagent.core                         :as r]))

(defn example
  []
  [(fn []
     (let [bookmarks? (r/atom true)
           profile (r/atom "benoit")]
       (fn [] [menubar/menubar {}
               [menubar/menubar-menu {}
                [menubar/menubar-trigger {}
                 "View"]
                [menubar/menubar-content {}
                 [menubar/menubar-checkbox-item {:checked @bookmarks?
                                                 :on-checked-change #(reset! bookmarks? %)}
                  "Bookmarks bar"]]]
               [menubar/menubar-menu {}
                [menubar/menubar-trigger {}
                 "Profiles"]
                [menubar/menubar-content {}
                 [menubar/menubar-radio-group {:value @profile
                                               :on-value-change #(reset! profile %)}
                  [menubar/menubar-radio-item {:value "andy"}
                   "Andy"]
                  [menubar/menubar-radio-item {:value "benoit"}
                   "Benoit"]]]]])))])
