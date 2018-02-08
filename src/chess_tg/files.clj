(ns chess-tg.files
  "This model represents movements in chess by files.
  Files are columns and range from a to h.")

(def files [\A \B \C \D \E \F \G \H])

(defn- shift-with-fn
  "Returns the new file, after applying shift function `fn` to `file`."
  [fn file]
  (let [current-index (.indexOf files file)
        new-index (fn current-index)]
    (get files new-index)))

(defn right
  "Returns the file that is to the right of given `file`."
  [file]
  (shift-with-fn inc file))

(defn left
  "Returns the file that is to the left of given `file`."
  [file]
  (shift-with-fn dec file))

(defn right-by
  "Returns the file to the right at a distance of `step` from given `file`."
  [step file]
  (let [current-index (.indexOf files file)
        new-index (+ current-index step)]
    (get files new-index)))
