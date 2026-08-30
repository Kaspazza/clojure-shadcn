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
  #js {:title "Docs/All Components"
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
    :preview theme-toggle/ThemeToggleInHeader}
   {:name "Toggle"
    :preview toggle/ToggleBasic}
   {:name "Toggle Group"
    :preview toggle-group/ToggleGroupSingle}
   {:name "Tooltip"
    :preview tooltip/TooltipDemo}
   {:name "Typography"
    :preview typography/TypeScale}])

(defn- component-card
  [{:keys [name preview]}]
  [:section {:id (-> name
                     str/lower-case
                     (str/replace #"\s+" "-"))
             :class "scroll-mt-6 overflow-hidden rounded-xl border bg-card shadow-sm"}
   [:header {:class "border-b bg-muted/30 px-5 py-4"}
    [:h2 {:class "text-lg font-semibold tracking-tight"}
     name]]
   [:div {:class "[&>.relative]:hidden"}
    [preview]]])

(defn ^:export Catalogue
  "Every available component rendered in one continuous, scannable page."
  []
  (r/as-element
   [:main {:class "min-h-screen bg-background px-4 py-10 text-foreground sm:px-6 lg:px-8"}
    [:div {:class "mx-auto max-w-6xl"}
     [:header {:class "mb-10 max-w-3xl"}
      [:p {:class "mb-2 text-sm font-medium text-primary"}
       "Component catalogue"]
      [:h1 {:class "text-3xl font-bold tracking-tight sm:text-4xl"}
       "All components"]
      [:p {:class "mt-3 text-base leading-7 text-muted-foreground"}
       "Browse every component in the library without leaving the page. Scroll through the live examples, then use the Components section in the sidebar for installation details and API references."]
      [:p {:class "mt-4 text-sm text-muted-foreground"}
       (str (count components) " components")]]
     (into [:div {:class "grid gap-6"}]
           (map (fn [{:keys [name]
                      :as component}]
                  ^{:key name} [component-card component])
                components))]]))
