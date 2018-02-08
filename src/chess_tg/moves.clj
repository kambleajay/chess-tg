(ns chess-tg.moves
  "This is a model to represent chess moves.
   As per chess terminology, rows are called ranks and are numbered from 1 to 8.
   The columns are called files and range from a to h."
  (:require [chess-tg.files :as f]
            [chess-tg.ranks :as r]))

(defn square
  "Takes a `file` and a `rank` (both are characters) and returns a string that represents the square having given file and rank."
  [file rank]
  (str file rank))

(defn top
  "Takes a `file` and a `rank` and returns the square that is at the top."
  [[file rank]]
  (square file (r/top rank)))

(defn top-right
  "Takes a `file` and a `rank` and returns the square that is at the top right."
  [[file rank]]
  (let [right-file (f/right file)
        top-rank (r/top rank)]
    (square right-file top-rank)))

(defn right
  "Takes a `file` and a `rank` and returns the square that is at the right."
  [[file rank]]
  (square (f/right file) rank))

(defn bottom-right
  "Takes a `file` and a `rank` and returns the square that is at the bottom right."
  [[file rank]]
  (let [right-file (f/right file)
        bottom-rank (r/bottom rank)]
    (square right-file bottom-rank)))

(defn bottom
  "Takes a `file` and a `rank` and returns the square that is at the bottom."
  [[file rank]]
  (square file (r/bottom rank)))

(defn bottom-left
  "Takes a `file` and a `rank` and returns the square that is at the bottom left."
  [[file rank]]
  (let [left-file (f/left file)
        bottom-rank (r/bottom rank)]
    (square left-file bottom-rank)))

(defn left
  "Takes a `file` and a `rank` and returns the square that is at the left."
  [[file rank]]
  (square (f/left file) rank))

(defn top-left
  "Takes a `file` and a `rank` and returns the square that is at the top left."
  [[file rank]]
  (let [left-file (f/left file)
        top-rank (r/top rank)]
    (square left-file top-rank)))

(defn king-moves
  "Takes a `square` occupied by a king, and returns all the squares it can occupy on the next move."
  [square]
  #{(top square) (top-right square) (right square) (bottom-right square) (bottom square) (bottom-left square) (left square) (top-left square)})

(defn top-by-2-then-right
  "Returns the square after going 2 ranks up and to right file, given a `file` and a `rank`."
  [[file rank]]
  (let [right-file (f/right file)
        top-by-2-rank (r/top-by 2 rank)]
    (square right-file top-by-2-rank)))

(defn right-by-2-then-top
  "Returns the square after going 2 files right and then 1 rank top, given a `file` and a `rank`."
  [[file rank]]
  (let [right-by-2-file (f/right-by 2 file)
        top-rank (r/top rank)]
    (square right-by-2-file top-rank)))

(defn knight-moves
  "Takes a `square`, and returns all the possible moves for a knight."
  [square]
  #{(top-by-2-then-right square) (right-by-2-then-top square)})

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    :knight (knight-moves square)))
