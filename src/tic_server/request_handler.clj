(ns tic-server.request-handler
  (:import tech.dynamic.http_builder.RequestHandler)
  (:import tech.dynamic.http_messages.RequestMessage)
  (:import tech.dynamic.http_messages.ResponseMessage)
  (:require [tic-server.text :as text]
            [tic-server.tic-tac-display :as tic-tac]
            [clojure.java.io :as io])
  (:gen-class))

(defn ^:dynamic directory []
    "textpipe-directory")

(def response
  (ResponseMessage. 200))

(defn knows? [request]
  (if (= (.uri request) "gato")
        true
        false))

(defn ^:dynamic tictac-output [request]
  (binding [text/directory directory]
    (text/set-content (.body request))
      (Thread/sleep 1000)
        (.setBody response
            (tic-tac/display (text/get-content)))
    response))

(def tic-tac-toe
  (reify RequestHandler
    (knows [this request]
        (knows? request))
    (responseTo [this request]
        (tictac-output request))
    (restricted [this]
        (into-array ["nil"]))
    (setResourcesHandler [this resources-handler])))
