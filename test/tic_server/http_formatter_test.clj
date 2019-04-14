(ns tic-server.http-formatter-test
(:require [tic-server.http_formatter :as formatter])
(:use clojure.test))

(deftest http_formatter
  (testing "with new line"
    (is (= "<br /> hello <br />"
        (formatter/newline-to-br "\n hello \n")))))