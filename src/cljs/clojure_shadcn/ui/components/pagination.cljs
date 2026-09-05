(ns clojure-shadcn.ui.components.pagination
  (:require
   ["lucide-react"                      :refer [ChevronLeft ChevronRight MoreHorizontal]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.utils.props          :refer [normalize-props]]
   [clojure-shadcn.utils.styles         :refer [merge-classes]]))

(defn- elem
  [tag slot base raw-props children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [tag
           (-> props
               (assoc :data-slot slot :class (merge-classes base class))
               (dissoc :class-name))]
          children)))

(defn pagination
  [props & children]
  (elem :nav
        "pagination"
        "mx-auto flex w-full justify-center"
        (merge {:role "navigation"
                :aria-label "pagination"}
               props)
        children))

(defn pagination-content
  [props & children]
  (elem :ul "pagination-content" "flex flex-row items-center gap-1" props children))

(defn pagination-item [props & children] (elem :li "pagination-item" nil props children))

(defn pagination-link
  [{:as raw-props} & children]
  (let [{:keys [class is-active size]
         :or {size :icon}
         :as props}
        (normalize-props raw-props)
        anchor-props (-> props
                         (assoc :data-slot "pagination-link"
                                :data-active is-active
                                :aria-current (when is-active "page")
                                :class class)
                         (dissoc :is-active :size))]
    [button/button {:as-child true
                    :variant (if is-active :outline :ghost)
                    :size size}
     (into [:a anchor-props] children)]))

(defn pagination-previous
  [props]
  (pagination-link (merge {:size :default
                           :aria-label "Go to previous page"
                           :class "gap-1 px-2.5 sm:pl-2.5"}
                          props)
                   [:> ChevronLeft]
                   [:span {:class "hidden sm:block"}
                    "Previous"]))

(defn pagination-next
  [props]
  (pagination-link (merge {:size :default
                           :aria-label "Go to next page"
                           :class "gap-1 px-2.5 sm:pr-2.5"}
                          props)
                   [:span {:class "hidden sm:block"}
                    "Next"]
                   [:> ChevronRight]))

(defn pagination-ellipsis
  [{:keys [label]
    :or {label "More pages"}
    :as props}]
  (elem :span
        "pagination-ellipsis"
        "flex size-9 items-center justify-center"
        (dissoc props :label)
        [[:>
          MoreHorizontal
          {:aria-hidden true
           :class "size-4"}]
         [:span {:class "sr-only"}
          label]]))
