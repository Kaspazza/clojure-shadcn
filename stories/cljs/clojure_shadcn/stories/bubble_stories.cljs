(ns clojure-shadcn.stories.bubble-stories
  (:require [clojure-shadcn.stories.helpers :as helpers] [clojure-shadcn.ui.components.bubble :as sut] [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Chat/Bubble" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Current shadcn/ui message bubble primitives." :npm-install "npm install @radix-ui/react-slot" :source-code (embed-source "clojure-shadcn.ui.components.bubble") :namespace-path "src/cljs/clojure_shadcn/ui/components/bubble.cljs" :filename "bubble.cljs"}]))
(defstory ApiReference [] (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-4xl space-y-4"}
 [helpers/api-component-card {:component-name "bubble-group / bubble / bubble-content" :description "Aligned message bubbles with Slot-capable content." :props [{:name ":variant" :type ":default | :secondary | :muted | :tinted | :outline | :ghost | :destructive" :default ":default"} {:name ":align" :type ":start | :end" :default ":start"} {:name ":as-child? (content)" :type "boolean" :default "false"}]}]
 [helpers/api-component-card {:component-name "bubble-reactions" :description "Reaction badge positioned against a bubble." :props [{:name ":side" :type ":top | :bottom" :default ":bottom"} {:name ":align" :type ":start | :end" :default ":end"}]}]])))
(defstory BubbleConversation [] (r/as-element (helpers/wrap-component [sut/bubble-group {:class "max-w-lg"}
 [sut/bubble {:variant :secondary} [sut/bubble-content {} "Can you send the latest report?"]]
 [sut/bubble {:align :end} [sut/bubble-content {} "Sure — attaching it now."] [sut/bubble-reactions {} "👍 2"]]
 [sut/bubble {:variant :destructive} [sut/bubble-content {} "Upload failed. Try again."]]])))
