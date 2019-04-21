(ns tic-server.tic-tac-display-test
  (:require [tic-server.tic-tac-display :as tic-tac]
            [tic-server.text-board :as text-board])
  (:use clojure.test))

(defn board-status [] "'board status'")

(defn fake-board [content]
    (spit "board.txt" (board-status)))

(deftest incoming-message
  (do
    (testing "board"
      (binding [text-board/set fake-board]
        (is (= "'board status'"
           (tic-tac/display "board:")))))
    (binding [text-board/get board-status]
      (testing "including configuration_options"
        (is (= "Player 2 Human or CPU?"
           (tic-tac/display "configuration_options player"))))
      (testing "invalid"
        (is (= "'board status'box already selected"
           (tic-tac/display "invalid"))))
      (testing "tie"
        (is (= "'board status'It was a Tie =/"
           (tic-tac/display "tie"))))
      (testing "including winner"
        (is (= "'board status'the winner is X"
           (tic-tac/display "the winner is X"))))
      (testing "with anything else"
        (is (= "unexpected message"
           (tic-tac/display "unexpected message")))))))
