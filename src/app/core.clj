(ns app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clojure.pprint     :refer [pprint]]
            [compojure.core     :refer [routes GET]]
            [compojure.route    :refer [not-found]]))

(def my-routes 
  (routes ; routes function: Create a Ring handler by combining several handlers into one.
   (GET  "/endpoint-a"  [] "<h1>Hello endpoint A</h1>")
   (GET  "/endpoint-b"  [] "<h1>Hello endpoint B</h1>")
   (not-found "<h1>Page not found</h1>")))

(defn -main
  "Main function executed when lein run command is used"
  []
  (run-jetty my-routes {:port 3000})) ; run-jetty is a ring adapter, used to convert ring handlers into running web servers.
