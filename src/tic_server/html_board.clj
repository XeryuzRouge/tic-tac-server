(ns tic-server.html-board
  (:require [clojure.string :as str])
  (:gen-class))

(defn ^:private box-tag [box]
  (str "<th onclick='playerInput("
    (str \" box \")  ");'>"))

(defn remove-characters [message]
  (str/replace
    (last
      (str/split message #": ")) #"=" ""))

(defn replace-separators [message]
  (str/replace message #"\||&" {(str "|") "</th>" "&" "</tr><tr>"}))

(defn format-boxes [message]
  (str/replace message #"\d+" #(str (box-tag %1))))

(defn ^:dynamic play[message]
  (str "<div style='padding-bottom: 20px'>
      <table>
      <tr>"
      (format-boxes (remove-characters (replace-separators message)))
      "</th>
      </tr>
      </table>
      </div>"))
