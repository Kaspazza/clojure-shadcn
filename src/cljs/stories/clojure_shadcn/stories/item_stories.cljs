(ns clojure-shadcn.stories.item-stories
  (:require
   ["lucide-react"                             :refer [FileText MoreHorizontal]]
   [clojure-shadcn.stories.helpers             :as helpers]
   [clojure-shadcn.ui.components.avatar        :as avatar]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [clojure-shadcn.ui.components.item          :as sut]
   [reagent.core                               :as r])
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
          []
          (r/as-element
           (helpers/wrap-component
            [sut/item-group {:class "max-w-xl"}
             [sut/item {:variant :outline}
              [sut/item-media {:variant :icon}
               [:> FileText]]
              [sut/item-content {}
               [sut/item-title {}
                "Quarterly report"]
               [sut/item-description {}
                "PDF · 2.4 MB"]]
              [sut/item-actions {}
               (button/button {:variant :ghost
                               :size :icon}
                              [:> MoreHorizontal])]]
             [sut/item-separator {}]
             [sut/item {:variant :muted
                        :size :sm}
              [sut/item-content {}
               [sut/item-title {}
                "Compact item"]
               [sut/item-description {}
                "Muted, small variant"]]]])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:variant "outline"
                  :size "default"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "outline" "muted"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["default" "sm"]}}
           :parameters {:playground {:description "Explore item variant and size with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "as-child?"]}}
           :decode-args (fn [{:keys [variant size]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            size (update :size keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [sut/item
                                                 (select-keys args [:variant :size])
                                                 [sut/item-content {}
                                                  [sut/item-title {}
                                                   "Quarterly report"]
                                                  [sut/item-description {}
                                                   "PDF · 2.4 MB"]]])))

(defstory
 ItemPolymorphicLinks
 "Polymorphic links — upstream navigation items render anchors through :as-child? rather than nesting links."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/item-group {:class "max-w-xl"}
    [sut/item {:as-child? true
               :variant :outline}
     [:a {:href "#documentation"}
      [sut/item-media {:variant :icon}
       [:> FileText]]
      [sut/item-content {}
       [sut/item-title {}
        "Documentation"]
       [sut/item-description {}
        "Read installation and component guides."]]]]
    [sut/item {:as-child? true
               :variant :muted}
     [:a {:href "#examples"}
      [sut/item-content {}
       [sut/item-title {}
        "Examples"]
       [sut/item-description {}
        "Browse complete composition recipes."]]]]])))

(defstory
 ItemMediaMatrix
 "Avatar and Image — upstream media recipes shown beside the default icon treatment."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/item-group {:class "max-w-2xl"}
    [sut/item {:variant :outline}
     [sut/item-media {}
      [avatar/avatar {}
       [avatar/avatar-image {:src "https://github.com/shadcn.png"
                             :alt "@shadcn"}]
       [avatar/avatar-fallback {}
        "CN"]]]
     [sut/item-content {}
      [sut/item-title {}
       "shadcn"]
      [sut/item-description {}
       "Updated the component registry."]]]
    [sut/item-separator {}]
    [sut/item {:variant :outline}
     [sut/item-media {:variant :image}
      [:img
       {:src
        "https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=200&q=80"
        :alt "Modern office"
        :class "size-full object-cover"}]]
     [sut/item-content {}
      [sut/item-title {}
       "Workspace tour"]
      [sut/item-description {}
       "See the new collaboration space."]]]])))

(defstory
 ItemDropdown
 "Item actions composed with Dropdown Menu; the icon button is the actual menu trigger and remains independently accessible."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/item {:variant :outline
              :class "max-w-xl"}
    [sut/item-media {:variant :icon}
     [:> FileText]]
    [sut/item-content {}
     [sut/item-title {}
      "Quarterly report"]
     [sut/item-description {}
      "PDF · 2.4 MB"]]
    [sut/item-actions {}
     [dropdown-menu/dropdown-menu {}
      [dropdown-menu/dropdown-menu-trigger {:as-child true}
       (button/button {:variant :ghost
                       :size :icon
                       :aria-label "Report actions"}
                      [:> MoreHorizontal])]
      [dropdown-menu/dropdown-menu-content {:align "end"}
       [dropdown-menu/dropdown-menu-item {}
        "Download"]
       [dropdown-menu/dropdown-menu-item {}
        "Share"]
       [dropdown-menu/dropdown-menu-separator {}]
       [dropdown-menu/dropdown-menu-item {:variant :destructive}
        "Delete"]]]]])))

(defstory ItemHeader
          "Header — upstream item header/footer composition for richer list content."
          []
          (r/as-element (helpers/wrap-component
                         [sut/item {:variant :outline
                                    :class "max-w-xl"}
                          [sut/item-header {:class "text-xs text-muted-foreground"}
                           [:span "Release notes"]
                           [:time {:date-time "2026-03-01"}
                            "March 1"]]
                          [sut/item-content {}
                           [sut/item-title {}
                            "Version 2.4"]
                           [sut/item-description {}
                            "Improved keyboard navigation and logical RTL spacing."]]
                          [sut/item-footer {:class "text-xs text-muted-foreground"}
                           "5 minute read"]])))

(defstory ItemRTL
          "RTL — item media, content, and actions follow logical right-to-left ordering."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "max-w-xl text-right"}
                                                 [sut/item {:variant :outline}
                                                  [sut/item-media {:variant :icon}
                                                   [:> FileText]]
                                                  [sut/item-content {}
                                                   [sut/item-title {}
                                                    "التقرير الفصلي"]
                                                   [sut/item-description {}
                                                    "ملف PDF بحجم 2.4 ميجابايت"]]
                                                  [sut/item-actions {}
                                                   (button/button {:variant :ghost
                                                                   :size :icon
                                                                   :aria-label "المزيد"}
                                                                  [:> MoreHorizontal])]]])))
