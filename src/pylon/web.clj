;;;;
;;;; web.clj
;;;;
;;;; View fns for pylon.
;;;;

(ns pylon.web
  (:require [hiccup.core :as h]
            ;; project requires
            [pylon.debug :as dbg]))

(defn index [request]
  (h/html
   [:html
    [:head
     [:title "Hello boot"]]
    [:body
     (dbg/dump-hiccup request)]]))
      
