(ns chess-tg.ranks)

(defn- char-to-int
  [c]
  (Integer/parseInt (str c)))

(defn- change-with-fn
  [change-fn rank-char]
  (let [rank (char-to-int rank-char)]
    (change-fn rank)))

(defn top
  [rank-char]
  (change-with-fn inc rank-char))

(defn bottom
  [rank-char]
  (change-with-fn dec rank-char))
