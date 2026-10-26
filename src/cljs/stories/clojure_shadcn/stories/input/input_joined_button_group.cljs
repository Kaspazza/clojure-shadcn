(ns clojure-shadcn.stories.input.input-joined-button-group
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as button-group]
   [clojure-shadcn.ui.components.input        :as input]
   [clojure-shadcn.ui.components.label        :as label]))

(defn example
  []
  [:div {:class "w-full max-w-md p-6"}
   [label/label {:html-for "site-url"
                 :class "mb-2"}
    "Website"]
   [button-group/button-group {:class "w-full"}
    [input/input {:id "site-url"
                  :type "url"
                  :placeholder "https://example.com"}]
    (button/button {:type "button"
                    :variant :outline}
                   "Copy")]])
