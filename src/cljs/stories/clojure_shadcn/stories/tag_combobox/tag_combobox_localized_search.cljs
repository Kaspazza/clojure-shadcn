(ns clojure-shadcn.stories.tag-combobox.tag-combobox-localized-search
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [tags (r/atom ["Projekt" "Pilne" "Do przeglądu"])
        selected (r/atom nil)]
    (fn [] [:div {:class "max-w-sm space-y-3 p-6"}
            [:p {:class "text-sm text-muted-foreground"}
             "Search for ‘brak’ to see the localized empty state and create action."]
            [tag-combobox/tag-combobox {:tags @tags
                                        :selected-tag @selected
                                        :placeholder "+ Dodaj tag"
                                        :search-placeholder "Szukaj tagów…"
                                        :empty-text "Nie znaleziono tagów."
                                        :mobile-title "Wybierz lub utwórz tag"
                                        :create-label #(str "Utwórz „" % "”")
                                        :on-select #(reset! selected %)
                                        :on-create
                                        (fn [tag] (swap! tags conj tag) (reset! selected tag))}]])))
