(ns clojure-shadcn.stories.drawer-stories
  "Storybook stories for the Drawer component. Ported from mateuszmazurczak.portfolio.ui-components.drawer."
  (:require
   [clojure-shadcn.stories.drawer.bottom-drawer            :as bottom-drawer]
   [clojure-shadcn.stories.drawer.drawer-no-scale          :as drawer-no-scale]
   [clojure-shadcn.stories.drawer.drawer-rtl               :as drawer-rtl]
   [clojure-shadcn.stories.drawer.drawer-with-scroll       :as drawer-with-scroll]
   [clojure-shadcn.stories.drawer.left-drawer-non-modal    :as left-drawer-non-modal]
   [clojure-shadcn.stories.drawer.nested-drawer            :as nested-drawer]
   [clojure-shadcn.stories.drawer.playground               :as playground]
   [clojure-shadcn.stories.drawer.responsive-drawer-dialog :as responsive-drawer-dialog]
   [clojure-shadcn.stories.drawer.right-drawer             :as right-drawer]
   [clojure-shadcn.stories.drawer.snap-points-drawer       :as snap-points-drawer]
   [clojure-shadcn.stories.drawer.stateful-goal-drawer     :as stateful-goal-drawer]
   [clojure-shadcn.stories.drawer.swipe-handle-only        :as swipe-handle-only]
   [clojure-shadcn.stories.drawer.top-drawer               :as top-drawer]
   [clojure-shadcn.stories.helpers                         :as helpers]
   [reagent.core                                           :as r])
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
                :description "Modal behavior"}
               {:name ":snap-points"
                :type "vector<number|string>"
                :default nil
                :description "Stops expressed as viewport fractions or pixel strings"}
               {:name ":active-snap-point / :set-active-snap-point"
                :type "number|string + function"
                :default nil
                :description "Controlled active snap point and its change callback"}
               {:name ":handle-only"
                :type "boolean"
                :default "false"
                :description "Restrict dragging to drawer-handle"}]}]
     [helpers/api-component-card {:component-name "drawer-nested-root"
                                  :description
                                  "Vaul nested root; place it inside a parent drawer-content"
                                  :props [{:name ":open / :on-open-change"
                                           :type "boolean + function"
                                           :default nil
                                           :description "Optional controlled nested state"}
                                          {:name ":direction"
                                           :type "keyword"
                                           :default ":bottom"
                                           :description "Nested drawer direction"}]}]
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
     [helpers/api-component-card
      {:component-name "drawer-content"
       :description "Drawer content component"
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":show-handle?"
                :type "boolean"
                :default "true"
                :description
                "Compatibility handle; set false when composing drawer-handle explicitly"}]}]
     [helpers/api-component-card
      {:component-name "drawer-handle"
       :description "Vaul drag handle; supports dragging and click-to-cycle snap points"
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":prevent-cycle"
                :type "boolean"
                :default "false"
                :description "Prevent clicking the handle from cycling snap points"}]}]
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
 "Bottom drawer with form example.\n   \n   Common use case for mobile: a drawer that slides up from the bottom\n   with form fields or actions."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.bottom-drawer")}
 []
 (r/as-element (helpers/wrap-component [bottom-drawer/example])))

(defstory RightDrawer
          "Right-side drawer with an independently scrollable body and persistent footer."
          {:source-code (embed-source "clojure-shadcn.stories.drawer.right-drawer")}
          []
          (r/as-element (helpers/wrap-component [right-drawer/example])))

(defstory
 DrawerWithScroll
 "Bottom drawer with scrollable content.\n   \n   Shows how the drawer handles overflow content with max-height."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.drawer-with-scroll")}
 []
 (r/as-element (helpers/wrap-component [drawer-with-scroll/example])))

(defstory
 DrawerNoScale
 "Drawer without background scaling.\n   \n   By default, the background scales down slightly when drawer opens.\n   This example shows how to disable that effect."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.drawer-no-scale")}
 []
 (r/as-element (helpers/wrap-component [drawer-no-scale/example])))

(defstory
 TopDrawer
 "Top drawer variant.\n\n  Useful for non-critical announcements or quick filters sliding from top."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.top-drawer")}
 []
 (r/as-element (helpers/wrap-component [top-drawer/example])))

(defstory
 LeftDrawerNonModal
 "Left drawer with non-modal behavior.\n\n  Demonstrates :direction :left and :modal false for side-by-side workflows."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.left-drawer-non-modal")}
 []
 (r/as-element (helpers/wrap-component [left-drawer-non-modal/example])))


(defstory StatefulGoalDrawer
          "Stateful mobile-style drawer for adjusting and saving a daily activity goal."
          {:source-code (embed-source "clojure-shadcn.stories.drawer.stateful-goal-drawer")}
          []
          (r/as-element (helpers/wrap-component [stateful-goal-drawer/example])))

(defstory DrawerRtl
          "Right-to-left drawer with localized labels and direction-aware alignment."
          {:source-code (embed-source "clojure-shadcn.stories.drawer.drawer-rtl")}
          []
          (r/as-element (helpers/wrap-component [drawer-rtl/example])))

(defstory
 NestedDrawer
 "A Vaul nested root inside its parent content. Opening the child lets Vaul transform the parent as a stack."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.nested-drawer")}
 []
 (r/as-element (helpers/wrap-component [nested-drawer/example])))

(defstory SnapPointsDrawer
          "Controlled snap points. Drag or click the Vaul handle to move between stops."
          {:source-code (embed-source "clojure-shadcn.stories.drawer.snap-points-drawer")}
          []
          (r/as-element (helpers/wrap-component [snap-points-drawer/example])))

(defstory
 SwipeHandleOnly
 "Only the explicit Vaul handle starts a swipe; content interactions do not drag the drawer."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.swipe-handle-only")}
 []
 (r/as-element (helpers/wrap-component [swipe-handle-only/example])))

(defstory
 ResponsiveDrawerDialog
 "The same controlled workflow uses a dialog from 768px upward and a bottom drawer below it."
 {:source-code (embed-source "clojure-shadcn.stories.drawer.responsive-drawer-dialog")}
 []
 (r/as-element (helpers/wrap-component [responsive-drawer-dialog/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:direction "bottom"}
           :arg-types {:direction {:name "Direction"
                                   :control {:type "select"}
                                   :options ["top" "right" "bottom" "left"]}}
           :parameters {:playground {:description "Explore drawer direction with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "default-open" "on-open-change"]}}
           :decode-args (fn [{:keys [direction]
                              :as args}]
                          (cond-> args
                            direction (update :direction keyword)))
           :source-code (embed-source "clojure-shadcn.stories.drawer.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
