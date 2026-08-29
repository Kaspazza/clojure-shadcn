(ns clojure-shadcn.stories.attachment-stories
  (:require ["lucide-react" :refer [FileText X]] [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.attachment :as sut] [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Attachment" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Current shadcn/ui attachment states and layouts." :npm-install "npm install @radix-ui/react-slot" :source-code (embed-source "clojure-shadcn.ui.components.attachment") :namespace-path "src/cljs/clojure_shadcn/ui/components/attachment.cljs" :filename "attachment.cljs"}]))
(defstory ApiReference [] (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-4xl space-y-4"}
 [helpers/api-component-card {:component-name "attachment / attachment-group" :link {:href "https://ui.shadcn.com/docs/components/attachment" :label "shadcn/ui Attachment"} :description "Stateful attachment shell and horizontally scrolling group." :props [{:name ":state" :type ":idle | :uploading | :processing | :error | :done" :default ":done"} {:name ":size" :type ":default | :sm | :xs" :default ":default"} {:name ":orientation" :type ":horizontal | :vertical" :default ":horizontal"}]}]
 [helpers/api-component-card {:component-name "attachment-media / content / title / description / actions / action / trigger" :description "Composition slots. Trigger overlays the attachment; action delegates to Button." :props [{:name ":variant (media)" :type ":icon | :image" :default ":icon"} {:name ":as-child? (trigger)" :type "boolean" :default "false"}]}]])))
(defstory AttachmentStates [] (r/as-element (helpers/wrap-component [sut/attachment-group {}
 (for [[state text] [[:done "invoice.pdf"] [:uploading "photo.jpg"] [:error "archive.zip"]]]
   ^{:key state} [sut/attachment {:state state} [sut/attachment-media {} [:> FileText]] [sut/attachment-content {} [sut/attachment-title {} text] [sut/attachment-description {} (name state)]] [sut/attachment-actions {} [sut/attachment-action {:aria-label "Remove"} [:> X]]]])])))
