(ns clojure-shadcn.ui.components.message-scroller
  "Auto-following message scroller based on the current shadcn/ui module."
  (:require
   ["@shadcn/react/message-scroller"    :refer [MessageScroller
                                                useMessageScroller
                                                useMessageScrollerScrollable
                                                useMessageScrollerVisibility]]
   ["lucide-react"                      :refer [ArrowDownIcon]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.utils.props          :refer [normalize-props]]
   [clojure-shadcn.utils.styles         :refer [merge-classes]]
   [reagent.core                        :as r]))

(def use-message-scroller useMessageScroller)

(def use-message-scroller-scrollable useMessageScrollerScrollable)

(def use-message-scroller-visibility useMessageScrollerVisibility)

(defn- primitive
  [component slot classes raw-props children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:>
           component
           (-> props
               (assoc :data-slot slot :className (merge-classes classes class))
               (dissoc :class :class-name))]
          children)))

(defn message-scroller-provider
  [props & children]
  (into [:> (.-Provider MessageScroller) props] children))

(defn message-scroller
  [props & children]
  (primitive (.-Root MessageScroller)
             "message-scroller"
             "group/message-scroller relative flex size-full min-h-0 flex-col overflow-hidden"
             props
             children))

(defn message-scroller-viewport
  [props & children]
  (primitive
   (.-Viewport MessageScroller)
   "message-scroller-viewport"
   "size-full min-h-0 min-w-0 scroll-fade-b scrollbar-thin scrollbar-gutter-stable overflow-y-auto overscroll-contain contain-content data-autoscrolling:scrollbar-none"
   props
   children))

(defn message-scroller-content
  [props & children]
  (primitive (.-Content MessageScroller)
             "message-scroller-content"
             "flex h-max min-h-full flex-col gap-8"
             props
             children))

(defn message-scroller-item
  [{:as raw-props} & children]
  (let [{:keys [scroll-anchor]
         :or {scroll-anchor false}
         :as props}
        (normalize-props raw-props)]
    (primitive (.-Item MessageScroller)
               "message-scroller-item"
               "min-w-0 shrink-0 [contain-intrinsic-size:auto_10rem] [content-visibility:auto]"
               (-> props
                   (assoc :scrollAnchor scroll-anchor)
                   (dissoc :scroll-anchor))
               children)))

(defn message-scroller-button
  [{:as raw-props} & children]
  (let [{:keys [direction variant size class render]
         :or {direction "end"
              variant :secondary
              size :icon-sm}
         :as props}
        (normalize-props raw-props)
        fallback (r/as-element (button/button {:variant variant
                                               :size size}))
        content (if (seq children)
                  children
                  [[:<>
                    [:> ArrowDownIcon]
                    [:span {:class "sr-only"}
                     (if (= direction "end") "Scroll to end" "Scroll to start")]]])]
    (into
     [:>
      (.-Button MessageScroller)
      (->
        props
        (assoc
         :data-slot "message-scroller-button"
         :data-direction direction
         :data-variant (name variant)
         :data-size (name size)
         :direction direction
         :render (or render fallback)
         :className
         (merge-classes
          "absolute inset-s-1/2 -translate-x-1/2 border-border bg-background text-foreground transition-[translate,scale,opacity] duration-200 hover:bg-muted hover:text-foreground data-[active=false]:pointer-events-none data-[active=false]:scale-95 data-[active=false]:opacity-0 data-[active=false]:duration-400 data-[active=false]:ease-[cubic-bezier(0.7,0,0.84,0)] data-[active=true]:translate-y-0 data-[active=true]:scale-100 data-[active=true]:opacity-100 data-[active=true]:ease-[cubic-bezier(0.23,1,0.32,1)] data-[direction=end]:bottom-4 data-[direction=end]:data-[active=false]:translate-y-full data-[direction=start]:top-4 data-[direction=start]:data-[active=false]:-translate-y-full rtl:translate-x-1/2 data-[direction=start]:[&_svg]:rotate-180"
          class))
        (dissoc :class :class-name :variant :size))]
     content)))
