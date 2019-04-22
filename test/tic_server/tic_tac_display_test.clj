(ns tic-server.tic-tac-display-test
  (:require [tic-server.tic-tac-display :as tic-tac]
            [tic-server.html-board :as html-board])
  (:use clojure.test))

(defn fake-board [content]
  "'board status'")

(deftest incoming-message
  (do
    (binding [html-board/play fake-board]
      (testing "board"
        (is
          (= "'board status'"
           (tic-tac/display "board:"))))
      (testing "including configuration_options"
        (is
          (= "Player 2 Human or CPU?"
           (tic-tac/display "configuration_options player"))))
      (testing "invalid"
        (is
          (= "'board status'box already selected"
           (tic-tac/display "invalid"))))
      (testing "tie"
        (is
          (= "'board status'It was a Tie =/"
           (tic-tac/display "tie"))))
      (testing "including winner"
        (is
          (= "'board status'the winner is X"
           (tic-tac/display "the winner is X"))))
      (testing "with anything else"
        (is
          (= "unexpected message"
           (tic-tac/display "unexpected message")))))))
