(ns tic-server.html-board
  (:require [clojure.string :as str])
  (:gen-class))

(defn ^:private box-tag [box]
  (str "<th style='width:50px;height:50px;border:1px solid;'
        onclick='playerInput(" (str \" box \")  ");'>"))

(defn remove-characters [message]
  (str/replace message #"=|board: " {"=" "" "board: " ""}))

(defn replace-separators [message]
  (str/replace message #"\||&" {(str "|") "</th>" "&" "</tr><tr>"}))

(defn format-boxes [message]
  (str/replace message #"\d+" #(str (box-tag %1))))

(defn as-table[message]
  (str "<div style='height:50%'>
    <table style='width:50%;height:50%;border:1px solid;'>
      <tr>"
      (format-boxes (remove-characters (replace-separators message)))
      "</th>
      </tr>
      </table>
      </div>"))