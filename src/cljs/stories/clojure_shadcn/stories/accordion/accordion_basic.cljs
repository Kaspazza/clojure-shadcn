(ns clojure-shadcn.stories.accordion.accordion-basic
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [accordion/accordion {:type "single" :collapsible true :default-value "item-1" :class "w-full"}
    [accordion/accordion-item {:value "item-1"}
     [accordion/accordion-trigger {} "Product Information"]
     [accordion/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
      [:p "Our flagship product combines cutting-edge technology with sleek design. Built with premium materials, it offers reliable performance for everyday use."]
      [:p "Key features include advanced processing capabilities and an intuitive interface designed for beginners and experts."]]]
    [accordion/accordion-item {:value "item-2"}
     [accordion/accordion-trigger {} "Shipping Details"]
     [accordion/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
      [:p "We offer worldwide shipping through trusted courier partners. Standard delivery takes 3–5 business days."]
      [:p "Every order is carefully packaged, insured, and available for real-time tracking."]]]
    [accordion/accordion-item {:value "item-3"}
     [accordion/accordion-trigger {} "Return Policy"]
     [accordion/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
      [:p "Return products in their original condition within 30 days for a full refund."]
      [:p "The return process includes free shipping and refunds processed after the returned item is received."]]]]])
