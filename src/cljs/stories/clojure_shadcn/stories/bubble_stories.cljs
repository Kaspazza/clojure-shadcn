(ns clojure-shadcn.stories.bubble-stories
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.bubble      :as sut]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [clojure-shadcn.ui.components.markdown    :as markdown]
   [clojure-shadcn.ui.components.popover     :as popover]
   [clojure-shadcn.ui.components.tooltip     :as tooltip]
   [reagent.core                             :as r])
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
          {:args {:variant "secondary"
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
          (r/as-element (helpers/wrap-component [sut/bubble-group {:class "max-w-lg"}
                                                 [sut/bubble
                                                  (select-keys args [:variant :align])
                                                  [sut/bubble-content {}
                                                   (:content args)]]])))

(defstory
 BubbleAlignmentAndVariantGallery
 "Alignment changes conversation geometry while variants communicate sender, emphasis, and error semantics."
 []
 (r/as-element (helpers/wrap-component
                [sut/bubble-group {:class "max-w-2xl"}
                 (for [[variant align copy] [[:default :end "Primary user message"]
                                             [:secondary :start "Secondary assistant response"]
                                             [:muted :start "Muted supporting context"]
                                             [:tinted :start "Tinted generated suggestion"]
                                             [:outline :end "Outlined pending reply"]
                                             [:ghost :start "Ghost long-form response"]
                                             [:destructive :start "Destructive delivery failure"]]]
                   ^{:key variant}
                   [sut/bubble {:variant variant
                                :align align}
                    [sut/bubble-content {}
                     copy]])])))

(defstory
 BubbleMarkdownComposition
 "Markdown remains an explicit child composition, so Bubble owns conversation styling without owning content parsing."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/bubble-group {:class "max-w-2xl"}
    [sut/bubble {:variant :muted}
     [sut/bubble-content {}
      [markdown/markdown {}
       "### Build summary\n\n- **18** tests passed\n- `release.jar` created\n\n[Open artifacts](https://example.com)"]]]])))

(defstory BubbleGroupWithReactions
          "A grouped exchange with reactions positioned on opposite bubble edges."
          []
          (r/as-element (helpers/wrap-component [sut/bubble-group {:class "max-w-lg pb-3"}
                                                 [sut/bubble {:variant :muted}
                                                  [sut/bubble-content {}
                                                   "The release notes are ready for review."]
                                                  [sut/bubble-reactions {:side :bottom
                                                                         :align :start}
                                                   "👍 3"]]
                                                 [sut/bubble {:align :end}
                                                  [sut/bubble-content {}
                                                   "Great — I’ll publish them after lunch."]
                                                  [sut/bubble-reactions {}
                                                   "✅ 1"]]])))

(defstory BubbleAsChildLinksAndButtons
          "Use :as-child? when the entire bubble should be a semantic link or button."
          []
          (r/as-element (helpers/wrap-component
                         [sut/bubble-group {:class "max-w-lg"}
                          [sut/bubble {:variant :outline}
                           [sut/bubble-content {:as-child? true}
                            [:a {:href "https://ui.shadcn.com/docs/components/bubble"
                                 :target "_blank"
                                 :rel "noopener noreferrer"}
                             "Open the Bubble documentation →"]]]
                          [sut/bubble {:variant :secondary}
                           [sut/bubble-content {:as-child? true}
                            [:button {:type "button"
                                      :on-click #(js/console.log "retry")}
                             "Retry this response"]]]])))

(defstory BubbleInteractiveCompositions
          "Bubble content can serve as a trigger for collapsible, tooltip, and popover primitives."
          []
          (r/as-element
           (helpers/wrap-component
            [:>
             tooltip/tooltip-provider-component
             {}
             [sut/bubble-group {:class "max-w-lg"}
              [collapsible/collapsible {}
               [sut/bubble {:variant :outline}
                [collapsible/collapsible-trigger {:as-child true}
                 [sut/bubble-content {:as-child? true}
                  [:button {:type "button"}
                   "Show delivery details"]]]]
               [collapsible/collapsible-content {:class "px-3 py-2 text-sm text-muted-foreground"}
                "Delivered to the project channel at 09:42."]]
              [sut/bubble {:variant :tinted}
               [tooltip/tooltip {:trigger (sut/bubble-content {:as-child? true}
                                                              [:button {:type "button"}
                                                               "Hover for response timing"])
                                 :trigger-as-child? true
                                 :content "Estimated response time: under a minute"}]]
              [popover/popover {}
               [popover/popover-trigger {:asChild true}
                (button/button {:variant :ghost
                                :size :sm}
                               "Open response details")]
               [popover/popover-content {:align "start"}
                [:p {:class "text-sm"}
                 "Generated from three indexed project documents."]]]]])))
