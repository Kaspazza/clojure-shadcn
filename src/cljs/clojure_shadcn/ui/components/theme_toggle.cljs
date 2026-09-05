(ns clojure-shadcn.ui.components.theme-toggle
  "Theme toggle button for switching between light and dark themes.

   Controlled component — the caller owns the theme state and supplies
   :theme (:light | :dark) plus :on-toggle.  No dependence on any global
   state management (re-frame, atoms, contexts are all fine callers).

Version: 2.0.0
Last updated: 2026-02-11"
  (:require
   ["lucide-react"              :refer [Moon Sun]]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :as styles]
   [clojure.string              :as str]))

(def ^:private base-classes
  "Base Tailwind classes shared by all states of the toggle."
  ["relative"
   "inline-flex"
   "items-center"
   "justify-center"
   "rounded-md"
   "border"
   "border-gray-300"
   "dark:border-gray-700"
   "bg-white"
   "dark:bg-gray-800"
   "px-3"
   "py-2"
   "text-sm"
   "font-medium"
   "text-gray-700"
   "dark:text-gray-200"
   "hover:bg-gray-50"
   "dark:hover:bg-gray-700"
   "focus:outline-none"
   "focus:ring-2"
   "focus:ring-indigo-500"
   "focus:ring-offset-2"
   "dark:focus:ring-offset-gray-900"
   "transition-colors"])

(defn theme-toggle
  "Controlled theme toggle button.

   Props (map):
   - :theme      — keyword :light | :dark (default :light); controls which
                   icon is visible.
   - :on-toggle  — (fn []) click handler; caller flips its theme state.
   - :class      — extra Tailwind classes merged with the base ones via
                   tailwind-merge."
  [{:as raw-props}]
  (let [{:keys [theme on-toggle class label]
         :or {theme :light label "Toggle theme"}
         :as props} (normalize-props raw-props)
        is-dark? (= theme :dark)
        button-props (-> props
                         (assoc :type (or (:type props) "button")
                                :on-click on-toggle
                                :aria-label (or (:aria-label props) label)
                                :class (styles/merge-classes (str/join " " base-classes) class))
                         (dissoc :theme :on-toggle :label :class-name))]
    [:button button-props
     [:> Sun {:aria-hidden true
              :class (str "h-[1.2rem] w-[1.2rem] transition-all motion-reduce:transition-none "
                          (if is-dark? "scale-0 -rotate-90" "scale-100 rotate-0"))}]
     [:> Moon {:aria-hidden true
               :class (str "absolute h-[1.2rem] w-[1.2rem] transition-all motion-reduce:transition-none "
                           (if is-dark? "scale-100 rotate-0" "scale-0 rotate-90"))}]]))
