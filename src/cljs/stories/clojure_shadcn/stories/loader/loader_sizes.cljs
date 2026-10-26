(ns clojure-shadcn.stories.loader.loader-sizes
  (:require
   [clojure-shadcn.ui.components.loader :as loader]))

(defn example
  []
  [:div {:class "p-6 overflow-x-auto"}
   (into [:div {:class "grid grid-cols-4 items-center gap-5 text-sm"}
          [:strong "Variant"]
          [:strong "Small"]
          [:strong "Medium"]
          [:strong "Large"]]
         (for [variant [:circular
                        :classic
                        :pulse
                        :pulse-dot
                        :dots
                        :typing
                        :wave
                        :bars
                        :terminal
                        :text-blink
                        :text-shimmer
                        :loading-dots]
               item (concat [[:span {:class "font-mono text-xs"}
                              (name variant)]]
                            (for [size [:sm :md :lg]]
                              [loader/loader {:variant variant
                                              :size size
                                              :text "Loading"
                                              :label (str (name variant) " " (name size))}]))]
           ^{:key (str variant "-" (hash item))} item))])
