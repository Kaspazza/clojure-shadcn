(ns clojure-shadcn.ui.components.resizable
  "Accessible resizable panel layouts. Sizes and callbacks use ordinary CLJS data."
  (:require
   ["lucide-react"              :refer [GripVerticalIcon]]
   ["react-resizable-panels"    :as panels]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn panel-group
  [{:keys [class orientation on-layout]
    :or {orientation :horizontal}
    :as raw-props}
   &
   children]
  (let [props (normalize-props raw-props)]
    (into [:>
           panels/Group
           (-> props
               (assoc :data-slot "resizable-panel-group"
                      :orientation (name orientation)
                      :onLayout (when on-layout #(on-layout (js->clj % :keywordize-keys true)))
                      :className (merge-classes
                                  "flex h-full w-full aria-[orientation=vertical]:flex-col"
                                  class))
               (dissoc :class :class-name :on-layout))]
          children)))

(defn panel
  [props & children]
  (into [:> panels/Panel (assoc props :data-slot "resizable-panel")] children))

(defn handle
  [{:keys [class with-handle?]
    :as props}]
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
      [:> GripVerticalIcon {:className "size-2.5"}]])])
