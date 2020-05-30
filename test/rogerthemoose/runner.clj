(ns rogerthemoose.runner
  (:require [midje.repl :as mr]))

(defn -main [& [mode]]
  (case mode
    "auto" (do
             (println "START AUTOTEST")
             (mr/autotest :all)
             @(promise))

    (mr/load-facts :all))
  (println "FINISHED"))