;;;;
;;;; web.clj
;;;;
;;;; View fns for pylon.
;;;;

(ns pylon.web
  (:require [hiccup.core :as h]
            ;; project requires
            [pylon.debug :as dbg]
            [pylon.sql :as sql]))

(defn index [request]
  (h/html
   [:html
    [:head
     [:title "Hello boot"]]
    [:body
     (dbg/dump-hiccup request)]]))

(defn sql [request]
  (h/html
   [:html
    [:head
     [:title "Let's see some SQL!"]]
    [:body
     [:pre
      (sql/encode
       [:entity
        {:id            {:type "BIGSERIAL" :required? true :pk? true}
         :-xt-during    {:type "TSTZRANGE" :required? true :default? "tstzrange('now', 'infinity')"}
         :-xt-tx-during {:type "TSTZRANGE" :required? true :default? "tstzrange('now', 'infinity')"}}])
      "\n\n"
      (sql/encode
       [:user
        {:name       {:type :text :required? true}
         :email      {:type :text :required? true}
         :registered {:type :date :required? true}}])]]]))

