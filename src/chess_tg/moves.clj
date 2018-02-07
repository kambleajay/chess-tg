(ns chess-tg.moves)

(defn moves
  [piece [col row]]
  (let [right (inc col)
        left  (dec col)
        top (inc row)
        bottom (dec row)]
    #{[left row] [right row] [col bottom] [col top] [left bottom] [right bottom] [left top] [right top]}))
