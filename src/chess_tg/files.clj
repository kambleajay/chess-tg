(ns chess-tg.files)

(def files [\A \B \C \D \E \F \G \H])

(defn- shift-with-fn
  [fn file]
  (let [current-index (.indexOf files file)
        new-index (fn current-index)]
    (get files new-index)))

(defn right
  [file]
  (shift-with-fn inc file))

(defn left
  [file]
  (shift-with-fn dec file))
