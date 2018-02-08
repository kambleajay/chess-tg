(ns chess-tg.files)

(def files [\A \B \C \D \E \F \G \H])

(defn right
  [file]
  (let [index-of-file (.indexOf files file)]
    (get files (inc index-of-file))))

(defn left
  [file]
  (let [index-of-file (.indexOf files file)]
    (get files (dec index-of-file))))
