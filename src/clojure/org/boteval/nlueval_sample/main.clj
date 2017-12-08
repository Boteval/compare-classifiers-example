(ns org.boteval.nlueval-sample.main
  (:require
    [org.boteval.nlueval.input.ready :refer :all]
    [org.boteval.nlueval-sample.execute :refer :all])
  (:gen-class)) ; for uberjar deploy to air-gapped environments

(defn -main []
  (let [ready-data (ready-data :data-files)]
    (execute
      (dims ready-data)
      ready-data)))

