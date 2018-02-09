(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

(deftest test-king-moves
  (is (= (moves :king "D5") #{"D6" "E6" "E5" "E4" "D4" "C4" "C5" "C6"}))
  (is (= (moves :king "A1") #{"A2" "B2" "B1"}))
  (is (= (moves :king "H4") #{"H5" "G5" "G4" "G3" "H3"})))

(comment (deftest test-knight-moves
           (is (=
                (moves :knight "E3")
                #{"F5" "G4" "G2" "F1" "D1" "C2" "C4" "D5"})))

         (deftest test-square-at
           (is (= (square-at "E3" :top) "E4"))
           (is (= (square-at "E3" :top :top :right) "F5"))
           (is (= (square-at "E3" :top :right :bottom) "F3"))
           (is (= (square-at "E3" :top :right :bottom :left) "E3")))

         (deftest test-top-rights
           (is (= (take 3 top-rights) [[:top :right] [:top :top :right :right] [:top :top :top :right :right :right]])))

         (deftest test-bishop-moves
           (is (= (moves :bishop "D4") #{"E5" "F6" "G7" "H8"}))
           (is (= (moves :bishop "F4") #{"G5" "H6"}))))
