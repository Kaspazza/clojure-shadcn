(ns clojure-shadcn.stories.scroll-area.scroll-area-composition-limitation)

(defn example
  []
  [:div {:class "max-w-lg rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-sm"}
   "The public scroll-bar can render only under a Radix Root, while scroll-area nests all children in its Viewport. Use :orientation for the managed vertical or horizontal bar; dual-axis or custom sibling composition requires a root/viewport API change."])
