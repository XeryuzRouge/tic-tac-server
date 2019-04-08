(ns tic-server.request-handler
	(:import tech.dynamic.http_builder.RequestHandler)
	(:import tech.dynamic.http_messages.RequestMessage)
	(:import tech.dynamic.http_messages.ResponseMessage)
	(:gen-class))

(def response-message
	(ResponseMessage. 200))

(def gato
	(reify RequestHandler
  	(responseTo [this RequestMessage] 
  		(.setBody response-message "I'm the Gato")
  		response-message)
  	(knows [this RequestMessage] 
  		(if (= (.uri RequestMessage) "gato")
  			true 
  			false))
  	(restricted [this]
  		(into-array ["nil"]))
  	(setResourcesHandler [this ResourcesHandler])))