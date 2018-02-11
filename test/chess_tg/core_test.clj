(ns chess-tg.core-test
  (:require [clojure.test :refer :all]
            [chess-tg.core :refer :all]))

(deftest test-king-moves
  (is (= (moves :king "D5") #{"D6" "E6" "E5" "E4" "D4" "C4" "C5" "C6"}))
  (is (= (moves :king "A1") #{"A2" "B2" "B1"}))
  (is (= (moves :king "H4") #{"H5" "G5" "G4" "G3" "H3"})))

(comment (deftest test-knight-moves
           (is (= (moves :knight "E3") #{"F5" "G4" "G2" "F1" "D1" "C2" "C4" "D5"}))))

(comment (deftest test-bishop-moves
           (is (= (moves :bishop "F4") #{"G5" "H6"}))))
