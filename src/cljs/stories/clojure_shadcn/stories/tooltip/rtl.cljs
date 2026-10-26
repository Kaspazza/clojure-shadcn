(ns clojure-shadcn.stories.tooltip.rtl
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn- compound-tooltip
  [{:keys [trigger content root content-props trigger-props portal arrow-props]}]
  [tooltip/tooltip-root
   root
   [tooltip/tooltip-trigger (merge {:as-child true} trigger-props) trigger]
   [tooltip/tooltip-portal
    portal
    [tooltip/tooltip-content
     content-props
     content
     (when arrow-props [tooltip/tooltip-arrow arrow-props])]]])

(defn example
  []
  [tooltip/tooltip-provider {:dir "rtl"}
   [:div {:class "p-12 text-right"
          :dir "rtl"}
    [compound-tooltip {:trigger (button/button {:variant :outline} "معلومات إضافية")
                       :content "تظهر هذه المعلومة عند التركيز أو المرور."
                       :content-props {:side :top
                                       :align :start}}]]])
