(ns clojure-shadcn.stories.menubar-stories
  (:require
   ["lucide-react"                       :refer [Copy Mail Scissors Undo2]]
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.menubar :as sut]
   [reagent.core                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Menubar"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Accessible application menubar with nested and selectable items."
                        :npm-install "npm install @radix-ui/react-menubar lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.menubar")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/menubar.cljs"
                        :filename "menubar.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "menubar / menubar-menu / menubar-trigger / menubar-content"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/menubar"
             :label "Radix Menubar Docs"}
      :description
      "Core Radix menubar composition with roving focus, keyboard navigation, menu ARIA, and automatically portaled content. Each Menu pairs a Trigger and Content beneath Root."
      :props [{:name ":align"
               :type "keyword | string"
               :default ":start"
               :description "Content alignment, converted to a string."}
              {:name ":align-offset / :side-offset"
               :type "number"
               :default "-4 / 8"
               :description "Content positioning offsets."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description
               "Normalized, class-merged, and forwarded to the corresponding Radix primitive."}]}]
    [helpers/api-component-card
     {:component-name "menubar-item"
      :description "Action item supporting destructive styling and inset alignment."
      :props [{:name ":variant"
               :type ":default | :destructive"
               :default ":default"
               :description "Visual intent only; selection behavior remains Radix Item behavior."}
              {:name ":inset"
               :type "boolean"
               :default nil
               :description "Adds leading alignment space."}
              {:name "Item props"
               :type "map entries"
               :default nil
               :description "Remaining props such as :disabled and :on-select are forwarded."}]}]
    [helpers/api-component-card
     {:component-name "menubar-checkbox-item / menubar-radio-group / menubar-radio-item"
      :description
      "Selectable items with built-in indicators. Radio items must be nested under RadioGroup; use Radix controlled/uncontrolled state props."
      :props [{:name ":checked / :on-checked-change"
               :type "boolean | indeterminate / function"
               :default nil
               :description "Checkbox state API."}
              {:name ":value / :on-value-change"
               :type "string / function"
               :default nil
               :description "Radio item and group state API."}]}]
    [helpers/api-component-card
     {:component-name "menubar-sub / menubar-sub-trigger / menubar-sub-content"
      :description
      "Nested-menu composition; SubTrigger appends a decorative chevron and supports inset alignment."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "SubTrigger-only alignment option."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded."}]}]
    [helpers/api-component-card
     {:component-name
      "menubar-group / menubar-portal / menubar-label / menubar-separator / menubar-shortcut"
      :description
      "Grouping, explicit portal, label, separator, and presentational shortcut helpers. Shortcut text does not install a keyboard handler."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "Label-only alignment option."}
              {:name "primitive or span props"
               :type "map entries"
               :default nil
               :description "Normalized, class-merged, and forwarded."}]}]])))

(defstory
 MenubarDemo
 []
 (r/as-element
  (helpers/wrap-component
   [sut/menubar {}
    [sut/menubar-menu {}
     [sut/menubar-trigger {}
      "File"]
     [sut/menubar-content {}
      [sut/menubar-item {}
       "New tab"
       [sut/menubar-shortcut {}
        "⌘T"]]
      [sut/menubar-item {}
       "New window"
       [sut/menubar-shortcut {}
        "⌘N"]]
      [sut/menubar-separator {}]
      [sut/menubar-sub {}
       [sut/menubar-sub-trigger {}
        "Share"]
       [sut/menubar-sub-content {}
        [sut/menubar-item {}
         "Email"]
        [sut/menubar-item {}
         "Messages"]]]
      [sut/menubar-item {:variant :destructive}
       "Close"]]]
    [sut/menubar-menu {}
     [sut/menubar-trigger {}
      "Edit"]
     [sut/menubar-content {}
      [sut/menubar-item {}
       [:> Undo2 {:aria-hidden true}]
       "Undo"
       [sut/menubar-shortcut {}
        "⌘Z"]]
      [sut/menubar-item {:inset true
                         :disabled true}
       "Redo"]
      [sut/menubar-separator {}]
      [sut/menubar-group {}
       [sut/menubar-label {:inset true}
        "Clipboard"]
       [sut/menubar-item {}
        [:> Scissors {:aria-hidden true}]
        "Cut"]
       [sut/menubar-item {}
        [:> Copy {:aria-hidden true}]
        "Copy"]]]]])))

(defstory MenubarSelections
          []
          (r/as-element
           [(fn []
              (let [bookmarks? (r/atom true)
                    profile (r/atom "benoit")]
                (fn []
                  (helpers/wrap-component
                   [sut/menubar {}
                    [sut/menubar-menu {}
                     [sut/menubar-trigger {}
                      "View"]
                     [sut/menubar-content {}
                      [sut/menubar-checkbox-item {:checked @bookmarks?
                                                  :on-checked-change #(reset! bookmarks? %)}
                       "Bookmarks bar"]]]
                    [sut/menubar-menu {}
                     [sut/menubar-trigger {}
                      "Profiles"]
                     [sut/menubar-content {}
                      [sut/menubar-radio-group {:value @profile
                                                :on-value-change #(reset! profile %)}
                       [sut/menubar-radio-item {:value "andy"}
                        "Andy"]
                       [sut/menubar-radio-item {:value "benoit"}
                        "Benoit"]]]]]))))]))


(defstory
 MenubarRtl
 "Right-to-left Radix Menubar with icons and checkbox state.

  Radix primitive: @radix-ui/react-menubar

  Set :dir on Root so arrow-key navigation follows reading direction."
 []
 (r/as-element
  [(fn []
     (let [mail? (r/atom true)]
       (fn []
         (helpers/wrap-component
          [sut/menubar {:dir "rtl"}
           [sut/menubar-menu {}
            [sut/menubar-trigger {}
             "تحرير"]
            [sut/menubar-content {:dir "rtl"}
             [sut/menubar-group {}
              [sut/menubar-item {}
               [:> Copy {:aria-hidden true}]
               "نسخ"]
              [sut/menubar-item {:inset true}
               "لصق"]]
             [sut/menubar-separator {}]
             [sut/menubar-checkbox-item {:checked @mail?
                                         :on-checked-change #(reset! mail? %)}
              [:> Mail {:aria-hidden true}]
              "إشعارات البريد"]]]]))))]))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description "Explore menubar css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "on-value-change" "on-checked-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/menubar
                                                 (select-keys args [:class])
                                                 [sut/menubar-menu {}
                                                  [sut/menubar-trigger {}
                                                   "File"]
                                                  [sut/menubar-content {}
                                                   [sut/menubar-item {}
                                                    "New tab"]
                                                   [sut/menubar-item {}
                                                    "Close"]]]])))
