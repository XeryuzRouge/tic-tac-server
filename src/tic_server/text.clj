(ns tic-server.text
	(:gen-class))

(def directory
  "/Users/germanalmaraz/tic-tac-server/tic-tac-toe/Gato_TDD_2/")

(def input
	(str directory "input.txt"))

(def output
	(str directory "output.txt"))

(defn ^:dynamic set-content [message]
  (spit input message))

(defn ^:dynamic get-content []
  (slurp output))