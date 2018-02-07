(ns chess-tg.moves)

(defn moves
  [piece [col row]]
  (let [right (inc col)
        left  (dec col)]
    (vector [left row] [right row])))
