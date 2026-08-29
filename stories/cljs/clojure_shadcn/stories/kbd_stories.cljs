(ns clojure-shadcn.stories.kbd-stories
  (:require ["lucide-react" :refer [Command]]
            [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.kbd :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Kbd" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Keyboard input and shortcut hint elements." :npm-install nil :source-code (embed-source "clojure-shadcn.ui.components.kbd") :namespace-path "src/cljs/clojure_shadcn/ui/components/kbd.cljs" :filename "kbd.cljs"}]))
(defstory KbdDemo [] (r/as-element (helpers/wrap-component [:div {:class "flex items-center gap-2 text-sm"} "Press" [sut/kbd {} "⌘"] [sut/kbd {} "K"] "to search"])))
(defstory KbdGroup [] (r/as-element (helpers/wrap-component [sut/kbd-group {} [sut/kbd {} [:> Command]] [:span "+"] [sut/kbd {} "Shift"] [:span "+"] [sut/kbd {} "P"]])))
