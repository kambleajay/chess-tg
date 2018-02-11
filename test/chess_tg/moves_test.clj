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

(deftest test-left
  (are [square expected]
      (= (left square) expected)
    "B1" "A1"
    "F5" "E5"
    "C7" "B7"))

(deftest test-top-right
  (are [square expected]
      (= (top-right square) expected)
    "E4" "F5"
    "A1" "B2"
    "D6" "E7"))

(deftest test-bottom-right
  (are [square expected]
      (= (bottom-right square) expected)
    "B2" "C1"
    "G4" "H3"
    "D4" "E3"))
