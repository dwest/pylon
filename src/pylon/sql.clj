;;;;
;;;; sql.clj
;;;;
;;;; Functions for interacting with an SQL datastore.
;;;;

(ns pylon.sql
  (:require [clojure.string :as s]))

(defn- sql-quote [n]
  (str "\"" (s/replace n #"[^a-zA-Z_-]*" "") "\""))

(defn- create-table [name fields & [inherits?]]
  (format
   (str
    "CREATE TABLE %s (\n"
    (s/join ",\n"
     (for [field fields]
       (format "    %s" field)))
    ") "
    (when inherits?
      (format "INHERITS %s" (sql-quote inherits?)))
    ";") name))

(defn- sql-datatype [k]
  (if (string? k)
    k
    (get 
     {:text   "TEXT"
      :number "NUMERIC(64, 32)"
      :date   "TIMESTAMPTZ"}
     k)))

(defn- create-field [n props]
  (format
   (s/join " " 
     [(sql-quote (name n))
      (sql-datatype (:type props))
      (when (:required? props)
        "NOT NULL")
      (when (:default? props)
        (str "DEFAULT " (:default? props)))
      (when (:pk? props)
        "PRIMARY KEY")])))

(defn encode [[table attrs]]
  (create-table
   (sql-quote (name table))
   (for [[k, v] attrs]
     (create-field k v))
   "entity"))
