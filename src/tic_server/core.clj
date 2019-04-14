(ns tic-server.core
	(:import tech.dynamic.app.ForeignMain)
	(:require [tic-server.request-handler :as handler])
  (:gen-class))

(def thread
	(Thread.
		(fn [] (ForeignMain. 5000 handler/gato))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  	(.start thread))