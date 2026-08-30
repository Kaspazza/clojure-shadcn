(ns clojure-shadcn.stories.combobox-stories
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.combobox :as sut]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Combobox"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Controlled, data-oriented single-select combobox."
                        :npm-install "npm install cmdk lucide-react @radix-ui/react-popover"
                        :source-code (embed-source "clojure-shadcn.ui.components.combobox")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/combobox.cljs"
                        :filename "combobox.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "combobox"
     :description
     "Controlled single-select combining Popover and Command. It manages only open state; the caller owns selection. The trigger exposes role=combobox and aria-expanded, while Command provides filtering and keyboard navigation."
     :props
     [{:name ":items"
       :type "vector of maps, required"
       :default nil
       :description
       "Each item needs :value and may provide :label, :keywords, and :disabled?. Values should be unique and stable."}
      {:name ":value"
       :type "any"
       :default nil
       :description "Controlled selected value, matched with = against item :value."}
      {:name ":on-value-change"
       :type "function"
       :default nil
       :description "Called with an item value, or nil when cleared. Update :value in the parent."}
      {:name ":placeholder"
       :type "string"
       :default "Select an option…"
       :description "Trigger text when no item is selected."}
      {:name ":search-placeholder"
       :type "string"
       :default "Search…"
       :description "Command search input placeholder."}
      {:name ":empty-text"
       :type "string"
       :default "No option found."
       :description "Displayed when filtering finds no item."}
      {:name ":disabled? / :clearable?"
       :type "boolean"
       :default nil
       :description
       "Disables the trigger or shows an accessible clear control for a selected value."}
      {:name ":item-render / :value-render"
       :type "function"
       :default nil
       :description
       "Receive the complete item map and return renderable content for a result or selected value."}
      {:name ":class / :content-class"
       :type "string"
       :default nil
       :description "Classes merged onto the trigger button or popover content."}]}])))

(def options
  [{:value :clj
    :label "Clojure"
    :keywords ["jvm"]}
   {:value :cljs
    :label "ClojureScript"
    :keywords ["browser"]}
   {:value :bb
    :label "Babashka"}])

(defn- combobox-example
  []
  (r/with-let [value (r/atom nil)]
              [sut/combobox {:items options
                             :value @value
                             :on-value-change #(reset! value %)
                             :placeholder "Select runtime…"
                             :search-placeholder "Search runtimes…"
                             :empty-text "No runtime found."
                             :clearable? true}]))

(defstory Basic [] (r/as-element (helpers/wrap-component [combobox-example])))
