(ns chess-tg.moves
  "model to represent chess moves. rows are called ranks - and are numbered 1 to 8. columns are files and are from a to h"
  (:require [chess-tg.files :as f]
            [chess-tg.ranks :as r]))

(defn square
  [file rank]
  (str file rank))

(defn top
  [[file rank]]
  (square file (r/top rank)))

(defn top-right
  [[file rank]]
  (let [right-file (f/right file)
        top-rank (r/top rank)]
    (square right-file top-rank)))

(defn right
  [[file rank]]
  (square (f/right file) rank))

(defn bottom-right
  [[file rank]]
  (let [right-file (f/right file)
        bottom-rank (r/bottom rank)]
    (square right-file bottom-rank)))

(defn bottom
  [[file rank-char]]
  (square file (r/bottom rank-char)))

(defn bottom-left
  [[file rank]]
  (let [left-file (f/left file)
        bottom-rank (r/bottom rank)]
    (square left-file bottom-rank)))

(defn left
  [[file rank]]
  (square (f/left file) rank))

(defn top-left
  [[file rank]]
  (let [left-file (f/left file)
        top-rank (r/top rank)]
    (square left-file top-rank)))

(defn king-moves
  [square]
  #{(top square) (top-right square) (right square) (bottom-right square) (bottom square) (bottom-left square) (left square) (top-left square)})

(comment (defn knight-moves
           [col row]
           (let [right (go-right col)
                 top (inc (inc row))
                 left (go-left col)]
             #{[right top] [left top]})))

(defn moves
  [piece square]
  (condp = piece
    :king (king-moves square)))
