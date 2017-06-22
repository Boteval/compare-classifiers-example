(ns org.boteval.nlueval-sample.main
  (:require
    [org.boteval.nlueval.input.ready :refer :all]
    [org.boteval.nlueval-sample.execute :refer :all]))

(defn -main []
  (let [ready-data (ready-data :data-files)]
    (execute
      (dims ready-data)
      ready-data)))

