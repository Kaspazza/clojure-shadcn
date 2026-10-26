(ns clojure-shadcn.stories.textarea.textarea-rtl
  (:require
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:dir "rtl"
         :class "max-w-md p-6"}
   [field/field {}
    [field/field-label {:html-for "message-rtl"}
     "رسالتك"]
    [textarea/textarea {:id "message-rtl"
                        :placeholder "اكتب رسالتك هنا"}]
    [field/field-description {}
     "سنرد خلال يوم عمل."]]])
