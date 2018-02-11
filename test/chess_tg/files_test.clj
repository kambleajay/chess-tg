(ns chess-tg.files-test
  (:require [clojure.test :refer :all]
            [chess-tg.files :refer :all]))

(deftest test-right
  (is (= (right \C) \D))
  (is (nil? (right \H))))

(deftest test-left
  (is (= (left \H) \G))
  (is (nil? (left \A))))

(deftest test-valid-file
  (is (true? (valid-file? \A)))
  (is (false? (valid-file? \Z))))
