(ns clojure-shadcn.stories.message.message-rich-markdown
  (:require
   [clojure-shadcn.ui.components.message :as message]))

(defn example
  []
  [message/message {:class "max-w-3xl"}
   [message/message-avatar {:src ""
                            :alt "Assistant"
                            :fallback "AI"}]
   [message/message-content {:markdown? true}
    "## Release checklist\n\n- [x] Run tests\n- [ ] Publish artifacts\n\nUse `npm publish` after reviewing the [package guide](https://docs.npmjs.com/cli/v11/commands/npm-publish).\n\n```clojure\n(def release-ready? true)\n```"]])
