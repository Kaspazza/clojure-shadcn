(ns clojure-shadcn.stories.native-select-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.native-select :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Native Select" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Styled native select preserving platform semantics and accessibility." :npm-install "npm install lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.native_select") :namespace-path "src/cljs/clojure_shadcn/ui/components/native_select.cljs" :filename "native_select.cljs"}]))
(defstory NativeSelectDemo []
  (r/as-element (helpers/wrap-component [sut/native-select {:aria-label "Choose a framework" :default-value ""} [sut/native-select-option {:value "" :disabled true} "Select framework"] [sut/native-select-optgroup {:label "Frontend"} [sut/native-select-option {:value "reagent"} "Reagent"] [sut/native-select-option {:value "react"} "React"]] [sut/native-select-optgroup {:label "Backend"} [sut/native-select-option {:value "clojure"} "Clojure"]]])))
(defstory NativeSelectSizes []
  (r/as-element (helpers/wrap-component [:div {:class "flex items-center gap-3"} [sut/native-select {:size :default :default-value "one"} [sut/native-select-option {:value "one"} "Default"]] [sut/native-select {:size :sm :default-value "two"} [sut/native-select-option {:value "two"} "Small"]] [sut/native-select {:disabled true} [sut/native-select-option {} "Disabled"]]])))
