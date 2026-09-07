(ns clojure-shadcn.stories.drawer-stories
  "Storybook stories for the Drawer component. Ported from mateuszmazurczak.portfolio.ui-components.drawer."
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as sut]
   [clojure-shadcn.ui.components.input  :as input]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Drawer"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Drawer component based on Vaul drawer primitive."
                        :npm-install "npm install vaul"
                        :source-code (embed-source "clojure-shadcn.ui.components.drawer")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/drawer.cljs"
                        :filename "drawer.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "drawer"
       :description "Drawer component"
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state"}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback (fn [open?])"}
               {:name ":direction"
                :type "keyword"
                :default ":bottom"
                :description "One of: :top | :right | :bottom | :left"}
               {:name ":should-scale-background"
                :type "boolean"
                :default nil
                :description "Scale background while open"}
               {:name ":modal"
                :type "boolean"
                :default "true"
                :description "Modal behavior"}]}]
     [helpers/api-component-card {:component-name "drawer-trigger"
                                  :description "Drawer trigger component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-close"
                                  :description "Drawer close component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-overlay"
                                  :description "Drawer overlay component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-content"
                                  :description "Drawer content component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-header"
                                  :description "Drawer header component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-footer"
                                  :description "Drawer footer component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-title"
                                  :description "Drawer title component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "drawer-description"
                                  :description "Drawer description component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Choose :modal false only when background interaction is intentional and safe."]
       [:li
        "Use :direction to match platform UX (bottom for mobile action sheets, side for navigation/tools)."]
       [:li
        "Prefer controlled mode (:open + :on-open-change) when drawers integrate with route/state orchestration."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[drawer {:open @open? :on-open-change #(reset! open? %)}\n [drawer-trigger {:as-child true} [button {:variant :outline} \"Open\"]]\n [drawer-content {}\n  [drawer-header {} [drawer-title {} \"Confirm\"]]\n  [drawer-footer {} [button {} \"Continue\"]]] ]"]]]]])))

(defstory
 BottomDrawer
 "Bottom drawer with form example.
   
   Common use case for mobile: a drawer that slides up from the bottom
   with form fields or actions."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-4"}
           [sut/drawer {:open @open?
                        :on-open-change #(reset! open? %)
                        :direction :bottom}
            [sut/drawer-trigger {:as-child true}
             (button/button {:variant :outline} "Open Bottom Drawer")]
            [sut/drawer-content {}
             [sut/drawer-header {}
              [sut/drawer-title {}
               "Edit Profile"]
              [sut/drawer-description {}
               "Make changes to your profile here. Click save when you're done."]]
             [:div {:class "p-4 space-y-4"}
              [:div {:class "space-y-2"}
               [:label {:class "text-sm font-medium"}
                "Name"]
               [input/input {:placeholder "Enter your name"}]]
              [:div {:class "space-y-2"}
               [:label {:class "text-sm font-medium"}
                "Email"]
               [input/input {:type "email"
                             :placeholder "Enter your email"}]]]
             [sut/drawer-footer {}
              (button/button {:on-click #(reset! open? false)} "Save Changes")
              (button/button {:variant :outline
                              :on-click #(reset! open? false)}
                             "Cancel")]]]]))))]))

(defstory RightDrawer
          "Right-side drawer with an independently scrollable body and persistent footer."
          []
          (r/as-element
           [(fn []
              (let [open? (r/atom false)
                    sections ["Overview"
                              "Activity"
                              "Members"
                              "Integrations"
                              "Billing"
                              "Security"
                              "Notifications"
                              "Automation"
                              "Exports"
                              "Advanced"]]
                (fn []
                  (helpers/wrap-component
                   [:div {:class "p-4"}
                    [sut/drawer {:open @open?
                                 :on-open-change #(reset! open? %)
                                 :direction :right}
                     [sut/drawer-trigger {:as-child true}
                      (button/button {:variant :outline} "Open project settings")]
                     [sut/drawer-content {:class "w-80 overflow-hidden"}
                      [sut/drawer-header {:class "shrink-0 border-b"}
                       [sut/drawer-title {}
                        "Project settings"]
                       [sut/drawer-description {}
                        "Choose a section to configure."]]
                      [:nav {:class "min-h-0 flex-1 space-y-2 overflow-y-auto p-4"
                             :aria-label "Project settings sections"}
                       (for [item sections]
                         ^{:key item}
                         [:button {:type "button"
                                   :class "w-full rounded-md px-3 py-3 text-left hover:bg-accent"}
                          item])]
                      [sut/drawer-footer {:class "shrink-0 border-t bg-background"}
                       [sut/drawer-close {:as-child true}
                        (button/button {:variant :outline
                                        :class "w-full"}
                                       "Close settings")]]]]]))))]))

