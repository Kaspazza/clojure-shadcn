(ns clojure-shadcn.ui.components.spinner
  "Spinner component for loading states.

Version: 1.0.0
Last updated: 2026-02-06

Custom component implementation."
  (:require
   ["lucide-react"              :refer [Loader2]]
   [clojure-shadcn.utils.styles :as styles]))

(defn spinner
  "Loading spinner component.
  
  Props:
  - `:class` - Additional CSS classes (optional)
  - Other props passed to svg element"
  [{:keys [class]
    :as props}]
  (let [other-props (dissoc props :class :class-name)]
    [:>
     Loader2
     (merge {:aria-hidden true
             :focusable false
             :class (styles/merge-classes "size-4 animate-spin motion-reduce:animate-none" class)}
            other-props)]))
