(ns clojure-shadcn.stories.button-stories
  (:require
   [clojure-shadcn.stories.button.button-as-child           :as button-as-child]
   [clojure-shadcn.stories.button.button-destructive        :as button-destructive]
   [clojure-shadcn.stories.button.button-ghost              :as button-ghost]
   [clojure-shadcn.stories.button.button-icon               :as button-icon]
   [clojure-shadcn.stories.button.button-icon-size-matrix   :as button-icon-size-matrix]
   [clojure-shadcn.stories.button.button-link               :as button-link]
   [clojure-shadcn.stories.button.button-loading            :as button-loading]
   [clojure-shadcn.stories.button.button-loading-placements :as button-loading-placements]
   [clojure-shadcn.stories.button.button-outline            :as button-outline]
   [clojure-shadcn.stories.button.button-rounded            :as button-rounded]
   [clojure-shadcn.stories.button.button-rtl                :as button-rtl]
   [clojure-shadcn.stories.button.button-secondary          :as button-secondary]
   [clojure-shadcn.stories.button.button-size               :as button-size]
   [clojure-shadcn.stories.button.button-with-icon          :as button-with-icon]
   [clojure-shadcn.stories.button.button-xs-size            :as button-xs-size]
   [clojure-shadcn.stories.button.playground                :as playground]
   [clojure-shadcn.stories.helpers                          :as helpers]
   [reagent.core                                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Button"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Button component with support for multiple variants and sizes."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.button")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/button.cljs"
                        :filename "button.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-6"}
     [:div {:class "space-y-4"}
      [helpers/api-component-card
       {:component-name "button / button-variants"
        :link {:href "https://ui.shadcn.com/docs/components/button"
               :label "shadcn Button Docs"}
        :description
        "Polymorphic button primitive with variant + size systems. Supports regular button rendering or slot-based composition via :as-child. Additional props are forwarded to the underlying element."
        :props
        [{:name ":variant"
          :type "keyword"
          :default ":default"
          :description "One of: :default | :destructive | :outline | :secondary | :ghost | :link"}
         {:name ":size"
          :type "keyword"
          :default ":default"
          :description
          "One of: :xs | :sm | :default | :lg | :icon | :icon-xs | :icon-sm | :icon-lg"}
         {:name ":as-child"
          :type "boolean"
          :default "false"
          :description "Render using Radix Slot and pass styles/behavior to the child element."}
         {:name ":disabled"
          :type "boolean"
          :default nil
          :description "Disables interaction and applies disabled styles."}
         {:name ":type"
          :type "string"
          :default nil
          :description "Native button type: \"button\" | \"submit\" | \"reset\"."}
         {:name ":on-click"
          :type "function"
          :default nil
          :description "Click handler: (fn [event] ...)."}
         {:name ":class"
          :type "string"
          :default nil
          :description "Additional Tailwind classes."}
         {:name "additional props"
          :type "map entries"
          :default nil
          :description "Forwarded to the rendered element (button or slotted child)."}]}]
      [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
       [:h4 {:class "text-sm font-semibold mb-2"}
        "⚠️ Important Notes"]
       [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
        [:li "Use :type \"submit\" inside forms to trigger native form submission semantics."]
        [:li
         "When :as-child is true, ensure the child is an interactive element (<a>, <button>, etc.) for accessibility."]
        [:li
         "Prefer semantic variants (:destructive for dangerous actions) over one-off color overrides."]]]
      [:div {:class "border rounded-lg p-4 bg-muted/50"}
       [:h4 {:class "text-sm font-semibold mb-2"}
        "Usage Example"]
       [:pre {:class "text-xs overflow-x-auto"}
        [:code
         "[:div {:class \"flex items-center gap-2\"}\n [button {:variant :default\n          :on-click #(js/console.log \"continue\")}\n  \"Continue\"]\n [button {:variant :outline\n          :size :icon\n          :aria-label \"Open settings\"}\n  [:> ArrowUpRight]]\n [button {:as-child true}\n  [:a {:href \"/docs\"} \"Documentation\"]]]"]]]]]])))

