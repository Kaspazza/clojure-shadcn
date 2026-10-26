(ns clojure-shadcn.stories.drawer.responsive-drawer-dialog
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)
           desktop? (r/atom false)
           media-query (atom nil)
           update-layout! (fn* [%1] (reset! desktop? (.-matches %1)))]
       (r/create-class
        {:display-name "responsive-drawer-dialog-story"
         :component-did-mount (fn []
                                (let [query (js/window.matchMedia "(min-width: 768px)")]
                                  (reset! media-query query)
                                  (reset! desktop? (.-matches query))
                                  (.addEventListener query "change" update-layout!)))
         :component-will-unmount (fn []
                                   (when-let [query @media-query]
                                     (.removeEventListener query "change" update-layout!)))
         :reagent-render (fn [] [:div {:class "p-4"}
                                 (if @desktop?
                                   [dialog/dialog {:open @open?
                                                   :on-open-change (fn* [%1] (reset! open? %1))}
                                    [dialog/dialog-trigger {:as-child true}
                                     (button/button {:variant :outline} "Edit profile")]
                                    [dialog/dialog-content {}
                                     [dialog/dialog-header {}
                                      [dialog/dialog-title {}
                                       "Edit profile"]
                                      [dialog/dialog-description {}
                                       "Desktop uses Radix Dialog; mobile uses Vaul."]]
                                     [dialog/dialog-footer {}
                                      [dialog/dialog-close {:as-child true}
                                       (button/button {:variant :outline} "Close")]]]]
                                   [drawer/drawer {:open @open?
                                                   :on-open-change (fn* [%1] (reset! open? %1))}
                                    [drawer/drawer-trigger {:as-child true}
                                     (button/button {:variant :outline} "Edit profile")]
                                    [drawer/drawer-content {}
                                     [drawer/drawer-header {}
                                      [drawer/drawer-title {}
                                       "Edit profile"]
                                      [drawer/drawer-description {}
                                       "Desktop uses Radix Dialog; mobile uses Vaul."]]
                                     [drawer/drawer-footer {}
                                      [drawer/drawer-close {:as-child true}
                                       (button/button {:variant :outline} "Close")]]]])])})))])
