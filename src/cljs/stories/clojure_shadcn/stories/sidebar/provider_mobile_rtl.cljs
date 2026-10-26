(ns clojure-shadcn.stories.sidebar.provider-mobile-rtl
  (:require
   ["lucide-react"                       :refer [Kanban]]
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn context-sidebar-layout
  [{:keys [dir label]}]
  (let [{:keys [open? set-open! open-mobile? set-open-mobile! is-mobile state toggle-sidebar!]}
        (sidebar/use-sidebar)
        active-open? (if is-mobile open-mobile? open?)]
    [:div {:class "flex min-h-[420px] w-full overflow-hidden rounded-xl border"
           :dir dir}
     [sidebar/sidebar {:open? active-open?
                       :is-mobile is-mobile
                       :on-open-change (if is-mobile set-open-mobile! set-open!)
                       :side (if (= dir "rtl") "right" "left")
                       :collapsible "icon"}
      [sidebar/sidebar-header {}
       [:strong {:class "px-2 text-sm"}
        label]]
      [sidebar/sidebar-content {}
       [sidebar/sidebar-menu {}
        [sidebar/sidebar-menu-item {}
         [sidebar/sidebar-menu-button {:is-active? true
                                       :collapsed? (= state "collapsed")
                                       :is-mobile is-mobile
                                       :tooltip "Overview"}
          [:> Kanban]
          [:span "Overview"]]]]]
      [sidebar/sidebar-rail {:on-click toggle-sidebar!}]]
     [sidebar/sidebar-inset {}
      [:header {:class "flex h-14 items-center gap-3 border-b px-4"}
       [sidebar/sidebar-trigger {:on-click toggle-sidebar!}]
       [:span {:class "text-sm"}
        (str "state=" state ", mobile=" is-mobile)]
       [:span {:class "ms-auto text-xs text-muted-foreground"}
        "Press Ctrl/Cmd+B"]]
      [:div {:class "p-6 text-sm text-muted-foreground"}
       "Provider state is read with use-sidebar and passed explicitly to every presentational component."]]]))

(defn example
  []
  [sidebar/sidebar-provider {:default-open-mobile? true
                             :is-mobile true}
   [:f>
    context-sidebar-layout
    {:dir "rtl"
     :label "Mobile RTL"}]])
