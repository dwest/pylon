;;;;
;;;; core.clj
;;;;
;;;; Startup procedures
;;;;

(ns pylon.core
  (:require [immutant.web :as web]
            ;; project requires
            [pylon.route :as u-route])
  (:gen-class))

(defn -main [& args]
  (web/run u-route/app {:host "localhost" :port 3000}))

