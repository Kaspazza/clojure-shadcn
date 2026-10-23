(ns clojure-shadcn.stories.typography-stories
  (:refer-clojure :exclude [list])
  (:require
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.typography :as sut]
   [reagent.core                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Typography"
       :parameters #js {:layout "padded"}})

(defdoc
 Installation
 []
 (r/as-element
  [helpers/installation-scene
   {:description
    "Reusable shadcn-style semantic typography; a project extra rather than a registry module."
    :npm-install "No external dependencies"
    :source-code (embed-source "clojure-shadcn.ui.components.typography")
    :namespace-path "src/cljs/clojure_shadcn/ui/components/typography.cljs"
    :filename "typography.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-4xl space-y-4 p-6"}
    [helpers/api-component-card
     {:component-name "h1 / h2 / h3 / h4 / p"
      :description
      "Semantic heading and paragraph elements using the canonical shadcn typography recipe classes."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with defaults."}
              {:name "DOM props"
               :type "map entries"
               :default nil
               :description "Forwarded to the semantic element."}]}]
    [helpers/api-component-card {:component-name "blockquote / list / inline-code"
                                 :description
                                 "Semantic quotation, unordered-list, and inline code recipes."
                                 :props [{:name ":class"
                                          :type "string"
                                          :default nil
                                          :description "Classes merged with defaults."}]}]
    [helpers/api-component-card
     {:component-name "lead / large / small / muted / table"
      :description
      "Display text recipes plus a responsive table wrapper. Table props target the nested table."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with defaults."}]}]
    [:div
     {:class
      "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
     "Typography is a data-oriented set of plain Reagent functions, not a context or global prose style. Use semantic variants according to document structure."]])))

(defstory
 TypeScale
 "Interactive typography playground."
 {:args
  {:heading "The Taxonomy of Type"
   :body
   "Typography should communicate hierarchy without coupling content to a page-specific stylesheet."}
  :arg-types {:heading {:control {:type "text"}}
              :body {:control {:type "text"}}}
  :parameters {:controls {:exclude ["class"]}}}
 [args]
 (r/as-element (helpers/wrap-component [:article {:class "max-w-2xl"}
                                        [sut/h1 {}
                                         (:heading args)]
                                        [sut/p {}
                                         (:body args)]])))

(defstory TypographyRecipeGallery
          "Comprehensive recipe gallery — every exported shadcn typography recipe shown in context."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "grid max-w-5xl gap-10 lg:grid-cols-2"}
             [:section {:class "space-y-5"}
              [sut/h1 {}
               "Heading one"]
              [sut/h2 {}
               "Heading two"]
              [sut/h3 {}
               "Heading three"]
              [sut/h4 {}
               "Heading four"]
              [sut/lead {}
               "A lead paragraph introduces the subject with lower visual contrast."]
              [sut/p {}
               "Body copy uses a comfortable line height for longer reading."]
              [sut/blockquote {}
               "Good design makes the right thing easy and the wrong thing obvious."]]
             [:section {:class "space-y-5"}
              [sut/large {}
               "Large display text"]
              [sut/small {}
               "Small supporting label"]
              [sut/muted {}
               "Muted metadata and secondary context"]
              [sut/p {}
               "Use "
               [sut/inline-code {}
                "defstory"]
               " to publish a Storybook example."]
              [sut/list {}
               [:li "Use semantic elements first."]
               [:li "Treat visual hierarchy as a separate concern."]
               [:li "Keep readable measure and rhythm."]]
              [sut/table {}
               [:thead
                [:tr {:class "border-b"}
                 [:th {:class "p-2 text-left"}
                  "Recipe"]
                 [:th {:class "p-2 text-left"}
                  "Element"]]]
               [:tbody
                [:tr {:class "border-b"}
                 [:td {:class "p-2"}
                  "Lead"]
                 [:td {:class "p-2"}
                  "p"]]
                [:tr {:class "border-b"}
                 [:td {:class "p-2"}
                  "Inline code"]
                 [:td {:class "p-2"}
                  "code"]]]]]])))

