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
  [props & children]
  [alert-dialog-portal {}
   [alert-dialog-overlay {}]
   (element
    primitive/Content
    "alert-dialog-content"
    "bg-background data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 data-[state=closed]:zoom-out-95 data-[state=open]:zoom-in-95 fixed top-[50%] left-[50%] z-50 grid w-full max-w-[calc(100%-2rem)] translate-x-[-50%] translate-y-[-50%] gap-4 rounded-lg border p-6 shadow-lg duration-200 sm:max-w-lg"
    props
    children)])

(defn alert-dialog-header
  [props & children]
  (element "div"
           "alert-dialog-header"
           "flex flex-col gap-2 text-center sm:text-left"
           props
           children))

(defn alert-dialog-footer
  [props & children]
  (element "div"
           "alert-dialog-footer"
           "flex flex-col-reverse gap-2 sm:flex-row sm:justify-end"
           props
           children))

(defn alert-dialog-title
  [props & children]
  (element primitive/Title "alert-dialog-title" "text-lg font-semibold" props children))

(defn alert-dialog-description
  [props & children]
  (element primitive/Description
           "alert-dialog-description"
           "text-muted-foreground text-sm"
           props
           children))

(defn alert-dialog-action
  [props & children]
  (element primitive/Action
           "alert-dialog-action"
           nil
           (assoc (normalize-props props) :as-child true)
           [(into [button {}]
                  children)]))

(defn alert-dialog-cancel
  [props & children]
  (element primitive/Cancel
           "alert-dialog-cancel"
           nil
           (assoc (normalize-props props) :as-child true)
           [(into [button {:variant :outline}]
                  children)]))
