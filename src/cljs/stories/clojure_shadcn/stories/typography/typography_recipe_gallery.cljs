(ns clojure-shadcn.stories.typography.typography-recipe-gallery
  (:require
   [clojure-shadcn.ui.components.typography :as typography]))

(defn example
  []
  [:div {:class "grid max-w-5xl gap-10 lg:grid-cols-2"}
   [:section {:class "space-y-5"}
    [typography/h1 {}
     "Heading one"]
    [typography/h2 {}
     "Heading two"]
    [typography/h3 {}
     "Heading three"]
    [typography/h4 {}
     "Heading four"]
    [typography/lead {}
     "A lead paragraph introduces the subject with lower visual contrast."]
    [typography/p {}
     "Body copy uses a comfortable line height for longer reading."]
    [typography/blockquote {}
     "Good design makes the right thing easy and the wrong thing obvious."]]
   [:section {:class "space-y-5"}
    [typography/large {}
     "Large display text"]
    [typography/small {}
     "Small supporting label"]
    [typography/muted {}
     "Muted metadata and secondary context"]
    [typography/p {}
     "Use "
     [typography/inline-code {}
      "defstory"]
     " to publish a Storybook example."]
    [typography/list {}
     [:li "Use semantic elements first."]
     [:li "Treat visual hierarchy as a separate concern."]
     [:li "Keep readable measure and rhythm."]]
    [typography/table {}
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
        "code"]]]]]])
