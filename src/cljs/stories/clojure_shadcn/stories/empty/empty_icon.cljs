(ns clojure-shadcn.stories.empty.empty-icon
  (:require
   ["lucide-react"                     :refer [Bell Bookmark Heart Inbox]]
   [clojure-shadcn.ui.components.empty :as empty]))

(defn example
  []
  [:div {:class "p-6 grid gap-8 md:grid-cols-2"}
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Inbox]]
     [empty/empty-title {}
      "No messages"]
     [empty/empty-description {}
      "Your inbox is empty. New messages will appear here."]]]
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Heart]]
     [empty/empty-title {}
      "No likes yet"]
     [empty/empty-description {}
      "Content you like will be saved here for easy access."]]]
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Bookmark]]
     [empty/empty-title {}
      "No bookmarks"]
     [empty/empty-description {}
      "Save interesting content by bookmarking it."]]]
   [empty/empty {}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Bell]]
     [empty/empty-title {}
      "No notifications"]
     [empty/empty-description {}
      "You're all caught up. New notifications will appear here."]]]])
