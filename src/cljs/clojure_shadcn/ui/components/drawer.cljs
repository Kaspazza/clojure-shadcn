(ns clojure-shadcn.ui.components.drawer
  "Drawer component based on Vaul drawer primitive.

Version: 1.0.0
Last updated: 2026-02-06

Custom component implementation."
  (:require
   ["vaul"                      :refer [Drawer]]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :as styles]))

(def drawer-root (.-Root Drawer))

(def drawer-trigger-primitive (.-Trigger Drawer))

(def drawer-portal-primitive (.-Portal Drawer))

(def drawer-close-primitive (.-Close Drawer))

(def drawer-overlay-primitive (.-Overlay Drawer))

(def drawer-content-primitive (.-Content Drawer))

(def drawer-title-primitive (.-Title Drawer))

(def drawer-description-primitive (.-Description Drawer))

(defn drawer
  [{:as raw-props} & children]
  (let [props (normalize-props raw-props)
        props (cond-> props
                (not (contains? props :direction)) (assoc :direction :bottom)
                (not (contains? props :modal)) (assoc :modal true)
                (keyword? (:direction props)) (update :direction name))]
    (into [:> drawer-root (assoc props :data-slot "drawer")] children)))

(defn drawer-trigger
  [raw-props & children]
  (into [:> drawer-trigger-primitive
         (assoc (normalize-props raw-props) :data-slot "drawer-trigger")]
        children))

(defn drawer-close
  [raw-props & children]
  (into [:> drawer-close-primitive
         (assoc (normalize-props raw-props) :data-slot "drawer-close")]
        children))

(defn drawer-overlay
  [{:as raw-props}]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    [:>
     drawer-overlay-primitive
     (-> props
         (assoc :data-slot "drawer-overlay"
                :class (styles/merge-classes
                        "data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 fixed inset-0 z-50 bg-black/50"
                        class))
         (dissoc :class-name))]))

(defn drawer-content
  [{:as raw-props} & children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    [:>
     drawer-portal-primitive
     {}
     [drawer-overlay {}]
     (into
      [:>
       drawer-content-primitive
       (-> props
           (assoc
            :data-slot "drawer-content"
            :class
            (styles/merge-classes
             "group/drawer-content bg-background fixed z-50 flex h-auto flex-col"
             "data-[vaul-drawer-direction=bottom]:inset-x-0 data-[vaul-drawer-direction=bottom]:bottom-0 data-[vaul-drawer-direction=bottom]:mt-24 data-[vaul-drawer-direction=bottom]:max-h-[80vh] data-[vaul-drawer-direction=bottom]:rounded-t-lg data-[vaul-drawer-direction=bottom]:border-t"
             "data-[vaul-drawer-direction=top]:inset-x-0 data-[vaul-drawer-direction=top]:top-0 data-[vaul-drawer-direction=top]:mb-24 data-[vaul-drawer-direction=top]:max-h-[80vh] data-[vaul-drawer-direction=top]:rounded-b-lg data-[vaul-drawer-direction=top]:border-b"
             "data-[vaul-drawer-direction=right]:inset-y-0 data-[vaul-drawer-direction=right]:right-0 data-[vaul-drawer-direction=right]:h-full data-[vaul-drawer-direction=right]:w-auto data-[vaul-drawer-direction=right]:rounded-l-lg data-[vaul-drawer-direction=right]:border-l"
             "data-[vaul-drawer-direction=left]:inset-y-0 data-[vaul-drawer-direction=left]:left-0 data-[vaul-drawer-direction=left]:h-full data-[vaul-drawer-direction=left]:w-auto data-[vaul-drawer-direction=left]:rounded-r-lg data-[vaul-drawer-direction=left]:border-r"
             class))
           (dissoc :class-name))
       [:div
        {:aria-hidden true
         :class
         "bg-muted mx-auto mt-4 hidden h-2 w-[100px] shrink-0 rounded-full group-data-[vaul-drawer-direction=bottom]/drawer-content:block"}]]
      children)]))

(defn drawer-header
  [{:keys [class]} & children]
  (into [:div {:data-slot "drawer-header"
               :class (styles/merge-classes "flex flex-col gap-0.5 p-4 md:gap-1.5" class)}]
        children))

(defn drawer-footer
  [{:keys [class]} & children]
  (into [:div {:data-slot "drawer-footer"
               :class (styles/merge-classes "mt-auto flex flex-col gap-2 p-4" class)}]
        children))

(defn drawer-title
  [{:as raw-props} & children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    (into [:>
           drawer-title-primitive
           (-> props
               (assoc :data-slot "drawer-title"
                      :class (styles/merge-classes "text-foreground font-semibold" class))
               (dissoc :class-name))]
          children)))

(defn drawer-description
  [{:as raw-props} & children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    (into [:>
           drawer-description-primitive
           (-> props
               (assoc :data-slot "drawer-description"
                      :class (styles/merge-classes "text-muted-foreground text-sm" class))
               (dissoc :class-name))]
          children)))
