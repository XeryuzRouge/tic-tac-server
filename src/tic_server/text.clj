(ns tic-server.text
  (:gen-class))

(defn ^:dynamic directory []
  "textpipe-directory")

(defn ^:dynamic input []
  (str (directory) "input.txt"))

(defn ^:dynamic output []
  (str (directory) "output.txt"))

(defn ^:dynamic set-content [message]
  (spit (input) message))

(defn ^:dynamic get-content []
  (slurp (output)))
