(ns clojure-shadcn.stories.bubble-stories
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.bubble :as sut]
   [reagent.core                        :as r])
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
                       {:control {:type "select"}
                        :options
                        ["default" "secondary" "muted" "tinted" "outline" "ghost" "destructive"]}
                       :align {:control {:type "select"}
                               :options ["start" "end"]}
                       :content {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "as-child?"]}}
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
