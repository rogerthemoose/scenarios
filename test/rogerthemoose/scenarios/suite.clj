(ns rogerthemoose.scenarios.suite
  (:require [rogerthemoose.scenarios.some-scenarios]
            [rogerthemoose.scenarios.some-more-scenarios]
            [rogerthemoose.framework.core :refer :all]))

(check-all-scenarios)
