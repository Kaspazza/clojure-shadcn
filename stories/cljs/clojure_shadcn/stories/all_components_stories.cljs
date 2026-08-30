(ns clojure-shadcn.stories.all-components-stories
  "A single scrollable catalogue of every clojure-shadcn component."
  (:require
   [clojure-shadcn.stories.accordion-stories                 :as accordion]
   [clojure-shadcn.stories.alert-dialog-stories              :as alert-dialog]
   [clojure-shadcn.stories.alert-stories                     :as alert]
   [clojure-shadcn.stories.aspect-ratio-stories              :as aspect-ratio]
   [clojure-shadcn.stories.attachment-stories                :as attachment]
   [clojure-shadcn.stories.avatar-stories                    :as avatar]
   [clojure-shadcn.stories.badge-stories                     :as badge]
   [clojure-shadcn.stories.breadcrumb-stories                :as breadcrumb]
   [clojure-shadcn.stories.bubble-stories                    :as bubble]
   [clojure-shadcn.stories.button-group-stories              :as button-group]
   [clojure-shadcn.stories.button-stories                    :as button]
   [clojure-shadcn.stories.calendar-stories                  :as calendar]
   [clojure-shadcn.stories.card-stories                      :as card]
   [clojure-shadcn.stories.carousel-stories                  :as carousel]
   [clojure-shadcn.stories.chart-stories                     :as chart]
   [clojure-shadcn.stories.chat-container-stories            :as chat-container]
   [clojure-shadcn.stories.checkbox-stories                  :as checkbox]
   [clojure-shadcn.stories.code-block-stories                :as code-block]
   [clojure-shadcn.stories.collapsible-stories               :as collapsible]
   [clojure-shadcn.stories.combobox-stories                  :as combobox]
   [clojure-shadcn.stories.command-stories                   :as command]
   [clojure-shadcn.stories.context-menu-stories              :as context-menu]
   [clojure-shadcn.stories.data-table-stories                :as data-table]
   [clojure-shadcn.stories.date-picker-stories               :as date-picker]
   [clojure-shadcn.stories.dialog-stories                    :as dialog]
   [clojure-shadcn.stories.direction-stories                 :as direction]
   [clojure-shadcn.stories.drawer-stories                    :as drawer]
   [clojure-shadcn.stories.dropdown-menu-stories             :as dropdown-menu]
   [clojure-shadcn.stories.empty-stories                     :as empty]
   [clojure-shadcn.stories.field-stories                     :as field]
   [clojure-shadcn.stories.form-stories                      :as form]
   [clojure-shadcn.stories.hover-card-stories                :as hover-card]
   [clojure-shadcn.stories.input-group-stories               :as input-group]
   [clojure-shadcn.stories.input-otp-stories                 :as input-otp]
   [clojure-shadcn.stories.input-stories                     :as input]
   [clojure-shadcn.stories.item-stories                      :as item]
   [clojure-shadcn.stories.kbd-stories                       :as kbd]
   [clojure-shadcn.stories.label-stories                     :as label]
   [clojure-shadcn.stories.loader-stories                    :as loader]
   [clojure-shadcn.stories.markdown-stories                  :as markdown]
   [clojure-shadcn.stories.marker-stories                    :as marker]
   [clojure-shadcn.stories.menubar-stories                   :as menubar]
   [clojure-shadcn.stories.message-scroller-stories          :as message-scroller]
   [clojure-shadcn.stories.message-stories                   :as message]
   [clojure-shadcn.stories.native-select-stories             :as native-select]
   [clojure-shadcn.stories.navigation-menu-stories           :as navigation-menu]
   [clojure-shadcn.stories.notification-stories              :as notification]
   [clojure-shadcn.stories.pagination-stories                :as pagination]
   [clojure-shadcn.stories.popover-stories                   :as popover]
   [clojure-shadcn.stories.progress-stories                  :as progress]
   [clojure-shadcn.stories.prompt-input-stories              :as prompt-input]
   [clojure-shadcn.stories.radio-group-stories               :as radio-group]
   [clojure-shadcn.stories.resizable-stories                 :as resizable]
   [clojure-shadcn.stories.scroll-area-stories               :as scroll-area]
   [clojure-shadcn.stories.scroll-button-stories             :as scroll-button]
   [clojure-shadcn.stories.select-stories                    :as select]
   [clojure-shadcn.stories.separator-stories                 :as separator]
   [clojure-shadcn.stories.sheet-stories                     :as sheet]
   [clojure-shadcn.stories.sidebar-stories                   :as sidebar]
   [clojure-shadcn.stories.skeleton-stories                  :as skeleton]
   [clojure-shadcn.stories.slider-stories                    :as slider]
   [clojure-shadcn.stories.speech-recognition-button-stories :as speech-recognition]
   [clojure-shadcn.stories.spinner-stories                   :as spinner]
   [clojure-shadcn.stories.stepper-stories                   :as stepper]
   [clojure-shadcn.stories.switch-stories                    :as switch]
   [clojure-shadcn.stories.system-message-stories            :as system-message]
   [clojure-shadcn.stories.table-stories                     :as table]
   [clojure-shadcn.stories.tabs-stories                      :as tabs]
   [clojure-shadcn.stories.tag-combobox-stories              :as tag-combobox]
   [clojure-shadcn.stories.textarea-stories                  :as textarea]
   [clojure-shadcn.stories.theme-toggle-stories              :as theme-toggle]
   [clojure-shadcn.stories.toggle-group-stories              :as toggle-group]
   [clojure-shadcn.stories.toggle-stories                    :as toggle]
   [clojure-shadcn.stories.tooltip-stories                   :as tooltip]
   [clojure-shadcn.stories.typography-stories                :as typography]
   [clojure.string                                           :as str]
   [reagent.core                                             :as r]))

