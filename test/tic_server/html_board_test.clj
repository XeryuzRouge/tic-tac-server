(ns tic-server.html-board-test
(:require [tic-server.html-board :as html-board])
(:use clojure.test))

(deftest remove-characters
  (testing "should remove '='"
    (is (= "not equals here"
        (html-board/remove-characters "=not =equals =here="))))
  (testing "should remove message header"
    (is (= "actual-board"
        (html-board/remove-characters "board: actual-board")))))

(deftest format-boxes
  (testing "should create html table cells from any number"
    (is (= (str 
               "<th style='width:50px;height:50px;border:1px solid;'\n"
               "        onclick='playerInput(\"2\");'>")
        (html-board/format-boxes "2"))))
  (testing "from any number in the message"
    (is (= (str 
               "<th style='width:50px;height:50px;border:1px solid;'\n"
               "        onclick='playerInput(\"1\");'>"
               "extra-content"
               "<th style='width:50px;height:50px;border:1px solid;'\n"
               "        onclick='playerInput(\"5\");'>"
               "more-extra-content"
               "<th style='width:50px;height:50px;border:1px solid;'\n"
               "        onclick='playerInput(\"9\");'>")
        (html-board/format-boxes "1extra-content5more-extra-content9")))))

(deftest replace-separators
  (testing "should replace '|' with closing tags"
    (is (= "this is a separator </th>"
        (html-board/replace-separators "this is a separator |")))))