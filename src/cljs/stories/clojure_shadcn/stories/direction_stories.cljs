(ns clojure-shadcn.stories.direction-stories
  (:require
   ["@radix-ui/react-radio-group"          :as radio-group]
   [clojure-shadcn.stories.helpers         :as helpers]
   [clojure-shadcn.ui.components.direction :as sut]
   [reagent.core                           :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Direction"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Writing-direction context for Radix primitives."
                        :npm-install "npm install @radix-ui/react-direction"
                        :source-code (embed-source "clojure-shadcn.ui.components.direction")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/direction.cljs"
                        :filename "direction.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "direction-provider"
      :link {:href "https://www.radix-ui.com/primitives/docs/utilities/direction-provider"
             :label "Radix Direction Provider Docs"}
      :description
      "Supplies writing direction to descendant Radix primitives. It is a context provider, not a DOM element; set a matching HTML dir attribute where native text/layout direction is also required."
      :props [{:name ":direction / :dir"
               :type ":ltr | :rtl"
               :default ":ltr"
               :description
               ":direction takes precedence; the selected value is converted to a string."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Forwarded to Radix DirectionProvider."}]}]
    [helpers/api-component-card
     {:component-name "use-direction"
      :description
      "React hook returning the resolved direction as :ltr or :rtl. Call only from a Reagent/React function component and obey the Rules of Hooks."
      :props
      [{:name "direction"
        :type "keyword, optional positional argument"
        :default nil
        :description
        "Local direction passed to Radix useDirection; without it, reads context or Radix's default."}]}]])))

(defstory RightToLeft
          []
          (r/as-element (helpers/wrap-component
                         [sut/direction-provider {:direction :rtl}
                          [:div {:dir "rtl"
                                 :class "w-80 rounded-lg border p-6 text-right"}
                           [:h3 {:class "font-semibold"}
                            "واجهة من اليمين إلى اليسار"]
                           [:p {:class "text-muted-foreground"}
                            "يحافظ المزود على اتجاه مكونات Radix المتداخلة."]]])))


(defn- direction-reader
  [{:keys [override]}]
  (let [direction (if override (sut/use-direction override) (sut/use-direction))]
    [:output {:class "rounded bg-muted px-2 py-1 font-mono text-xs"}
     (name direction)]))

(defstory
 DirectionRadixDescendant
 "A real Radix radio-group descendant receives direction context; use arrow keys to observe RTL navigation."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/direction-provider {:direction :rtl}
    [:div {:dir "rtl"
           :class "space-y-3 rounded-md border p-5"}
     [:p {:class "text-sm"}
      "اختر خطة، ثم استخدم مفاتيح الأسهم"]
     [:>
      (.-Root radio-group)
      {:defaultValue "team"
       :className "flex gap-4"}
      (for [[value label] [["solo" "فردي"] ["team" "فريق"] ["org" "مؤسسة"]]]
        ^{:key value}
        [:label {:class "flex items-center gap-2"}
         [:>
          (.-Item radio-group)
          {:value value
           :className "size-5 rounded-full border"}
          [:>
           (.-Indicator radio-group)
           {:className "block size-full scale-50 rounded-full bg-primary"}]]
         label])]]])))

(defstory DirectionHookContextAndOverride
          "The hook reads provider context unless an explicit local direction overrides it."
          []
          (r/as-element (helpers/wrap-component
                         [sut/direction-provider {:direction :rtl}
                          [:div {:class "flex items-center gap-4 rounded-md border p-4"}
                           [:span "Context:"]
                           [:f> direction-reader {}]
                           [:span "Local override:"]
                           [:f> direction-reader {:override :ltr}]]])))

(defstory
 DirectionPlayground
 "Interactive provider direction; native dir is mirrored because the provider itself renders no DOM."
 {:args {:dir "ltr"}
  :arg-types {:dir {:control {:type "select"}
                    :options ["ltr" "rtl"]}}
  :parameters {:controls {:exclude ["children"]}}}
 [args]
 (r/as-element (helpers/wrap-component [sut/direction-provider
                                        (select-keys args [:dir])
                                        [:div {:dir (:dir args)
                                               :class "rounded-md border p-4"}
                                         "Direction-aware content"]])))
