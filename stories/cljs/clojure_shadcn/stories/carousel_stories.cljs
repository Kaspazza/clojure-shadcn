(ns clojure-shadcn.stories.carousel-stories
  "Storybook stories for the Carousel component. Ported from mateuszmazurczak.portfolio.ui-components.carousel."
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.carousel :as sut]
   [reagent.core                          :as    r
                                          :refer [defc]]
   [reagent.hooks                         :as rhooks])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Carousel"
       :parameters #js {:layout "padded"}})

(defn- slide-card
  [label]
  [:div {:class "p-1"}
   [:div {:class "flex aspect-square items-center justify-center rounded-lg border bg-card"}
    [:span {:class "text-3xl font-semibold"}
     label]]])

(defn carousel-events-example
  []
  (let [api (r/atom nil)
        event-log (r/atom [])]
    (fn []
      (let [on-select (rhooks/use-callback (fn []
                                             (when-let [^js current-api @api]
                                               (swap! event-log conj
                                                 (str "select → snap "
                                                      (.selectedScrollSnap current-api)))))
                                           [])
            on-settle
            (rhooks/use-callback (fn [] (swap! event-log conj "settle → carousel stopped")) [])
            on-pointer-down
            (rhooks/use-callback (fn [] (swap! event-log conj "pointerDown → drag started")) [])]
        (rhooks/use-effect (fn []
                             (when-let [^js current-api @api]
                               (.on current-api "select" on-select)
                               (.on current-api "settle" on-settle)
                               (.on current-api "pointerDown" on-pointer-down)
                               (fn []
                                 (.off current-api "select" on-select)
                                 (.off current-api "settle" on-settle)
                                 (.off current-api "pointerDown" on-pointer-down))))
                           [@api])
        (helpers/wrap-component
         [:div {:class "p-6"}
          [sut/carousel {:set-api (fn [carousel-api] (reset! api carousel-api))
                         :class "w-full max-w-xs"}
           (into [sut/carousel-content {}]
                 (for [idx (range 1 6)]
                   [sut/carousel-item {:key idx}
                    [slide-card idx]]))
           [sut/carousel-previous {}]
           [sut/carousel-next {}]]
          [:div {:class "mt-4 space-y-1"}
           [:p {:class "text-sm font-semibold"}
            "Event Log:"]
           [:div {:class "rounded-lg border bg-muted/50 p-3 max-h-32 overflow-y-auto"}
            (if (empty? @event-log)
              [:p {:class "text-xs text-muted-foreground"}
               "Interact with carousel..."]
              (into [:div {:class "space-y-1"}]
                    (for [[idx event] (map-indexed vector (take-last 5 @event-log))]
                      [:p {:key idx
                           :class "text-xs font-mono"}
                       event])))]]])))))

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
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:class "w-full max-w-xs"}
                                         (into [sut/carousel-content {}]
                                               (for [idx (range 1 6)]
                                                 [sut/carousel-item {:key idx}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselSize
 "Carousel with smaller item sizes showing multiple slides.

  Library: embla-carousel

  Use basis-1/3 to show 3 items at once. Adjust with responsive classes as needed."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:align "start"}
                                                       :class "w-full max-w-sm"}
                                         (into [sut/carousel-content {}]
                                               (for [idx (range 1 6)]
                                                 [sut/carousel-item {:key idx
                                                                     :class "basis-1/3"}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselOrientation
 "Vertical carousel orientation.

  Library: embla-carousel

  Use :orientation :vertical for stacked slides. Container height determines visible area."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:align "start"}
                                                       :orientation :vertical
                                                       :class "w-full max-w-xs"}
                                         (into [sut/carousel-content {:class "h-[340px]"}]
                                               (for [idx (range 1 6)]
                                                 [sut/carousel-item {:key idx}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselSpacing
 "Carousel with custom spacing between items.

  Library: embla-carousel

  Use pl-* on items with -ml-* on content to create gaps. Shows 3 items to demonstrate spacing."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:align "start"}
                                                       :class "w-full max-w-sm"}
                                         (into [sut/carousel-content {:class "-ml-4"}]
                                               (for [idx (range 1 6)]
                                                 [sut/carousel-item {:key idx
                                                                     :class "pl-4 basis-1/3"}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselLoop
 "Carousel with infinite looping enabled.

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :loop true in opts for seamless infinite scrolling. Embla auto-adjusts positions."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:loop true}
                                                       :class "w-full max-w-xs"}
                                         (into [sut/carousel-content {}]
                                               (for [idx (range 1 6)]
                                                 [sut/carousel-item {:key idx}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselDragFree
 "Carousel with free-form dragging (no snap points).

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :dragFree true for momentum-based scrolling without snap constraints."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:dragFree true
                                                              :loop true}
                                                       :class "w-full max-w-sm"}
                                         (into [sut/carousel-content {}]
                                               (for [idx (range 1 10)]
                                                 [sut/carousel-item {:key idx
                                                                     :class "basis-1/3"}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defstory
 CarouselSlidesToScroll
 "Carousel scrolling multiple slides at once.

  Library: embla-carousel — https://www.embla-carousel.com/api/options

  Use :slidesToScroll in opts to advance multiple slides per navigation action."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/carousel {:opts {:align "start"
                                                              :slidesToScroll 2}
                                                       :class "w-full max-w-sm"}
                                         (into [sut/carousel-content {}]
                                               (for [idx (range 1 11)]
                                                 [sut/carousel-item {:key idx
                                                                     :class "basis-1/3"}
                                                  [slide-card idx]]))
                                         [sut/carousel-previous {}]
                                         [sut/carousel-next {}]]])))

(defn ^:export CarouselEvents
  "Carousel with event listeners for scroll and settle.

  Library: embla-carousel — https://www.embla-carousel.com/api/events

  Listen to events: select, scroll, settle, pointerDown, pointerUp via :set-api."
  []
  (r/as-element [carousel-events-example]))


(defstory CarouselPlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:orientation "horizontal"}
           :arg-types {:orientation {:control {:type "select"}
                                     :options ["horizontal" "vertical"]}}
           :parameters {:controls {:exclude ["children" "opts" "plugins" "set-api"]}}
           :decode-args (fn [{:keys [orientation]
                              :as args}]
                          (cond-> args
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component
                         [sut/carousel
                          (assoc (select-keys args [:orientation]) :class "w-full max-w-xs")
                          [sut/carousel-content {}
                           [sut/carousel-item {}
                            [slide-card "1"]]
                           [sut/carousel-item {}
                            [slide-card "2"]]]
                          [sut/carousel-previous {}]
                          [sut/carousel-next {}]])))
