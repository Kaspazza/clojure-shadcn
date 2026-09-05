(ns clojure-shadcn.ui.components.loader
  "Accessible loader primitives. All variants forward DOM/ARIA props to their root."
  (:require
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- valid-size [size] (if (#{:sm :md :lg} size) size :md))
(defn- size-classes
  [size]
  ({:sm "size-4"
    :md "size-5"
    :lg "size-6"}
   (valid-size size)))
(defn- root-props
  [raw-props classes]
  (let [{:keys [class size label]
         :or {label "Loading"}
         :as props}
        (normalize-props raw-props)]
    (-> props
        (assoc :role (or (:role props) "status")
               :aria-label (or (:aria-label props) label)
               :class (merge-classes classes class))
        (dissoc :class-name :size :label :text :variant))))
(defn- animation-delay [value] {:animation-delay value})

(defn circular-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    [:div
     (root-props
      props
      (merge-classes
       "border-primary animate-spin motion-reduce:animate-none rounded-full border-2 border-t-transparent"
       (size-classes size)))]))

(defn classic-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))
        dimensions ({:sm ["6px" "1.5px" "-0.75px" "0.75px 10px"]
                     :md ["8px" "2px" "-1px" "1px 12px"]
                     :lg ["10px" "2.5px" "-1.25px" "1.25px 14px"]}
                    size)
        [height width margin-left transform-origin] dimensions]
    (into
     [:div (root-props props (merge-classes "relative" (size-classes size)))]
     (for [i (range 12)]
       ^{:key i}
       [:div
        {:aria-hidden true
         :class
         "bg-primary absolute animate-[spinner-fade_1.2s_linear_infinite] motion-reduce:animate-none rounded-full"
         :style {:top "0"
                 :left "50%"
                 :margin-left margin-left
                 :transform-origin transform-origin
                 :transform (str "rotate(" (* i 30) "deg)")
                 :opacity 0
                 :animation-delay (str (* i 0.1) "s")
                 :height height
                 :width width}}]))))

(defn pulse-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    [:div
     (root-props props (merge-classes "relative" (size-classes size)))
     [:div
      {:aria-hidden true
       :class
       "border-primary absolute inset-0 animate-[thin-pulse_1.5s_ease-in-out_infinite] motion-reduce:animate-none rounded-full border-2"}]]))

(defn pulse-dot-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    [:div
     (root-props
      props
      (merge-classes
       "bg-primary animate-[pulse-dot_1.2s_ease-in-out_infinite] motion-reduce:animate-none rounded-full"
       ({:sm "size-1"
         :md "size-2"
         :lg "size-3"}
        size)))]))

(defn- dots*
  [props count animation sizes container-sizes delay-ms]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    (into [:div
           (root-props props (merge-classes "flex items-center space-x-1" (container-sizes size)))]
          (for [i (range count)]
            ^{:key i}
            [:div {:aria-hidden true
                   :class (merge-classes "bg-primary rounded-full motion-reduce:animate-none"
                                         animation
                                         (sizes size))
                   :style (animation-delay (str (* i delay-ms) "ms"))}]))))

(defn dots-loader
  [props]
  (dots* props
         3
         "animate-[bounce-dots_1.4s_ease-in-out_infinite]"
         {:sm "h-1.5 w-1.5"
          :md "h-2 w-2"
          :lg "h-2.5 w-2.5"}
         {:sm "h-4"
          :md "h-5"
          :lg "h-6"}
         160))
(defn typing-loader
  [props]
  (dots* props
         3
         "animate-[typing_1s_infinite]"
         {:sm "h-1 w-1"
          :md "h-1.5 w-1.5"
          :lg "h-2 w-2"}
         {:sm "h-4"
          :md "h-5"
          :lg "h-6"}
         250))

(defn wave-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))
        heights ({:sm ["6px" "9px" "12px" "9px" "6px"]
                  :md ["8px" "12px" "16px" "12px" "8px"]
                  :lg ["10px" "15px" "20px" "15px" "10px"]}
                 size)]
    (into
     [:div
      (root-props props
                  (merge-classes "flex items-center gap-0.5"
                                 ({:sm "h-4"
                                   :md "h-5"
                                   :lg "h-6"}
                                  size)))]
     (for [i (range 5)]
       ^{:key i}
       [:div
        {:aria-hidden true
         :class
         (merge-classes
          "bg-primary animate-[wave_1s_ease-in-out_infinite] motion-reduce:animate-none rounded-full"
          ({:sm "w-0.5"
            :md "w-0.5"
            :lg "w-1"}
           size))
         :style {:animation-delay (str (* i 100) "ms")
                 :height (nth heights i)}}]))))

