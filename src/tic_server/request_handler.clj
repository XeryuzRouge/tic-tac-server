(ns tic-server.request-handler
	(:import tech.dynamic.http_builder.RequestHandler)
	(:import tech.dynamic.http_messages.RequestMessage)
  (:import tech.dynamic.http_messages.ResponseMessage)
  (:require [tic-server.http_formatter :as formatter]
            [tic-server.text :as text]
            [clojure.java.io :as io])
	(:gen-class))

(def response
  (ResponseMessage. 200))

(defn knows? [request]
  (if (= (.uri request) "gato")
        true
        false))

(defn tictac-output [request]
    (do
      (text/set-content (.body request))
      (Thread/sleep 1000)
      (.setBody response
        (formatter/newline-to-br (text/get-content)))
      response))

(def gato
	(reify RequestHandler
  	(knows [this request]
        (knows? request))
    (responseTo [this request]
        (tictac-output request))
  	(restricted [this]
  		  (into-array ["nil"]))
  	(setResourcesHandler [this resources-handler])))