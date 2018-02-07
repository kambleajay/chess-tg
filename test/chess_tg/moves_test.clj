(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

;;[[1 2] [2 2] [3 2] [1 3] [3 3] [1 4] [2 4] [3 4]]

;;[6 5] [4 5] [3 4] [3 2] [4 1] [6 1] [7 2] [7 4]

(deftest test-king-moves
  (is (=
       (moves :king "D5")
       #{"D6" "E6" "E5" "E4" "D4" "C4" "C5" "C6"})))

(comment (deftest test-knight-moves
           (is (=
                (moves :knight [5 3])
                #{[6 5] [4 5]}))))
