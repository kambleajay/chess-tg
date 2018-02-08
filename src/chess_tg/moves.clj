(ns chess-tg.moves
  "model to represent chess moves. rows are called ranks - and are numbered 1 to 8. columns are files and are from a to h")

(def files ["A" "B" "C" "D" "E" "F" "G" "H"])

(defn square
  [file rank]
  (str file rank))

(defn char-to-int
  [c]
  (Integer/parseInt (str c)))

(defn top
  [[file rank-char]]
  (let [rank (char-to-int rank-char)]
    (square file (inc rank))))

(defn bottom
  [[file rank-char]]
  (let [rank (char-to-int rank-char)]
    (square file (dec rank))))

(defn bottom-right
  [[file rank-char]]
  (let [rank (char-to-int rank-char)
        bottom-rank (dec rank)
        index-of-file (.indexOf files (str file))
        right-file (files (inc index-of-file))]
    (square right-file bottom-rank)))

(defn right
  [[file rank]]
  (let [index-of-file (.indexOf files (str file))]
    (square (files (inc index-of-file)) rank)))

(defn top-right
  [[file rank-char]]
  (let [index-of-file (.indexOf files (str file))
        top-file (files (inc index-of-file))
        right-rank (inc (char-to-int rank-char))]
    (square top-file right-rank)))

(defn king-moves
  [square]
  #{(top square) (top-right square) (right square) (bottom-right square) (bottom square) "C4" "C5" "C6"})

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
