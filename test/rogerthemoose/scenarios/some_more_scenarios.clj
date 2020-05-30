(ns rogerthemoose.scenarios.some-more-scenarios
  (:require [rogerthemoose.framework.core :refer :all]
            [rogerthemoose.framework.builder :refer :all]
            [rogerthemoose.calculator.multiplier :refer [multiply]]))

(defscenario my-first-scenario

             (-> (scenario-context)

                 (with :description "my first scenario")

                 (with :a 1)

                 (check-later (multiply :a 4) 4)

                 (with :a 4)

                 (check-later (multiply :a 3) 12)))