(ns chess-tg.ranks
  "This model represents movements in chess by ranks.
  Ranks are rows and are numbered from 1 to 8.")

(defn- char-to-int
  "A utility function that takes a rank `c` as character and returns it as int."
  [c]
  (Integer/parseInt (str c)))

(defn- shift-with-fn
  "Returns the new rank, after applying shift function `fn` to `rank-char`."
  [fn rank-char]
  (let [rank (char-to-int rank-char)]
    (fn rank)))

(defn top
  "Takes a `rank-char` and returns the rank that is at the top."
  [rank-char]
  (shift-with-fn inc rank-char))

(defn bottom
  "Takes a `rank-char` and return the rank that is at the bottom."
  [rank-char]
  (shift-with-fn dec rank-char))
