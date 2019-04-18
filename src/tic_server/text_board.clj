(ns tic-server.text-board
	(:gen-class))

(defn ^:dynamic set [board]
  (spit "board.txt" board))

(defn ^:dynamic get []
  (slurp "board.txt"))