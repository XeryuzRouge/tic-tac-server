(ns tic-server.text-test
	(:require [tic-server.text :as text])
	(:use clojure.test))

(def directory
  "/Users/germanalmaraz/tic-tac-server/tic-tac-toe/Gato_TDD_2/")

(def input-file
  (str directory "input.txt"))

(def output-file
  (str directory "output.txt"))

(deftest text-def
  (testing "with set-content"
    (is (= "something to set"
      (do
    	  (text/set-content "something to set")
        (slurp input-file)))))
  (testing "read tic-tac output text file"
    (spit output-file "content to read")
    (is (= (text/get-content)
           "content to read"))))