(ns chess-tg.core
  (:require [chess-tg.moves :as m]))

(defn valid-seq-of
  [f square]
  (take-while #(not (nil? %)) (f square)))

(defn valid-squares-from
  [square & fs]
  (reduce (fn [acc next-f] (conj acc (valid-seq-of next-f square))) [] fs))

(defn squares-at
  [square & moves]
  (->> square
       ((juxt m/top m/right m/bottom m/left m/top-right m/bottom-right m/bottom-left m/top-left))
       (filter m/valid-square?)
       set))

(defn king-moves
  [square]
  (squares-at square m/top m/right m/bottom m/left m/top-right m/bottom-right m/bottom-left m/top-left))

(defn move-by-2
  [seq-fn square']
  (last (take 3 (seq-fn square'))))

(defn move-by-2-then-pick-adjacent-squares
  [direction square]
  (condp = direction
    :top ((juxt m/right m/left) (move-by-2 m/top-seq square))
    :right ((juxt m/top m/bottom) (move-by-2 m/right-seq square))
    :bottom ((juxt m/right m/left) (move-by-2 m/bottom-seq square))
    :left ((juxt m/top m/bottom) (move-by-2 m/left-seq square))))

(defn knight-moves
  [square]
  (->> (vector
        (move-by-2-then-pick-adjacent-squares :top square)
        (move-by-2-then-pick-adjacent-squares :right square)
        (move-by-2-then-pick-adjacent-squares :bottom square)
        (move-by-2-then-pick-adjacent-squares :left square))
       flatten
       (remove nil?)
       set))

(defn bishop-moves
  [square]
  (->> (valid-squares-from square m/top-right-seq m/bottom-right-seq m/bottom-left-seq m/top-left-seq)
       flatten
       (remove #(= % square))
       set))

(defn queen-moves
  [square]
  (->> (valid-squares-from square m/top-seq m/right-seq m/bottom-seq m/left-seq
                           m/top-right-seq m/bottom-right-seq m/bottom-left-seq m/top-left-seq)
       flatten
       (remove #(= % square))
       set))

(defn rook-moves
  [square]
  (->> (valid-squares-from square m/top-seq m/right-seq m/bottom-seq m/left-seq)
       flatten
       (remove #(= % square))
       set))

(defn pawn-moves
  [square]
  (m/top square))

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    :knight (knight-moves square)
    :bishop (bishop-moves square)
    :queen (queen-moves square)
    :rook (rook-moves square)
    :pawn (pawn-moves square)))

(defn -main
  [& args]
  (let [piece (keyword (nth args 0))
        square (nth args 1)]
    (println (moves piece square))))
