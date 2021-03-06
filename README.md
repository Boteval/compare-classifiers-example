# compare-classifiers example usage

Example usage of the compare-classifiers library

## Usage ― without leiningen

To run an uberjar created from this project, without requiring leiningen:

0. place the uberjar in some directory
1. Place the input files mentioned in (2) below, in an `input` subdirectory
2. run: `java -jar <uberjar-name>`

## Usage ― with leiningen

0. Clone this repo

1. [Install leiningen](https://leiningen.org/#install)

2. Place the following under an `input` directory in your project:

        2.1 one or more CSV data files containing a gold tagging alongside a classifier's tagging per object.

        2.2 a mapping file from header names used in your CSV → to names used by this program. see sample mapping file below.

3. in a terminal session, run: `lein run`


## Sample Mapping file

The following sample demonstrates how the required mapping file informs the program as to the semantics of the input csv files, as well as additional configuration semantics. Your real mapping file should be placed under directory "input", and it must be called `mapping.edn`.

```clojure
;;;
;;; this mapping file advises the program where to read the data from, and how to read it
;;;

{
  :data-files
    ; one or more input data files, expected under directory "input".
    ; to maintain traceability, the data within each file will be associated with the name provided by :data-group.
    [ {:file "input-file-1.csv"
       :data-group :corpus}

      {:file "input-file-2.csv"
       :data-group :exa-corpus} ]

  ; mapping of column header names, to enable reading the gold and
  ; result classifications, from the above provided input data file
  :headers-mapping

    { :object-id "id"
      :object "msg"

      ; every classification header set is a tuple comprising: a header name,
      ; a header name for its score ― or a value in case no score column is provided,
      ; and a maximum score value (the latter will be used as a normalization factor).

      :classification-result-sets

        {
          ; gold tags
          :gold
           [[:label1 1 1]
            [:label2 1 1]]

          ; classifier foo's tagging
          :foo
           [[:foo-label1 :foo-label1-score 100]
            [:foo-label2 :foo-label2-score 100]]

          ; classifier bar tagging
          :bar
           [[:bar-label1 :bar-label1-score 1]
            [:bar-label2 :bar-label2-score 1]]}}}

  ;; sometimes input data is dirty, containing irrelevant classes.
  ;; if the following entry is included, classes outside this list will be ignored.
  :valid-classes
    ["class A"
     "class B"
     "class C"]
}
```

## Important notes about input data conventions

1. All input data files must have the same headers structure.
2. An object id must be unique across all input data files provided to a single run.


## License

Distributed under the Eclipse Public License either version 1.0
