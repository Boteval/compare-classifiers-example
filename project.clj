(defproject boteval "0.1.0-SNAPSHOT"
  :description "user code driving the nlu-eval library"
  :url "https://github.com/Boteval/nlu-eval-user"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"

  :source-paths      ["src/clojure"]
  :javac-options     ["-target" "1.8" "-source" "1.8"]

  :test-paths ["test" "src/clojure"] ; for picking up unit tests from regular source files not only the tests directory

  :test-selectors {:default (complement :unit) ; https://github.com/technomancy/leiningen/blob/983847276d12fcdac7a5b5eabbd5dfcb926087d7/src/leiningen/test.clj#L172
                   :all (constantly true)}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.boteval.nlueval "0.0.1"]

                 ;; sql wrangling stack
                 [honeysql "0.8.2"]                     ; sort of a query builder
                 [mysql/mysql-connector-java "5.1.41"]  ; mysql jdbc driver
                 [org.clojure/java.jdbc "0.7.0-alpha3"] ; clojure jdbc, needed for the rest of them libraries
                 [hikari-cp "1.7.5"]                    ; jdbc connection pooling, if we really need it (https://github.com/tomekw/hikari-cp)

                 [cheshire "5.7.1"]                    ; for working with json

                 [org.clojure/data.csv "0.1.3"]        ; for csv

                 [org.clojure/math.combinatorics "0.1.4"]

                 [io.aviso/pretty "0.1.33"] ; pretty exceptions in leinigen (http://ioavisopretty.readthedocs.io/en/latest/lein-plugin.html)
                 [mvxcvi/puget "1.0.1"]]    ; color printing function (https://github.com/greglook/puget#usage), see `with-color` and `cprint`

  :profiles {:dev {:dependencies
                    [[org.clojure/tools.trace "0.7.5"]
                     [criterium "0.4.3"]
                     [rhizome "0.2.5"]
                     ;[org.noisesmith.poirot :as poirot]
                     ]}}

  :plugins [[io.aviso/pretty "0.1.33"]      ; pretty exceptions in leinigen, needed here as well as in :dependencies
            [mvxcvi/whidbey "1.3.1"]        ; more colorful repl (https://github.com/greglook/whidbey)
            [lein-codox "0.10.3"]
            [lein-auto "0.1.3"]   ; provides the auto lein command for watching source changes
            [test2junit "1.2.6"]] ; push test results into junit xml format (or sucky html reports) https://github.com/ruedigergad/test2junit

  ; this doesn't work yet ― see https://github.com/weavejester/lein-auto/issues/6
  ; :auto {:default {:paths (:source-paths :java-source-paths :test-paths :java-source-paths "my path")}} ; https://github.com/weavejester/lein-auto#usage

  :codox {:metadata {:doc/format :markdown}} ; treat docstrings as codox extended markdown (https://github.com/weavejester/codox/blob/master/example/src/clojure/codox/markdown.clj)

  :main org.boteval.nlueval-sample.main)
