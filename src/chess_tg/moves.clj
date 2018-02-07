(ns chess-tg.moves
  "model to represent chess moves. rows are called ranks - and are numbered 1 to 8. columns are files and are from a to h")

(defn square [file rank]
  (str file rank))

(defn char-to-int [c]
  (Integer/parseInt (str c)))

(defn go-right [col]
  (inc col))

(defn go-left [col]
  (dec col))

(defn top [[file rank-char]]
  (let [rank (char-to-int rank-char)]
    (square file (inc rank))))

(defn bottom [[file rank-char]]
  (let [rank (char-to-int rank-char)]
    (square file (dec rank))))

(defn go-top-by [col step]
  nil)

(defn go-bottom [row]
  (dec row))

(defn king-moves
  [square]
  #{(top square) "E6" "E5" "E4" (bottom square) "C4" "C5" "C6"})

(defn knight-moves
  [col row]
  (let [right (go-right col)
        top (inc (inc row))
        left (go-left col)]
    #{[right top] [left top]}))

(defn moves
  [piece square]
  (condp = piece
    :king (king-moves square)))
