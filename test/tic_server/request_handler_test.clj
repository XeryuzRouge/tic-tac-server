(ns tic-server.request-handler-test
  (:require [tic-server.request-handler :as handler]
            [clojure.java.io :as io]
            [tic-server.text :as text])
  (:import tech.dynamic.http_messages.RequestMessage)
  (:import tech.dynamic.http_messages.ResponseMessage)
  (:use clojure.test))

(def request-content "2")

(defn content [response]
  (String. (.body response)))

(defn new-content [request]
  (.body request))

(defn response-content []
  (str (* 2 (Integer. request-content))))

(deftest request-handler
  (testing "with response message"
    (is (= "HTTP/1.1 200 OK" (.statusLine handler/response))))
  (testing "with knows"
    (is (= false
    	(handler/knows? (RequestMessage. "GET" "unknown_request"))))
    (is (= true
    	(handler/knows? (RequestMessage. "GET" "gato")))))
  (testing "with player-input"
    (is (= "1"
      (let [request (RequestMessage. "GET" "gato" "1")]
          (.responseTo handler/gato request)
          (slurp text/input)))))
  (testing "with tictac-output"
    (is (= "4"
      (let [request (RequestMessage. "GET" "gato" request-content)]
        (binding [text/get-content response-content]
          (content (.responseTo handler/gato request))))))))