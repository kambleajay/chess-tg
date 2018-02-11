(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

(deftest test-top
  (are [square expected]
      (= (top square) expected)
    "E4" "E5"
    "H1" "H2"
    "C7" "C8"))

(deftest test-bottom
  (are [square expected]
      (= (bottom square) expected)
    "C3" "C2"
    "H8" "H7"
    "F4" "F3"))

(deftest test-right
  (are [square expected]
      (= (right square) expected)
    "D4" "E4"
    "A1" "B1"
    "G8" "H8"))
