(ns clojure-shadcn.stories.attachment-stories
  (:require
   ["lucide-react"                          :refer [FileText X]]
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.attachment :as sut]
   [reagent.core                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Attachment"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Current shadcn/ui attachment states and layouts."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.attachment")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/attachment.cljs"
                        :filename "attachment.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6 max-w-4xl space-y-4"}
             [helpers/api-component-card
              {:component-name "attachment / attachment-group"
               :link {:href "https://ui.shadcn.com/docs/components/attachment"
                      :label "shadcn/ui Attachment"}
               :description "Stateful attachment shell and horizontally scrolling group."
               :props [{:name ":state"
                        :type ":idle | :uploading | :processing | :error | :done"
                        :default ":done"}
                       {:name ":size"
                        :type ":default | :sm | :xs"
                        :default ":default"}
                       {:name ":orientation"
                        :type ":horizontal | :vertical"
                        :default ":horizontal"}]}]
             [helpers/api-component-card
              {:component-name
               "attachment-media / content / title / description / actions / action / trigger"
               :description
               "Composition slots. Trigger overlays the attachment; action delegates to Button."
               :props [{:name ":variant (media)"
                        :type ":icon | :image"
                        :default ":icon"}
                       {:name ":as-child? (trigger)"
                        :type "boolean"
                        :default "false"}]}]])))

(defstory AttachmentStates
          "Interactive attachment playground."
          {:args {:state "done"
                  :size "default"
                  :orientation "horizontal"
                  :title "invoice.pdf"}
           :arg-types {:state {:control {:type "select"}
                               :options ["idle" "uploading" "processing" "error" "done"]}
                       :size {:control {:type "select"}
                              :options ["xs" "sm" "default"]}
                       :orientation {:control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :title {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "on-click"]}}
           :decode-args (fn [{:keys [state size orientation]
                              :as args}]
                          (cond-> args
                            state (update :state keyword)
                            size (update :size keyword)
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element
           (helpers/wrap-component
            [sut/attachment-group {}
             [sut/attachment
              (select-keys args [:state :size :orientation])
              [sut/attachment-media {}
               [:> FileText]]
              [sut/attachment-content {}
               [sut/attachment-title {}
                (:title args)]
               [sut/attachment-description {}
                (let [state (:state args)]
                  (cond
                    (keyword? state) (name state)
                    (some? state) (str state)
                    :else ""))]]
              [sut/attachment-actions {}
               [sut/attachment-action {:aria-label "Remove"}
                [:> X]]]]])))

(defstory
 AttachmentImageStateMatrix
 "Image attachments across every supported lifecycle state."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid gap-4 sm:grid-cols-2 lg:grid-cols-5"}
    (for [state [:idle :uploading :processing :error :done]]
      ^{:key state}
      [sut/attachment {:state state
                       :orientation :vertical}
       [sut/attachment-media {:variant :image}
        [:img {:src
               "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=160&h=160&fit=crop"
               :alt "Mountain landscape"}]]
       [sut/attachment-content {}
        [sut/attachment-title {}
         "landscape.jpg"]
        [sut/attachment-description {}
         (name state)]]
       [sut/attachment-actions {}
        [sut/attachment-action {:aria-label "Remove landscape.jpg"}
         [:> X]]]])])))

(defstory
 AttachmentSizeGallery
 "All supported sizes in the same file-row composition, making density and action hit areas comparable."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid max-w-2xl gap-4 sm:grid-cols-3"}
    (for [[size label] [[:default "Default"] [:sm "Small"] [:xs "Extra small"]]]
      ^{:key size}
      [:div {:class "space-y-2"}
       [:p {:class "text-xs font-medium text-muted-foreground"}
        label]
       [sut/attachment {:size size}
        [sut/attachment-media {}
         [:> FileText]]
        [sut/attachment-content {}
         [sut/attachment-title {}
          "roadmap.pdf"]
         [sut/attachment-description {}
          "PDF · 840 KB"]]
        [sut/attachment-actions {}
         [sut/attachment-action {:aria-label (str "Remove " label " attachment")}
          [:> X]]]]])])))

(defstory AttachmentOverflowGroup
          "A horizontally scrollable attachment group for narrow layouts and long filenames."
          []
          (r/as-element (helpers/wrap-component [sut/attachment-group {:class "max-w-md"
                                                                       :aria-label "Project files"}
                                                 (for [index (range 1 8)]
                                                   ^{:key index}
                                                   [sut/attachment {:size :sm}
                                                    [sut/attachment-media {}
                                                     [:> FileText]]
                                                    [sut/attachment-content {}
                                                     [sut/attachment-title {}
                                                      (str "quarterly-report-final-" index ".pdf")]
                                                     [sut/attachment-description {}
                                                      "2.4 MB"]]])])))

(defstory
 AttachmentFullCardTrigger
 "The overlay trigger makes the full card openable while actions remain independently clickable."
 []
 (r/as-element (helpers/wrap-component
                [sut/attachment {}
                 [sut/attachment-trigger {:aria-label "Open project brief"
                                          :on-click #(js/console.log "open project brief")}]
                 [sut/attachment-media {}
                  [:> FileText]]
                 [sut/attachment-content {}
                  [sut/attachment-title {}
                   "project-brief.pdf"]
                  [sut/attachment-description {}
                   "Click anywhere to open"]]
                 [sut/attachment-actions {}
                  [sut/attachment-action {:aria-label "Remove project brief"
                                          :on-click #(js/console.log "remove project brief")}
                   [:> X]]]])))
