(ns clojure-shadcn.stories.dialog.responsive-dialog-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [clojure-shadcn.ui.components.input  :as input]
   [clojure-shadcn.ui.components.label  :as label]
   [reagent.core                        :as r]))

(defn- profile-form
  [{:keys [class]}]
  [:div {:class (str "grid gap-4 " class)}
   [:div {:class "grid gap-2"}
    [label/label {:html-for "dialog-name"}
     "Name"]
    [input/input {:id "dialog-name"
                  :default-value "Pedro Duarte"}]]
   [:div {:class "grid gap-2"}
    [label/label {:html-for "dialog-username"}
     "Username"]
    [input/input {:id "dialog-username"
                  :default-value "@peduarte"}]]])

(defn example
  []
  [(fn []
     (let [open? (r/atom false)
           desktop? (r/atom false)
           media-query (atom nil)
           update-layout! (fn* [%1] (reset! desktop? (.-matches %1)))]
       (r/create-class
        {:display-name "responsive-dialog-drawer-story"
         :component-did-mount (fn []
                                (let [query (js/window.matchMedia "(min-width: 768px)")]
                                  (reset! media-query query)
                                  (reset! desktop? (.-matches query))
                                  (.addEventListener query "change" update-layout!)))
         :component-will-unmount (fn []
                                   (when-let [query @media-query]
                                     (.removeEventListener query "change" update-layout!)))
         :reagent-render
         (fn [] [:div {:class "p-6"}
                 (if @desktop?
                   [dialog/dialog {:open @open?
                                   :on-open-change (fn* [%1] (reset! open? %1))}
                    [dialog/dialog-trigger {:as-child true}
                     (button/button {:variant :outline} "Edit profile")]
                    [dialog/dialog-content {:class "sm:max-w-[425px]"}
                     [dialog/dialog-header {}
                      [dialog/dialog-title {}
                       "Edit profile"]
                      [dialog/dialog-description {}
                       "Make changes to your profile and save when finished."]]
                     [profile-form {}]
                     [dialog/dialog-footer {}
                      [dialog/dialog-close {:as-child true}
                       (button/button {:variant :outline} "Cancel")]
                      (button/button {:on-click (fn* [] (reset! open? false))} "Save changes")]]]
                   [drawer/drawer {:open @open?
                                   :on-open-change (fn* [%1] (reset! open? %1))
                                   :direction :bottom}
                    [drawer/drawer-trigger {:as-child true}
                     (button/button {:variant :outline} "Edit profile")]
                    [drawer/drawer-content {}
                     [drawer/drawer-header {:class "text-left"}
                      [drawer/drawer-title {}
                       "Edit profile"]
                      [drawer/drawer-description {}
                       "Make changes to your profile and save when finished."]]
                     [profile-form {:class "px-4"}]
                     [drawer/drawer-footer {}
                      (button/button {:on-click (fn* [] (reset! open? false))} "Save changes")
                      [drawer/drawer-close {:as-child true}
                       (button/button {:variant :outline} "Cancel")]]]])])})))])
