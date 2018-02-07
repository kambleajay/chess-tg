(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

;;[[1 2] [2 2] [3 2] [1 3] [3 3] [1 4] [2 4] [3 4]]

(deftest test-king-moves
  (is (=
       (moves :king [2 3])
       #{[1 2] [2 2] [3 2] [1 3] [3 3] [1 4] [2 4] [3 4]})))
