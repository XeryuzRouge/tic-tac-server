(ns tic-server.http_formatter
	(:gen-class))

(defn newline-to-br [line]
  (clojure.string/replace line #"\n" "<br />"))