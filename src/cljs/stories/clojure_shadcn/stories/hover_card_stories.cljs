(ns clojure-shadcn.stories.hover-card-stories
  (:require
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.hover-card :as sut]
   [reagent.core                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Hover Card"
       :parameters #js {:layout "padded"}})

(defn- project-preview
  []
  [:div {:class "space-y-2"}
   [:h4 {:class "font-semibold"}
    "shadcn/ui"]
   [:p {:class "text-sm text-muted-foreground"}
    "Beautifully designed components you can copy and own."]])

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Preview content revealed on hover or keyboard focus."
                        :npm-install "npm install @radix-ui/react-hover-card"
                        :source-code (embed-source "clojure-shadcn.ui.components.hover-card")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/hover_card.cljs"
                        :filename "hover_card.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "hover-card / hover-card-trigger / hover-card-content"
     :link {:href "https://www.radix-ui.com/primitives/docs/components/hover-card"
            :label "Radix Hover Card Docs"}
     :description
     "Accessible Radix wrappers with collision-aware portalled content. Normalized additional props are forwarded to each primitive."
     :props [{:name ":open / :default-open / :on-open-change"
              :type "boolean | function"
              :default nil
              :description "Controlled or uncontrolled visibility."}
             {:name ":open-delay / :close-delay"
              :type "number"
              :default nil
              :description "Pointer timing in milliseconds."}
             {:name ":side / :align / :side-offset"
              :type "keyword|string|number"
              :default nil
              :description "Content positioning."}]}])))

(defstory HoverCardBasic
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-16"}
                                                 [sut/hover-card {}
                                                  [sut/hover-card-trigger {:as-child true}
                                                   [:a {:href "https://github.com/shadcn-ui/ui"
                                                        :class
                                                        "font-medium underline underline-offset-4"}
                                                    "@shadcn"]]
                                                  [sut/hover-card-content {:class "w-80"}
                                                   [project-preview]]]])))

(defstory HoverCardDelays
          "Both opening and closing delays tuned for a fast preview interaction."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-16"}
                                                 [sut/hover-card {:open-delay 100
                                                                  :close-delay 600}
                                                  [sut/hover-card-trigger {:as-child true}
                                                   [:a {:href "https://github.com/shadcn-ui/ui"
                                                        :class
                                                        "font-medium underline underline-offset-4"}
                                                    "Preview with custom delays"]]
                                                  [sut/hover-card-content {}
                                                   [project-preview]]]])))

(defstory HoverCardPositioning
          "All preferred sides combined with start, center, and end alignment."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "grid min-h-[32rem] grid-cols-3 place-items-center gap-16 p-24"}
             (for [side [:top :right :bottom :left]
                   align [:start :center :end]]
               ^{:key (str (name side) "-" (name align))}
               [sut/hover-card {}
                [sut/hover-card-trigger {:as-child true}
                 [:button {:type "button"
                           :class "rounded-md border px-3 py-2 text-sm"
                           :aria-label (str "Preview " (name side) " " (name align) " placement")}
                  (str (name side) " / " (name align))]]
                [sut/hover-card-content {:side side
                                         :align align}
                 [project-preview]]])])))

(defstory HoverCardRtl
          "Right-to-left trigger and preview content."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-16 text-right"
                                :dir "rtl"}
                          [sut/hover-card {}
                           [sut/hover-card-trigger {:as-child true}
                            [:a {:href "https://github.com/shadcn-ui/ui"
                                 :class "font-medium underline underline-offset-4"}
                             "معاينة المشروع"]]
                           [sut/hover-card-content {:align :start
                                                    :dir "rtl"}
                            [:div {:class "space-y-2 text-right"}
                             [:h4 {:class "font-semibold"}
                              "واجهة المستخدم"]
                             [:p {:class "text-sm text-muted-foreground"}
                              "مكوّنات مصممة بعناية يمكنك نسخها وتخصيصها."]]]]])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:open-delay 700}
           :arg-types {:open-delay {:name "Open delay"
                                    :control {:type "number"}
                                    :min 0
                                    :max 2000
                                    :step 100}}
           :parameters {:playground {:description "Explore hover card open delay with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "default-open" "on-open-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/hover-card
                                                 (select-keys args [:open-delay])
                                                 [sut/hover-card-trigger {:as-child true}
                                                  [:a {:href "#"
                                                       :class "underline"}
                                                   "@shadcn"]]
                                                 [sut/hover-card-content {}
                                                  "A reusable component library."]])))
