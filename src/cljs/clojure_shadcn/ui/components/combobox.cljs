(ns clojure-shadcn.ui.components.combobox
  "Composable combobox built from Radix Popover and cmdk Command primitives.

  This intentionally does not emulate Base UI's Combobox primitive. Radix has no
  combobox primitive, so Popover owns disclosure/focus and cmdk owns filtering and
  keyboard option navigation. See the stories for the resulting API differences."
  (:require
   ["lucide-react"                       :refer [Check ChevronsUpDown X]]
   ["react"                              :as react]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.command :as command]
   [clojure-shadcn.ui.components.popover :as popover]
   [clojure-shadcn.utils.props           :refer [normalize-props]]
   [clojure-shadcn.utils.styles          :refer [merge-classes]]
   [reagent.core                         :as r]))

(def ^:private combobox-context (react/createContext nil))

(defn- use-combobox
  []
  (or (react/useContext combobox-context)
      (throw (js/Error. "Combobox components must be nested inside combobox-root"))))

(defn- context-value [context key] (aget context key))

(defn- selected?
  [value candidate multiple?]
  (if multiple? (boolean (some #(= candidate %) (or value []))) (= value candidate)))

(defn combobox-root
  "State root. Supports controlled :value/:open and uncontrolled :default-value/:default-open.
  Set :multiple? true for vector values. Additional props are forwarded to Radix Popover."
  [{:as raw-props} & children]
  (let [{:keys
         [value default-value on-value-change open default-open on-open-change multiple? disabled?]
         :or {multiple? false
              default-open false}
         :as props}
        (normalize-props raw-props)]
    (r/with-let
     [internal-value
      (r/atom (if (some? default-value) default-value (when multiple? [])))
      internal-open
      (r/atom default-open)]
     (let [controlled-value? (contains? props :value)
           controlled-open? (contains? props :open)
           current-value (if controlled-value? value @internal-value)
           current-open (if controlled-open? open @internal-open)
           change-value! (fn [next-value]
                           (when-not controlled-value? (reset! internal-value next-value))
                           (when on-value-change (on-value-change next-value)))
           change-open! (fn [next-open]
                          (when-not controlled-open? (reset! internal-open next-open))
                          (when on-open-change (on-open-change next-open)))
           select! (fn [candidate]
                     (when-not disabled?
                       (if multiple?
                         (change-value! (if (selected? current-value candidate true)
                                          (vec (remove #(= candidate %) current-value))
                                          (conj (vec (or current-value [])) candidate)))
                         (do (change-value! candidate) (change-open! false)))))
           context (clj->js {:value current-value
                             :open current-open
                             :multiple multiple?
                             :disabled disabled?
                             :setValue change-value!
                             :setOpen change-open!
                             :select select!})
           popover-props (-> props
                             (dissoc :value
                                     :default-value
                                     :on-value-change
                                     :default-open
                                     :multiple?
                                     :disabled?
                                     :dir)
                             (assoc :open current-open :on-open-change change-open!))]
       [:>
        (.-Provider combobox-context)
        {:value context}
        (into [popover/popover popover-props] children)]))))

(defn combobox-trigger
  "Radix Popover trigger rendered as a shadcn Button. Children define trigger contents."
  [{:as raw-props} & children]
  (let [{:keys [class disabled]
         :as props}
        (normalize-props raw-props)
        context (use-combobox)
        open? (context-value context "open")
        disabled? (or disabled (context-value context "disabled"))]
    [popover/popover-trigger {:as-child true}
     (into [button/button
            (-> props
                (assoc :data-slot "combobox-trigger"
                       :type (or (:type props) "button")
                       :variant (or (:variant props) :outline)
                       :role "combobox"
                       :aria-haspopup "listbox"
                       :aria-expanded open?
                       :disabled disabled?
                       :class (merge-classes "w-[240px] justify-between font-normal" class)))]
           (if (seq children)
             children
             [[:span "Select an option…"]
              [:>
               ChevronsUpDown
               {:className "size-4 opacity-50"
                :aria-hidden true}]]))]))

(defn combobox-value
  "Displays selection. :render receives the current value; :placeholder is used when empty."
  [{:keys [class placeholder render]
    :or {placeholder "Select an option…"}}]
  (let [context (use-combobox)
        value (context-value context "value")
        multiple? (context-value context "multiple")
        empty? (if multiple? (empty? value) (nil? value))]
    [:span {:data-slot "combobox-value"
            :class (merge-classes "truncate" class)}
     (if empty? placeholder (if render (render value) (str value)))]))

(defn combobox-content
  "Radix Popover content. Usually contains one combobox-collection."
  [{:keys [class]
    :as props}
   &
   children]
  (into [popover/popover-content
         (assoc props :data-slot "combobox-content" :class (merge-classes "w-[240px] p-0" class))]
        children))

(defn combobox-collection
  "cmdk Command root. Owns filtering and active-option keyboard navigation."
  [props & children]
  (into [command/command (assoc props :data-slot "combobox-collection")] children))

(defn combobox-input
  "cmdk search input. All props are forwarded to Command.Input."
  [props]
  [command/command-input (assoc props :data-slot "combobox-input")])

(defn combobox-list
  "cmdk option list (the popup listbox implementation)."
  [props & children]
  (into [command/command-list (assoc props :data-slot "combobox-list")] children))

(defn combobox-empty
  [props & children]
  (into [command/command-empty (assoc props :data-slot "combobox-empty")] children))

(defn combobox-group
  [props & children]
  (into [command/command-group (assoc props :data-slot "combobox-group")] children))

(defn combobox-label
  "Visual label intended inside combobox-group. Use :heading on combobox-group when cmdk
  filtering should automatically hide the heading with an empty group."
  [{:keys [class]
    :as props}
   &
   children]
  (into [:div
         (assoc props
                :data-slot "combobox-label"
                :class (merge-classes "text-muted-foreground px-2 py-1.5 text-xs font-medium"
                                      class))]
        children))

(defn combobox-separator
  [props]
  [command/command-separator (assoc props :data-slot "combobox-separator")])

(defn combobox-item
  "Selectable cmdk item. :value is the application value; optional :text-value is its
  stable cmdk filtering string. In multiple mode selecting toggles membership."
  [{:keys [value text-value on-select class]
    :as props}
   &
   children]
  (let [context (use-combobox)
        current-value (context-value context "value")
        multiple? (context-value context "multiple")
        checked? (selected? current-value value multiple?)
        select! (context-value context "select")]
    (into [command/command-item
           (-> props
               (dissoc :text-value)
               (assoc :data-slot "combobox-item"
                      :value (or text-value (str value))
                      :aria-selected checked?
                      :on-select (fn [_] (select! value) (when on-select (on-select value)))
                      :class (merge-classes "[&_[data-slot=combobox-item-indicator]]:ml-auto"
                                            class)))]
          (concat children
                  [[:>
                    Check
                    {:data-slot "combobox-item-indicator"
                     :className (merge-classes "size-4" (if checked? "opacity-100" "opacity-0"))
                     :aria-hidden true}]]))))

(defn combobox-clear
  "Accessible clear button. In multiple mode resets to []; otherwise resets to nil."
  [{:keys [class label disabled]
    :or {label "Clear selection"}
    :as props}]
  (let [context (use-combobox)
        set-value! (context-value context "setValue")
        multiple? (context-value context "multiple")
        disabled? (or disabled (context-value context "disabled"))]
    [button/button
     (-> props
         (dissoc :label)
         (assoc :data-slot "combobox-clear"
                :type "button"
                :variant (or (:variant props) :ghost)
                :size (or (:size props) :icon-xs)
                :disabled disabled?
                :aria-label label
                :class class
                :on-click (fn [event]
                            (.stopPropagation event)
                            (when-not disabled? (set-value! (if multiple? [] nil))))))
     [:>
      X
      {:className "size-3.5"
       :aria-hidden true}]]))

(defn combobox-chip
  "Presentational selected-value chip. :on-remove adds an accessible remove button."
  [{:keys [class on-remove remove-label]
    :or {remove-label "Remove"}
    :as props}
   &
   children]
  (into [:span
         (-> props
             (dissoc :on-remove :remove-label)
             (assoc :data-slot "combobox-chip"
                    :class (merge-classes
                            "inline-flex items-center gap-1 rounded-md bg-muted px-2 py-1 text-sm"
                            class)))]
        (cond-> (vec children)
          on-remove (conj [button/button {:type "button"
                                          :variant :ghost
                                          :size :icon-xs
                                          :aria-label remove-label
                                          :on-click on-remove}
                           [:>
                            X
                            {:className "size-3"
                             :aria-hidden true}]]))))

(defn- item-label [item] (or (:label item) (str (:value item))))

(defn simple-combobox
  "Backward-compatible data-driven controlled single-select facade. Prefer the compound
  components for new call sites."
  [{:keys [items
           value
           on-value-change
           placeholder
           search-placeholder
           empty-text
           class
           content-class
           disabled?
           clearable?
           clear-label
           item-render
           value-render]
    :or {placeholder "Select an option…"
         search-placeholder "Search…"
         empty-text "No option found."
         clear-label "Clear selection"}}]
  (let [selected (some #(when (= value (:value %)) %) items)]
    [combobox-root {:value (when selected value)
                    :on-value-change on-value-change
                    :disabled? disabled?}
     [:div {:class "relative inline-flex"}
      [combobox-trigger {:class (merge-classes (when (and clearable? selected) "pr-14") class)}
       [combobox-value {:placeholder placeholder
                        :render (fn [_]
                                  (if value-render (value-render selected) (item-label selected)))}]
       [:>
        ChevronsUpDown
        {:className "size-4 opacity-50"
         :aria-hidden true}]]
      (when (and clearable? selected)
        [combobox-clear {:label clear-label
                         :class "absolute right-7 top-1/2 -translate-y-1/2"}])]
     [combobox-content {:align "start"
                        :class content-class}
      [combobox-collection {}
       [combobox-input {:placeholder search-placeholder}]
       [combobox-list {}
        [combobox-empty {}
         empty-text]
        (into [combobox-group {}]
              (for [{item-value :value
                     :as item}
                    items]
                ^{:key (str item-value)}
                [combobox-item {:value item-value
                                :text-value (item-label item)
                                :keywords (clj->js (:keywords item))
                                :disabled (:disabled? item)}
                 (if item-render (item-render item) (item-label item))]))]]]]))

(def combobox
  "Deprecated compatibility alias. Use simple-combobox explicitly, or combobox-root for
  the compound API."
  simple-combobox)
