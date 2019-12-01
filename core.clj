(ns core
  (:import (java.net ServerSocket))
  (:require [clojure.java.io :as io])
  )

(def port 8080)

(defn -main []
  (let [server-socket (ServerSocket. port)]
    (println "Starting up http-server!! The port number is" port)
    (while true
      (with-open [
                  socket (.accept server-socket)
                  reader (io/reader socket)
                  writer (io/writer socket)
                  ]
        (println (.readLine reader))     
        (.write writer
                "HTTP/1.1 200 OK\n\nHello!!\n"
                )
        )
      ) 
    )
  )

(-main)
