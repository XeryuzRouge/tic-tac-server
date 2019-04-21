(ns tic-server.text-test
  (:require [tic-server.text :as text])
  (:use clojure.test))

(defn directory-dummy []
  "resources/directory_dummy/")

(def input-file
  (str (directory-dummy) "input.txt"))

(def output-file
  (str (directory-dummy) "output.txt"))

(deftest text-def
(binding [text/directory directory-dummy]
  (testing "with set-content"
    (is (= "something to set"
      (do
        (text/set-content "something to set")
        (slurp input-file)))))
  (testing "read tic-tac output text file"
    (spit output-file "content to read")
    (is (= (text/get-content)
           "content to read")))))
