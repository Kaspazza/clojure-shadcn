(ns clojure-shadcn.ui.components.navigation-menu
  (:require
   ["@radix-ui/react-navigation-menu" :as P]
   ["lucide-react"                    :refer [ChevronDown]]
   [clojure-shadcn.utils.props        :refer [normalize-props]]
   [clojure-shadcn.utils.styles       :refer [merge-classes]]))

(declare navigation-menu-viewport)

(def trigger-style
  "group inline-flex h-9 w-max items-center justify-center rounded-md bg-background px-4 py-2 text-sm font-medium transition-[color,box-shadow] outline-none hover:bg-accent hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground focus-visible:ring-[3px] focus-visible:ring-ring/50 focus-visible:outline-1 disabled:pointer-events-none disabled:opacity-50 data-[state=open]:bg-accent/50 data-[state=open]:text-accent-foreground data-[state=open]:hover:bg-accent data-[state=open]:focus:bg-accent")

(defn navigation-menu-trigger-style [& classes] (apply merge-classes trigger-style classes))

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

(defn navigation-menu
  [{:as raw-props} & children]
  (let [{:keys [class viewport]
         :or {viewport true}
         :as props}
        (normalize-props raw-props)]
    (primitive (.-Root P)
               "navigation-menu"
               "group/navigation-menu relative flex max-w-max flex-1 items-center justify-center"
               (-> props
                   (assoc :data-viewport viewport :class class)
                   (dissoc :viewport))
               (cond-> (vec children)
                 viewport (conj [navigation-menu-viewport {}])))))

(defn navigation-menu-list
  [p & c]
  (primitive (.-List P)
             "navigation-menu-list"
             "group flex flex-1 list-none items-center justify-center gap-1"
             p
             c))

(defn navigation-menu-item [p & c] (primitive (.-Item P) "navigation-menu-item" "relative" p c))

(defn navigation-menu-trigger
  [p & children]
  (primitive
   (.-Trigger P)
   "navigation-menu-trigger"
   trigger-style
   p
   (concat
    children
    [[:>
      ChevronDown
      {:aria-hidden true
       :class
       "relative top-px ml-1 size-3 transition duration-300 group-data-[state=open]:rotate-180"}]])))

(defn navigation-menu-content
  [p & c]
  (primitive
   (.-Content P)
   "navigation-menu-content"
   "top-0 left-0 w-full p-2 pr-2.5 data-[motion=from-end]:slide-in-from-right-52 data-[motion=from-start]:slide-in-from-left-52 data-[motion=to-end]:slide-out-to-right-52 data-[motion=to-start]:slide-out-to-left-52 data-[motion^=from-]:animate-in data-[motion^=from-]:fade-in data-[motion^=to-]:animate-out data-[motion^=to-]:fade-out md:absolute md:w-auto group-data-[viewport=false]/navigation-menu:top-full group-data-[viewport=false]/navigation-menu:mt-1.5 group-data-[viewport=false]/navigation-menu:overflow-hidden group-data-[viewport=false]/navigation-menu:rounded-md group-data-[viewport=false]/navigation-menu:border group-data-[viewport=false]/navigation-menu:bg-popover group-data-[viewport=false]/navigation-menu:text-popover-foreground group-data-[viewport=false]/navigation-menu:shadow group-data-[viewport=false]/navigation-menu:duration-200 **:data-[slot=navigation-menu-link]:focus:ring-0 **:data-[slot=navigation-menu-link]:focus:outline-none"
   p
   c))

(defn navigation-menu-link
  [p & c]
  (primitive
   (.-Link P)
   "navigation-menu-link"
   "flex flex-col gap-1 rounded-sm p-2 text-sm transition-all outline-none hover:bg-accent hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground focus-visible:ring-[3px] focus-visible:ring-ring/50 focus-visible:outline-1 data-[active=true]:bg-accent/50 data-[active=true]:text-accent-foreground data-[active=true]:hover:bg-accent data-[active=true]:focus:bg-accent [&_svg:not([class*='size-'])]:size-4 [&_svg:not([class*='text-'])]:text-muted-foreground"
   p
   c))

(defn navigation-menu-viewport
  [p]
  [:div {:class "absolute top-full left-0 isolate z-50 flex justify-center"}
   (primitive
    (.-Viewport P)
    "navigation-menu-viewport"
    "origin-top-center relative mt-1.5 h-[var(--radix-navigation-menu-viewport-height)] w-full overflow-hidden rounded-md border bg-popover text-popover-foreground shadow data-[state=closed]:animate-out data-[state=closed]:zoom-out-95 data-[state=open]:animate-in data-[state=open]:zoom-in-90 md:w-[var(--radix-navigation-menu-viewport-width)]"
    p
    [])])

(defn navigation-menu-indicator
  [p]
  (primitive
   (.-Indicator P)
   "navigation-menu-indicator"
   "top-full z-[1] flex h-1.5 items-end justify-center overflow-hidden data-[state=hidden]:animate-out data-[state=hidden]:fade-out data-[state=visible]:animate-in data-[state=visible]:fade-in"
   p
   [[:div {:class "relative top-[60%] h-2 w-2 rotate-45 rounded-tl-sm bg-border shadow-md"}]]))
