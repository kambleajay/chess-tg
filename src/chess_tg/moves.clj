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

(defn go-to
  "Returns the square that can be reached by following `next-move` from current `square`."
  [square next-move]
  (condp = next-move
    :top (top square)
    :right (right square)
    :bottom (bottom square)
    :left (left square)))

(defn square-at
  "Takes a `square` and one or more `moves` and returns the square that can be reached by following `moves`."
  [square & moves]
  (reduce go-to square moves))

(defn squares-for-moves
  "Takes a sequence of `moves` and a `square`, and returns all squares that can be reached by following each move."
  [square moves]
  (into #{} (map #(apply square-at square %1) moves)))

(defn king-moves
  "Takes a `square` occupied by a king, and returns all the squares it can occupy on the next move."
  [square]
  (squares-for-moves square [[:top] [:top :right] [:right] [:bottom :right] [:bottom] [:bottom :left] [:left] [:top :left]]))

(defn knight-moves
  "Takes a `square`, and returns all the possible moves for a knight."
  [square]
  (squares-for-moves square [[:top :top :right] [:right :right :top]]))

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    :knight (knight-moves square)))
