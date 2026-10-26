(ns clojure-shadcn.stories.sheet-stories
  "Storybook stories for the Sheet component. Ported from mateuszmazurczak.portfolio.ui-components.sheet."
  (:require
   [clojure-shadcn.stories.helpers                          :as helpers]
   [clojure-shadcn.stories.sheet.playground                 :as example-playground]
   [clojure-shadcn.stories.sheet.sheet-demo                 :as example-sheet-demo]
   [clojure-shadcn.stories.sheet.sheet-rtl                  :as example-sheet-rtl]
   [clojure-shadcn.stories.sheet.sheet-scrollable           :as example-sheet-scrollable]
   [clojure-shadcn.stories.sheet.sheet-side                 :as example-sheet-side]
   [clojure-shadcn.stories.sheet.sheet-without-close-button :as example-sheet-without-close-button]
   [reagent.core                                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Sheet"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Sheet (drawer/slide-out) component for modal content that slides in from edges."
           :npm-install "npm install @radix-ui/react-dialog lucide-react"
           :source-code (embed-source "clojure-shadcn.ui.components.sheet")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/sheet.cljs"
           :filename "sheet.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "sheet"
       :description "Radix Dialog.Root alias used as sheet root controller."
       :link {:href "https://ui.shadcn.com/docs/components/sheet"
              :label "shadcn Sheet Docs"}
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback when state changes: (fn [open?] ...)."}
               {:name ":modal"
                :type "boolean"
                :default "true"
                :description "Whether sheet is modal."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Dialog.Root."}]}]
     [helpers/api-component-card {:component-name "sheet-trigger"
                                  :description "Control that opens the sheet."
                                  :props [{:name ":as-child"
                                           :type "boolean"
                                           :default nil
                                           :description "Compose with a child element."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Trigger."}]}]
     [helpers/api-component-card {:component-name "sheet-close"
                                  :description "Control that closes the sheet."
                                  :props [{:name ":as-child"
                                           :type "boolean"
                                           :default nil
                                           :description "Compose with a child element."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Close."}]}]
     [helpers/api-component-card {:component-name "sheet-overlay"
                                  :description "Backdrop overlay rendered behind the sheet."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Dialog overlay."}]}]
     [helpers/api-component-card
      {:component-name "sheet-content"
       :description "Sliding panel container with built-in overlay and close button."
       :props [{:name ":side"
                :type "keyword"
                :default ":right"
                :description "One of: :top | :bottom | :left | :right."}
               {:name ":show-close-button"
                :type "boolean"
                :default "true"
                :description "Whether to render the generated corner close button."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Dialog content."}]}]
     [helpers/api-component-card {:component-name "sheet-header"
                                  :description
                                  "Layout wrapper for sheet-title and sheet-description."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying div."}]}]
     [helpers/api-component-card {:component-name "sheet-footer"
                                  :description "Responsive action area for sheet controls."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying div."}]}]
     [helpers/api-component-card {:component-name "sheet-title"
                                  :description "Accessible title announced by screen readers."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Dialog.Title."}]}]
     [helpers/api-component-card {:component-name "sheet-description"
                                  :description "Accessible supporting text for sheet content."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Dialog.Description."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "All exported sheet components are ordinary Reagent wrappers and use standard vector invocation."]
       [:li "Use :side on sheet-content to control animation direction and panel placement."]
       [:li "For forms, keep explicit close controls (sheet-close) in footer for predictable UX."]
       [:li
        "Set :show-close-button false on sheet-content when the generated corner control is not appropriate; provide another clear dismissal control."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[sheet {}\n [sheet-trigger {:as-child true}\n  [button {:variant :outline} \"Open\"]]\n [sheet-content {:side :right}\n  [sheet-header {}\n   [sheet-title {} \"Edit profile\"]\n   [sheet-description {} \"Update details and save.\"]]\n  [sheet-footer {}\n   [sheet-close {:as-child true} [button {:variant :outline} \"Close\"]]]]]"]]]]])))

(defstory
 SheetDemo
 "Basic sheet with profile form.

  Radix primitive: @radix-ui/react-dialog

  All exported sheet components are ordinary Reagent wrapper functions.

  Sheets slide in from an edge to reveal secondary content."
 {:source-code (embed-source "clojure-shadcn.stories.sheet.sheet-demo")}
 []
 (r/as-element (helpers/wrap-component [example-sheet-demo/example])))

(defstory
 SheetWithoutCloseButton
 "Sheet without the generated corner close button. An explicit footer action remains available for dismissal."
 {:source-code (embed-source "clojure-shadcn.stories.sheet.sheet-without-close-button")}
 []
 (r/as-element (helpers/wrap-component [example-sheet-without-close-button/example])))

(defstory
 SheetSide
 "Sheets on all four sides.

  Radix primitive: @radix-ui/react-dialog

  Use :side to control where the sheet appears."
 {:source-code (embed-source "clojure-shadcn.stories.sheet.sheet-side")}
 []
 (r/as-element (helpers/wrap-component [example-sheet-side/example])))

(defstory
 SheetScrollable
 "Sheet with scrollable content.

  Radix primitive: @radix-ui/react-dialog

  Use overflow classes to handle long content in sheets."
 {:source-code (embed-source "clojure-shadcn.stories.sheet.sheet-scrollable")}
 []
 (r/as-element (helpers/wrap-component [example-sheet-scrollable/example])))


(defstory SheetRtl
          "Right-to-left sheet content with localized labels and explicit close action."
          {:source-code (embed-source "clojure-shadcn.stories.sheet.sheet-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-sheet-rtl/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:side "right"}
           :arg-types {:side {:name "Side"
                              :control {:type "select"}
                              :options ["top" "right" "bottom" "left"]}}
           :parameters {:playground {:description "Explore sheet side with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "default-open" "on-open-change"]}}
           :decode-args (fn [{:keys [side]
                              :as args}]
                          (cond-> args
                            side (update :side keyword)))
           :source-code (embed-source "clojure-shadcn.stories.sheet.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
