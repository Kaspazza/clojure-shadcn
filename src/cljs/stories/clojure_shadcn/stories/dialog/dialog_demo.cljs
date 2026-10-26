(ns clojure-shadcn.stories.dialog.dialog-demo
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]
   [clojure-shadcn.ui.components.input  :as input]
   [clojure-shadcn.ui.components.label  :as label]))

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
  [:div {:class "p-6"}
   [dialog/dialog {}
    [dialog/dialog-trigger {:as-child true}
     (button/button {:variant :outline} "Open Dialog")]
    [dialog/dialog-content {:class "sm:max-w-[425px]"}
     [dialog/dialog-header {}
      [dialog/dialog-title {}
       "Edit profile"]
      [dialog/dialog-description {}
       "Make changes to your profile here. Click save when you're done."]]
     [profile-form {}]
     [dialog/dialog-footer {}
      [dialog/dialog-close {:as-child true}
       (button/button {:variant :outline} "Cancel")]
      (button/button {:type "submit"} "Save changes")]]]])
