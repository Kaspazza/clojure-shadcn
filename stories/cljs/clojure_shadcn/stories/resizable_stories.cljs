(ns clojure-shadcn.stories.resizable-stories
  (:require
   [clojure-shadcn.stories.helpers         :as helpers]
   [clojure-shadcn.ui.components.resizable :as sut]
   [reagent.core                           :as r])
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
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "h-48 w-[600px] overflow-hidden rounded-lg border"}
                          [sut/panel-group {:orientation :horizontal}
                           [sut/panel {:defaultSize 35}
                            [:div {:class "grid h-full place-items-center"}
                             "Navigation"]]
                           [sut/handle {:with-handle? true}]
                           [sut/panel {:defaultSize 65}
                            [:div {:class "grid h-full place-items-center"}
                             "Content"]]]])))


(defstory
 Vertical
 "Dedicated top/bottom split. The group owns vertical keyboard and pointer resize semantics."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "h-[420px] w-[520px] overflow-hidden rounded-lg border"}
                 [sut/panel-group {:orientation :vertical}
                  [sut/panel {:defaultSize 35
                              :minSize 20}
                   [:div {:class "grid h-full place-items-center bg-muted/30"}
                    "Preview"]]
                  [sut/handle {:with-handle? true
                               :aria-label "Resize preview and console"}]
                  [sut/panel {:defaultSize 65
                              :minSize 25}
                   [:div {:class "grid h-full place-items-center"}
                    "Console"]]]])))

(defstory
 Nested
 "Nested panel groups: a horizontal workspace containing an independent vertical detail split."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "h-[420px] w-[720px] overflow-hidden rounded-lg border"}
    [sut/panel-group {:orientation :horizontal}
     [sut/panel {:defaultSize 28
                 :minSize 18}
      [:div {:class "grid h-full place-items-center bg-muted/30"}
       "Files"]]
     [sut/handle {:with-handle? true
                  :aria-label "Resize files and workspace"}]
     [sut/panel {:defaultSize 72}
      [sut/panel-group {:orientation :vertical}
       [sut/panel {:defaultSize 70}
        [:div {:class "grid h-full place-items-center"}
         "Editor"]]
       [sut/handle {:with-handle? true
                    :aria-label "Resize editor and terminal"}]
       [sut/panel {:defaultSize 30
                   :minSize 15}
        [:div {:class "grid h-full place-items-center bg-muted/30"}
         "Terminal"]]]]]])))

(defstory
 Rtl
 "RTL document direction with panels ordered from the inline start; resize behavior remains library-owned."
 []
 (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                              :class
                                              "h-48 w-[600px] overflow-hidden rounded-lg border"}
                                        [sut/panel-group {:orientation :horizontal}
                                         [sut/panel {:defaultSize 35}
                                          [:div {:class "grid h-full place-items-center"}
                                           "التنقل"]]
                                         [sut/handle {:with-handle? true
                                                      :aria-label "تغيير حجم اللوحات"}]
                                         [sut/panel {:defaultSize 65}
                                          [:div {:class "grid h-full place-items-center"}
                                           "المحتوى"]]]])))

(defstory ResizablePlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:orientation "horizontal"}
           :arg-types {:orientation {:control {:type "select"}
                                     :options ["horizontal" "vertical"]}}
           :parameters {:controls {:exclude ["children" "on-layout"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component
                         [:div {:class "h-48 w-[600px] overflow-hidden rounded-lg border"}
                          [sut/panel-group
                           (select-keys args [:orientation])
                           [sut/panel {:defaultSize 40}
                            [:div {:class "grid h-full place-items-center"}
                             "Navigation"]]
                           [sut/handle {:with-handle? true}]
                           [sut/panel {:defaultSize 60}
                            [:div {:class "grid h-full place-items-center"}
                             "Content"]]]])))
