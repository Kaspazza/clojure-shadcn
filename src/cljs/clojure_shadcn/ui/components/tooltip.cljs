(ns clojure-shadcn.ui.components.tooltip
  "Radix Tooltip wrappers with two deliberately separate APIs.

  The compound API mirrors upstream composition: `tooltip-provider`,
  `tooltip-root`, `tooltip-trigger`, `tooltip-portal`, `tooltip-content`, and
  `tooltip-arrow`. Every wrapper accepts a props map, forwards additional props,
  and adds its matching `data-slot`.

  `simple-tooltip` is the monolithic convenience API. The historical `tooltip`
  name aliases `simple-tooltip` for source compatibility; it is not the compound
  root. The historical `tooltip-provider-component` remains the raw Radix React
  Provider for callers using `[:> tooltip-provider-component ...]`.

  Both kebab-case and camelCase keyword props are accepted. If both forms are
  present, kebab-case wins."
  (:require
   ["@radix-ui/react-tooltip"   :as primitive]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(def ^:private content-classes
  "bg-primary text-primary-foreground animate-in fade-in-0 zoom-in-95 data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=closed]:zoom-out-95 data-[side=bottom]:slide-in-from-top-2 data-[side=left]:slide-in-from-right-2 data-[side=right]:slide-in-from-left-2 data-[side=top]:slide-in-from-bottom-2 z-50 w-fit origin-(--radix-tooltip-content-transform-origin) rounded-md px-3 py-1.5 text-xs text-balance")

(def ^:private arrow-classes
  "bg-primary fill-primary z-50 size-2.5 translate-y-[calc(-50%_-_2px)] rotate-45 rounded-[2px]")

(defn- enum-name [value] (if (keyword? value) (name value) value))

(defn- radix-props
  [raw-props slot]
  (let [props (normalize-props (or raw-props {}))]
    (cond-> (assoc props :data-slot slot)
      (:side props) (update :side enum-name)
      (:align props) (update :align enum-name)
      (:sticky props) (update :sticky enum-name)
      (:collision-boundary props) (update :collision-boundary identity))))

(defn tooltip-provider
  "Radix Tooltip.Provider. Forwards provider timing, hoverability, direction, and all upstream props."
  [props & children]
  (into [:> primitive/Provider (radix-props props "tooltip-provider")] children))

(defn tooltip-root
  "Radix Tooltip.Root. Supports uncontrolled or controlled state and per-tooltip delay options."
  [props & children]
  (into [:> primitive/Root (radix-props props "tooltip")] children))

(defn tooltip-trigger
  "Radix Tooltip.Trigger. Pass `:as-child true` to compose with one focusable child."
  [props & children]
  (into [:> primitive/Trigger (radix-props props "tooltip-trigger")] children))

(defn tooltip-content
  "Styled Radix Tooltip.Content. Positioning, collision, event, and DOM props are forwarded."
  [raw-props & children]
  (let [{:keys [class side-offset]
         :or {side-offset 0}
         :as props}
        (radix-props raw-props "tooltip-content")]
    (into [:>
           primitive/Content
           (-> props
               (assoc :side-offset side-offset :class (merge-classes content-classes class))
               (dissoc :class-name))]
          children)))

(defn tooltip-portal
  "Radix Tooltip.Portal. `:container` and `:force-mount` are forwarded upstream.
  Direct tooltip-content children are expanded so Radix can attach its DOM ref
  to Content rather than to a Reagent component instance. Custom child components
  must likewise forward their ref to the underlying DOM element."
  [props & children]
  (into [:> primitive/Portal (radix-props props "tooltip-portal")]
        (map (fn [child]
               (if (and (vector? child) (= tooltip-content (first child)))
                 (apply tooltip-content (rest child))
                 child)))
        children))

(defn tooltip-arrow
  "Styled Radix Tooltip.Arrow. Size, styling, and SVG props are forwarded."
  [raw-props & children]
  (let [{:keys [class]
         :as props}
        (radix-props raw-props "tooltip-arrow")]
    (into [:>
           primitive/Arrow
           (-> props
               (assoc :class (merge-classes arrow-classes class))
               (dissoc :class-name))]
          children)))

(def tooltip-provider-component
  "Compatibility alias for the raw Radix Provider React component. Prefer the
  Reagent wrapper `tooltip-provider` in new code."
  primitive/Provider)

(defn- native-interactive-trigger?
  [trigger]
  (and (vector? trigger)
       (contains? #{:a :button :input :select :textarea :summary} (first trigger))))

(defn simple-tooltip
  "Monolithic convenience tooltip.

  Required options are `:trigger` and `:content`. Existing options remain
  supported: side/alignment/collision options, delay and controlled state,
  `:content-class`, `:content-hidden?`, `:trigger-as-child?`, and
  `:disable-hoverable-content?`.

  `:root-props`, `:trigger-props`, `:portal-props`, `:content-props`, and
  `:arrow-props` forward directly to their compound parts. Explicit convenience
  options win over forwarding-map values. Set `:arrow? true` to render an arrow."
  [{:keys [trigger
           content
           side
           side-offset
           align
           align-offset
           collision-padding
           avoid-collisions?
           sticky
           delay-duration
           open
           default-open
           on-open-change
           content-class
           content-hidden?
           trigger-as-child?
           disable-hoverable-content?
           root-props
           trigger-props
           portal-props
           content-props
           arrow?
           arrow-props]
    :or {side-offset 4
         avoid-collisions? true
         delay-duration 700}}]
  (let [as-child?
        (if (some? trigger-as-child?) trigger-as-child? (native-interactive-trigger? trigger))
        root-props (cond-> (merge root-props
                                  {:delay-duration delay-duration
                                   :disable-hoverable-content disable-hoverable-content?})
                     (some? open) (assoc :open open)
                     (some? default-open) (assoc :default-open default-open)
                     on-open-change (assoc :on-open-change on-open-change))
        content-props (cond-> (merge content-props
                                     {:side-offset side-offset
                                      :avoid-collisions avoid-collisions?
                                      :class content-class})
                        side (assoc :side side)
                        align (assoc :align align)
                        (some? align-offset) (assoc :align-offset align-offset)
                        (some? collision-padding) (assoc :collision-padding collision-padding)
                        sticky (assoc :sticky sticky)
                        (some? content-hidden?) (assoc :hidden content-hidden?))]
    [tooltip-root
     root-props
     [tooltip-trigger (assoc (or trigger-props {}) :as-child as-child?) trigger]
     [tooltip-portal
      portal-props
      [tooltip-content content-props content (when arrow? [tooltip-arrow arrow-props])]]]))

(def tooltip
  "Compatibility alias for `simple-tooltip`. New compound callers should use
  `tooltip-root`; `tooltip` intentionally retains its historical map API."
  simple-tooltip)
