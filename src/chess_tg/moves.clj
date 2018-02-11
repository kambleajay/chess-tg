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

#_(defn go-to
    "Returns the square that can be reached by following `next-move` from current `square`."
    [square next-move]
    (condp = next-move
      :top (top square)
      :right (right square)
      :bottom (bottom square)
      :left (left square)))

#_(defn square-at
    "Takes a `square` and one or more `moves` and returns the square that can be reached by following `moves`."
    [square & moves]
    (reduce go-to square moves))

#_(defn squares-for-moves
    "Takes a sequence of sequence of `moves` and a `square`, and returns all squares that can be reached by following each sequence of moves."
    [square moves]
    (into #{} (map #(apply square-at square %1) moves)))

#_(def file->index {\A 0 \B 1 \C 2 \D 3 \E 4 \F 5 \G 6 \H 7})
#_(def index->file (set/map-invert file->index))

#_(def rank->index {\8 0 \7 1 \6 2 \5 3 \4 4 \3 5 \2 6 \1 7})
#_(def index->rank (set/map-invert rank->index))

#_(defn king-moves'
    "Takes a `square` occupied by a king, and returns all the squares it can occupy on the next move."
    [square]
    (squares-for-moves square [[:top] [:top :right] [:right] [:bottom :right] [:bottom] [:bottom :left] [:left] [:top :left]]))

#_(defn split-indexes
    [index]
    (vector (quot index 10) (rem index 10)))

#_(defn index-from-square
    [[file rank]]
    (let [file-index (get file->index file)
          rank-index (get rank->index rank)]
      (Integer/parseInt (str file-index rank-index))))

#_(defn square-from-index
    [index]
    (let [[file-index rank-index] (split-indexes index)
          file (get index->file file-index)
          rank (get index->rank rank-index)]
      (str file rank)))

#_(defn- char-to-int
    "A utility function that takes a rank `c` as character and returns it as int."
    [c]
    (Integer/parseInt (str c)))

#_(defn square-for-step
    [file rank [file-diff rank-diff]]
    (Integer/parseInt (str (+ file file-diff) (+ rank rank-diff))))

#_(defn between
    [n low high]
    (and (>= n low) (<= n high)))

#_(defn possible-moves
    [index]
    (let [[file-index rank-index] (split-indexes index)
          steps (for [y [-1 0 1]
                      x [-1 0 1]] [x y])
          all-indexes (map (fn [[f r]] (vector (+ f file-index) (+ r rank-index))) steps)
          valid-indexes (filter (fn [[x y]] (and (between x 0 7) (between y 0 7))) all-indexes)
          indexes (map (fn [[x y]] (Integer/parseInt (str x y))) valid-indexes)]
      (remove #{index} indexes)))

#_(defn king-moves
    "Returns all possible moves for king, given a `square`."
    [square]
    (let [square-index (index-from-square square)
          possible-indexes (possible-moves square-index)]
      (set (map square-from-index possible-indexes))))

#_(defn neg
    [n]
    (- 0 n))

#_(defn knight-moves
    "Takes a `square`, and returns all the possible moves for a knight."
    [square]
    (let [square-index (index-from-square square)
          [file-index rank-index] (split-indexes square-index)
          steps (reduce (fn [xs x]
                          (let [diff (- 3 (Math/abs x))]
                            (conj (conj xs [diff x]) [(neg diff) x]))) [] [-2 -1 1 2])
          all-indexes (map (fn [[f r]] (vector (+ f file-index) (+ r rank-index))) steps)
          valid-indexes (filter (fn [[x y]] (and (between x 0 7) (between y 0 7))) all-indexes)
          indexes (map (fn [[x y]] (Integer/parseInt (str x y))) valid-indexes)]
      (set (map square-from-index indexes))))

#_(defn top-right-steps
    [file-index rank-index]
    (let [rank-limit (- 7 rank-index)
          possible-steps (range 1 (inc rank-limit))
          diffs (map #(vector (neg %1) %1) possible-steps)
          x (map (fn [[f r]] (vector (+ f file-index) (+ r rank-index))) diffs)
          y (filter (fn [[f r]] (and (between f 0 7) (between r 0 7))) x)
          z (map (fn [[f r]] (Integer/parseInt (str f r))) y)]
      (println file-index "/" rank-index "/" rank-limit)
      z))

#_(defn bishop-moves
    "Takes a `square`, and returns all the possible moves for a bishop."
    [square]
    (let [square-index (index-from-square square)
          [file-index rank-index] (split-indexes square-index)
          top-right-steps' (top-right-steps file-index rank-index)]
      (println top-right-steps')
      (set (map square-from-index top-right-steps'))))
