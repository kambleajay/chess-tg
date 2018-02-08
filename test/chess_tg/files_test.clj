(ns chess-tg.files-test
  (:require [clojure.test :refer :all]
            [chess-tg.files :refer :all]))

(deftest test-right
  (is (= (right \C) \D)))

(deftest test-right-by
  (is (= (right-by 2 \D) \F)))

(deftest test-left
  (is (= (left \H) \G)))
