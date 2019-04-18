(ns tic-server.text-board-test
	(:require [tic-server.text-board :as board])
	(:use clojure.test))

(def board-file "board.txt")

(deftest board-def
  (testing "should set board"
    (is (= "something to set"
      (do
    	  (board/set "something to set")
        (slurp board-file)))))
  (testing "read get board"
    (spit board-file "something to get")
    (is (= (board/get) "something to get"))))