(ns clojure-shadcn.stories.direction.direction-radix-descendant
  (:require
   ["@radix-ui/react-radio-group"          :as radio-group]
   [clojure-shadcn.ui.components.direction :as direction]))

(defn example
  []
  [direction/direction-provider {:direction :rtl}
   [:div {:dir "rtl"
          :class "space-y-3 rounded-md border p-5"}
    [:p {:class "text-sm"}
     "اختر خطة، ثم استخدم مفاتيح الأسهم"]
    [:>
     (.-Root radio-group)
     {:defaultValue "team"
      :className "flex gap-4"}
     (for [[value label] [["solo" "فردي"] ["team" "فريق"] ["org" "مؤسسة"]]]
       ^{:key value}
       [:label {:class "flex items-center gap-2"}
        [:>
         (.-Item radio-group)
         {:value value
          :className "size-5 rounded-full border"}
         [:>
          (.-Indicator radio-group)
          {:className "block size-full scale-50 rounded-full bg-primary"}]]
        label])]]])
