(ns clojure-shadcn.stories.item-stories
  (:require ["lucide-react" :refer [FileText MoreHorizontal]]
            [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.button :as button]
            [clojure-shadcn.ui.components.item :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))
(def ^:export default #js {:title "Components/Item" :parameters #js {:layout "padded"}})
(defdoc Installation [] (r/as-element [helpers/installation-scene {:description "Current shadcn/ui composable item primitive." :npm-install "npm install @radix-ui/react-slot" :source-code (embed-source "clojure-shadcn.ui.components.item") :namespace-path "src/cljs/clojure_shadcn/ui/components/item.cljs" :filename "item.cljs"}]))
(defstory ApiReference [] (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-4xl space-y-4"}
 [helpers/api-component-card {:component-name "item / item-group / item-separator" :link {:href "https://ui.shadcn.com/docs/components/item" :label "shadcn/ui Item"} :description "List container, variant-aware item and separator. Item supports Slot composition." :props [{:name ":variant" :type ":default | :outline | :muted" :default ":default"} {:name ":size" :type ":default | :sm" :default ":default"} {:name ":as-child?" :type "boolean" :default "false"}]}]
 [helpers/api-component-card {:component-name "item-media / item-content / item-title / item-description / item-actions / item-header / item-footer" :description "Semantic composition slots; all forward DOM props and merge :class." :props [{:name ":variant (media)" :type ":default | :icon | :image" :default ":default"} {:name ":class" :type "string" :default nil}]}]])))
(defstory ItemVariants [] (r/as-element (helpers/wrap-component [sut/item-group {:class "max-w-xl"}
 [sut/item {:variant :outline} [sut/item-media {:variant :icon} [:> FileText]] [sut/item-content {} [sut/item-title {} "Quarterly report"] [sut/item-description {} "PDF · 2.4 MB"]] [sut/item-actions {} (button/button {:variant :ghost :size :icon} [:> MoreHorizontal])]]
 [sut/item-separator {}]
 [sut/item {:variant :muted :size :sm} [sut/item-content {} [sut/item-title {} "Compact item"] [sut/item-description {} "Muted, small variant"]]]])))
