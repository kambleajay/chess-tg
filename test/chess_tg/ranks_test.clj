(ns chess-tg.ranks-test
  (:require [clojure.test :refer :all]
            [chess-tg.ranks :refer :all]))

(deftest test-top
  (is (= (top \2) 3)))

(deftest test-top-by
  (is (= (top-by 3 \3) 6)))

(deftest test-bottom
  (is (= (bottom \6) 5)))
