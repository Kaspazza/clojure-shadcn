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
     "Local data-driven facade over Popover and Command. Unlike shadcn/ui's compositional example, it accepts item maps and controlled selection rather than arbitrary Command children. It manages only open state; the caller owns selection. Filtering uses each item's text label and keywords, even when custom renderers display richer content."
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


(defstory
 ComboboxPlayground
 "Interactive combobox playground."
 {:args {:placeholder "Select runtime…"
         :search-placeholder "Search runtimes…"
         :clearable true}
  :arg-types {:placeholder {:control {:type "text"}}
              :search-placeholder {:control {:type "text"}}
              :clearable {:control {:type "boolean"}}}
  :parameters {:controls {:exclude ["items" "value" "on-value-change" "class" "content-class"]}}
  :decode-args (fn [{:keys [clearable]
                     :as args}]
                 (assoc (dissoc args :clearable) :clearable? clearable))}
 [args]
 (r/as-element (r/with-let
                [value (r/atom nil)]
                (helpers/wrap-component
                 [sut/combobox
                  (assoc args :items options :value @value :on-value-change #(reset! value %))]))))

(def rich-options
  [{:value {:id 1
            :slug "clojure"}
    :label "Clojure"
    :kind "JVM"
    :status "Stable"
    :keywords ["lisp" "jvm"]}
   {:value {:id 2
            :slug "cljs"}
    :label "ClojureScript"
    :kind "Browser"
    :status "Stable"
    :keywords ["javascript" "browser"]}
   {:value {:id 3
            :slug "bb"}
    :label "Babashka"
    :kind "CLI"
    :status "Fast"
    :keywords ["scripts" "native"]}])

(defn- rich-combobox-example
  []
  (r/with-let
   [value (r/atom nil)]
   [:div {:class "grid gap-3"}
    [sut/combobox
     {:items rich-options
      :value @value
      :on-value-change #(reset! value %)
      :placeholder "Choose a runtime…"
      :clearable? true
      :item-render (fn [{:keys [label kind status]}]
                     [:div {:class "flex min-w-0 flex-1 items-center justify-between gap-4"}
                      [:span
                       [:span {:class "font-medium"}
                        label]
                       [:span {:class "ml-2 text-xs text-muted-foreground"}
                        kind]]
                      [:span {:class "rounded bg-muted px-1.5 py-0.5 text-xs"}
                       status]])
      :value-render (fn [{:keys [label kind]}] [:span {:class "flex items-center gap-2"}
                                                [:strong label]
                                                [:span {:class "text-xs text-muted-foreground"}
                                                 kind]])}]
    [:p {:class "text-xs text-muted-foreground"}
     "Selected object: "
     (if @value (pr-str @value) "none")]]))

(defstory
 RichObjectRendering
 "Rich result and selected-value rendering while preserving complete object values and label/keyword filtering."
 []
 (r/as-element (helpers/wrap-component [rich-combobox-example])))