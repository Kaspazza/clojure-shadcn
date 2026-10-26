(ns clojure-shadcn.stories.field.field-with-textarea
  (:require
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [field/field {}
    [field/field-label {:html-for "bio"}
     "Bio"]
    [field/field-content {}
     [textarea/textarea {:id "bio"
                         :placeholder "Tell us about yourself"}]
     [field/field-description {}
      "A short bio will appear on your profile."]]]])
