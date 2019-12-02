(ns core
  (:import (java.net ServerSocket))
  (:require [clojure.java.io :as io])
  )

(defn request-line->map [request-line]
  (zipmap [:method :path :protcol] (clojure.string/split request-line #" ")))

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
        (let [request-line (request-line->map (.readLine reader))
              method (request-line :method)]
          (println request-line)
          (cond (= method "GET") (.write writer "HTTP/1.1 200 OK\n\nHello!!\n")
                (= method "POST")　(.write writer "HTTP/1.1 201 Created\n\nHello!!\n")
                :else (.write writer "HTTP/1.1 405 Method Not Allowed\n\nHello!!\n")
                ))))))

(-main)
