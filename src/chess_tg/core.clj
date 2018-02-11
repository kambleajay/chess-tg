(ns chess-tg.core
  (:require [chess-tg.moves :as m]))

(defn king-moves
  [square]
  (set (filter m/valid-square? ((juxt m/top m/right m/bottom m/left m/top-right m/bottom-right m/bottom-left m/top-left) square))))

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    ;;:knight (knight-moves square)
    ;;:bishop (bishop-moves square)
    ))
