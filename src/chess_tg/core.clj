(ns chess-tg.core)

(defn king-moves
  [square]
  nil)

(defn moves
  "Takes a `piece` and the current `square` it occupies, and returns
  all the squares it can occupy on the next move."
  [piece square]
  (condp = piece
    :king (king-moves square)
    ;;:knight (knight-moves square)
    ;;:bishop (bishop-moves square)
    ))
