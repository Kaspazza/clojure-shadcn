(ns clojure-shadcn.stories.attachment-stories
  (:require
   [clojure-shadcn.stories.attachment.playground :as playground]
   [clojure-shadcn.stories.attachment.attachment-image-state-matrix :as attachment-image-state-matrix]
   [clojure-shadcn.stories.attachment.attachment-size-gallery :as attachment-size-gallery]
   [clojure-shadcn.stories.attachment.attachment-overflow-group :as attachment-overflow-group]
   [clojure-shadcn.stories.attachment.attachment-full-card-trigger :as attachment-full-card-trigger]
   [clojure-shadcn.stories.helpers :as helpers]
   [reagent.core :as r])
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

(defstory Playground
          "Explore attachment state, size, orientation, and filename with live controls."
          {:source-code (embed-source "clojure-shadcn.stories.attachment.playground")
           :args {:state "done"
                  :size "default"
                  :orientation "horizontal"
                  :title "invoice.pdf"}
           :arg-types {:state {:name "State"
                               :control {:type "select"}
                               :options ["idle" "uploading" "processing" "error" "done"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["xs" "sm" "default"]}
                       :orientation {:name "Layout"
                                     :control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :title {:name "Filename"
                               :control {:type "text"}}}
           :parameters {:playground
                        {:description
                         "Explore attachment states, sizes, and layouts with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "on-click"]}}
           :decode-args (fn [{:keys [state size orientation]
                              :as args}]
                          (cond-> args
                            state (update :state keyword)
                            size (update :size keyword)
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory AttachmentImageStateMatrix
 "Image attachments across every supported lifecycle state."
 {:source-code (embed-source "clojure-shadcn.stories.attachment.attachment-image-state-matrix")}
 []
 (r/as-element (helpers/wrap-component [attachment-image-state-matrix/example])))

(defstory AttachmentSizeGallery
 "All supported sizes in the same file-row composition, making density and action hit areas comparable."
 {:source-code (embed-source "clojure-shadcn.stories.attachment.attachment-size-gallery")}
 []
 (r/as-element (helpers/wrap-component [attachment-size-gallery/example])))

(defstory AttachmentOverflowGroup
 "A horizontally scrollable attachment group for narrow layouts and long filenames."
 {:source-code (embed-source "clojure-shadcn.stories.attachment.attachment-overflow-group")}
 []
 (r/as-element (helpers/wrap-component [attachment-overflow-group/example])))

(defstory AttachmentFullCardTrigger
 "The overlay trigger makes the full card openable while actions remain independently clickable."
 {:source-code (embed-source "clojure-shadcn.stories.attachment.attachment-full-card-trigger")}
 []
 (r/as-element (helpers/wrap-component [attachment-full-card-trigger/example])))
