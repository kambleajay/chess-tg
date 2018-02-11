(ns chess-tg.files-test
  (:require [clojure.test :refer :all]
            [chess-tg.files :refer :all]))

(deftest test-right
  (is (= (right \C) \D)))

(deftest test-left
  (is (= (left \H) \G)))

(deftest test-valid-file
  (is (true? (valid-file? \A)))
  (is (false? (valid-file? \Z))))