(defn bars-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    (into
     [:div
      (root-props props
                  (merge-classes "flex"
                                 ({:sm "h-4 gap-1"
                                   :md "h-5 gap-1.5"
                                   :lg "h-6 gap-2"}
                                  size)))]
     (for [i (range 3)]
       ^{:key i}
       [:div
        {:aria-hidden true
         :class
         (merge-classes
          "bg-primary h-full animate-[wave-bars_1.2s_ease-in-out_infinite] motion-reduce:animate-none"
          ({:sm "w-1"
            :md "w-1.5"
            :lg "w-2"}
           size))
         :style (animation-delay (str (* i 0.2) "s"))}]))))

(defn terminal-loader
  [props]
  (let [size (valid-size (or (:size (normalize-props props)) :md))]
    [:div
     (root-props props
                 (merge-classes "flex items-center space-x-1"
                                ({:sm "h-4"
                                  :md "h-5"
                                  :lg "h-6"}
                                 size)))
     [:span {:aria-hidden true
             :class (merge-classes "text-primary font-mono"
                                   ({:sm "text-xs"
                                     :md "text-sm"
                                     :lg "text-base"}
                                    size))}
      ">"]
     [:div {:aria-hidden true
            :class (merge-classes
                    "bg-primary animate-[blink_1s_step-end_infinite] motion-reduce:animate-none"
                    ({:sm "h-3 w-1.5"
                      :md "h-4 w-2"
                      :lg "h-5 w-2.5"}
                     size))}]]))

(defn- text-loader
  [props classes]
  (let [{:keys [text size]
         :or {text "Thinking"
              size :md}}
        (normalize-props props)
        size (valid-size size)]
    [:div
     (root-props props
                 (merge-classes classes
                                ({:sm "text-xs"
                                  :md "text-sm"
                                  :lg "text-base"}
                                 size)))
     text]))
(defn text-blink-loader
  [props]
  (text-loader
   props
   "animate-[text-blink_2s_ease-in-out_infinite] motion-reduce:animate-none font-medium"))
(defn text-shimmer-loader
  [props]
  (text-loader
   props
   "bg-[linear-gradient(to_right,var(--muted-foreground)_40%,var(--foreground)_60%,var(--muted-foreground)_80%)] bg-size-[200%_auto] bg-clip-text font-medium text-transparent animate-[shimmer_4s_infinite_linear] motion-reduce:animate-none"))
(defn text-dots-loader
  [props]
  (let [{:keys [text size]
         :or {text "Thinking"
              size :md}}
        (normalize-props props)
        size (valid-size size)]
    [:div
     (root-props props "inline-flex items-center")
     [:span {:class (merge-classes "text-primary font-medium"
                                   ({:sm "text-xs"
                                     :md "text-sm"
                                     :lg "text-base"}
                                    size))}
      text]
     [:span {:aria-hidden true
             :class "inline-flex motion-reduce:hidden"}
      (for [i (range 3)]
        ^{:key i}
        [:span {:class "text-primary animate-[loading-dots_1.4s_infinite]"
                :style (animation-delay (str (* (inc i) 0.2) "s"))}
         "."])]]))

(defn loader
  [{:as raw-props}]
  (let [{:keys [variant]
         :or {variant :circular}
         :as props}
        (normalize-props raw-props)
        component ({:circular circular-loader
                    :classic classic-loader
                    :pulse pulse-loader
                    :pulse-dot pulse-dot-loader
                    :dots dots-loader
                    :typing typing-loader
                    :wave wave-loader
                    :bars bars-loader
                    :terminal terminal-loader
                    :text-blink text-blink-loader
                    :text-shimmer text-shimmer-loader
                    :loading-dots text-dots-loader}
                   variant
                   circular-loader)]
    [component (dissoc props :variant)]))
