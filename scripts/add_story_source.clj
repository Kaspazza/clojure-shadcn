#!/usr/bin/env bb
;; Story source is now captured by the defstory macro at compile time.
;;
;; This compatibility entry point intentionally performs no source annotation.
;; Keep it temporarily so existing local automation does not fail while callers
;; migrate away from `bb scripts/add_story_source.clj`.

(println "No changes: defstory captures and injects story source automatically.")
