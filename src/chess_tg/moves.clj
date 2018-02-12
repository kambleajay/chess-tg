(ns chess-tg.moves
  "This is a model to represent chess moves.
   As per chess terminology, rows are called ranks and are numbered from 1 to 8.
   The columns are called files and range from a to h."
  (:require [chess-tg.files :as f]
            [chess-tg.ranks :as r]
            [clojure.set :as set]))

(defn square
  "Takes a `file` and a `rank` (both are characters) and returns a string that represents the square having given file and rank."
  [file rank]
  (when-not (or (nil? file) (nil? rank))
    (str file rank)))

(defn valid-square?
  [[file rank]]
  (and (f/valid-file? file) (r/valid-rank? rank)))

(defn top
  "Takes a `file` and a `rank` and returns the square that is at the top."
  [[file rank]]
  (square file (r/top rank)))

(defn right
  "Takes a `file` and a `rank` and returns the square that is at the right."
  [[file rank]]
  (square (f/right file) rank))

(defn bottom
  "Takes a `file` and a `rank` and returns the square that is at the bottom."
  [[file rank]]
  (square file (r/bottom rank)))

(defn left
  "Takes a `file` and a `rank` and returns the square that is at the left."
  [[file rank]]
  (square (f/left file) rank))

(defn top-right
  [[file rank]]
  (square (f/right file) (r/top rank)))

(defn bottom-right
  [[file rank]]
  (square (f/right file) (r/bottom rank)))

(defn bottom-left
  [[file rank]]
  (square (f/left file) (r/bottom rank)))

(defn top-left
  [[file rank]]
  (square (f/left file) (r/top rank)))

(defn top-seq
  [square]
  (iterate top square))

(defn right-seq
  [square]
  (iterate right square))

(defn bottom-seq
  [square]
  (iterate bottom square))

(defn left-seq
  [square]
  (iterate left square))

(defn top-right-seq
  [square]
  (iterate top-right square))

(defn bottom-right-seq
  [square]
  (iterate bottom-right square))

(defn bottom-left-seq
  [square]
  (iterate bottom-left square))

(defn top-left-seq
  [square]
  (iterate top-left square))
