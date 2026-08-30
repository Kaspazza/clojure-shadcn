(ns clojure-shadcn.ui.components.form
  "Practical Reagent adapter for React Hook Form.
  `use-form` returns the native RHF methods object; field render callbacks receive keywordized field/state maps."
  (:require
   ["@radix-ui/react-slot"             :refer [Slot]]
   ["react"                            :as react]
   ["react-hook-form"                  :refer [Controller
                                               FormProvider
                                               useForm
                                               useFormContext
                                               useFormState]]
   [clojure-shadcn.ui.components.label :as label]
   [clojure-shadcn.utils.styles        :refer [merge-classes]]
   [reagent.core                       :as r]))

(def field-context (react/createContext nil))

(def item-context (react/createContext nil))

(defn use-form
  "Creates RHF methods. Accepts a CLJS options map; `:default-values` is converted to JS."
  ([] (useForm))
  ([opts]
   (useForm (clj->js (cond-> opts
                       (:default-values opts) (update :default-values clj->js))))))

(defn form
  [{:keys [methods]
    :as props}
   &
   children]
  (into [:> FormProvider (merge (js->clj methods :keywordize-keys true) (dissoc props :methods))]
        children))

(defn handle-submit
  [^js methods on-valid & [on-invalid]]
  (.handleSubmit methods
                 #(on-valid (js->clj % :keywordize-keys true))
                 (when on-invalid #(on-invalid (js->clj % :keywordize-keys true)))))

(defn form-field
  [{:keys [name render]
    :as props}]
  [:>
   (.-Provider field-context)
   {:value #js {:name name}}
   [:>
    Controller
    (-> props
        (assoc :name name
               :render (fn [^js state]
                         (r/as-element
                          (render {:field (js->clj (.-field state) :keywordize-keys true)
                                   :field-state (js->clj (.-fieldState state) :keywordize-keys true)
                                   :form-state
                                   (js->clj (.-formState state) :keywordize-keys true)}))))
        (dissoc :render))]])

(defn use-form-field
  []
  (let [field (react/useContext field-context)
        item (react/useContext item-context)
        methods (useFormContext)
        name (some-> field
                     .-name)
        state (useFormState #js {:name name})
        field-state (.getFieldState methods name state)
        id (some-> item
                   .-id)]
    (when-not field (throw (js/Error. "use-form-field must be used within form-field")))
    {:id id
     :name name
     :form-item-id (str id "-form-item")
     :form-description-id (str id "-form-item-description")
     :form-message-id (str id "-form-item-message")
     :error (some-> field-state
                    .-error
                    (js->clj :keywordize-keys true))
     :invalid? (boolean (.-invalid field-state))
     :touched? (boolean (.-isTouched field-state))
     :dirty? (boolean (.-isDirty field-state))}))

(defn form-item
  [{:keys [class]
    :as props}
   &
   children]
  (let [id (react/useId)]
    (into [:>
           (.-Provider item-context)
           {:value #js {:id id}}
           [:div
            (-> props
                (assoc :data-slot "form-item" :class (merge-classes "grid gap-2" class))
                (dissoc :class-name))]]
          children)))

(defn form-label
  [{:keys [class]
    :as props}
   &
   children]
  (let [{:keys [invalid? form-item-id]} (use-form-field)]
    (into [label/label
           (-> props
               (assoc :data-slot "form-label"
                      :data-error invalid?
                      :html-for form-item-id
                      :class (merge-classes "data-[error=true]:text-destructive" class))
               (dissoc :class-name))]
          children)))

(defn form-control
  [props & children]
  (let [{:keys [invalid? form-item-id form-description-id form-message-id]} (use-form-field)]
    (into [:>
           Slot
           (assoc props
                  :data-slot "form-control"
                  :id form-item-id
                  :aria-invalid invalid?
                  :aria-describedby
                  (if invalid? (str form-description-id " " form-message-id) form-description-id))]
          children)))

(defn form-description
  [{:keys [class]
    :as props}
   &
   children]
  (let [{:keys [form-description-id]} (use-form-field)]
    (into [:p
           (-> props
               (assoc :data-slot "form-description"
                      :id form-description-id
                      :class (merge-classes "text-sm text-muted-foreground" class))
               (dissoc :class-name))]
          children)))

(defn form-message
  [{:keys [class]
    :as props}
   &
   children]
  (let [{:keys [error form-message-id]} (use-form-field)
        body (or (get error "message") (:message error) (first children))]
    (when body
      [:p
       (-> props
           (assoc :data-slot "form-message"
                  :id form-message-id
                  :class (merge-classes "text-sm text-destructive" class))
           (dissoc :class-name))
       body])))
