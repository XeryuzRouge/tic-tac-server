(ns tic-server.request-handler-test
  (:require [tic-server.request-handler :as handler]
            [clojure.java.io :as io]
            [tic-server.text :as text])
  (:import tech.dynamic.http_messages.RequestMessage)
  (:import tech.dynamic.http_messages.ResponseMessage)
  (:use clojure.test))

(def request-content "2")

(defn content [response]
  (String.
    (.body response)))

(defn new-content [request]
  (.body request))

(defn response-content []
  (str
    (* 2
      (Integer. request-content))))

(defn input-dummy []
  (str "resources/input_dummy.txt"))

(defn output-dummy []
  (str "resources/output_dummy.txt"))

(deftest request-handler
  (testing "with response message"
    (is
      (= "HTTP/1.1 200 OK"
        (.statusLine handler/response))))
  (testing "with knows"
    (is
      (= false
        (handler/knows?
          (RequestMessage. "GET" "unknown_request"))))
    (is
      (= true
        (handler/knows?
          (RequestMessage. "GET" "gato")))))
  (testing "with player-input"
    (is
      (= "1"
        (let [request (RequestMessage. "GET" "gato" "1")]
          (binding [text/input input-dummy, text/output output-dummy]
            (.responseTo handler/tic-tac-toe request)
            (slurp (text/input)))))))
  (testing "with tictac-output"
    (is
      (= "4"
        (let [request (RequestMessage. "GET" "gato" request-content)]
          (binding [text/get-content response-content]
            (content (.responseTo handler/tic-tac-toe request))))))))
