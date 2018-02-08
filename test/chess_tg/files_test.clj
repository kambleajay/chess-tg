(ns chess-tg.files-test
  (:require [clojure.test :refer :all]
            [chess-tg.files :refer :all]))

(deftest test-right
  (is (= (right \C) \D)))