(defstory
 TypographyFullProse
 "Full prose — a complete upstream-style article demonstrates hierarchy, rhythm, lists, quotes, code, and tabular content."
 []
 (r/as-element
  (helpers/wrap-component
   [:article {:class "mx-auto max-w-3xl"}
    [sut/h1 {}
     "Designing durable interfaces"]
    [sut/lead {}
     "A component library succeeds when its contracts stay clear as products and teams evolve."]
    [sut/p {}
     "Reusable UI is not merely a collection of styled elements. It is a vocabulary: each component should carry a small, coherent responsibility and compose without surprising its neighbors."]
    [sut/h2 {}
     "Prefer explicit contracts"]
    [sut/p {}
     "An API should expose supported behavior directly. Avoid documenting imagined variants or relying on accidental implementation details. In code, prefer "
     [sut/inline-code {}
      ":variant :outline"]
     " only when the component actually supports it."]
    [sut/h3 {}
     "Composition before configuration"]
    [sut/p {}
     "Cards, alerts, and empty states become useful through composition. Their slots provide structure while the surrounding application supplies domain meaning."]
    [sut/blockquote {}
     "Make invalid states hard to represent, and make supported composition easy to discover."]
    [sut/h3 {}
     "A practical review"]
    [sut/list {}
     [:li "Read the implementation as the source of truth."]
     [:li "Document behavior rather than incidental markup."]
     [:li "Exercise responsive and right-to-left layouts."]
     [:li "Preserve semantic HTML and keyboard interaction."]]
    [sut/h2 {}
     "Recipe reference"]
    [sut/table {}
     [:thead
      [:tr {:class "border-b"}
       [:th {:class "p-2 text-left"}
        "Need"]
       [:th {:class "p-2 text-left"}
        "Recipe"]]]
     [:tbody
      [:tr {:class "border-b"}
       [:td {:class "p-2"}
        "Introductory emphasis"]
       [:td {:class "p-2"}
        "lead"]]
      [:tr {:class "border-b"}
       [:td {:class "p-2"}
        "Supporting metadata"]
       [:td {:class "p-2"}
        "muted"]]
      [:tr {:class "border-b"}
       [:td {:class "p-2"}
        "Literal identifier"]
       [:td {:class "p-2"}
        "inline-code"]]]]
    [sut/small {:class "mt-8 block"}
     "Last reviewed March 2026"]])))

(defstory
 TypographyRTL
 "RTL — full right-to-left prose uses logical alignment while preserving semantic hierarchy."
 []
 (r/as-element
  (helpers/wrap-component
   [:article {:dir "rtl"
              :class "mx-auto max-w-3xl text-right"}
    [sut/h1 {}
     "تصميم واجهات تدوم"]
    [sut/lead {}
     "تنجح مكتبة المكونات عندما تبقى عقودها واضحة مع تطور المنتج."]
    [sut/p {}
     "ليست الواجهة القابلة لإعادة الاستخدام مجرد مجموعة من العناصر المنسقة، بل هي لغة مشتركة بين المصممين والمطورين."]
    [sut/h2 {}
     "ابدأ بالدلالات"]
    [sut/p {}
     "استخدم العناصر الدلالية أولًا، ثم أضف الأسلوب البصري من خلال وصفات واضحة وقابلة للتركيب."]
    [sut/blockquote {:class "border-l-0 border-r-2 pl-0 pr-6"}
     "اجعل المسار الصحيح واضحًا وسهلًا."]
    [sut/list {:class "ml-0 mr-6"}
     [:li "عقود صغيرة وواضحة"]
     [:li "تركيب بدل الإعداد المفرط"]
     [:li "دعم اتجاه النص"]]
    [sut/muted {}
     "تمت المراجعة في مارس 2026"]])))
