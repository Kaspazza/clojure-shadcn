(ns clojure-shadcn.stories.item-stories
  (:require
   [clojure-shadcn.stories.helpers                     :as helpers]
   [clojure-shadcn.stories.item.item-dropdown          :as item-dropdown]
   [clojure-shadcn.stories.item.item-header            :as item-header]
   [clojure-shadcn.stories.item.item-media-matrix      :as item-media-matrix]
   [clojure-shadcn.stories.item.item-polymorphic-links :as item-polymorphic-links]
   [clojure-shadcn.stories.item.item-rtl               :as item-rtl]
   [clojure-shadcn.stories.item.item-variants          :as item-variants]
   [clojure-shadcn.stories.item.playground             :as playground]
   [reagent.core                                       :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Item"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Current shadcn/ui composable item primitive."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.item")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/item.cljs"
                        :filename "item.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl space-y-4"}
    [helpers/api-component-card
     {:component-name "item / item-group / item-separator"
      :link {:href "https://ui.shadcn.com/docs/components/item"
             :label "shadcn/ui Item"}
      :description
      "List container, variant-aware item and separator. Item supports Slot composition."
      :props [{:name ":variant"
               :type ":default | :outline | :muted"
               :default ":default"}
              {:name ":size"
               :type ":default | :sm"
               :default ":default"}
              {:name ":as-child?"
               :type "boolean"
               :default "false"}]}]
    [helpers/api-component-card
     {:component-name
      "item-media / item-content / item-title / item-description / item-actions / item-header / item-footer"
      :description "Semantic composition slots; all forward DOM props and merge :class."
      :props [{:name ":variant (media)"
               :type ":default | :icon | :image"
               :default ":default"}
              {:name ":class"
               :type "string"
               :default nil}]}]])))

(defstory ItemVariants
          {:source-code (embed-source "clojure-shadcn.stories.item.item-variants")}
          []
          (r/as-element (helpers/wrap-component [item-variants/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.item.playground")
           :args {:variant "outline"
                  :size "default"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "outline" "muted"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["default" "sm"]}}
           :parameters {:playground {:description
                                     "Explore item variant and size with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "as-child?"]}}
           :decode-args (fn [{:keys [variant size]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            size (update :size keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 ItemPolymorphicLinks
 "Polymorphic links — upstream navigation items render anchors through :as-child? rather than nesting links."
 {:source-code (embed-source "clojure-shadcn.stories.item.item-polymorphic-links")}
 []
 (r/as-element (helpers/wrap-component [item-polymorphic-links/example])))

(defstory ItemMediaMatrix
          "Avatar and Image — upstream media recipes shown beside the default icon treatment."
          {:source-code (embed-source "clojure-shadcn.stories.item.item-media-matrix")}
          []
          (r/as-element (helpers/wrap-component [item-media-matrix/example])))

(defstory
 ItemDropdown
 "Item actions composed with Dropdown Menu; the icon button is the actual menu trigger and remains independently accessible."
 {:source-code (embed-source "clojure-shadcn.stories.item.item-dropdown")}
 []
 (r/as-element (helpers/wrap-component [item-dropdown/example])))

(defstory ItemHeader
          "Header — upstream item header/footer composition for richer list content."
          {:source-code (embed-source "clojure-shadcn.stories.item.item-header")}
          []
          (r/as-element (helpers/wrap-component [item-header/example])))

(defstory ItemRTL
          "RTL — item media, content, and actions follow logical right-to-left ordering."
          {:source-code (embed-source "clojure-shadcn.stories.item.item-rtl")}
          []
          (r/as-element (helpers/wrap-component [item-rtl/example])))
