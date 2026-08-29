(ns clojure-shadcn.stories.button-group-stories
  (:require ["lucide-react" :refer [Bold Italic Underline]]
            [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.button :as button]
            [clojure-shadcn.ui.components.button-group :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Button Group" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Groups related controls with shared borders and orientation-aware corners." :npm-install "npm install @radix-ui/react-slot @radix-ui/react-separator" :source-code (embed-source "clojure-shadcn.ui.components.button_group") :namespace-path "src/cljs/clojure_shadcn/ui/components/button_group.cljs" :filename "button_group.cljs"}]))
(defstory ButtonGroupDemo []
  (r/as-element (helpers/wrap-component [sut/button-group {} [button/button {:variant :outline} "Back"] [button/button {:variant :outline} "Next"]])))
(defstory ButtonGroupWithText []
  (r/as-element (helpers/wrap-component [sut/button-group {} [sut/button-group-text {} "https://"] [button/button {:variant :outline} "example.com"]])))
(defstory ButtonGroupVertical []
  (r/as-element (helpers/wrap-component [sut/button-group {:orientation :vertical} [button/button {:variant :outline :size :icon :aria-label "Bold"} [:> Bold]] [button/button {:variant :outline :size :icon :aria-label "Italic"} [:> Italic]] [button/button {:variant :outline :size :icon :aria-label "Underline"} [:> Underline]]])))
(defstory ButtonGroupSeparator []
  (r/as-element (helpers/wrap-component [sut/button-group {} [button/button {:variant :outline} "Save"] [sut/button-group-separator {}] [button/button {:variant :outline} "Options"]])))
