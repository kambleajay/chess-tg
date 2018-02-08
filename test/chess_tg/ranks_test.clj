(ns chess-tg.ranks-test
  (:require [clojure.test :refer :all]
            [chess-tg.ranks :refer :all]))

(deftest test-top
  (is (= (top \2) 3)))

(deftest test-bottom
  (is (= (bottom \6) 5)))
