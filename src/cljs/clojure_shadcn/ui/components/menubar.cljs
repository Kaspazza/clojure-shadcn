(ns clojure-shadcn.ui.components.menubar
  (:require ["@radix-ui/react-menubar" :as P]
            ["lucide-react" :refer [Check ChevronRight Circle]]
            [clojure-shadcn.utils.props :refer [normalize-props]]
            [clojure-shadcn.utils.styles :refer [merge-classes]]))
(def content-class "z-50 min-w-[12rem] origin-(--radix-menubar-content-transform-origin) overflow-hidden rounded-md border bg-popover p-1 text-popover-foreground shadow-md data-[side=bottom]:slide-in-from-top-2 data-[side=left]:slide-in-from-right-2 data-[side=right]:slide-in-from-left-2 data-[side=top]:slide-in-from-bottom-2 data-[state=closed]:fade-out-0 data-[state=closed]:zoom-out-95 data-[state=open]:animate-in data-[state=open]:fade-in-0 data-[state=open]:zoom-in-95")
(def item-class "relative flex cursor-default items-center gap-2 rounded-sm px-2 py-1.5 text-sm outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[disabled]:pointer-events-none data-[disabled]:opacity-50 data-[inset]:pl-8 data-[variant=destructive]:text-destructive data-[variant=destructive]:focus:bg-destructive/10 data-[variant=destructive]:focus:text-destructive dark:data-[variant=destructive]:focus:bg-destructive/20 [&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4 [&_svg:not([class*='text-'])]:text-muted-foreground data-[variant=destructive]:*:[svg]:text-destructive!")
(defn- primitive [component slot base raw-props children] (let [{:keys [class] :as props} (normalize-props raw-props)] (into [:> component (-> props (assoc :data-slot slot :class (merge-classes base class)) (dissoc :class-name))] children)))
(defn menubar [p & c] (primitive (.-Root P) "menubar" "flex h-9 items-center gap-1 rounded-md border bg-background p-1 shadow-xs" p c))
(defn menubar-menu [p & c] (primitive (.-Menu P) "menubar-menu" nil p c))
(defn menubar-group [p & c] (primitive (.-Group P) "menubar-group" nil p c))
(defn menubar-portal [p & c] (primitive (.-Portal P) "menubar-portal" nil p c))
(defn menubar-radio-group [p & c] (primitive (.-RadioGroup P) "menubar-radio-group" nil p c))
(defn menubar-trigger [p & c] (primitive (.-Trigger P) "menubar-trigger" "flex items-center rounded-sm px-2 py-1 text-sm font-medium outline-hidden select-none focus:bg-accent focus:text-accent-foreground data-[state=open]:bg-accent" p c))
(defn menubar-content [{:as raw-props} & c] (let [{:keys [align align-offset side-offset] :or {align :start align-offset -4 side-offset 8} :as p} (normalize-props raw-props)] [:> (.-Portal P) (primitive (.-Content P) "menubar-content" content-class (-> p (assoc :align (name align) :align-offset align-offset :side-offset side-offset) (dissoc :align-offset :side-offset)) c)]))
(defn menubar-item [{:as raw-props} & c] (let [{:keys [variant inset] :or {variant :default} :as p} (normalize-props raw-props)] (primitive (.-Item P) "menubar-item" item-class (-> p (assoc :data-inset inset :data-variant (name variant)) (dissoc :variant :inset)) c)))
(defn menubar-checkbox-item [p & c] (primitive (.-CheckboxItem P) "menubar-checkbox-item" (str item-class " pl-8") p (cons [:span {:class "pointer-events-none absolute left-2"} [:> (.-ItemIndicator P) [:> Check {:class "size-4"}]]] c)))
(defn menubar-radio-item [p & c] (primitive (.-RadioItem P) "menubar-radio-item" (str item-class " pl-8") p (cons [:span {:class "pointer-events-none absolute left-2"} [:> (.-ItemIndicator P) [:> Circle {:class "size-2 fill-current"}]]] c)))
(defn menubar-label [{:as raw-props} & children] (let [{:keys [inset] :as props} (normalize-props raw-props)] (primitive (.-Label P) "menubar-label" "px-2 py-1.5 text-sm font-medium data-[inset]:pl-8" (-> props (assoc :data-inset inset) (dissoc :inset)) children)))
(defn menubar-separator [p] (primitive (.-Separator P) "menubar-separator" "-mx-1 my-1 h-px bg-border" p []))
(defn menubar-shortcut [p & c] (primitive "span" "menubar-shortcut" "ml-auto text-xs tracking-widest text-muted-foreground" p c))
(defn menubar-sub [p & c] (primitive (.-Sub P) "menubar-sub" nil p c))
(defn menubar-sub-trigger [{:as raw-props} & children] (let [{:keys [inset] :as props} (normalize-props raw-props)] (primitive (.-SubTrigger P) "menubar-sub-trigger" "flex cursor-default items-center rounded-sm px-2 py-1.5 text-sm outline-none select-none focus:bg-accent focus:text-accent-foreground data-[inset]:pl-8 data-[state=open]:bg-accent data-[state=open]:text-accent-foreground" (-> props (assoc :data-inset inset) (dissoc :inset)) (concat children [[:> ChevronRight {:class "ml-auto h-4 w-4"}]]))))
(defn menubar-sub-content [p & c] (primitive (.-SubContent P) "menubar-sub-content" (merge-classes content-class "min-w-[8rem] shadow-lg data-[state=closed]:animate-out") p c))
