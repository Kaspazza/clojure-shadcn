(ns clojure-shadcn.ui.components.chart
  "Reusable Recharts config, responsive container, tooltip and legend integration."
  (:require
   ["react"                     :as react]
   ["recharts"                  :as recharts]
   [clojure-shadcn.utils.styles :refer [merge-classes]]
   [clojure.string              :as str]
   [reagent.core                :as r]))

(def chart-context (react/createContext nil))

(defn use-chart
  []
  (or (react/useContext chart-context)
      (throw (js/Error. "use-chart must be used within chart-container"))))

(defn- chart-style
  [id config]
  (let [light-rules (for [[k {:keys [theme]}] config
                          :when (:light theme)]
                      (str "--color-" (name k) ":" (:light theme) ";"))
        dark-rules (for [[k {:keys [theme]}] config
                         :when (:dark theme)]
                     (str "--color-" (name k) ":" (:dark theme) ";"))]
    (when (or (seq light-rules) (seq dark-rules))
      [:style {:dangerouslySetInnerHTML {:__html (str "[data-chart='"
                                                      id
                                                      "']{"
                                                      (apply str light-rules)
                                                      "}"
                                                      ".dark [data-chart='"
                                                      id
                                                      "']{"
                                                      (apply str dark-rules)
                                                      "}")}}])))

(defn- chart-color-vars
  [config]
  (into {}
        (keep (fn [[k {:keys [color]}]]
                (when color
                  [(str "--color-" (name k)) color])))
        config))

(defn- chart-container-root
  [^js react-props]
  (let [{:keys [id config class initial-dimension]
         :or {initial-dimension {:width 320
                                 :height 200}}
         :as props}
        (.-chartProps react-props)
        child (.-child react-props)
        generated (react/useId)
        ;; Caller IDs remain DOM IDs; an opaque generated token owns selector identity.
        chart-id (str "chart-" (str/replace generated ":" ""))]
    (r/as-element
     [:>
      (.-Provider chart-context)
      {:value config}
      [:div
       (->
         props
         (assoc
          :data-slot "chart"
          :data-chart chart-id
          :style (merge (chart-color-vars config) (:style props))
          :class
          (merge-classes
           "flex aspect-video justify-center text-xs [&_.recharts-cartesian-axis-tick_text]:fill-muted-foreground [&_.recharts-cartesian-grid_line]:stroke-border/50 [&_.recharts-tooltip-cursor]:stroke-border [&_.recharts-layer]:outline-hidden [&_.recharts-surface]:outline-hidden"
           class))
         (dissoc :config :class-name :initial-dimension))
       [chart-style chart-id config]
       [:> recharts/ResponsiveContainer {:initialDimension (clj->js initial-dimension)} child]]])))

(defn chart-container
  [props child]
  (r/create-element chart-container-root
                    #js {:chartProps props
                         :child (r/as-element child)}))

(def chart-tooltip recharts/Tooltip)

(def chart-legend recharts/Legend)

(defn- payload-config
  [config ^js item key]
  (let [^js payload (some-> item
                            .-payload)
        candidate (or (some-> item
                              (aget key))
                      (some-> payload
                              (aget key))
                      key)]
    (or (get config (keyword (str candidate)))
        (get config (str candidate))
        (get config (keyword key)))))

(defn- tooltip-content-root
  [^js react-props]
  (let [raw-props (.-rawProps react-props)
        config (use-chart)
        {:keys [active
                payload
                label
                class
                indicator
                hide-label?
                hide-indicator?
                formatter
                label-formatter
                name-key]
         :or {indicator :dot}}
        (js->clj raw-props :keywordize-keys true)]
    (r/as-element
     (when (and active (seq payload))
       [:div
        {:class
         (merge-classes
          "grid min-w-32 items-start gap-1.5 rounded-lg border border-border/50 bg-background px-2.5 py-1.5 text-xs shadow-xl"
          class)}
        (when-not hide-label?
          [:div {:class "font-medium"}
           (if label-formatter (label-formatter label payload) label)])
        [:div {:class "grid gap-1.5"}
         (for [[idx ^js item] (map-indexed vector (array-seq (.-payload raw-props)))
               :let [key (str (or name-key (.-name item) (.-dataKey item) "value"))
                     item-config (payload-config config item key)
                     color (or (.-color item)
                               (some-> item
                                       .-payload
                                       .-fill))]
               :when (not= "none" (.-type item))]
           ^{:key idx}
           [:div {:class "flex w-full items-center gap-2"}
            (when-not hide-indicator?
              (if-let [icon (:icon item-config)]
                [:> icon {:className "size-3"}]
                [:span {:class (merge-classes "shrink-0 rounded-sm"
                                              (case indicator
                                                :line "h-3 w-1"
                                                :dashed "h-3 w-0 border border-dashed"
                                                "size-2.5"))
                        :style {:background-color (when-not (= indicator :dashed) color)
                                :border-color color}}]))
            (if formatter
              (formatter (.-value item) (.-name item) item idx (.-payload item))
              [:<>
               [:span {:class "flex-1 text-muted-foreground"}
                (or (:label item-config) (.-name item))]
               [:span {:class "font-mono font-medium tabular-nums"}
                (.toLocaleString (.-value item))]])])]]))))

(defn tooltip-content
  "Recharts `:content` renderer. Config labels/icons/colors are resolved from chart-container."
  [raw-props]
  (r/create-element tooltip-content-root #js {:rawProps raw-props}))

(defn- legend-content-root
  [^js react-props]
  (let [raw-props (.-rawProps react-props)
        config (use-chart)
        payload (some-> raw-props
                        .-payload
                        array-seq)
        top? (= "top" (.-verticalAlign raw-props))]
    (r/as-element (when (seq payload)
                    [:div {:class (merge-classes "flex items-center justify-center gap-4"
                                                 (if top? "pb-3" "pt-3"))}
                     (for [[idx ^js item] (map-indexed vector payload)
                           :let [key (str (or (.-dataKey item) (.-value item) "value"))
                                 cfg (payload-config config item key)]]
                       ^{:key idx}
                       [:div {:class "flex items-center gap-1.5"}
                        (if-let [icon (:icon cfg)]
                          [:> icon {:className "size-3"}]
                          [:span {:class "size-2 rounded-sm"
                                  :style {:background-color (.-color item)}}])
                        (or (:label cfg) (.-value item))])]))))

(defn legend-content [raw-props] (r/create-element legend-content-root #js {:rawProps raw-props}))
