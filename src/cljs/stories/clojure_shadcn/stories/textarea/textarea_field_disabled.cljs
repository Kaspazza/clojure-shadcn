(ns clojure-shadcn.stories.textarea.textarea-field-disabled
  (:require
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [field/field {:data-disabled true}
    [field/field-label {:html-for "disabled-notes"}
     "Internal notes"]
    [textarea/textarea {:id "disabled-notes"
                        :disabled true
                        :default-value "Editing is restricted."}]
    [field/field-description {}
     "Only administrators can edit notes."]]])
