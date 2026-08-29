(ns clojure-shadcn.stories.combobox-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.combobox :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Combobox"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Controlled, data-oriented single-select combobox."
     :npm-install "npm install cmdk lucide-react @radix-ui/react-popover"
     :source-code (embed-source "clojure-shadcn.ui.components.combobox")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/combobox.cljs"
     :filename "combobox.cljs"}]))

(def options
  [{:value :clj :label "Clojure" :keywords ["jvm"]}
   {:value :cljs :label "ClojureScript" :keywords ["browser"]}
   {:value :bb :label "Babashka"}])

(defn- combobox-example []
  (r/with-let [value (r/atom nil)]
    [sut/combobox {:items options
                   :value @value
                   :on-value-change #(reset! value %)
                   :placeholder "Select runtime…"
                   :search-placeholder "Search runtimes…"
                   :empty-text "No runtime found."
                   :clearable? true}]))

(defstory Basic
  []
  (r/as-element
   (helpers/wrap-component
    [combobox-example])))
