(ns chess-tg.ranks-test
  (:require [clojure.test :refer :all]
            [chess-tg.ranks :refer :all]))

(deftest test-top
  (is (= (top \2) 3)))

(deftest test-bottom
  (is (= (bottom \6) 5)))

(deftest test-valid-rank
  (is (false? (valid-rank? \0)))
  (is (true? (valid-rank? \1)))
  (is (true? (valid-rank? \3)))
  (is (true? (valid-rank? \7)))
  (is (true? (valid-rank? \8)))
  (is (false? (valid-rank? \9))))
