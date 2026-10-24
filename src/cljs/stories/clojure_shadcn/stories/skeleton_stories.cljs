(ns clojure-shadcn.stories.skeleton-stories
  "Storybook stories for the Skeleton component. Ported from mateuszmazurczak.portfolio.ui-components.skeleton."
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.card     :as card]
   [clojure-shadcn.ui.components.skeleton :as sut]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Skeleton"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Skeleton component for loading placeholders."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.skeleton")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/skeleton.cljs"
                        :filename "skeleton.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "skeleton"
       :description
       "Animated loading placeholder block. Additional props are forwarded to underlying <div>."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name ":role"
                :type "string"
                :default nil
                :description "Accessibility role, e.g. \"status\"."}
               {:name ":aria-label"
                :type "string"
                :default nil
                :description "Screen reader label for loading context."}
               {:name ":aria-live"
                :type "string"
                :default nil
                :description "Announce updates politely/assertively."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to native <div>."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Skeleton is purely visual by default; add ARIA attributes when used as a status indicator."]
       [:li "Use realistic width/height classes to reduce layout shift during content hydration."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"space-y-2 w-64\"}\n [skeleton {:class \"h-4 w-40\"}]\n [skeleton {:class \"h-4 w-full\"}]\n [skeleton {:class \"h-4 w-5/6\" :role \"status\" :aria-label \"Loading profile\"}]]"]]]]])))

(defstory Playground
          "Interactive skeleton playground."
          {:args {:class "h-4 w-[250px] rounded-md"}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "select"}
                               :options ["h-4 w-[250px] rounded-md"
                                         "h-12 w-12 rounded-full"
                                         "h-24 w-full rounded-lg"]}}
           :parameters {:playground {:description "Explore skeleton css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["role" "aria-label" "aria-live"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/skeleton (select-keys args [:class])]])))

(defstory
 SkeletonCard
 "Card-like skeleton placeholder.

  Native element: <div>

  Use for cards, previews, or media blocks."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [:div {:class "flex flex-col space-y-3"}
                                         [sut/skeleton {:class "h-[125px] w-[250px] rounded-xl"}]
                                         [:div {:class "space-y-2"}
                                          [sut/skeleton {:class "h-4 w-[250px]"}]
                                          [sut/skeleton {:class "h-4 w-[200px]"}]]]])))

(defstory
 SkeletonGrid
 "Multi-column skeleton grid.

  Native element: <div>

  Useful for list or gallery loading states."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 grid gap-4 sm:grid-cols-3"}
                                        (for [idx (range 6)]
                                          ^{:key idx}
                                          [sut/skeleton {:class "h-24 w-full rounded-lg"}])])))

(defstory SkeletonIdentityRow
          "Identity row — upstream avatar-and-copy placeholder sized to the eventual content."
          []
          (r/as-element (helpers/wrap-component [:div {:class "flex w-80 items-center gap-4"}
                                                 [sut/skeleton {:class
                                                                "size-12 shrink-0 rounded-full"}]
                                                 [:div {:class "flex-1 space-y-2"}
                                                  [sut/skeleton {:class "h-4 w-32"}]
                                                  [sut/skeleton {:class "h-3 w-48"}]]])))

(defstory
 SkeletonRealCard
 "Card — real Card composition preserves the final component's regions while content loads."
 []
 (r/as-element (helpers/wrap-component [card/card {:class "w-full max-w-sm"
                                                   :aria-busy true
                                                   :aria-label "Loading project"}
                                        [card/card-header {}
                                         [sut/skeleton {:class "h-6 w-40"}]
                                         [sut/skeleton {:class "h-4 w-64"}]]
                                        [card/card-content {:class "space-y-3"}
                                         [sut/skeleton {:class "aspect-video w-full rounded-lg"}]
                                         [sut/skeleton {:class "h-4 w-full"}]]
                                        [card/card-footer {}
                                         [sut/skeleton {:class "h-9 w-full"}]]])))

(defstory SkeletonForm
          "Form — realistic labels and controls minimize layout shift during form loading."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "w-full max-w-md space-y-5 rounded-lg border p-6"
                                :aria-busy true
                                :aria-label "Loading form"}
                          (for [width ["w-24" "w-32" "w-20"]]
                            ^{:key width}
                            [:div {:class "space-y-2"}
                             [sut/skeleton {:class (str "h-4 " width)}]
                             [sut/skeleton {:class "h-9 w-full"}]])
                          [sut/skeleton {:class "h-9 w-28"}]])))

(defstory SkeletonTable
          "Table — upstream row-and-column placeholder mirrors a compact data table."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "w-full max-w-2xl overflow-hidden rounded-lg border"
                   :aria-busy true
                   :aria-label "Loading invoices"}
             [:div {:class "grid grid-cols-[1fr_8rem_6rem] gap-4 border-b bg-muted/50 p-3"}
              [sut/skeleton {:class "h-4 w-24"}]
              [sut/skeleton {:class "h-4 w-16"}]
              [sut/skeleton {:class "h-4 w-12"}]]
             (for [row (range 4)]
               ^{:key row}
               [:div {:class "grid grid-cols-[1fr_8rem_6rem] gap-4 border-b p-3 last:border-0"}
                [sut/skeleton {:class "h-4 w-40"}]
                [sut/skeleton {:class "h-4 w-20"}]
                [sut/skeleton {:class "h-4 w-14"}]])])))

(defstory SkeletonRTL
          "RTL — identity and text placeholders align with right-to-left content."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "flex w-80 items-center gap-4"}
                                                 [sut/skeleton {:class
                                                                "size-12 shrink-0 rounded-full"}]
                                                 [:div {:class "flex-1 space-y-2"}
                                                  [sut/skeleton {:class "ms-auto h-4 w-32"}]
                                                  [sut/skeleton {:class "ms-auto h-3 w-48"}]]])))
