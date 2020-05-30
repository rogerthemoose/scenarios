(ns rogerthemoose.framework.builder)

(defn under-construction [ctx]
  (assoc ctx :wip true))

(defn with [ctx k v]
  (assoc-in ctx [:ctx k] v))
