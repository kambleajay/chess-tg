(ns chess-tg.moves-test
  (:require [clojure.test :refer :all]
            [chess-tg.moves :refer :all]))

(deftest test-top
  (are [expected square]
      (= expected (top square))
    "E5" "E4"
    "H2" "H1"
    "C8" "C7"))
