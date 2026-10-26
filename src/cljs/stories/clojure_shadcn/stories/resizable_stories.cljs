(ns clojure-shadcn.stories.resizable-stories
  (:require
   [clojure-shadcn.stories.helpers                           :as helpers]
   [clojure-shadcn.stories.resizable.horizontal              :as example-horizontal]
   [clojure-shadcn.stories.resizable.nested                  :as example-nested]
   [clojure-shadcn.stories.resizable.playground              :as example-playground]
   [clojure-shadcn.stories.resizable.resizable-handle-states :as example-resizable-handle-states]
   [clojure-shadcn.stories.resizable.rtl                     :as example-rtl]
   [clojure-shadcn.stories.resizable.vertical                :as example-vertical]
   [reagent.core                                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Resizable"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard-accessible resizable panel layouts."
                        :npm-install "npm install react-resizable-panels lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.resizable")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/resizable.cljs"
                        :filename "resizable.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "panel-group"
      :link {:href "https://github.com/bvaughn/react-resizable-panels"
             :label "react-resizable-panels Docs"}
      :description
      "Accessible panel Group. Compose Panel and Separator siblings in alternating order; the library supplies pointer and keyboard resizing semantics."
      :props [{:name ":orientation"
               :type ":horizontal | :vertical"
               :default ":horizontal"
               :description "Converted to a string and controls group axis."}
              {:name ":on-layout"
               :type "function"
               :default nil
               :description
               "Receives the library layout converted from JavaScript to ordinary CLJS data."}
              {:name ":class"
               :type "string"
               :default nil
               :description "Classes merged onto Group."}
              {:name "Group props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded to react-resizable-panels Group."}]}]
    [helpers/api-component-card
     {:component-name "panel"
      :description "Thin wrapper over react-resizable-panels Panel."
      :props
      [{:name "Panel props"
        :type "map entries"
        :default nil
        :description
        "Forwarded directly, plus data-slot. Use the installed library version's prop names and units."}]}]
    [helpers/api-component-card
     {:component-name "handle"
      :description
      "Accessible react-resizable-panels Separator between adjacent panels, optionally showing a grip icon. Do not replace it with a decorative div: Separator supplies resize interaction and ARIA state."
      :props [{:name ":with-handle?"
               :type "boolean"
               :default nil
               :description "Shows the centered visual grip; does not change interaction."}
              {:name ":class"
               :type "string"
               :default nil
               :description "Classes merged onto Separator."}
              {:name "Separator props"
               :type "map entries"
               :default nil
               :description "Forwarded after wrapper-only props are removed."}]}]])))

(defstory Horizontal
          {:source-code (embed-source "clojure-shadcn.stories.resizable.horizontal")}
          []
          (r/as-element (helpers/wrap-component [example-horizontal/example])))


(defstory
 Vertical
 "Dedicated top/bottom split. The group owns vertical keyboard and pointer resize semantics."
 {:source-code (embed-source "clojure-shadcn.stories.resizable.vertical")}
 []
 (r/as-element (helpers/wrap-component [example-vertical/example])))

(defstory
 Nested
 "Nested panel groups: a horizontal workspace containing an independent vertical detail split."
 {:source-code (embed-source "clojure-shadcn.stories.resizable.nested")}
 []
 (r/as-element (helpers/wrap-component [example-nested/example])))

(defstory
 Rtl
 "RTL document direction with panels ordered from the inline start; resize behavior remains library-owned."
 {:source-code (embed-source "clojure-shadcn.stories.resizable.rtl")}
 []
 (r/as-element (helpers/wrap-component [example-rtl/example])))

(defstory
 ResizableHandleStates
 "Separators remain interactive without a grip icon; the second example exposes the focus-visible treatment for documentation."
 {:source-code (embed-source "clojure-shadcn.stories.resizable.resizable-handle-states")}
 []
 (r/as-element (helpers/wrap-component [example-resizable-handle-states/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:orientation "horizontal"}
           :arg-types {:orientation {:name "Orientation"
                                     :control {:type "select"}
                                     :options ["horizontal" "vertical"]}}
           :parameters {:playground {:description
                                     "Explore resizable orientation with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "on-layout"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))
           :source-code (embed-source "clojure-shadcn.stories.resizable.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
