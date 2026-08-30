(ns clojure-shadcn.stories.kbd-stories
  (:require ["lucide-react" :refer [Command]]
            [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.kbd :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))
(def ^:export default #js {:title "Components/Kbd" :parameters #js {:layout "padded"}})
(defdoc Installation [] (r/as-element [helpers/installation-scene {:description "Keyboard input and shortcut hint elements." :npm-install nil :source-code (embed-source "clojure-shadcn.ui.components.kbd") :namespace-path "src/cljs/clojure_shadcn/ui/components/kbd.cljs" :filename "kbd.cljs"}]))
(defstory ApiReference []
  (r/as-element (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card {:component-name "kbd" :description "Semantic HTML kbd element for an individual key or compact keyboard input hint. It is presentational and non-interactive." :props [{:name ":class" :type "string" :default nil :description "Classes merged with the key-cap styles."} {:name "additional props" :type "map entries" :default nil :description "Normalized and forwarded to the kbd element."}]}]
     [helpers/api-component-card {:component-name "kbd-group" :description "Inline layout for a shortcut sequence. It also renders a kbd element, so use it to group key tokens rather than as an interactive control." :props [{:name ":class / additional props" :type "string / map entries" :default nil :description "Merged and forwarded to the kbd element."}]}]])))
(defstory KbdDemo [] (r/as-element (helpers/wrap-component [:div {:class "flex items-center gap-2 text-sm"} "Press" [sut/kbd {} "⌘"] [sut/kbd {} "K"] "to search"])))
(defstory KbdGroup [] (r/as-element (helpers/wrap-component [sut/kbd-group {} [sut/kbd {} [:> Command]] [:span "+"] [sut/kbd {} "Shift"] [:span "+"] [sut/kbd {} "P"]])))
