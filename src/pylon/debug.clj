;;;;
;;;; debug.clj
;;;;
;;;; Unfortunately I'm not perfect, so these functions help identify
;;;; where things went awry.  Utilities for dumping, printing, and
;;;; otherwise debugging code during development.
;;;;

(ns pylon.debug
  (:require [clojure.pprint :as pprint]))

(defn dump
  "Dump a list of clojure data structures as a pretty printed string."
  [& args]
  (-> (map-indexed 
       (fn [i arg]
         (str
          (format "Arg: %d\n=======\n\n" i)
          (with-out-str 
            (pprint/pprint arg)))) args)))

(defn dump-hiccup
  "Dump a list of data structures to a string wrapped in a pre tag, for inclusion in a page."
  [& args]
  [:pre (apply dump args)])

