(ns clojure-shadcn.ui.components.resizable
  "Accessible resizable panel layouts. Sizes and callbacks use ordinary CLJS data."
  (:require
   ["lucide-react"              :refer [GripVerticalIcon]]
   ["react-resizable-panels"    :as panels]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- cljs-data [value]
  (when-not (nil? value)
    (js->clj value :keywordize-keys true)))

(defn panel-group
  [raw-props & children]
  (let [{:keys [class orientation on-layout on-layout-changed]
         :or {orientation :horizontal}
         :as props}
        (normalize-props raw-props)
        callback (or on-layout-changed on-layout)]
    (into [:>
           panels/Group
           (-> props
               (assoc :data-slot "resizable-panel-group"
                      :orientation (name orientation)
                      :onLayoutChanged
                      (when callback
                        (fn [layout meta]
                          (callback (cljs-data layout) (cljs-data meta))))
                      :className (merge-classes
                                  "flex h-full w-full aria-[orientation=vertical]:flex-col"
                                  class))
               (dissoc :class :class-name :on-layout :on-layout-changed))]
          children)))

(defn panel
  [raw-props & children]
  (let [{:keys [on-resize]
         :as props}
        (normalize-props raw-props)]
    (into [:>
           panels/Panel
           (-> props
               (assoc :data-slot "resizable-panel"
                      :onResize
                      (when on-resize
                        (fn [size id previous-size]
                          (on-resize (cljs-data size) id (cljs-data previous-size)))))
               (dissoc :on-resize))]
          children)))

(defn handle
  [raw-props]
  (let [{:keys [class with-handle?]
         :as props}
        (normalize-props raw-props)]
    [:>
     panels/Separator
     (->
      props
      (assoc
       :data-slot "resizable-handle"
       :className
       (merge-classes
        "relative flex w-px items-center justify-center bg-border after:absolute after:inset-y-0 after:left-1/2 after:w-1 after:-translate-x-1/2 focus-visible:ring-1 focus-visible:ring-ring aria-[orientation=horizontal]:h-px aria-[orientation=horizontal]:w-full aria-[orientation=horizontal]:after:left-0 aria-[orientation=horizontal]:after:h-1 aria-[orientation=horizontal]:after:w-full aria-[orientation=horizontal]:after:translate-x-0 aria-[orientation=horizontal]:after:-translate-y-1/2 [&[aria-orientation=horizontal]>div]:rotate-90"
        class))
      (dissoc :class :class-name :with-handle?))
     (when with-handle?
       [:div {:class "z-10 flex h-4 w-3 items-center justify-center rounded-xs border bg-border"}
        [:> GripVerticalIcon {:className "size-2.5"}]])]))
