(set-env!
  :resource-paths #{"src"}
  :asset-paths    #{"resources"}
  :dependencies '[;; boot deps
                  [adzerk/boot-cljs "1.7.228-1"]

                  ;; project deps
                  [org.immutant/web "2.1.2"]
                  [ring "1.4.0"]
                  [compojure "1.4.0"]
                  [hiccup "1.0.5"]]
  :target-dir "target")

(require '[adzerk.boot-cljs :refer [cljs]])

(task-options!
  pom {:project 'pylon
       :version "0.1.0"}
  jar {:manifest {}})

(defn require-and-run 
  "Reload, require, and run a project fn."
  [ns f]
  (fn [next]
    (fn [fileset]
      (require ns :reload-all)
      (let [main (ns-resolve ns f)]
        (main)))))

(deftask run []
  (comp 
   (target "target") 
   (watch)
   (aot)
   ;;(cljs)
   (require-and-run 'pylon.core '-main)))
