(ns clojure-shadcn.stories.native-select.native-select-sizes
  (:require
   [clojure-shadcn.ui.components.native-select :as native-select]))

(defn example
  []
  [:div {:class "flex items-center gap-3"}
   [native-select/native-select {:size :default
                                 :default-value "one"}
    [native-select/native-select-option {:value "one"}
     "Default"]]
   [native-select/native-select {:size :sm
                                 :default-value "two"}
    [native-select/native-select-option {:value "two"}
     "Small"]]
   [native-select/native-select {:disabled true}
    [native-select/native-select-option {}
     "Disabled"]]])
