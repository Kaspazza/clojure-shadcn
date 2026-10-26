(ns clojure-shadcn.stories.alert.alert-action
  (:require
   [clojure-shadcn.ui.components.alert :as alert]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [alert/alert {:class "max-w-md"}
   [alert/alert-title {} "Dark mode is now available"]
   [alert/alert-description {} "Enable it under your profile settings to get started."]
   [alert/alert-action {}
    [button/button {:variant :default :size :xs} "Enable"]]])
