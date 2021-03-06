(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

(defmacro defmovetest
  [name-of-test f & square-and-expected-seq]
  `(deftest ~name-of-test
     (are [square# expected#]
         (= (~f square#) expected#)
       ~@square-and-expected-seq)))

(defmovetest test-top top
  "E4" "E5"
  "H1" "H2"
  "C7" "C8"
  "H8" nil)

(defmovetest test-bottom bottom
  "C3" "C2"
  "H8" "H7"
  "F4" "F3"
  "C1" nil)

(defmovetest test-right right
  "D4" "E4"
  "A1" "B1"
  "G8" "H8"
  "H3" nil)

(defmovetest test-left left
  "B1" "A1"
  "F5" "E5"
  "C7" "B7"
  "A7" nil)

(defmovetest test-top-right top-right
  "E4" "F5"
  "A1" "B2"
  "D6" "E7"
  "H8" nil)

(defmovetest test-bottom-right bottom-right
  "B2" "C1"
  "G4" "H3"
  "D4" "E3"
  "H1" nil)

(defmovetest test-bottom-left bottom-left
  "H8" "G7"
  "G5" "F4"
  "B6" "A5"
  "A1" nil)

(defmovetest test-top-left top-left
  "C1" "B2"
  "E4" "D5"
  "D6" "C7"
  "A8" nil)

(deftest test-top-seq
  (is (= (take 3 (top-seq "A1")) ["A1" "A2" "A3"])))

(deftest test-right-seq
  (is (= (take 3 (right-seq "D5")) ["D5" "E5" "F5"])))

(deftest test-bottom-seq
  (is (= (take 3 (bottom-seq "C4")) ["C4" "C3" "C2"])))

(deftest test-left-seq
  (is (= (take 3 (left-seq "H3")) ["H3" "G3" "F3"])))

(deftest test-top-right-seq
  (is (= (take 3 (top-right-seq "D4")) ["D4" "E5" "F6"])))

(deftest test-bottom-right-seq
  (is (= (take 3 (bottom-right-seq "E6")) ["E6" "F5" "G4"])))

(deftest test-bottom-left-seq
  (is (= (take 3 (bottom-left-seq "C6")) ["C6" "B5" "A4"])))

(deftest test-top-left-seq
  (is (= (take 3 (top-left-seq "D2")) ["D2" "C3" "B4"])))

(deftest test-valid-square
  (is (true? (valid-square? "A1")))
  (is (true? (valid-square? "H7")))
  (is (true? (valid-square? "D4")))
  (is (false? (valid-square? "Z3")))
  (is (false? (valid-square? "P1"))))
