(ns clojure-shadcn.ui.components.typography
  "Reusable shadcn-style typography elements. Project extra; not registry:ui."
  (:refer-clojure :exclude [list small])
  (:require [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- text-element [tag classes {:keys [class] :as props} children]
  (into [tag (-> props (assoc :data-slot "typography" :class (merge-classes classes class))
                 (dissoc :class-name))] children))

(defn h1 [props & children] (text-element :h1 "scroll-m-20 text-4xl font-extrabold tracking-tight text-balance" props children))
(defn h2 [props & children] (text-element :h2 "scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight first:mt-0" props children))
(defn h3 [props & children] (text-element :h3 "scroll-m-20 text-2xl font-semibold tracking-tight" props children))
(defn h4 [props & children] (text-element :h4 "scroll-m-20 text-xl font-semibold tracking-tight" props children))
(defn p [props & children] (text-element :p "leading-7 [&:not(:first-child)]:mt-6" props children))
(defn blockquote [props & children] (text-element :blockquote "mt-6 border-l-2 pl-6 italic" props children))
(defn list [props & children] (text-element :ul "my-6 ml-6 list-disc [&>li]:mt-2" props children))
(defn inline-code [props & children] (text-element :code "bg-muted relative rounded px-[0.3rem] py-[0.2rem] font-mono text-sm font-semibold" props children))
(defn lead [props & children] (text-element :p "text-muted-foreground text-xl" props children))
(defn large [props & children] (text-element :div "text-lg font-semibold" props children))
(defn small [props & children] (text-element :small "text-sm leading-none font-medium" props children))
(defn muted [props & children] (text-element :p "text-muted-foreground text-sm" props children))
(defn table [props & children]
  [:div {:data-slot "typography-table" :class "my-6 w-full overflow-y-auto"}
   (text-element :table "w-full" props children)])
