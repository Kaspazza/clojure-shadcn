(ns clojure-shadcn.stories.breadcrumb.breadcrumb-responsive
  (:require
   [clojure-shadcn.ui.components.breadcrumb    :as breadcrumb]
   [clojure-shadcn.ui.components.drawer        :as drawer]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [reagent.core                               :as r]))

(defn example
  []
  (r/with-let
   [dropdown-open?
    (r/atom false)
    drawer-open?
    (r/atom false)
    items
    [{:href "#"
      :label "Home"}
     {:href "#"
      :label "Documentation"}
     {:href "#"
      :label "Building Your Application"}
     {:href "#"
      :label "Data Fetching"}
     {:label "Caching and Revalidating"}]]
   [:div {:class "p-6 space-y-6"}
    [:div {:class "hidden md:block"}
     [breadcrumb/breadcrumb {}
      [breadcrumb/breadcrumb-list {}
       [breadcrumb/breadcrumb-item {}
        [breadcrumb/breadcrumb-link {:href (:href (first items))}
         (:label (first items))]]
       [breadcrumb/breadcrumb-separator {}]
       [breadcrumb/breadcrumb-item {}
        [dropdown-menu/dropdown-menu {:open @dropdown-open?
                                      :on-open-change #(reset! dropdown-open? %)}
         [dropdown-menu/dropdown-menu-trigger {:as-child true}
          [:button {:class "flex items-center gap-1"
                    :aria-label "Toggle menu"}
           [breadcrumb/breadcrumb-ellipsis {}]]]
         [dropdown-menu/dropdown-menu-content {:align "start"}
          (for [{:keys [href label]} (subvec (vec items) 1 3)]
            ^{:key label}
            [dropdown-menu/dropdown-menu-item {}
             [:a {:href (or href "#")}
              label]])]]]
       [breadcrumb/breadcrumb-separator {}]
       [breadcrumb/breadcrumb-item {}
        [breadcrumb/breadcrumb-link {:href "#"
                                     :class "max-w-20 truncate"}
         "Data Fetching"]]
       [breadcrumb/breadcrumb-separator {}]
       [breadcrumb/breadcrumb-item {}
        [breadcrumb/breadcrumb-page {:class "max-w-20 truncate"}
         "Caching and Revalidating"]]]]]
    [:div {:class "md:hidden"}
     [breadcrumb/breadcrumb {}
      [breadcrumb/breadcrumb-list {}
       [breadcrumb/breadcrumb-item {}
        [breadcrumb/breadcrumb-link {:href (:href (first items))}
         (:label (first items))]]
       [breadcrumb/breadcrumb-separator {}]
       [breadcrumb/breadcrumb-item {}
        [drawer/drawer {:open @drawer-open?
                        :on-open-change #(reset! drawer-open? %)}
         [drawer/drawer-trigger {:class "cursor-pointer"
                                 :aria-label "Toggle menu"}
          [breadcrumb/breadcrumb-ellipsis {}]]
         [drawer/drawer-content {}
          [drawer/drawer-header {:class "text-left"}
           [drawer/drawer-title {}
            "Navigate to"]
           [drawer/drawer-description {}
            "Select a page to navigate to."]]
          [:div {:class "grid gap-1 px-4"}
           (for [{:keys [href label]} (subvec (vec items) 1 3)]
             ^{:key label}
             [:a {:href (or href "#")
                  :class "py-1 text-sm"}
              label])]
          [drawer/drawer-footer {:class "pt-4"}
           [drawer/drawer-close
            {:class
             "inline-flex h-10 w-full items-center justify-center rounded-md border border-input bg-background px-4 py-2 text-sm font-medium ring-offset-background transition-colors hover:bg-accent hover:text-accent-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"}
            "Close"]]]]]]]]]))
