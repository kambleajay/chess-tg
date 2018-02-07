(ns chess-tg.moves)

(defn king-moves
  [col row]
  (let [right (inc col)
        left  (dec col)
        top (inc row)
        bottom (dec row)]
    #{[left row] [right row] [col bottom] [col top] [left bottom] [right bottom] [left top] [right top]}))

(defn knight-moves
  [col row]
  #{[6 5] [4 5]})

(defn moves
  [piece [col row]]
  (condp = piece
    :king (king-moves col row)
    :knight (knight-moves col row)))
