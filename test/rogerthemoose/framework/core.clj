(ns rogerthemoose.framework.core
  (:require [midje.sweet :refer [facts fact =>]]))

(defonce ^:private state (atom {:scenarios #{}}))

(defmacro defscenario [name body]
  (swap! state update-in [:scenarios] conj (str *ns* "/" name))
  `(do
     (def ~name ~body)))

(defn scenario-context [] {:ctx    {}
                           :checks []})

(defn check-later [ctx check-fn expected]
  (update ctx :checks conj {:check-ctx ctx
                            :check-fn  check-fn
                            :expected  expected}))

(defn apply-check [{:keys [check-ctx check-fn expected] :as check}]
  (let [actual (check-fn (:ctx check-ctx))]
    (assoc check :actual actual
                 :passed? (= expected actual))))

(defn apply-all-checks [scenario]
  (-> scenario
      (update :checks #(map apply-check %))))

; TODO report meta correctly for failures
; TODO annotate facts so that they can be filtered for autotest

(defmacro ^:private check-scenario-with-midje [scenario]
  `(doall
     (let [evaluated-scenario# (apply-all-checks ~scenario)]
       (for [check# (:checks evaluated-scenario#)]
         (fact {:midje/description (get-in ~scenario [:ctx :description])}
               (:actual check#) => (:expected check#))))))

(defn check-scenario [scenario]
  (check-scenario-with-midje scenario))

(defn check-scenarios [scenarios]
  (doall (map (fn [scenario] (check-scenario scenario)) scenarios)))

(defn check-all-scenarios []
  (doall
    (map
      (fn [sname]
        (check-scenario-with-midje (var-get (find-var (symbol sname)))))
      (:scenarios @state))))

