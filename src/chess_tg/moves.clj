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
  "Takes a sequence of sequence of `moves` and a `square`, and returns all squares that can be reached by following each sequence of moves."
  [square moves]
  (into #{} (map #(apply square-at square %1) moves)))

(def file->index {\A 0 \B 1 \C 2 \D 3 \E 4 \F 5 \G 6 \H 7})
(def index->file (set/map-invert file->index))

(def rank->index {\8 0 \7 1 \6 2 \5 3 \4 4 \3 5 \2 6 \1 7})
(def index->rank (set/map-invert rank->index))

(defn king-moves'
  "Takes a `square` occupied by a king, and returns all the squares it can occupy on the next move."
  [square]
  (squares-for-moves square [[:top] [:top :right] [:right] [:bottom :right] [:bottom] [:bottom :left] [:left] [:top :left]]))

(defn split-indexes
  [index]
  (vector (quot index 10) (rem index 10)))

(defn index-from-square
  [[file rank]]
  (let [file-index (get file->index file)
        rank-index (get rank->index rank)]
    (Integer/parseInt (str file-index rank-index))))

(defn square-from-index
  [index]
  (let [[file-index rank-index] (split-indexes index)
        file (get index->file file-index)
        rank (get index->rank rank-index)]
    (str file rank)))

(defn- char-to-int
  "A utility function that takes a rank `c` as character and returns it as int."
  [c]
  (Integer/parseInt (str c)))

(defn square-for-step
  [file rank [file-diff rank-diff]]
  (Integer/parseInt (str (+ file file-diff) (+ rank rank-diff))))

(defn possible-moves
  [index]
  (let [[file-index rank-index] (split-indexes index)
        steps (for [y [-1 0 1]
                    x [-1 0 1]] [x y])
        indexes (map #(square-for-step file-index rank-index %1) steps)]
    (remove #{index} indexes)))

(defn king-moves
  "Returns all possible moves for king, given a `square`."
  [square]
  (let [square-index (index-from-square square)
        possible-indexes (possible-moves square-index)]
    (into #{} (map square-from-index possible-indexes))))

(defn knight-moves
  "Takes a `square`, and returns all the possible moves for a knight."
  [square]
  (squares-for-moves square [[:top :top :right] [:right :right :top] [:right :right :bottom] [:bottom :bottom :right] [:bottom :bottom :left] [:left :left :bottom] [:left :left :top] [:top :top :left]]))

(def top-rights
  "Returns a lazy sequence of moves that when followed will take to squares along the top right diagonal. "
  (let [top (repeat :top)
        right (repeat :right)]
    (map #(concat (take %1 top) (take %1 right)) (iterate inc 1))))

(defn bishop-moves
  "Takes a `square`, and returns all the possible moves for a bishop."
  [square]
  (let [rank (Integer/parseInt (str (second square)))
        top-right-limit (- 8 rank)]
    (squares-for-moves square (take top-right-limit top-rights))))

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    :knight (knight-moves square)
    :bishop (bishop-moves square)))
