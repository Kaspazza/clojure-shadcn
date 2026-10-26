(ns clojure-shadcn.stories.carousel-stories
  (:require
   [clojure-shadcn.stories.carousel.carousel-demo             :as carousel-demo]
   [clojure-shadcn.stories.carousel.carousel-drag-free        :as carousel-drag-free]
   [clojure-shadcn.stories.carousel.carousel-events           :as carousel-events]
   [clojure-shadcn.stories.carousel.carousel-loop             :as carousel-loop]
   [clojure-shadcn.stories.carousel.carousel-orientation      :as carousel-orientation]
   [clojure-shadcn.stories.carousel.carousel-position         :as carousel-position]
   [clojure-shadcn.stories.carousel.carousel-rtl              :as carousel-rtl]
   [clojure-shadcn.stories.carousel.carousel-size             :as carousel-size]
   [clojure-shadcn.stories.carousel.carousel-slides-to-scroll :as carousel-slides-to-scroll]
   [clojure-shadcn.stories.carousel.carousel-spacing          :as carousel-spacing]
   [clojure-shadcn.stories.carousel.playground                :as playground]
   [clojure-shadcn.stories.helpers                            :as helpers]
   [reagent.core                                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Carousel"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Carousel component built on top of Embla Carousel."
                        :npm-install "npm install embla-carousel-react lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.carousel")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/carousel.cljs"
                        :filename "carousel.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "carousel"
       :link {:href "https://www.embla-carousel.com/api/options/"
              :label "Embla Carousel Docs"}
       :description
       "Carousel root component - creates container with embla-carousel. Sets role=\"region\" and aria-roledescription=\"carousel\" for accessibility."
       :props
       [{:name ":opts"
         :type "map"
         :default nil
         :description
         "Embla carousel options passed directly to embla-carousel. Keys must use camelCase to match Embla's JS API (e.g. :loop, :align, :dragFree, :slidesToScroll). Note: do NOT set :axis here — use :orientation instead, which takes precedence."}
        {:name ":plugins"
         :type "array"
         :default nil
         :description "Embla carousel plugins (see https://www.embla-carousel.com/api/plugins/)"}
        {:name ":orientation"
         :type "keyword"
         :default ":horizontal"
         :description
         ":horizontal | :vertical. Controls the scroll axis. Overrides any :axis value in :opts."}
        {:name ":set-api"
         :type "function"
         :default nil
         :description
         "Callback to receive the raw Embla carousel API instance: (fn [api] ...). Use this to attach event listeners or call methods like .scrollTo, .canScrollNext, etc."}
        {:name ":class"
         :type "string"
         :default nil
         :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "carousel-content"
       :description
       "Carousel content wrapper - contains the carousel items. Applies default spacing via negative margin (-ml-4 horizontal, -mt-4 vertical). Override with custom -ml-* / -mt-* classes and matching pl-* / pt-* on carousel-item."
       :props
       [{:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes. All additional props are forwarded to the underlying DOM element."}]}]
     [helpers/api-component-card
      {:component-name "carousel-item"
       :description
       "Carousel item - individual slide in the carousel. Sets role=\"group\" and aria-roledescription=\"slide\" for accessibility. Applies default spacing (pl-4 horizontal, pt-4 vertical) that pairs with carousel-content's negative margin."
       :props
       [{:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes. Use basis-* for sizing (e.g. basis-1/3 for 3 visible slides). All additional props are forwarded to the underlying DOM element."}]}]
     [helpers/api-component-card
      {:component-name "carousel-previous"
       :description
       "Carousel previous button - navigates to previous slide. Automatically disabled when at the start (unless loop is enabled). See Button component for variant visual details."
       :props
       [{:name ":variant"
         :type "keyword"
         :default ":outline"
         :description
         "One of: :default | :destructive | :outline | :secondary | :ghost | :link. See Button component for visual details."}
        {:name ":size"
         :type "keyword"
         :default ":icon"
         :description "One of: :default | :sm | :lg | :icon"}
        {:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes. All additional props are forwarded to the Button component."}]}]
     [helpers/api-component-card
      {:component-name "carousel-next"
       :description
       "Carousel next button - navigates to next slide. Automatically disabled when at the end (unless loop is enabled). See Button component for variant visual details."
       :props
       [{:name ":variant"
         :type "keyword"
         :default ":outline"
         :description
         "One of: :default | :destructive | :outline | :secondary | :ghost | :link. See Button component for visual details."}
        {:name ":size"
         :type "keyword"
         :default ":icon"
         :description "One of: :default | :sm | :lg | :icon"}
        {:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes. All additional props are forwarded to the Button component."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "The props map {} is required on all components, even when empty."]
       [:li
        "Embla option keys must use camelCase (e.g. :dragFree, :slidesToScroll), not kebab-case."]
       [:li
        "Default item spacing is 1rem (pl-4/pt-4). Override both carousel-content (-ml-*/-mt-*) and carousel-item (pl-*/pt-*) together."]
       [:li "The :orientation prop overrides any :axis value passed in :opts."]
       [:li
        "The wrapper accepts Embla plugin instances through :plugins, but no plugin package (such as embla-carousel-autoplay) is installed in this project; a plugin story would not compile until that dependency is added."]
       [:li
        "Arrow key events are captured — may interfere with other key handlers in parent components."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[carousel {:opts {:loop true :align \"start\"}\n            :set-api (fn [api] (println \"Carousel ready!\"))}\n  [carousel-content {}\n    [carousel-item {} \"Slide 1\"]\n    [carousel-item {} \"Slide 2\"]]\n  [carousel-previous {}]\n  [carousel-next {}]]"]]]]])))

(defstory
 CarouselDemo
 "Basic carousel with previous/next controls.

  Library: embla-carousel

  Use for showcasing images or featured content."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-demo")}
 []
 (r/as-element (helpers/wrap-component [carousel-demo/example])))

(defstory
 CarouselSize
 "Carousel with smaller item sizes showing multiple slides.

  Library: embla-carousel

  Uses basis-1/2, sm:basis-1/3, and lg:basis-1/4 so visible slide count follows the viewport."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-size")}
 []
 (r/as-element (helpers/wrap-component [carousel-size/example])))

(defstory CarouselPosition
          "Consumer-managed current slide and total using the API supplied by :set-api."
          {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-position")}
          []
          (r/as-element (helpers/wrap-component [carousel-position/example])))

(defstory
 CarouselOrientation
 "Vertical carousel orientation.

  Library: embla-carousel

  Use :orientation :vertical for stacked slides. Container height determines visible area."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-orientation")}
 []
 (r/as-element (helpers/wrap-component [carousel-orientation/example])))

(defstory
 CarouselSpacing
 "Carousel with custom spacing between items.

  Library: embla-carousel

  Use pl-* on items with -ml-* on content to create gaps. Shows 3 items to demonstrate spacing."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-spacing")}
 []
 (r/as-element (helpers/wrap-component [carousel-spacing/example])))

(defstory
 CarouselLoop
 "Carousel with infinite looping enabled.

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :loop true in opts for seamless infinite scrolling. Embla auto-adjusts positions."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-loop")}
 []
 (r/as-element (helpers/wrap-component [carousel-loop/example])))

(defstory
 CarouselDragFree
 "Carousel with free-form dragging (no snap points).

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :dragFree true for momentum-based scrolling without snap constraints."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-drag-free")}
 []
 (r/as-element (helpers/wrap-component [carousel-drag-free/example])))

(defstory
 CarouselSlidesToScroll
 "Carousel scrolling multiple slides at once.

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :slidesToScroll in opts to advance multiple slides per navigation action."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-slides-to-scroll")}
 []
 (r/as-element (helpers/wrap-component [carousel-slides-to-scroll/example])))

(defstory
 CarouselRtl
 "RTL carousel with Embla direction configured as well as DOM direction, so dragging and previous/next behavior follow reading order."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-rtl")}
 []
 (r/as-element (helpers/wrap-component [carousel-rtl/example])))

(defstory
 CarouselEvents
 "Carousel with event listeners for scroll and settle.

  Library: embla-carousel — https://www.embla-carousel.com/api/events

  Listen to events: select, scroll, settle, pointerDown, pointerUp via :set-api."
 {:source-code (embed-source "clojure-shadcn.stories.carousel.carousel-events")}
 []
 (r/as-element (helpers/wrap-component [carousel-events/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.carousel.playground")
           :args {:orientation "horizontal"}
           :arg-types {:orientation {:name "Orientation"
                                     :control {:type "select"}
                                     :options ["horizontal" "vertical"]}}
           :parameters {:playground {:description
                                     "Explore carousel orientation with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "opts" "plugins" "set-api"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
