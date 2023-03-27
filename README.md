### File Cabinet

Methods to flatten directory structures and aggregate file contents.

Java 17 with Gradle 8.0.2

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