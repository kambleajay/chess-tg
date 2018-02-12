(ns chess-tg.core
  (:require [chess-tg.moves :as m]))

(defn valid-seq-of
  [f square]
  (take-while #(not (nil? %)) (f square)))

(defn valid-squares-from
  [square & fs]
  (reduce (fn [acc next-f] (conj acc (valid-seq-of next-f square))) [] fs))

(defn king-moves
  [square]
  (set (filter m/valid-square? ((juxt m/top m/right m/bottom m/left m/top-right m/bottom-right m/bottom-left m/top-left) square))))

(defn knight-moves
  [square]
  (letfn [(move-by-2 [seq-fn square']
            (last (take 3 (seq-fn square'))))]
    (set
     (remove nil?
             (flatten
              (vector
               ((juxt m/right m/left) (move-by-2 m/top-seq square))
               ((juxt m/top m/bottom) (move-by-2 m/right-seq square))
               ((juxt m/right m/left) (move-by-2 m/bottom-seq square))
               ((juxt m/top m/bottom) (move-by-2 m/left-seq square))))))))

(defn bishop-moves
  [square]
  (set
   (remove #(= % square)
           (flatten
            (valid-squares-from square m/top-right-seq m/bottom-right-seq m/bottom-left-seq m/top-left-seq)))))

(defn queen-moves
  [square]
  (set
   (remove #(= % square)
           (flatten
            (valid-squares-from square m/top-seq m/right-seq m/bottom-seq m/left-seq
                                m/top-right-seq m/bottom-right-seq m/bottom-left-seq m/top-left-seq)))))

(defn rook-moves
  [square]
  (set
   (remove #(= % square)
           (flatten
            (valid-squares-from square m/top-seq m/right-seq m/bottom-seq m/left-seq)))))

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
