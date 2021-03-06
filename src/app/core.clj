(ns app.core
  (:require [ring.adapter.jetty   :refer [run-jetty]]
            [clojure.pprint       :refer [pprint]]
            [compojure.core       :refer [routes GET POST]]
            [compojure.route      :refer [not-found]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response   :refer [response]]))

(def my-routes
  (routes ; routes function: Create a Ring handler by combining several handlers into one.
   (GET  "/endpoint-a"  []      (response {:foo "bar"}))
   (GET  "/endpoint-b"  []      (response {:baz "qux"}))
   (POST "/debug"       request (response (with-out-str (clojure.pprint/pprint request))))
   (not-found {:error "Not found"})))

(def app ; Wrap middlewares to the my-routes handler 
  (-> my-routes
      wrap-json-body
      wrap-json-response))

(defn -main
  "Main function executed when lein run command is used"
  []
  (run-jetty app {:port 3000})) ; run-jetty is a ring adapter, used to convert ring handlers into running web servers.
