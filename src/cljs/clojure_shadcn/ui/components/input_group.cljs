(ns clojure-shadcn.ui.components.input-group
  "Accessible compound input primitives."
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input :as input]
   [clojure-shadcn.ui.components.textarea :as textarea]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn input-group [{:keys [class] :as props} & children]
  (into [:div (-> props (assoc :data-slot "input-group" :role "group"
                               :class (merge-classes "group/input-group relative flex min-w-0 w-full items-center rounded-md border border-input shadow-xs outline-none transition-[color,box-shadow] h-9 has-[>textarea]:h-auto has-[[data-slot=input-group-control]:focus-visible]:border-ring has-[[data-slot=input-group-control]:focus-visible]:ring-[3px] has-[[data-slot=input-group-control]:focus-visible]:ring-ring/50 has-[[aria-invalid=true]]:border-destructive has-[[aria-invalid=true]]:ring-destructive/20 dark:bg-input/30" class))
                    (dissoc :class :class-name))] children))

(defn input-group-addon [{:keys [class align on-click] :or {align :inline-start} :as props} & children]
  (let [focus-input (fn [e]
                      (when-not (.closest (.-target e) "button")
                        (some-> (.-currentTarget e) .-parentElement (.querySelector "input,textarea") .focus))
                      (when on-click (on-click e)))
        align-class (case align
                      :inline-end "order-last pr-3"
                      :block-start "order-first w-full justify-start px-3 pt-3"
                      :block-end "order-last w-full justify-start px-3 pb-3"
                      "order-first pl-3")]
    (into [:div (-> props (assoc :data-slot "input-group-addon" :data-align (name align) :role "group"
                                  :on-click focus-input
                                  :class (merge-classes "flex h-auto cursor-text items-center justify-center gap-2 py-1.5 text-sm font-medium text-muted-foreground select-none [&>svg:not([class*='size-'])]:size-4" align-class class))
                       (dissoc :class :class-name :align))] children)))

(defn input-group-button [{:keys [class size] :or {size :xs} :as props} & children]
  (let [size-class (case size :sm "h-8 px-2.5" :icon-xs "size-6 p-0" :icon-sm "size-8 p-0" "h-6 px-2")]
    (into [button/button (-> props (assoc :type (or (:type props) "button") :size :xs :variant (or (:variant props) :ghost)
                                     :data-size (name size) :class (merge-classes "flex items-center gap-1 text-sm shadow-none" size-class class))
                             (dissoc :class-name))] children)))

(defn input-group-text [{:keys [class] :as props} & children]
  (into [:span (-> props (assoc :class (merge-classes "flex items-center gap-2 text-sm text-muted-foreground [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4" class)) (dissoc :class-name))] children))

(defn input-group-input [{:keys [class] :as props}]
  [input/input (-> props (assoc :data-slot "input-group-control" :class (merge-classes "flex-1 rounded-none border-0 bg-transparent shadow-none focus-visible:ring-0 dark:bg-transparent" class)) (dissoc :class-name))])

(defn input-group-textarea [{:keys [class] :as props}]
  [textarea/textarea (-> props (assoc :data-slot "input-group-control" :class (merge-classes "flex-1 resize-none rounded-none border-0 bg-transparent py-3 shadow-none focus-visible:ring-0 dark:bg-transparent" class)) (dissoc :class-name))])