(defstory
 Playground
 "Interactive Button playground for primary actions.

  Radix primitive: @radix-ui/react-slot (for :as-child polymorphism)

  Our CLJS wrapper uses keyword props (:variant, :size) instead of
  string variants. The default variant is :default."
 {:source-code (embed-source "clojure-shadcn.stories.button.playground")
  :args {:label "Continue"
         :variant "default"
         :size "default"
         :disabled false}
  :arg-types {:label {:name "Label"
                      :control {:type "text"}}
              :variant {:name "Variant"
                        :control {:type "select"}
                        :options ["default" "destructive" "outline" "secondary" "ghost" "link"]}
              :size {:name "Size"
                     :control {:type "select"}
                     :options ["xs" "sm" "default" "lg" "icon" "icon-xs" "icon-sm" "icon-lg"]}
              :disabled {:name "Disabled"
                         :control {:type "boolean"}}}
  :parameters {:playground {:description
                            "Explore button label, variant, size and disabled with a live preview."}
               :controls {:expanded false
                          :exclude ["on-click" "as-child" "class"]}}
  :decode-args (fn [{:keys [variant size]
                     :as args}]
                 (cond-> args
                   variant (update :variant keyword)
                   size (update :size keyword)))}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 ButtonDestructive
 "Destructive button for dangerous actions.

  Radix primitive: @radix-ui/react-slot

  Use :destructive for irreversible actions (delete, remove, etc.)."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-destructive")}
 []
 (r/as-element (helpers/wrap-component [button-destructive/example])))

(defstory
 ButtonOutline
 "Outlined button for secondary actions.

  Radix primitive: @radix-ui/react-slot

  Outline buttons are visually lighter but still prominent."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-outline")}
 []
 (r/as-element (helpers/wrap-component [button-outline/example])))

(defstory
 ButtonSecondary
 "Secondary button for neutral actions.

  Radix primitive: @radix-ui/react-slot

  Use :secondary to de-emphasize a primary action."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-secondary")}
 []
 (r/as-element (helpers/wrap-component [button-secondary/example])))

(defstory
 ButtonGhost
 "Ghost button for low-emphasis actions.

  Radix primitive: @radix-ui/react-slot

  Ghost buttons are useful in dense toolbars."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-ghost")}
 []
 (r/as-element (helpers/wrap-component [button-ghost/example])))

(defstory
 ButtonLink
 "Link-styled button for inline actions.

  Radix primitive: @radix-ui/react-slot

  Use :link when you want a textual action that still behaves like a button."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-link")}
 []
 (r/as-element (helpers/wrap-component [button-link/example])))

(defstory
 ButtonIcon
 "Icon-only button for compact controls.

  Radix primitive: @radix-ui/react-slot

  Use :size :icon for square icon buttons.
  Note: shadcn uses size=icon; our wrapper uses :icon."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-icon")}
 []
 (r/as-element (helpers/wrap-component [button-icon/example])))

(defstory
 ButtonWithIcon
 "Button with leading icon and label.

  Radix primitive: @radix-ui/react-slot

  Icons should be placed before text for consistent alignment."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-with-icon")}
 []
 (r/as-element (helpers/wrap-component [button-with-icon/example])))

(defstory
 ButtonLoading
 "Loading button with spinner and disabled state.

  Radix primitive: @radix-ui/react-slot

  Use a spinner + disabled to communicate in-progress actions."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-loading")}
 []
 (r/as-element (helpers/wrap-component [button-loading/example])))

(defstory
 ButtonAsChild
 "Polymorphic rendering via :as-child.

  Radix primitive: @radix-ui/react-slot

  Use :as-child to render as an anchor while preserving button styles."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-as-child")}
 []
 (r/as-element (helpers/wrap-component [button-as-child/example])))

(defstory
 ButtonSize
 "Size variants for compact or prominent buttons.

  Radix primitive: @radix-ui/react-slot

  Our wrapper supports :xs, :sm, :default, :lg, and icon sizes (:icon, :icon-xs, :icon-sm, :icon-lg)."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-size")}
 []
 (r/as-element (helpers/wrap-component [button-size/example])))

(defstory
 ButtonRounded
 "Rounded icon button for floating actions.

  Radix primitive: @radix-ui/react-slot

  Add a custom :class to achieve rounded-full styling."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-rounded")}
 []
 (r/as-element (helpers/wrap-component [button-rounded/example])))

(defstory
 ButtonXsSize
 "Extra-small button size for dense interfaces.

  Aligned with the current shadcn style profile.
  Use :size :xs for compact toolbars or inline actions."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-xs-size")}
 []
 (r/as-element (helpers/wrap-component [button-xs-size/example])))

(defstory ButtonIconSizeMatrix
          "All supported square icon sizes. Based on shadcn/ui Button — Icon Size."
          {:source-code (embed-source "clojure-shadcn.stories.button.button-icon-size-matrix")}
          []
          (r/as-element (helpers/wrap-component [button-icon-size-matrix/example])))

(defstory
 ButtonLoadingPlacements
 "Leading, trailing, and icon-only loading states across supported variants. Based on shadcn/ui Button — Spinner."
 {:source-code (embed-source "clojure-shadcn.stories.button.button-loading-placements")}
 []
 (r/as-element (helpers/wrap-component [button-loading-placements/example])))

(defstory ButtonRtl
          "Bidirectional icon placement. Based on shadcn/ui Button — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.button.button-rtl")}
          []
          (r/as-element (helpers/wrap-component [button-rtl/example])))
