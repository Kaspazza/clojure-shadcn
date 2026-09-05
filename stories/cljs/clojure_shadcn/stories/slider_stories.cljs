(ns clojure-shadcn.stories.slider-stories
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.slider :as sut]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Slider"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard and pointer accessible numeric slider."
                        :npm-install "npm install @radix-ui/react-slider"
                        :source-code (embed-source "clojure-shadcn.ui.components.slider")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/slider.cljs"
                        :filename "slider.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [helpers/api-component-card
             {:component-name "slider"
              :link {:href "https://www.radix-ui.com/primitives/docs/components/slider"
                     :label "Radix Slider Docs"}
              :description "Radix Slider with one thumb per controlled/default value."
              :props [{:name ":value / :default-value"
                       :type "vector<number>"
                       :default "[min]"
                       :description "Controlled or initial values."}
                      {:name ":on-value-change / :on-value-commit"
                       :type "function"
                       :default nil
                       :description "Value callbacks."}
                      {:name ":min / :max / :step"
                       :type "number"
                       :default "0 / 100 / 1"
                       :description "Numeric bounds."}]}])))

(defstory SliderBasic
          []
          (r/as-element [(fn []
                           (let [value (r/atom [40])]
                             (fn []
                               (helpers/wrap-component [:div {:class "w-[420px] p-6 space-y-3"}
                                                        [:p {:class "text-sm"}
                                                         (str "Volume: " (first @value))]
                                                        [sut/slider {:value @value
                                                                     :on-value-change
                                                                     #(reset! value (js->clj %))
                                                                     :max 100
                                                                     :step 1
                                                                     :aria-label "Volume"}]]))))]))

(defstory SliderRange
          []
          (r/as-element (helpers/wrap-component [:div {:class "w-[420px] p-6"}
                                                 [sut/slider {:default-value [25 75]
                                                              :min 0
                                                              :max 100
                                                              :thumb-labels ["Minimum price"
                                                                             "Maximum price"]}]])))


(defstory SliderPlayground
          "Interactive slider playground."
          {:args {:default-value [40]
                  :min 0
                  :max 100
                  :step 1
                  :disabled false}
           :arg-types {:default-value {:control {:type "number"}}
                       :min {:control {:type "number"}}
                       :max {:control {:type "number"}}
                       :step {:control {:type "number"}}
                       :disabled {:control {:type "boolean"}}}
           :parameters {:controls
                        {:exclude
                         ["value" "on-value-change" "on-value-commit" "class" "thumb-class"]}}
           :decode-args (fn [{:keys [default-value]
                              :as args}]
                          (assoc args :default-value [default-value]))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "w-[420px] p-6"}
                                                 [sut/slider (assoc args :aria-label "Value")]])))
