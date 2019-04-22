(ns tic-server.tic-tac-display
  (:require [tic-server.html-board :as html-board]
            [clojure.string :as str])
  (:gen-class))

(defn winner[message]
  (first 
  (str/split message #": ")))

(defn display [message]
  (cond
    (str/includes? message "configuration_options player") "Player 2 Human or CPU?"
    (str/includes? message "board") (html-board/play message)
    (str/includes? message "invalid")  (str (html-board/play message) "box already selected")
    (str/includes? message "tie") (str (html-board/play message) "It was a Tie =/")
    (str/includes? message "winner") (str (html-board/play message) (winner message))
    :else message))
