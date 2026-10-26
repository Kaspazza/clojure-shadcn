(ns clojure-shadcn.stories.stepper-stories
  "Storybook stories for the Stepper component. Ported from mateuszmazurczak.portfolio.ui-components.stepper."
  (:require
   [clojure-shadcn.stories.helpers                           :as helpers]
   [clojure-shadcn.stories.stepper.playground                :as example-playground]
   [clojure-shadcn.stories.stepper.stepper-circle            :as example-stepper-circle]
   [clojure-shadcn.stories.stepper.stepper-custom-icons      :as example-stepper-custom-icons]
   [clojure-shadcn.stories.stepper.stepper-disabled-steps    :as example-stepper-disabled-steps]
   [clojure-shadcn.stories.stepper.stepper-form-content      :as example-stepper-form-content]
   [clojure-shadcn.stories.stepper.stepper-horizontal        :as example-stepper-horizontal]
   [clojure-shadcn.stories.stepper.stepper-label-orientation :as example-stepper-label-orientation]
   [clojure-shadcn.stories.stepper.stepper-reverse-progress  :as example-stepper-reverse-progress]
   [clojure-shadcn.stories.stepper.stepper-vertical          :as example-stepper-vertical]
   [reagent.core                                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Stepper"
       :parameters #js {:layout "padded"}})



(defdoc
 Installation
 "Install dependencies and copy the component code into your project."
 []
 (r/as-element
  [helpers/installation-scene
   {:description
    "Custom context-based stepper inspired by shadcn/ui composition patterns; it is not a current official shadcn/ui component."
    :npm-install "npm install react"
    :source-code (embed-source "clojure-shadcn.ui.components.stepper")
    :namespace-path "src/cljs/clojure_shadcn/ui/components/stepper.cljs"
    :filename "stepper.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "stepper"
       :description
       "Custom root context provider for multi-step flows, inspired by shadcn/ui composition conventions rather than copied from an official shadcn/ui Stepper. Coordinates caller-owned active state shared by navigation and panels."
       :props [{:name ":current-step"
                :type "string | keyword | number"
                :default nil
                :description "Active step id."}
               {:name ":on-step-change"
                :type "function"
                :default nil
                :description "Callback when step changes: (fn [step-id])."}
               {:name ":variant"
                :type "keyword"
                :default ":horizontal"
                :description ":horizontal | :vertical | :circle."}
               {:name ":label-orientation"
                :type "keyword"
                :default ":horizontal"
                :description ":horizontal | :vertical."}
               {:name ":reverse-progress?"
                :type "boolean"
                :default "false"
                :description "Marks progress in reverse direction."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "stepper-navigation"
       :description
       "Navigation container that auto-indexes stepper-step children and injects step metadata."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "stepper-step"
       :description
       "Interactive step node with indicator, title/description slots, and optional icon override."
       :props [{:name ":id"
                :type "keyword | string | number"
                :default nil
                :description "Step identifier."}
               {:name ":disabled?"
                :type "boolean"
                :default nil
                :description "Disables interaction for this step."}
               {:name ":icon"
                :type "hiccup | React element"
                :default nil
                :description "Replaces numeric step indicator."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "stepper-panel"
       :description "Content panel bound to a specific step id; renders only when active."
       :props [{:name ":id"
                :type "keyword | string | number"
                :default nil
                :description "Step id this panel belongs to."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "stepper-title"
                                  :description "Title text slot for step labels."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "stepper-description"
                                  :description "Secondary description text slot for step labels."
                                  :props [[":class"
                                           "string, optional - Additional Tailwind classes."]]}]
     [helpers/api-component-card {:component-name "stepper-controls"
                                  :description
                                  "Layout container for navigation buttons (Back/Next/Finish)."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "stepper-step and stepper-panel require stepper context; use them inside [stepper ...]."]
       [:li "Each stepper-step :id should have a corresponding stepper-panel :id for complete UX."]
       [:li
        "stepper-navigation auto-injects index metadata; avoid manually setting :index/:total props."]
       [:li
        "Known Circle gap: the current :circle branch displays progress and labels but does not render an interactive step button. This story leaves that limitation visible."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [current (r/atom :details)]\n  [stepper {:current-step @current\n            :on-step-change #(reset! current %)}\n   [stepper-navigation {}\n    [stepper-step {:id :details} [stepper-title {} \"Details\"]]\n    [stepper-step {:id :billing} [stepper-title {} \"Billing\"]]]\n   [stepper-panel {:id :details} [:div \"Details form\"]]\n   [stepper-panel {:id :billing} [:div \"Billing form\"]]])"]]]]])))

(defstory
 StepperHorizontal
 "Horizontal stepper with panels.
  Uses React Context to share step state across components.

  Best for linear multi-step flows."
 {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-horizontal")}
 []
 (r/as-element (helpers/wrap-component [example-stepper-horizontal/example])))

(defstory StepperVertical
          "Vertical stepper layout.
  Use :variant :vertical for stacked steps and panels."
          {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-vertical")}
          []
          (r/as-element (helpers/wrap-component [example-stepper-vertical/example])))

(defstory
 StepperCircle
 "Circle progress rendering with the current implementation gap exposed: it shows count/label but cannot change steps from the circle itself."
 {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-circle")}
 []
 (r/as-element (helpers/wrap-component [example-stepper-circle/example])))

(defstory
 StepperReverseProgress
 "Reverse progress for newest-first lists.
  Use :reverse-progress? to mark later steps as completed."
 {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-reverse-progress")}
 []
 (r/as-element (helpers/wrap-component [example-stepper-reverse-progress/example])))

(defstory StepperDisabledSteps
          "Stepper with disabled steps.
  Disabled steps are not interactive and appear muted."
          {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-disabled-steps")}
          []
          (r/as-element (helpers/wrap-component [example-stepper-disabled-steps/example])))

(defstory StepperLabelOrientation
          "Vertical label orientation.
  Use :label-orientation :vertical for compact headers."
          {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-label-orientation")}
          []
          (r/as-element (helpers/wrap-component [example-stepper-label-orientation/example])))

(defstory StepperCustomIcons
          "Stepper with custom icons per step.
  Provide an :icon to override the step number."
          {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-custom-icons")}
          []
          (r/as-element (helpers/wrap-component [example-stepper-custom-icons/example])))

(defstory
 StepperFormContent
 "Stepper panels with form-like content.
  Panels can host any content, including forms or summaries."
 {:source-code (embed-source "clojure-shadcn.stories.stepper.stepper-form-content")}
 []
 (r/as-element (helpers/wrap-component [example-stepper-form-content/example])))


(defstory
 Playground
 "Interactive stepper playground."
 {:args {:current-step "details"
         :variant "horizontal"
         :label-orientation "horizontal"
         :reverse-progress false}
  :arg-types {:current-step {:name "Current step"
                             :control {:type "select"}
                             :options ["details" "billing" "confirm"]}
              :variant {:name "Variant"
                        :control {:type "select"}
                        :options ["horizontal" "vertical" "circle"]}
              :label-orientation {:name "Label orientation"
                                  :control {:type "select"}
                                  :options ["horizontal" "vertical"]}
              :reverse-progress {:name "Reverse progress"
                                 :control {:type "boolean"}}}
  :parameters
  {:playground
   {:description
    "Explore stepper current step, variant, label orientation and reverse progress with a live preview."}
   :controls {:expanded false
              :exclude ["on-step-change" "class"]}}
  :decode-args (fn [{:keys [current-step variant label-orientation reverse-progress]
                     :as args}]
                 (-> args
                     (assoc :current-step (keyword current-step)
                            :variant (keyword variant)
                            :label-orientation (keyword label-orientation)
                            :reverse-progress? reverse-progress)
                     (dissoc :reverse-progress)))
  :source-code (embed-source "clojure-shadcn.stories.stepper.playground")}
 [args]
 (r/as-element (helpers/wrap-component [example-playground/example args])))
\n