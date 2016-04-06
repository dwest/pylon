;;;;
;;;; route.clj
;;;;
;;;; App routes for Uniform
;;;;

(ns pylon.route
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            ;; project requires
            [pylon.web :as web]))

(defroutes app
  (GET "/" request web/index)
  (GET "/sql" request web/sql))
