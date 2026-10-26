(ns clojure-shadcn.stories.typography.typography-full-prose
  (:require
   [clojure-shadcn.ui.components.typography :as typography]))

(defn example
  []
  [:article {:class "mx-auto max-w-3xl"}
   [typography/h1 {}
    "Designing durable interfaces"]
   [typography/lead {}
    "A component library succeeds when its contracts stay clear as products and teams evolve."]
   [typography/p {}
    "Reusable UI is not merely a collection of styled elements. It is a vocabulary: each component should carry a small, coherent responsibility and compose without surprising its neighbors."]
   [typography/h2 {}
    "Prefer explicit contracts"]
   [typography/p {}
    "An API should expose supported behavior directly. Avoid documenting imagined variants or relying on accidental implementation details. In code, prefer "
    [typography/inline-code {}
     ":variant :outline"]
    " only when the component actually supports it."]
   [typography/h3 {}
    "Composition before configuration"]
   [typography/p {}
    "Cards, alerts, and empty states become useful through composition. Their slots provide structure while the surrounding application supplies domain meaning."]
   [typography/blockquote {}
    "Make invalid states hard to represent, and make supported composition easy to discover."]
   [typography/h3 {}
    "A practical review"]
   [typography/list {}
    [:li "Read the implementation as the source of truth."]
    [:li "Document behavior rather than incidental markup."]
    [:li "Exercise responsive and right-to-left layouts."]
    [:li "Preserve semantic HTML and keyboard interaction."]]
   [typography/h2 {}
    "Recipe reference"]
   [typography/table {}
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
   [typography/small {:class "mt-8 block"}
    "Last reviewed March 2026"]])
