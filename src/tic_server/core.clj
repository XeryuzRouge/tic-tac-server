(ns tic-server.core
  (:import tech.dynamic.app.ForeignMain)
  (:require [tic-server.request-handler :as handler])
  (:gen-class))

(defn thread [textpipe-directory]
  (Thread.
    (fn []
      (binding [handler/directory
        (fn [] textpipe-directory)]
          (ForeignMain. 5000 handler/tic-tac-toe)))))

(defn -main
  [& args]
  (.start (thread (first args))))
