(ns clojure-shadcn.ui.components.native-select
  (:require ["lucide-react" :refer [ChevronDown]]
            [clojure-shadcn.utils.props :refer [normalize-props]]
            [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn native-select [{:as raw-props} & children]
  (let [{:keys [class size] :or {size :default} :as props} (normalize-props raw-props)]
    [:div {:data-slot "native-select-wrapper" :class "group/native-select relative w-fit has-[select:disabled]:opacity-50"}
     (into [:select (-> props
                        (assoc :data-slot "native-select" :data-size (name size)
                               :class (merge-classes "h-9 w-full min-w-0 appearance-none rounded-md border border-input bg-transparent px-3 py-2 pr-9 text-sm shadow-xs transition-[color,box-shadow] outline-none selection:bg-primary selection:text-primary-foreground placeholder:text-muted-foreground disabled:pointer-events-none disabled:cursor-not-allowed data-[size=sm]:h-8 data-[size=sm]:py-1 dark:bg-input/30 dark:hover:bg-input/50 focus-visible:border-ring focus-visible:ring-[3px] focus-visible:ring-ring/50 aria-invalid:border-destructive aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40" class))
                        (dissoc :class-name :size))] children)
     [:> ChevronDown {:data-slot "native-select-icon" :aria-hidden true :class "pointer-events-none absolute top-1/2 right-3.5 size-4 -translate-y-1/2 text-muted-foreground opacity-50 select-none"}]]))

(defn- option-element [tag slot raw-props children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    (into [tag (-> props (assoc :data-slot slot :class (merge-classes "bg-[Canvas] text-[CanvasText]" class)) (dissoc :class-name))] children)))
(defn native-select-option [props & children] (option-element :option "native-select-option" props children))
(defn native-select-optgroup [props & children] (option-element :optgroup "native-select-optgroup" props children))
