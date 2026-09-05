(ns clojure-shadcn.ui.components.tag-combobox
  "Tag combobox component for selecting existing tags or creating new ones.
  
  Responsive component that uses popover on desktop and sheet on mobile.
  Allows filtering through existing tags and creating new ones if no match found.

Version: 1.0.0
Last updated: 2026-02-06

Custom component implementation."
  (:require
   ["lucide-react"                        :refer [Check Plus]]
   [clojure-shadcn.ui.components.button   :as mateuszmazurczak-button]
   [clojure-shadcn.ui.components.command  :as command]
   [clojure-shadcn.ui.components.popover  :as popover]
   [clojure-shadcn.ui.components.sheet    :as sheet]
   [clojure-shadcn.ui.hooks.use-is-mobile :as use-mobile]
   [clojure-shadcn.utils.props            :refer [normalize-props]]
   [clojure-shadcn.utils.styles           :refer [merge-classes]]
   [clojure.string                        :as str]
   [reagent.core                          :as    r
                                          :refer [defc]]
   [reagent.hooks                         :as hooks]))

(defn- normalized-tag
  [tag]
  (some-> tag
          str
          str/trim
          str/lower-case))

(defn- distinct-tags
  [tags]
  (vals (reduce (fn [by-name tag]
                  (let [trimmed (str/trim (str tag))]
                    (if (str/blank? trimmed)
                      by-name
                      (update by-name (normalized-tag trimmed) #(or % trimmed)))))
                (sorted-map)
                tags)))

(defc tag-list
 "Command list component for tag selection.
  
  Props:
  - `:tags` - Set of available tag strings
  - `:selected-tag` - Currently selected tag (string or nil)
  - `:on-select` - Callback when tag is selected (fn [tag])
  - `:on-create` - Callback when new tag should be created (fn [tag])
  - `:set-open` - Callback to close the popover/sheet (fn [open?])
  Both kebab-case and camelCase prop spellings are accepted."
 [{:as raw-props}]
 (let [{:keys
        [tags selected-tag on-select on-create set-open search-placeholder empty-text create-label]
        :or {search-placeholder "Search tags..."
             empty-text "No tags found."
             create-label #(str "Create \"" % "\"")}}
       (normalize-props raw-props)
       [search-value set-search-value] (hooks/use-state "")
       candidate (str/trim search-value)
       normalized-search (normalized-tag candidate)
       filtered-tags (if (str/blank? candidate)
                       tags
                       (filter #(str/includes? (normalized-tag %) normalized-search) tags))
       exact-match? (some #(= normalized-search (normalized-tag %)) tags)
       show-create? (and on-create (not (str/blank? candidate)) (not exact-match?))]
   [command/command {}
    [command/command-input {:placeholder search-placeholder
                            :value search-value
                            :onValueChange #(set-search-value %)}]
    [command/command-list {}
     [command/command-empty {}
      empty-text]
     (when (seq filtered-tags)
       [command/command-group {}
        (for [tag (sort filtered-tags)]
          ^{:key tag}
          [command/command-item {:value tag
                                 :on-select
                                 (fn [_] (when on-select (on-select tag)) (set-open false))}
           [:>
            Check
            {:class (merge-classes "size-4" (if (= selected-tag tag) "opacity-100" "opacity-0"))
             :aria-hidden true}]
           [:span tag]])])
     (when show-create?
       [:<>
        (when (seq filtered-tags)
          [command/command-separator {}])
        [command/command-group {:forceMount true}
         [command/command-item
          {:on-select (fn [_] (on-create candidate) (set-search-value "") (set-open false))
           :forceMount true}
          [:>
           Plus
           {:class "size-4"
            :aria-hidden true}]
          [:span
           (if (fn? create-label)
             (create-label candidate)
             (str create-label " " candidate))]]]])]]))

(defc tag-combobox
 "Responsive tag combobox component.
  
  Displays a popover on desktop and a sheet (drawer) on mobile.
  Allows selecting from existing tags or creating new ones.
  
  Props:
  - `:tags` - Set or vector of available tag strings
  - `:selected-tag` - Currently selected tag (string or nil)
  - `:on-select` - Callback when tag is selected (fn [tag])
  - `:on-create` - Callback when new tag should be created (fn [tag])
  - `:placeholder` - Placeholder text for trigger button (default: '+ Add tag')
  - `:class` - Additional Tailwind classes for trigger button
  Both kebab-case and camelCase prop spellings are accepted.
  
  Example:
  ```clojure
  (let [available-tags #{\"important\" \"urgent\" \"review\"}
        selected-tag (r/atom nil)]
    [tag-combobox
     {:tags available-tags
      :selected-tag @selected-tag
      :on-select #(reset! selected-tag %)
      :on-create #(do
                    ;; Add to available tags
                    ;; Dispatch event to backend
                    (reset! selected-tag %))}])
  ```"
 [{:as raw-props}]
 (let [{:keys [tags
               selected-tag
               on-select
               on-create
               placeholder
               class
               search-placeholder
               empty-text
               create-label
               mobile-title]
        :or {placeholder "+ Add tag"
             search-placeholder "Search tags..."
             empty-text "No tags found."
             create-label #(str "Create \"" % "\"")
             mobile-title "Select or create a tag"}}
       (normalize-props raw-props)
       [open? set-open] (hooks/use-state false)
       is-mobile? (use-mobile/use-is-mobile)
       tags-set (distinct-tags tags)
       list-props {:tags tags-set
                   :selected-tag selected-tag
                   :on-select on-select
                   :on-create on-create
                   :set-open set-open
                   :search-placeholder search-placeholder
                   :empty-text empty-text
                   :create-label create-label}]
   (if is-mobile?
     ;; Mobile: use sheet (drawer)
     [sheet/sheet {:open open?
                   :on-open-change (fn [is-open] (set-open is-open))}
      [sheet/sheet-trigger {:as-child true}
       (mateuszmazurczak-button/button {:variant :outline
                                        :class (merge-classes "w-[150px] justify-start" class)}
                                       (if selected-tag selected-tag placeholder))]
      [sheet/sheet-content {:side :bottom}
       [sheet/sheet-title {:class "sr-only"}
        mobile-title]
       [:div {:class "mt-4"}
        [tag-list list-props]]]]
     ;; Desktop: use popover
     [popover/popover {:open open?
                       :onOpenChange (fn [is-open] (set-open is-open))}
      [popover/popover-trigger {:asChild true}
       (mateuszmazurczak-button/button {:variant :outline
                                        :class (merge-classes "w-[150px] justify-start" class)}
                                       (if selected-tag selected-tag placeholder))]
      [popover/popover-content {:class "w-[200px] p-0"
                                :align "start"}
       [tag-list list-props]]])))