(def ^:export default
  #js {:title "Home"
       :parameters #js {:layout "fullscreen"}})

(def components
  [{:name "Accordion"
    :preview accordion/AccordionBasic}
   {:name "Alert"
    :preview alert/AlertBasic}
   {:name "Alert Dialog"
    :preview alert-dialog/AlertDialogBasic}
   {:name "Aspect Ratio"
    :preview aspect-ratio/AspectRatioBasic}
   {:name "Attachment"
    :preview attachment/AttachmentStates}
   {:name "Avatar"
    :preview avatar/AvatarDemo}
   {:name "Badge"
    :preview badge/BadgeDemo}
   {:name "Breadcrumb"
    :preview breadcrumb/BreadcrumbDemo}
   {:name "Bubble"
    :preview bubble/BubbleConversation}
   {:name "Button"
    :preview button/ButtonSize}
   {:name "Button Group"
    :preview button-group/ButtonGroupDemo}
   {:name "Calendar"
    :preview calendar/SingleDate}
   {:name "Card"
    :preview card/CardDemo}
   {:name "Carousel"
    :preview carousel/CarouselDemo}
   {:name "Chart"
    :preview chart/BarChart}
   {:name "Chat Container"
    :preview chat-container/ChatContainerComposition}
   {:name "Checkbox"
    :preview checkbox/CheckboxDemo}
   {:name "Code Block"
    :preview code-block/CodeBlockWithHeader}
   {:name "Collapsible"
    :preview collapsible/CollapsibleBasic}
   {:name "Combobox"
    :preview combobox/Basic}
   {:name "Command"
    :preview command/CommandDemo}
   {:name "Context Menu"
    :preview context-menu/ContextMenuDemo}
   {:name "Data Table"
    :preview data-table/BasicDataTable}
   {:name "Date Picker"
    :preview date-picker/DatePickerStates}
   {:name "Dialog"
    :preview dialog/DialogDemo}
   {:name "Direction"
    :preview direction/RightToLeft}
   {:name "Drawer"
    :preview drawer/BottomDrawer}
   {:name "Dropdown Menu"
    :preview dropdown-menu/DropdownMenuDemo}
   {:name "Empty"
    :docs-id "empty-state"
    :preview empty/EmptyDemo}
   {:name "Field"
    :preview field/FieldWithInput}
   {:name "Form"
    :preview form/Validation}
   {:name "Hover Card"
    :preview hover-card/HoverCardBasic}
   {:name "Input"
    :preview input/InputDemo}
   {:name "Input Group"
    :preview input-group/WithAddon}
   {:name "Input OTP"
    :preview input-otp/SixDigits}
   {:name "Item"
    :preview item/ItemVariants}
   {:name "Kbd"
    :preview kbd/KbdDemo}
   {:name "Label"
    :preview label/LabelDemo}
   {:name "Loader"
    :preview loader/LoaderAllVariants}
   {:name "Markdown"
    :preview markdown/MarkdownCombined}
   {:name "Marker"
    :preview marker/MarkerVariants}
   {:name "Menubar"
    :preview menubar/MenubarDemo}
   {:name "Message"
    :preview message/MessageUserVsAssistant}
   {:name "Message Scroller"
    :preview message-scroller/Conversation}
   {:name "Native Select"
    :preview native-select/NativeSelectDemo}
   {:name "Navigation Menu"
    :preview navigation-menu/NavigationMenuDemo}
   {:name "Notification"
    :preview notification/ToastWithDescription}
   {:name "Pagination"
    :preview pagination/PaginationDemo}
   {:name "Popover"
    :preview popover/PopoverDemo}
   {:name "Progress"
    :preview progress/ProgressBasic}
   {:name "Prompt Input"
    :preview prompt-input/PromptInputMultipleActions}
   {:name "Radio Group"
    :preview radio-group/RadioGroupDemo}
   {:name "Resizable"
    :preview resizable/Horizontal}
   {:name "Scroll Area"
    :preview scroll-area/ScrollAreaBasic}
   {:name "Scroll Button"
    :preview scroll-button/ScrollButtonStandalone}
   {:name "Select"
    :preview select/SelectDemo}
   {:name "Separator"
    :preview separator/SeparatorDemo}
   {:name "Sheet"
    :preview sheet/SheetDemo}
   {:name "Sidebar"
    :preview sidebar/SidebarDemo}
   {:name "Skeleton"
    :preview skeleton/SkeletonCard}
   {:name "Slider"
    :preview slider/SliderBasic}
   {:name "Speech Recognition Button"
    :preview speech-recognition/SpeechRecognitionStandalone}
   {:name "Spinner"
    :preview spinner/SpinnerDemo}
   {:name "Stepper"
    :preview stepper/StepperHorizontal}
   {:name "Switch"
    :preview switch/SwitchDemo}
   {:name "System Message"
    :preview system-message/SystemMessageDefault}
   {:name "Table"
    :preview table/TableDemo}
   {:name "Tabs"
    :preview tabs/TabsBasic}
   {:name "Tag Combobox"
    :preview tag-combobox/BasicTagSelection}
   {:name "Textarea"
    :preview textarea/TextareaDemo}
   {:name "Theme Toggle"
    :docs-id "themetoggle"
    :preview theme-toggle/ThemeToggleInHeader}
   {:name "Toggle"
    :preview toggle/ToggleBasic}
   {:name "Toggle Group"
    :preview toggle-group/ToggleGroupSingle}
   {:name "Tooltip"
    :preview tooltip/TooltipDemo}
   {:name "Typography"
    :preview typography/TypeScale}])

(defn- component-id
  [name]
  (-> name
      str/lower-case
      (str/replace #"\s+" "-")))

(defn- component-card
  [{:keys [docs-id name preview]}]
  (let [id (component-id name)
        ^js story preview]
    [:section {:id id
               :class "min-w-0 scroll-mt-6 overflow-hidden rounded-lg border bg-card shadow-sm"}
     [:header {:class "border-b bg-muted/30 px-4 py-2.5"}
      [:h2 {:class "text-sm font-semibold tracking-tight"}
       [:a {:href (str "./?path=/docs/components-" (or docs-id id) "--docs")
            :target "_top"
            :class "underline-offset-4 hover:text-primary hover:underline"}
        name]]]
     [:div {:class "min-w-0 overflow-x-auto [&>.relative]:hidden"}
      (if-some [args (.-args story)]
        [:> story (js->clj args :keywordize-keys true)]
        [:> story])]]))

(def ^:private base-presets
  [{:id :neutral :label "Neutral" :swatch "oklch(0.55 0 0)"}
   {:id :slate :label "Slate" :swatch "oklch(0.55 0.05 260)"}
   {:id :taupe :label "Taupe" :swatch "oklch(0.55 0.03 65)"}])

(def ^:private theme-presets
  [{:id :graphite :label "Graphite" :swatch "oklch(0.32 0.02 260)"}
   {:id :ocean :label "Blue" :swatch "oklch(0.55 0.24 263)"}
   {:id :violet :label "Violet" :swatch "oklch(0.58 0.26 293)"}
   {:id :rose :label "Rose" :swatch "oklch(0.59 0.25 18)"}
   {:id :forest :label "Green" :swatch "oklch(0.53 0.15 150)"}])

(def ^:private radii
  [{:label "0" :value "0rem"}
   {:label "0.3" :value "0.3rem"}
   {:label "0.5" :value "0.5rem"}
   {:label "0.75" :value "0.75rem"}
   {:label "1.0" :value "1rem"}])

(defonce ^:private showcase-base
  (r/atom (or (some-> (.getAttribute js/document.documentElement "data-base") keyword)
              :taupe)))

(defonce ^:private showcase-preset
  (r/atom (or (some-> (.getAttribute js/document.documentElement "data-theme") keyword)
              :ocean)))

(defonce ^:private showcase-mode
  (r/atom (if (.contains (.-classList js/document.documentElement) "dark")
            :dark
            :light)))

(defonce ^:private showcase-radius (r/atom "0.75rem"))

(.setAttribute js/document.documentElement "data-base" (name @showcase-base))
(.setAttribute js/document.documentElement "data-theme" (name @showcase-preset))

(defn- select-showcase-base!
  [base]
  (.setAttribute js/document.documentElement "data-base" (name base))
  (reset! showcase-base base))

(defn- select-showcase-preset!
  [preset]
  (.setAttribute js/document.documentElement "data-theme" (name preset))
  (reset! showcase-preset preset))

(defn- select-showcase-mode!
  [mode]
  (.toggle (.-classList js/document.documentElement) "dark" (= mode :dark))
  (reset! showcase-mode mode))

(defn- shuffle-theme!
  []
  (let [base (:id (rand-nth base-presets))
        preset (:id (rand-nth theme-presets))
        mode (rand-nth [:light :dark])
        radius (:value (rand-nth radii))]
    (select-showcase-base! base)
    (select-showcase-preset! preset)
    (select-showcase-mode! mode)
    (reset! showcase-radius radius)))

(defn- reset-theme!
  []
  (select-showcase-base! :taupe)
  (select-showcase-preset! :ocean)
  (select-showcase-mode! :light)
  (reset! showcase-radius "0.75rem"))

(def ^:private choice-classes
  "flex w-full items-center justify-between rounded-md border border-transparent px-3 py-2 text-left text-sm transition-colors hover:border-border hover:bg-accent focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring")

(defn- theme-configurator
  []
  [:aside {:class "overflow-hidden rounded-2xl border border-white/10 bg-zinc-950 text-zinc-50 shadow-2xl lg:sticky lg:top-5"}
   [:div {:class "flex items-center justify-between border-b border-white/10 px-4 py-4"}
    [:div
     [:p {:class "text-sm font-semibold"} "Customize"]
     [:p {:class "mt-0.5 text-xs text-zinc-400"} "Design your component theme"]]
    [:span {:class "rounded-md border border-white/10 bg-white/5 px-2 py-1 font-mono text-[10px] text-zinc-400"}
     "Live"]]
   [:div {:class "max-h-[calc(100vh-13rem)] space-y-6 overflow-y-auto p-4"}
    [:fieldset
     [:legend {:class "mb-2 text-xs font-medium uppercase tracking-wider text-zinc-500"} "Base color"]
     [:div {:class "grid grid-cols-3 gap-1"}
      (for [{:keys [id label swatch]} base-presets]
        ^{:key id}
        [:button {:type "button"
                  :aria-pressed (= id @showcase-base)
                  :on-click #(select-showcase-base! id)
                  :class (str "flex flex-col items-center gap-2 rounded-md border px-2 py-2.5 text-xs transition-colors hover:bg-white/10 "
                              (if (= id @showcase-base)
                                "border-white/30 bg-white/10 text-white"
                                "border-white/10 text-zinc-400"))}
         [:span {:class "size-5 rounded-full ring-1 ring-white/20"
                 :style {:background swatch}}]
         label])]]
    [:fieldset
     [:legend {:class "mb-2 text-xs font-medium uppercase tracking-wider text-zinc-500"} "Theme color"]
     [:div {:class "grid gap-1"}
      (for [{:keys [id label swatch]} theme-presets]
        ^{:key id}
        [:button {:type "button"
                  :aria-pressed (= id @showcase-preset)
                  :on-click #(select-showcase-preset! id)
                  :class (str choice-classes
                              (when (= id @showcase-preset) " border-white/15 bg-white/10"))}
         [:span {:class "flex items-center gap-3"}
          [:span {:class "size-4 rounded-full ring-1 ring-white/20"
                  :style {:background swatch}}]
          label]
         (when (= id @showcase-preset)
           [:span {:class "text-xs text-zinc-400"} "✓"])])]]
    [:fieldset
     [:legend {:class "mb-2 text-xs font-medium uppercase tracking-wider text-zinc-500"} "Appearance"]
     [:div {:class "grid grid-cols-2 gap-2"}
      (for [[mode icon label] [[:light "☀" "Light"] [:dark "◐" "Dark"]]]
        ^{:key mode}
        [:button {:type "button"
                  :aria-pressed (= mode @showcase-mode)
                  :on-click #(select-showcase-mode! mode)
                  :class (str "rounded-lg border px-3 py-3 text-left text-sm transition-colors hover:bg-white/10 "
                              (if (= mode @showcase-mode)
                                "border-white/30 bg-white/10"
                                "border-white/10"))}
         [:span {:class "mr-2 text-base"} icon] label])]]
    [:fieldset
     [:legend {:class "mb-2 text-xs font-medium uppercase tracking-wider text-zinc-500"} "Radius"]
     [:div {:class "grid grid-cols-5 gap-1"}
      (for [{:keys [label value]} radii]
        ^{:key value}
        [:button {:type "button"
                  :aria-pressed (= value @showcase-radius)
                  :on-click #(reset! showcase-radius value)
                  :class (str "h-9 rounded-md border text-xs transition-colors hover:bg-white/10 "
                              (if (= value @showcase-radius)
                                "border-white/30 bg-white/10 text-white"
                                "border-white/10 text-zinc-400"))}
         label])]]
    [:div {:class "rounded-lg border border-white/10 bg-white/[0.04] p-3"}
     [:p {:class "text-[10px] uppercase tracking-wider text-zinc-500"} "Current preset"]
     [:p {:class "mt-1 truncate font-mono text-xs text-zinc-300"}
      (str (name @showcase-base) " / " (name @showcase-preset) " / "
           (name @showcase-mode) " / " @showcase-radius)]]]
   [:div {:class "grid grid-cols-2 gap-2 border-t border-white/10 p-4"}
    [:button {:type "button"
              :on-click reset-theme!
              :class "rounded-md border border-white/15 px-3 py-2 text-xs font-medium hover:bg-white/10"}
     "Reset"]
    [:button {:type "button"
              :on-click shuffle-theme!
              :class "rounded-md bg-white px-3 py-2 text-xs font-semibold text-zinc-950 hover:bg-zinc-200"}
     "Shuffle ↻"]]])

(defn- overview
  []
  [:main {:data-base (name @showcase-base)
          :data-theme (name @showcase-preset)
          :style {"--radius" @showcase-radius}
          :class (str "min-h-screen bg-background text-foreground transition-colors"
                      (when (= @showcase-mode :dark) " dark"))}
   [:div {:class "mx-auto max-w-[1800px] px-4 py-5 sm:px-6 lg:px-8"}
    [:header {:class "mb-6 flex flex-col justify-between gap-4 border-b pb-5 sm:flex-row sm:items-end"}
     [:div
      [:div {:class "mb-2 flex items-center gap-2"}
       [:span {:class "rounded-full bg-primary/10 px-2.5 py-1 text-[11px] font-semibold uppercase tracking-wider text-primary"}
        "Component showcase"]]
      [:h1 {:class "text-3xl font-bold tracking-tight"} "clojure-shadcn"]
      [:p {:class "mt-1.5 max-w-xl text-sm text-muted-foreground"}
       (str "Explore " (count components) " live ClojureScript components and tune their semantic theme in real time.")]]
     [:a {:href "./?path=/story/docs-introduction--overview"
          :target "_top"
          :class "inline-flex h-9 items-center justify-center rounded-md border bg-background px-4 text-sm font-medium shadow-xs transition-colors hover:bg-accent"}
      "Read the docs →"]]
    [:div {:class "grid items-start gap-5 lg:grid-cols-[17rem_minmax(0,1fr)]"}
     [theme-configurator]
     (into [:div {:class "grid min-w-0 items-start gap-4 md:grid-cols-2 2xl:grid-cols-3"}]
           (map (fn [{:keys [name]
                      :as component}]
                  ^{:key name} [component-card component])
                components))]]])

(defn ^:export Overview
  "Every available component rendered in one continuous, scannable page."
  []
  (r/as-element [overview]))