(defstory
 DrawerWithScroll
 "Bottom drawer with scrollable content.
   
   Shows how the drawer handles overflow content with max-height."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-4"}
           [sut/drawer {:open @open?
                        :on-open-change #(reset! open? %)
                        :direction :bottom}
            [sut/drawer-trigger {:as-child true}
             (button/button {:variant :outline} "Open Scrollable Drawer")]
            [sut/drawer-content {}
             [sut/drawer-header {}
              [sut/drawer-title {}
               "Terms and Conditions"]
              [sut/drawer-description {}
               "Please read through the terms before continuing"]]
             [:div {:class "p-4 overflow-y-auto"}
              (for [i (range 20)]
                ^{:key i}
                [:p {:class "mb-4 text-sm text-muted-foreground"}
                 (str "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                      "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                      "Section "
                      (inc i)
                      " of the terms.")])]
             [sut/drawer-footer {}
              (button/button {:on-click #(reset! open? false)} "I Accept")
              (button/button {:variant :outline
                              :on-click #(reset! open? false)}
                             "Decline")]]]]))))]))

(defstory
 DrawerNoScale
 "Drawer without background scaling.
   
   By default, the background scales down slightly when drawer opens.
   This example shows how to disable that effect."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-4"}
           [sut/drawer {:open @open?
                        :on-open-change #(reset! open? %)
                        :direction :bottom
                        :should-scale-background false}
            [sut/drawer-trigger {:as-child true}
             (button/button {:variant :outline} "Open (No Scale)")]
            [sut/drawer-content {}
             [sut/drawer-header {}
              [sut/drawer-title {}
               "No Background Scaling"]
              [sut/drawer-description {}
               "Notice the background doesn't scale when this opens"]]
             [:div {:class "p-4"}
              [:p {:class "text-sm text-muted-foreground"}
               "This drawer is configured with :should-scale-background false"]]
             [sut/drawer-footer {}
              (button/button {:on-click #(reset! open? false)} "Close")]]]]))))]))

(defstory
 TopDrawer
 "Top drawer variant.

  Useful for non-critical announcements or quick filters sliding from top."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component [:div {:class "p-4"}
                                  [sut/drawer {:open @open?
                                               :on-open-change #(reset! open? %)
                                               :direction :top}
                                   [sut/drawer-trigger {:as-child true}
                                    (button/button {:variant :outline} "Open Top Drawer")]
                                   [sut/drawer-content {}
                                    [sut/drawer-header {}
                                     [sut/drawer-title {}
                                      "Quick Filters"]
                                     [sut/drawer-description {}
                                      "Apply fast filters without leaving current context."]]
                                    [sut/drawer-footer {}
                                     (button/button {:on-click #(reset! open? false)}
                                                    "Apply")]]]]))))]))

(defstory
 LeftDrawerNonModal
 "Left drawer with non-modal behavior.

  Demonstrates :direction :left and :modal false for side-by-side workflows."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-4"}
           [sut/drawer {:open @open?
                        :on-open-change #(reset! open? %)
                        :direction :left
                        :modal false}
            [sut/drawer-trigger {:as-child true}
             (button/button {:variant :outline} "Open Left Non-Modal")]
            [sut/drawer-content {:class "w-80"}
             [sut/drawer-header {}
              [sut/drawer-title {}
               "Inspector"]
              [sut/drawer-description {}
               "Background stays interactive while this panel is open."]]
             [sut/drawer-footer {}
              (button/button {:variant :outline
                              :on-click #(reset! open? false)}
                             "Close")]]]]))))]))


