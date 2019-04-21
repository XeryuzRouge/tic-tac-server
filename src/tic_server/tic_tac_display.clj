(ns tic-server.tic-tac-display
  (:require [tic-server.html-board :as html-board]
            [clojure.string :as str]
            [tic-server.text-board :as text-board])
  (:gen-class))

(defn display [message]
  (cond 
    (str/includes? message "board:") 
      (do 
        (text-board/set (html-board/as-table message))
        (text-board/get))
    (str/includes? message "configuration_options player") "Player 2 Human or CPU?"
    (str/includes? message "winner") (str (text-board/get) message)
    (= message "invalid") (str (text-board/get) "box already selected")
    (= message "tie") (str (text-board/get) "It was a Tie =/")
    :else message))
