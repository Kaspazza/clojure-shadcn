(ns clojure-shadcn.stories.bubble-stories
  (:require
   [clojure-shadcn.stories.bubble.bubble-conversation :as bubble-conversation]
   [clojure-shadcn.stories.bubble.bubble-alignment-and-variant-gallery :as bubble-alignment-and-variant-gallery]
   [clojure-shadcn.stories.bubble.bubble-markdown-composition :as bubble-markdown-composition]
   [clojure-shadcn.stories.bubble.bubble-group-with-reactions :as bubble-group-with-reactions]
   [clojure-shadcn.stories.bubble.bubble-as-child-links-and-buttons :as bubble-as-child-links-and-buttons]
   [clojure-shadcn.stories.bubble.bubble-interactive-compositions :as bubble-interactive-compositions]
   [clojure-shadcn.stories.helpers :as helpers]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Bubble"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Current shadcn/ui message bubble primitives."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.bubble")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/bubble.cljs"
                        :filename "bubble.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6 max-w-4xl space-y-4"}
             [helpers/api-component-card
              {:component-name "bubble-group / bubble / bubble-content"
               :description "Aligned message bubbles with Slot-capable content."
               :props
               [{:name ":variant"
                 :type ":default | :secondary | :muted | :tinted | :outline | :ghost | :destructive"
                 :default ":default"}
                {:name ":align"
                 :type ":start | :end"
                 :default ":start"}
                {:name ":as-child? (content)"
                 :type "boolean"
                 :default "false"}]}]
             [helpers/api-component-card {:component-name "bubble-reactions"
                                          :description "Reaction badge positioned against a bubble."
                                          :props [{:name ":side"
                                                   :type ":top | :bottom"
                                                   :default ":bottom"}
                                                  {:name ":align"
                                                   :type ":start | :end"
                                                   :default ":end"}]}]])))

(defstory BubbleConversation
          "Interactive message-bubble playground."
          {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-conversation")
           :args {:variant "secondary"
                  :align "start"
                  :content "Can you send the latest report?"}
           :arg-types {:variant
                       {:name "Variant"
                        :control {:type "select"}
                        :options
                        ["default" "secondary" "muted" "tinted" "outline" "ghost" "destructive"]}
                       :align {:name "Align"
                               :control {:type "select"}
                               :options ["start" "end"]}
                       :content {:name "Content"
                                 :control {:type "text"}}}
           :parameters {:playground {:description "Explore bubble variant, align and content with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "as-child?"]}}
           :decode-args (fn [{:keys [variant align]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            align (update :align keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [bubble-conversation/example args])))

(defstory BubbleAlignmentAndVariantGallery
 "Alignment changes conversation geometry while variants communicate sender, emphasis, and error semantics."
 {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-alignment-and-variant-gallery")}
 []
 (r/as-element (helpers/wrap-component [bubble-alignment-and-variant-gallery/example])))

(defstory BubbleMarkdownComposition
 "Markdown remains an explicit child composition, so Bubble owns conversation styling without owning content parsing."
 {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-markdown-composition")}
 []
 (r/as-element (helpers/wrap-component [bubble-markdown-composition/example])))

(defstory BubbleGroupWithReactions
 "A grouped exchange with reactions positioned on opposite bubble edges."
 {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-group-with-reactions")}
 []
 (r/as-element (helpers/wrap-component [bubble-group-with-reactions/example])))

(defstory BubbleAsChildLinksAndButtons
 "Use :as-child? when the entire bubble should be a semantic link or button."
 {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-as-child-links-and-buttons")}
 []
 (r/as-element (helpers/wrap-component [bubble-as-child-links-and-buttons/example])))

(defstory BubbleInteractiveCompositions
 "Bubble content can serve as a trigger for collapsible, tooltip, and popover primitives."
 {:source-code (embed-source "clojure-shadcn.stories.bubble.bubble-interactive-compositions")}
 []
 (r/as-element (helpers/wrap-component [bubble-interactive-compositions/example])))
