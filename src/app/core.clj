(ns app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clojure.pprint :refer [pprint]]))

(defn handler [request]
  (clojure.pprint/pprint request) ;
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello world"})

(defn -main
  []
  (run-jetty handler {:port 3000}))
