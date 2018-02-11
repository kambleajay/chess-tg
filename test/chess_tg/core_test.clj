(ns chess-tg.core-test
  (:require [clojure.test :refer :all]
            [chess-tg.core :refer :all]))

(deftest test-king-moves
  (is (= (moves :king "D5") #{"D6" "E6" "E5" "E4" "D4" "C4" "C5" "C6"}))
  (is (= (moves :king "A1") #{"A2" "B2" "B1"}))
  (is (= (moves :king "H4") #{"H5" "G5" "G4" "G3" "H3"})))

(deftest test-knight-moves
  (is (= (moves :knight "E3") #{"F5" "G4" "G2" "F1" "D1" "C2" "C4" "D5"}))
  (is (= (moves :knight "A1") #{"B3" "C2"}))
  (is (= (moves :knight "F7") #{"H8" "H6" "D8" "D6" "E5" "G5"})))

(deftest test-bishop-moves
  (is (= (moves :bishop "F4") #{"G5" "H6" "G3" "H2" "E3" "D2" "C1" "E5" "D6" "C7" "B8"}))
  (is (= (moves :bishop "B2") #{"A1" "A3" "C1" "C3" "D4" "E5" "F6" "G7" "H8"})))
