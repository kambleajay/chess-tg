(ns chess-tg.ranks)

(defn- char-to-int
  [c]
  (Integer/parseInt (str c)))

(defn- shift-with-fn
  [fn rank-char]
  (let [rank (char-to-int rank-char)]
    (fn rank)))

(defn top
  [rank-char]
  (shift-with-fn inc rank-char))

(defn bottom
  [rank-char]
  (shift-with-fn dec rank-char))
