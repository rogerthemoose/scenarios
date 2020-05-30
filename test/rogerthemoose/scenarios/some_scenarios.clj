(ns rogerthemoose.scenarios.some-scenarios
  (:require [rogerthemoose.framework.core :refer :all]
            [rogerthemoose.framework.builder :refer :all]
            [rogerthemoose.calculator.multiplier :refer [multiply]]))

(defscenario my-first-scenario

             (-> (scenario-context)

                 (under-construction)

                 (with :description "my first scenario")

                 (with :a 1)

                 (check-later (multiply :a 4) 4)

                 (with :a 4)

                 (check-later (multiply :a 3) 12)))

(defscenario composed-scenario

             (-> my-first-scenario

                 (with :description "a composed scenario")

                 (check-later (multiply :a 2) 8)))

(check-scenario composed-scenario)