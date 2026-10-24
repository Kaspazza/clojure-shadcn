(ns clojure-shadcn.stories.kbd-stories
  (:require
   ["lucide-react"                           :refer [Command]]
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.input-group :as input-group]
   [clojure-shadcn.ui.components.kbd         :as sut]
   [clojure-shadcn.ui.components.tooltip     :as tooltip]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Kbd"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard input and shortcut hint elements."
                        :npm-install nil
                        :source-code (embed-source "clojure-shadcn.ui.components.kbd")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/kbd.cljs"
                        :filename "kbd.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "kbd"
      :description
      "Semantic HTML kbd element for an individual key or compact keyboard input hint. It is presentational and non-interactive."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with the key-cap styles."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded to the kbd element."}]}]
    [helpers/api-component-card
     {:component-name "kbd-group"
      :description
      "Inline layout for a shortcut sequence. It also renders a kbd element, so use it to group key tokens rather than as an interactive control."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to the kbd element."}]}]])))

(defstory Playground
          "Interactive keyboard hint playground."
          {:args {:prefix "Press"
                  :key "K"
                  :suffix "to search"}
           :arg-types {:prefix {:name "Prefix"
                                :control {:type "text"}}
                       :key {:name "Shortcut key"
                             :control {:type "text"}}
                       :suffix {:name "Suffix"
                                :control {:type "text"}}}
           :parameters {:playground {:description "Explore kbd prefix, shortcut key and suffix with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "flex items-center gap-2 text-sm"}
                                                 (:prefix args)
                                                 [sut/kbd {}
                                                  "⌘"]
                                                 [sut/kbd {}
                                                  (:key args)]
                                                 (:suffix args)])))

(defstory KbdGroup
          []
          (r/as-element (helpers/wrap-component [sut/kbd-group {}
                                                 [sut/kbd {}
                                                  [:> Command]]
                                                 [:span "+"]
                                                 [sut/kbd {}
                                                  "Shift"]
                                                 [:span "+"]
                                                 [sut/kbd {}
                                                  "P"]])))

(defstory
 KbdButton
 "Button — upstream command-button recipe pairs an action label with a visual shortcut hint."
 []
 (r/as-element (helpers/wrap-component (button/button {:variant :outline
                                                       :class "gap-4"}
                                                      "Open command menu"
                                                      [sut/kbd-group {}
                                                       [sut/kbd {}
                                                        [:> Command]]
                                                       [sut/kbd {}
                                                        "K"]]))))

(defstory KbdTooltip
          "Tooltip — a shortcut hint enriches an icon button without becoming interactive itself."
          []
          (r/as-element (helpers/wrap-component
                         [:>
                          tooltip/tooltip-provider-component
                          {}
                          [tooltip/tooltip {:trigger (button/button {:variant :outline} "Save")
                                            :trigger-as-child? true
                                            :content [sut/kbd-group {}
                                                      [sut/kbd {}
                                                       [:> Command]]
                                                      [sut/kbd {}
                                                       "S"]]}]])))

(defstory KbdInputGroup
          "Input Group — upstream search field shows its keyboard shortcut as an end addon."
          []
          (r/as-element (helpers/wrap-component [input-group/input-group {:class "max-w-sm"}
                                                 [input-group/input-group-input
                                                  {:type "search"
                                                   :aria-label "Search"
                                                   :placeholder "Search documentation..."}]
                                                 [input-group/input-group-addon {:align :inline-end}
                                                  [sut/kbd-group {}
                                                   [sut/kbd {}
                                                    [:> Command]]
                                                   [sut/kbd {}
                                                    "K"]]]])))

(defstory KbdRTL
          "RTL — shortcut tokens remain legible inside right-to-left instructional copy."
          []
          (r/as-element (helpers/wrap-component [:p {:dir "rtl"
                                                     :class "flex items-center gap-2 text-sm"}
                                                 "اضغط"
                                                 [sut/kbd-group {}
                                                  [sut/kbd {}
                                                   [:> Command]]
                                                  [sut/kbd {}
                                                   "K"]]
                                                 "للبحث"])))
