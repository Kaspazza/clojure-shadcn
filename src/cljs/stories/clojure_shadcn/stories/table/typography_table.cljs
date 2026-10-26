(ns clojure-shadcn.stories.table.typography-table)

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "my-6 w-full overflow-y-auto"}
    [:table {:class "w-full"}
     [:thead
      [:tr {:class "even:bg-muted m-0 border-t p-0"}
       [:th {:class "border px-4 py-2 text-left font-bold"}
        "King's Treasury"]
       [:th {:class "border px-4 py-2 text-left font-bold"}
        "People's happiness"]]]
     [:tbody
      [:tr {:class "even:bg-muted m-0 border-t p-0"}
       [:td {:class "border px-4 py-2 text-left"}
        "Empty"]
       [:td {:class "border px-4 py-2 text-left"}
        "Overflowing"]]
      [:tr {:class "even:bg-muted m-0 border-t p-0"}
       [:td {:class "border px-4 py-2 text-left"}
        "Modest"]
       [:td {:class "border px-4 py-2 text-left"}
        "Satisfied"]]
      [:tr {:class "even:bg-muted m-0 border-t p-0"}
       [:td {:class "border px-4 py-2 text-left"}
        "Full"]
       [:td {:class "border px-4 py-2 text-left"}
        "Ecstatic"]]]]]])
