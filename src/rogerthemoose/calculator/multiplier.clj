(ns rogerthemoose.calculator.multiplier)

(defn multiply [k v]
  (fn [ctx]
    (* (get ctx k) v)))