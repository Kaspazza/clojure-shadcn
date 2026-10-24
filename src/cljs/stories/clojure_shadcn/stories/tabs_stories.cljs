(ns clojure-shadcn.stories.tabs-stories
  (:require
   ["lucide-react"                    :refer [Bell Settings User]]
   [clojure-shadcn.stories.helpers    :as helpers]
   [clojure-shadcn.ui.components.tabs :as sut]
   [reagent.core                      :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Tabs"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard-accessible tabbed content."
                        :npm-install "npm install @radix-ui/react-tabs"
                        :source-code (embed-source "clojure-shadcn.ui.components.tabs")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/tabs.cljs"
                        :filename "tabs.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [helpers/api-component-card
             {:component-name "tabs / tabs-list / tabs-trigger / tabs-content"
              :link {:href "https://www.radix-ui.com/primitives/docs/components/tabs"
                     :label "Radix Tabs Docs"}
              :description
              "Radix Tabs wrappers preserving roving focus, activation and ARIA relationships."
              :props [{:name ":value / :default-value"
                       :type "string"
                       :default nil
                       :description "Controlled or initial tab."}
                      {:name ":on-value-change"
                       :type "function"
                       :default nil
                       :description "Selection callback."}
                      {:name ":orientation / :activation-mode"
                       :type "keyword|string"
                       :default nil
                       :description "Keyboard behavior."}]}])))

(defstory TabsBasic
          []
          (r/as-element (helpers/wrap-component [:div {:class "w-[420px] p-6"}
                                                 [sut/tabs {:default-value "account"}
                                                  [sut/tabs-list {}
                                                   [sut/tabs-trigger {:value "account"}
                                                    "Account"]
                                                   [sut/tabs-trigger {:value "password"}
                                                    "Password"]]
                                                  [sut/tabs-content {:value "account"
                                                                     :class "rounded-md border p-4"}
                                                   "Manage account preferences."]
                                                  [sut/tabs-content {:value "password"
                                                                     :class "rounded-md border p-4"}
                                                   "Change your password."]]])))

(defstory TabsDisabled
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/tabs {:default-value "active"}
                                                  [sut/tabs-list {}
                                                   [sut/tabs-trigger {:value "active"}
                                                    "Active"]
                                                   [sut/tabs-trigger {:value "disabled"
                                                                      :disabled true}
                                                    "Disabled"]]
                                                  [sut/tabs-content {:value "active"}
                                                   "The second tab cannot be selected."]]])))


(defstory
 TabsWithIcons
 "Radix Tabs triggers with decorative Lucide icons and visible labels.

  Radix primitive: @radix-ui/react-tabs

  Pair icons with text so each tab retains a clear accessible name."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "w-[420px] p-6"}
    [sut/tabs {:default-value "profile"}
     [sut/tabs-list {}
      [sut/tabs-trigger {:value "profile"}
       [:> User {:aria-hidden true}]
       "Profile"]
      [sut/tabs-trigger {:value "notifications"}
       [:> Bell {:aria-hidden true}]
       "Notifications"]
      [sut/tabs-trigger {:value "settings"}
       [:> Settings {:aria-hidden true}]
       "Settings"]]
     [sut/tabs-content {:value "profile"
                        :class "rounded-md border p-4"}
      "Update your public profile."]
     [sut/tabs-content {:value "notifications"
                        :class "rounded-md border p-4"}
      "Choose which alerts you receive."]
     [sut/tabs-content {:value "settings"
                        :class "rounded-md border p-4"}
      "Manage workspace defaults."]]])))

(defstory
 TabsRtl
 "Right-to-left Radix Tabs without custom orientation styling.

  Radix primitive: @radix-ui/react-tabs

  Set :dir on Root so horizontal arrow-key behavior follows reading direction."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "w-[420px] p-6"
          :dir "rtl"}
    [sut/tabs {:default-value "account"
               :dir "rtl"}
     [sut/tabs-list {}
      [sut/tabs-trigger {:value "account"}
       [:> User {:aria-hidden true}]
       "الحساب"]
      [sut/tabs-trigger {:value "alerts"}
       [:> Bell {:aria-hidden true}]
       "التنبيهات"]]
     [sut/tabs-content {:value "account"
                        :class "rounded-md border p-4"}
      "إدارة معلومات الحساب."]
     [sut/tabs-content {:value "alerts"
                        :class "rounded-md border p-4"}
      "اختيار إعدادات التنبيهات."]]])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:default-value "account"}
           :arg-types {:default-value {:name "Initial value"
                                       :control {:type "select"}
                                       :options ["account" "password"]}}
           :parameters {:playground {:description "Explore tabs initial value with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "value" "on-value-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/tabs
                                                 (select-keys args [:default-value])
                                                 [sut/tabs-list {}
                                                  [sut/tabs-trigger {:value "account"}
                                                   "Account"]
                                                  [sut/tabs-trigger {:value "password"}
                                                   "Password"]]
                                                 [sut/tabs-content {:value "account"}
                                                  "Account settings"]
                                                 [sut/tabs-content {:value "password"}
                                                  "Password settings"]])))
