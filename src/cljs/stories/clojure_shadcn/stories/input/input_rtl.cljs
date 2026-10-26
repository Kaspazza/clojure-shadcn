(ns clojure-shadcn.stories.input.input-rtl
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:dir "rtl"
         :class "max-w-sm p-6"}
   [field/field {}
    [field/field-label {:html-for "name-rtl"}
     "الاسم الكامل"]
    [input/input {:id "name-rtl"
                  :placeholder "اكتب اسمك"}]
    [field/field-description {}
     "كما يظهر في هويتك."]]])
