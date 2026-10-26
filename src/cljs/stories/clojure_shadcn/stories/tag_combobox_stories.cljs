(ns clojure-shadcn.stories.tag-combobox-stories
  "Storybook stories for the Tag Combobox component. Ported from mateuszmazurczak.portfolio.ui-components.tag_combobox."
  (:require
   [clojure-shadcn.stories.helpers                                    :as helpers]
   [clojure-shadcn.stories.tag-combobox.basic-tag-selection           :as
                                                                      example-basic-tag-selection]
   [clojure-shadcn.stories.tag-combobox.multiple-tag-comboboxes
    :as example-multiple-tag-comboboxes]
   [clojure-shadcn.stories.tag-combobox.playground                    :as example-playground]
   [clojure-shadcn.stories.tag-combobox.tag-combobox-case-handling
    :as example-tag-combobox-case-handling]
   [clojure-shadcn.stories.tag-combobox.tag-combobox-localized-search
    :as example-tag-combobox-localized-search]
   [clojure-shadcn.stories.tag-combobox.tag-creation-workflow         :as
                                                                      example-tag-creation-workflow]
   [clojure-shadcn.stories.tag-combobox.tag-with-custom-placeholder
    :as example-tag-with-custom-placeholder]
   [clojure-shadcn.stories.tag-combobox.tag-with-custom-width         :as
                                                                      example-tag-with-custom-width]
   [reagent.core                                                      :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Tag Combobox"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Tag combobox component for selecting existing tags or creating new ones."
                        :npm-install "npm install lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.tag_combobox")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/tag_combobox.cljs"
                        :filename "tag_combobox.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "tag-combobox"
       :description
       "Responsive tag selector that supports both choosing existing tags and creating new tags. Uses Popover on desktop and Sheet on mobile for better ergonomics."
       :link {:href "https://ui.shadcn.com/docs/components/combobox"
              :label "Combobox Pattern (shadcn)"}
       :props [{:name ":tags"
                :type "set<string> | vector<string>"
                :default nil
                :description "Available tags shown in the list."}
               {:name ":selected-tag"
                :type "string | nil"
                :default nil
                :description "Currently selected tag."}
               {:name ":on-select"
                :type "function"
                :default nil
                :description "Called when user selects a tag: (fn [tag] ...)."}
               {:name ":on-create"
                :type "function"
                :default nil
                :description "Called when user creates a new tag: (fn [new-tag] ...)."}
               {:name ":placeholder"
                :type "string"
                :default "\"+ Add tag\""
                :description "Trigger button text when no tag is selected."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes merged into the trigger button."}
               {:name ":search-placeholder / :empty-text / :mobile-title"
                :type "string"
                :default "localized English defaults"
                :description "Consumer-supplied search, empty-state, and mobile-sheet text."}
               {:name ":create-label"
                :type "string | function"
                :default "function"
                :description "Creation copy or (fn [candidate] label)."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "The component is controlled by your state: keep :selected-tag in sync via :on-select."]
       [:li
        "To allow creating missing tags, provide :on-create and persist the new tag in your tag source."]
       [:li
        "Desktop and mobile render different containers (Popover vs Sheet) but share the same API."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [tags (r/atom #{\"bug\" \"feature\"})\n      selected (r/atom nil)]\n  [tag-combobox {:tags @tags\n                 :selected-tag @selected\n                 :on-select #(reset! selected %)\n                 :on-create (fn [new-tag]\n                              (swap! tags conj new-tag)\n                              (reset! selected new-tag))}])"]]]]])))

(defstory
 BasicTagSelection
 "Basic tag combobox with predefined tags.

   Select from existing tags or create new ones.
   Responsive: uses popover on desktop, sheet on mobile."
 {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.basic-tag-selection")}
 []
 (r/as-element (helpers/wrap-component [example-basic-tag-selection/example])))

(defstory TagWithCustomPlaceholder
          "Tag combobox with custom placeholder text."
          {:source-code (embed-source
                         "clojure-shadcn.stories.tag-combobox.tag-with-custom-placeholder")}
          []
          (r/as-element (helpers/wrap-component [example-tag-with-custom-placeholder/example])))

(defstory TagWithCustomWidth
          "Tag combobox with custom width styling."
          {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.tag-with-custom-width")}
          []
          (r/as-element (helpers/wrap-component [example-tag-with-custom-width/example])))

(defstory
 MultipleTagComboboxes
 "Multiple independent tag comboboxes.

   Shows how to use multiple tag comboboxes with different tag sets."
 {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.multiple-tag-comboboxes")}
 []
 (r/as-element (helpers/wrap-component [example-multiple-tag-comboboxes/example])))

(defstory
 TagCreationWorkflow
 "Demonstrates the tag creation workflow.

   Type a tag name that doesn't exist and see the 'Create' option appear."
 {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.tag-creation-workflow")}
 []
 (r/as-element (helpers/wrap-component [example-tag-creation-workflow/example])))


(defstory Playground
          "Interactive tag-combobox playground."
          {:args {:placeholder "+ Add tag"
                  :allow-create true}
           :arg-types {:placeholder {:name "Placeholder"
                                     :control {:type "text"}}
                       :allow-create {:name "Allow new tags"
                                      :control {:type "boolean"}}}
           :parameters
           {:playground {:description
                         "Explore tag combobox placeholder and allow new tags with a live preview."}
            :controls {:expanded false
                       :exclude ["tags" "selected-tag" "on-select" "on-create" "class"]}}
           :source-code (embed-source "clojure-shadcn.stories.tag-combobox.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory
 TagComboboxLocalizedSearch
 "Localized consumer copy covers search, empty results, creation, and the responsive sheet title."
 {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.tag-combobox-localized-search")}
 []
 (r/as-element (helpers/wrap-component [example-tag-combobox-localized-search/example])))

(defstory
 TagComboboxCaseHandling
 "Input tags are trimmed and deduplicated case-insensitively while preserving the first display spelling."
 {:source-code (embed-source "clojure-shadcn.stories.tag-combobox.tag-combobox-case-handling")}
 []
 (r/as-element (helpers/wrap-component [example-tag-combobox-case-handling/example])))