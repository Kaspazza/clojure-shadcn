(ns clojure-shadcn.ui.components.alert-dialog
  (:require
   ["@radix-ui/react-alert-dialog"      :as primitive]
   [clojure-shadcn.ui.components.button :refer [button]]
   [clojure-shadcn.utils.props          :refer [normalize-props]]
   [clojure-shadcn.utils.styles         :refer [merge-classes]]))

(defn- element
  [component slot base raw-props children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:>
           component
           (-> props
               (assoc :data-slot slot :class (merge-classes base class))
               (dissoc :class-name))]
          children)))

(defn alert-dialog [props & children] (element primitive/Root "alert-dialog" nil props children))

(defn alert-dialog-trigger
  [props & children]
  (element primitive/Trigger "alert-dialog-trigger" nil props children))

(defn alert-dialog-portal
  [props & children]
  (element primitive/Portal "alert-dialog-portal" nil props children))

(defn alert-dialog-overlay
  [props]
  (element
   primitive/Overlay
   "alert-dialog-overlay"
   "data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 fixed inset-0 z-50 bg-black/50"
   props
   []))

(defn alert-dialog-content
  [{:as raw-props} & children]
  (let [{:keys [size]
         :or {size :default}
         :as props}
        (normalize-props raw-props)]
    (element
     primitive/Portal
     "alert-dialog-portal"
     nil
     {}
     [(element
       primitive/Overlay
       "alert-dialog-overlay"
       "fixed inset-0 z-50 bg-black/50 data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:animate-in data-[state=open]:fade-in-0"
       {}
       [])
      (element
       primitive/Content
       "alert-dialog-content"
       "group/alert-dialog-content fixed top-[50%] left-[50%] z-50 grid w-full max-w-[calc(100%-2rem)] translate-x-[-50%] translate-y-[-50%] gap-4 rounded-lg border bg-background p-6 shadow-lg duration-200 data-[size=sm]:max-w-xs data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=closed]:zoom-out-95 data-[state=open]:animate-in data-[state=open]:fade-in-0 data-[state=open]:zoom-in-95 data-[size=default]:sm:max-w-lg"
       (-> props
           (assoc :data-size (name size))
           (dissoc :size))
       children)])))

(defn alert-dialog-header
  [props & children]
  (element
   "div"
   "alert-dialog-header"
   "grid grid-rows-[auto_1fr] place-items-center gap-1.5 text-center has-data-[slot=alert-dialog-media]:grid-rows-[auto_auto_1fr] has-data-[slot=alert-dialog-media]:gap-x-6 sm:group-data-[size=default]/alert-dialog-content:place-items-start sm:group-data-[size=default]/alert-dialog-content:text-left sm:group-data-[size=default]/alert-dialog-content:has-data-[slot=alert-dialog-media]:grid-rows-[auto_1fr]"
   props
   children))

(defn alert-dialog-footer
  [props & children]
  (element
   "div"
   "alert-dialog-footer"
   "flex flex-col-reverse gap-2 group-data-[size=sm]/alert-dialog-content:grid group-data-[size=sm]/alert-dialog-content:grid-cols-2 sm:flex-row sm:justify-end"
   props
   children))

(defn alert-dialog-title
  [props & children]
  (element
   primitive/Title
   "alert-dialog-title"
   "text-lg font-semibold sm:group-data-[size=default]/alert-dialog-content:group-has-data-[slot=alert-dialog-media]/alert-dialog-content:col-start-2"
   props
   children))

(defn alert-dialog-description
  [props & children]
  (element primitive/Description
           "alert-dialog-description"
           "text-muted-foreground text-sm"
           props
           children))

(defn alert-dialog-media
  "Optional visual context for an alert dialog. Additional props are forwarded to the div."
  [props & children]
  (element
   "div"
   "alert-dialog-media"
   "mb-2 inline-flex size-16 items-center justify-center rounded-md bg-muted sm:group-data-[size=default]/alert-dialog-content:row-span-2 *:[svg:not([class*='size-'])]:size-8"
   props
   children))

(defn alert-dialog-action
  "Consequential action composed with a button.

  Button props such as :variant, :size, :disabled and :on-click are forwarded to
  the rendered button while Radix retains the alert-dialog dismissal behavior."
  [props & children]
  (element primitive/Action
           "alert-dialog-action"
           nil
           {:as-child true}
           [(apply button props children)]))

(defn alert-dialog-cancel
  "Safe escape action composed with an outline button by default."
  [props & children]
  (element primitive/Cancel
           "alert-dialog-cancel"
           nil
           {:as-child true}
           [(apply button (merge {:variant :outline} props) children)]))
