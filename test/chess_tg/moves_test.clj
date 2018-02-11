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
  "C7" "C8")

(defmovetest test-bottom bottom
  "C3" "C2"
  "H8" "H7"
  "F4" "F3")

(defmovetest test-right right
  "D4" "E4"
  "A1" "B1"
  "G8" "H8")

(defmovetest test-left left
  "B1" "A1"
  "F5" "E5"
  "C7" "B7")

(defmovetest test-top-right top-right
  "E4" "F5"
  "A1" "B2"
  "D6" "E7")

(defmovetest test-bottom-right bottom-right
  "B2" "C1"
  "G4" "H3"
  "D4" "E3")

(defmovetest test-bottom-left bottom-left
  "H8" "G7"
  "G5" "F4"
  "B6" "A5")

(deftest test-top-left top-left
  "C1" "B2"
  "E4" "D5"
  "D6" "C7")
