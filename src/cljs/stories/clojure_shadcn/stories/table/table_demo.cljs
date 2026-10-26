(ns clojure-shadcn.stories.table.table-demo
  (:require
   [clojure-shadcn.ui.components.table :as table]))

(defn example
  []
  (let [invoices [{:id "INV001"
                   :status "Paid"
                   :method "Credit Card"
                   :amount "$250.00"}
                  {:id "INV002"
                   :status "Pending"
                   :method "PayPal"
                   :amount "$150.00"}
                  {:id "INV003"
                   :status "Unpaid"
                   :method "Bank Transfer"
                   :amount "$350.00"}
                  {:id "INV004"
                   :status "Paid"
                   :method "Credit Card"
                   :amount "$450.00"}
                  {:id "INV005"
                   :status "Paid"
                   :method "PayPal"
                   :amount "$550.00"}
                  {:id "INV006"
                   :status "Pending"
                   :method "Bank Transfer"
                   :amount "$200.00"}
                  {:id "INV007"
                   :status "Unpaid"
                   :method "Credit Card"
                   :amount "$300.00"}]]
    [:div {:class "p-6"}
     [table/table {}
      [table/table-caption {}
       "A list of your recent invoices."]
      [table/table-header {}
       [table/table-row {}
        [table/table-head {:class "w-[120px]"}
         "Invoice"]
        [table/table-head {}
         "Status"]
        [table/table-head {}
         "Method"]
        [table/table-head {:class "text-right"}
         "Amount"]]]
      [table/table-body {}
       (for [{:keys [id status method amount]} invoices]
         ^{:key id}
         [table/table-row {}
          [table/table-cell {:class "font-medium"}
           id]
          [table/table-cell {}
           status]
          [table/table-cell {}
           method]
          [table/table-cell {:class "text-right"}
           amount]])]
      [table/table-footer {}
       [table/table-row {}
        [table/table-cell {:col-span 3}
         "Total"]
        [table/table-cell {:class "text-right"}
         "$2,500.00"]]]]]))