(defstory StatefulGoalDrawer
          "Stateful mobile-style drawer for adjusting and saving a daily activity goal."
          []
          (r/as-element
           [(fn []
              (let [open? (r/atom false)
                    goal (r/atom 350)]
                (fn []
                  (helpers/wrap-component
                   [:div {:class "p-4"}
                    [sut/drawer {:open @open?
                                 :on-open-change #(reset! open? %)
                                 :direction :bottom}
                     [sut/drawer-trigger {:as-child true}
                      (button/button {:variant :outline} "Set daily goal")]
                     [sut/drawer-content {}
                      [sut/drawer-header {:class "text-center"}
                       [sut/drawer-title {}
                        "Daily activity goal"]
                       [sut/drawer-description {}
                        "Choose how many calories you want to burn each day."]]
                      [:div
                       {:class
                        "mx-auto flex w-full max-w-sm items-center justify-center gap-6 px-4 py-6"}
                       (button/button {:variant :outline
                                       :size :icon
                                       :aria-label "Decrease daily goal"
                                       :disabled (<= @goal 100)
                                       :on-click #(swap! goal (fn [value] (max 100 (- value 10))))}
                                      "−")
                       [:div {:class "min-w-28 text-center"}
                        [:div {:class "text-5xl font-bold tabular-nums"}
                         @goal]
                        [:div {:class "text-sm text-muted-foreground"}
                         "calories per day"]]
                       (button/button {:variant :outline
                                       :size :icon
                                       :aria-label "Increase daily goal"
                                       :disabled (>= @goal 1000)
                                       :on-click #(swap! goal (fn [value] (min 1000 (+ value 10))))}
                                      "+")]
                      [sut/drawer-footer {:class "mx-auto w-full max-w-sm"}
                       (button/button {:on-click #(reset! open? false)} "Save goal")
                       [sut/drawer-close {:as-child true}
                        (button/button {:variant :outline} "Cancel")]]]]]))))]))

(defstory DrawerRtl
          "Right-to-left drawer with localized labels and direction-aware alignment."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-4"
                   :dir "rtl"}
             [sut/drawer {:direction :right}
              [sut/drawer-trigger {:as-child true}
               (button/button {:variant :outline} "فتح الإعدادات")]
              [sut/drawer-content {:class "w-80"
                                   :dir "rtl"}
               [sut/drawer-header {:class "text-right"}
                [sut/drawer-title {}
                 "إعدادات الحساب"]
                [sut/drawer-description {}
                 "حدّث تفضيلات حسابك."]]
               [:div {:class "flex-1 px-4 text-right text-sm text-muted-foreground"}
                "تُطبّق التغييرات بعد الحفظ."]
               [sut/drawer-footer {}
                [sut/drawer-close {:as-child true}
                 (button/button {:variant :outline} "إلغاء")]
                [sut/drawer-close {:as-child true}
                 (button/button {} "حفظ")]]]]])))

(defstory DrawerPlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:direction "bottom"}
           :arg-types {:direction {:control {:type "select"}
                                   :options ["top" "right" "bottom" "left"]}}
           :parameters {:controls {:exclude ["children" "open" "default-open" "on-open-change"]}}
           :decode-args (fn [{:keys [direction]
                              :as args}]
                          (cond-> args
                            direction (update :direction keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/drawer
                                                  (select-keys args [:direction])
                                                  [sut/drawer-trigger {:as-child true}
                                                   [button/button {:variant :outline}
                                                    "Open drawer"]]
                                                  [sut/drawer-content {}
                                                   [sut/drawer-header {}
                                                    [sut/drawer-title {}
                                                     "Move goal"]
                                                    [sut/drawer-description {}
                                                     "Set your daily activity goal."]]]]])))
