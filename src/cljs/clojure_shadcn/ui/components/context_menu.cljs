(ns clojure-shadcn.ui.components.context-menu
  (:require
   ["@radix-ui/react-context-menu" :as ContextMenuPrimitive]
   ["lucide-react"                 :refer [Check ChevronRight Circle]]
   [clojure-shadcn.utils.props     :refer [normalize-props]]
   [clojure-shadcn.utils.styles    :refer [merge-classes]]))

(defn- primitive
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

(def content-class
  "z-50 min-w-[8rem] origin-(--radix-context-menu-content-transform-origin) overflow-hidden rounded-md border bg-popover p-1 text-popover-foreground shadow-lg data-[side=bottom]:slide-in-from-top-2 data-[side=left]:slide-in-from-right-2 data-[side=right]:slide-in-from-left-2 data-[side=top]:slide-in-from-bottom-2 data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=closed]:zoom-out-95 data-[state=open]:animate-in data-[state=open]:fade-in-0 data-[state=open]:zoom-in-95")

(def item-class
  "relative flex cursor-default items-center gap-2 rounded-sm px-2 py-1.5 text-sm outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[disabled]:pointer-events-none data-[disabled]:opacity-50 data-[inset]:pl-8 data-[variant=destructive]:text-destructive data-[variant=destructive]:focus:bg-destructive/10 data-[variant=destructive]:focus:text-destructive dark:data-[variant=destructive]:focus:bg-destructive/20 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4 [&_svg:not([class*='text-'])]:text-muted-foreground data-[variant=destructive]:*:[svg]:text-destructive!")

(defn context-menu
  [props & children]
  (primitive (.-Root ContextMenuPrimitive) "context-menu" nil props children))

(defn context-menu-trigger
  [props & children]
  (primitive (.-Trigger ContextMenuPrimitive) "context-menu-trigger" nil props children))

(defn context-menu-group
  [props & children]
  (primitive (.-Group ContextMenuPrimitive) "context-menu-group" nil props children))

(defn context-menu-portal
  [props & children]
  (primitive (.-Portal ContextMenuPrimitive) "context-menu-portal" nil props children))

(defn context-menu-sub
  [props & children]
  (primitive (.-Sub ContextMenuPrimitive) "context-menu-sub" nil props children))

(defn context-menu-radio-group
  [props & children]
  (primitive (.-RadioGroup ContextMenuPrimitive) "context-menu-radio-group" nil props children))

(defn context-menu-sub-trigger
  [{:as raw-props} & children]
  (let [{:keys [inset]
         :as props}
        (normalize-props raw-props)]
    (primitive
     (.-SubTrigger ContextMenuPrimitive)
     "context-menu-sub-trigger"
     "flex cursor-default items-center rounded-sm px-2 py-1.5 text-sm outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[inset]:pl-8 data-[state=open]:bg-accent data-[state=open]:text-accent-foreground [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4 [&_svg:not([class*='text-'])]:text-muted-foreground"
     (-> props
         (assoc :data-inset inset)
         (dissoc :inset))
     (concat children [[:> ChevronRight {:class "ml-auto"}]]))))

(defn context-menu-sub-content
  [props & children]
  (primitive (.-SubContent ContextMenuPrimitive)
             "context-menu-sub-content"
             content-class
             props
             children))

(defn context-menu-content
  [props & children]
  [:>
   (.-Portal ContextMenuPrimitive)
   (primitive
    (.-Content ContextMenuPrimitive)
    "context-menu-content"
    (merge-classes
     content-class
     "max-h-(--radix-context-menu-content-available-height) overflow-x-hidden overflow-y-auto shadow-md")
    props
    children)])

(defn context-menu-item
  [{:as raw-props} & children]
  (let [{:keys [variant inset]
         :or {variant :default}
         :as props}
        (normalize-props raw-props)]
    (primitive (.-Item ContextMenuPrimitive)
               "context-menu-item"
               item-class
               (-> props
                   (assoc :data-inset inset :data-variant (name variant))
                   (dissoc :variant :inset))
               children)))

(defn context-menu-checkbox-item
  [props & children]
  (primitive
   (.-CheckboxItem ContextMenuPrimitive)
   "context-menu-checkbox-item"
   "relative flex cursor-default items-center gap-2 rounded-sm py-1.5 pr-2 pl-8 text-sm outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[disabled]:pointer-events-none data-[disabled]:opacity-50 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4"
   props
   (cons [:span {:class
                 "pointer-events-none absolute left-2 flex size-3.5 items-center justify-center"}
          [:> (.-ItemIndicator ContextMenuPrimitive) [:> Check {:class "size-4"}]]]
         children)))

(defn context-menu-radio-item
  [props & children]
  (primitive
   (.-RadioItem ContextMenuPrimitive)
   "context-menu-radio-item"
   "relative flex cursor-default items-center gap-2 rounded-sm py-1.5 pr-2 pl-8 text-sm outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[disabled]:pointer-events-none data-[disabled]:opacity-50 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4"
   props
   (cons [:span {:class
                 "pointer-events-none absolute left-2 flex size-3.5 items-center justify-center"}
          [:> (.-ItemIndicator ContextMenuPrimitive) [:> Circle {:class "size-2 fill-current"}]]]
         children)))

(defn context-menu-label
  [{:as raw-props} & children]
  (let [{:keys [inset]
         :as props}
        (normalize-props raw-props)]
    (primitive (.-Label ContextMenuPrimitive)
               "context-menu-label"
               "px-2 py-1.5 text-sm font-medium text-foreground data-[inset]:pl-8"
               (-> props
                   (assoc :data-inset inset)
                   (dissoc :inset))
               children)))

(defn context-menu-separator
  [props]
  (primitive (.-Separator ContextMenuPrimitive)
             "context-menu-separator"
             "-mx-1 my-1 h-px bg-border"
             props
             []))

(defn context-menu-shortcut
  [props & children]
  (primitive "span"
             "context-menu-shortcut"
             "ml-auto text-xs tracking-widest text-muted-foreground"
             props
             children))
