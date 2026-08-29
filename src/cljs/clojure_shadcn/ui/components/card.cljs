(ns clojure-shadcn.ui.components.card
  (:require [clojure-shadcn.utils.props :refer [normalize-props]]
            [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- element [tag slot base raw-props children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    (into [tag (-> props (assoc :data-slot slot :class (merge-classes base class))
                         (dissoc :class-name))] children)))

(defn card [props & children] (element :div "card" "flex flex-col gap-6 rounded-xl border bg-card py-6 text-card-foreground shadow-sm" props children))
(defn card-header [props & children] (element :div "card-header" "@container/card-header grid auto-rows-min grid-rows-[auto_auto] items-start gap-2 px-6 has-data-[slot=card-action]:grid-cols-[1fr_auto] [.border-b]:pb-6" props children))
(defn card-title [props & children] (element :div "card-title" "leading-none font-semibold" props children))
(defn card-description [props & children] (element :div "card-description" "text-sm text-muted-foreground" props children))
(defn card-action [props & children] (element :div "card-action" "col-start-2 row-span-2 row-start-1 self-start justify-self-end" props children))
(defn card-content [props & children] (element :div "card-content" "px-6" props children))
(defn card-footer [props & children] (element :div "card-footer" "flex items-center px-6 [.border-t]:pt-6" props children))
