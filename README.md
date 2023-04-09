### File Cabinet

A tool to flatten a directory structure and aggregate the contents into a json list.

Inspired by the limitations of 
[importing test data into CouchbaseDB](https://forums.couchbase.com/t/using-scopes-and-collections-with-cbimport-sample-format/35587).

Java 17 with Gradle 8.0.2

[File class docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/File.html)

Initial structure:
```
/src
    /test
        /dbdata
            /testdata
                /bar
                    file1.json
                /baz
                    file2.json
                    file3.json
                /foo
                    file4.json
                setup.json
```

The contents of `fileX.json` need to be aggregated and placed into a list in `setup.json`.

#### To run:
```
$ ./gradlew run
```
