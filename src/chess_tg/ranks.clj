(ns chess-tg.ranks)

(defn char-to-int
  [c]
  (Integer/parseInt (str c)))

(defn top
  [rank-char]
  (let [rank (char-to-int rank-char)]
    (inc rank)))

(defn bottom
  [[file rank-char]]
  (let [rank (char-to-int rank-char)]
    (dec rank)))
