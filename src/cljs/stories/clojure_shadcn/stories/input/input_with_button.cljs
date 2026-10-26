(ns clojure-shadcn.stories.input.input-with-button
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input  :as input]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex w-full max-w-sm items-center gap-2"}
    [input/input {:type "email"
                  :placeholder "Email"}]
    (button/button {:type "submit"
                    :variant :outline}
                   "Subscribe")]])
