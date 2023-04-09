package com.cabinet.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Folder {

  public static void main(String[] args) throws IOException {
    System.out.println("Hello File Cabinet!");
    System.out.println(Arrays.toString(listFiles("src/test/dbdata/testdata")));
    System.out.println(listFilesUsingFilesList("src/test/dbdata/testdata/bar"));
    System.out.println(listFilesUsingFileWalk("src/test/dbdata/testdata", 2));
    System.out.println(listFilesUsingFileWalkAndVisitor("src/test/dbdata/testdata"));

    final String source = "src/test/dbdata/testdata/bar/color-codes.json";
    final String destination = "src/test/dbdata/setup.json";
    copyContents(source, destination);
  }

  // Place the provided directory structure in a List/Set.
  // Dump the Java List/Set in setup.json with a xml json object library.
  public static List<?> aggregate(String path) {
    return Collections.emptyList();
  }

  public static void copyContents(String source, String destination) throws IOException {
    final Path originalPath = Paths.get(source);
    final Path copied = Paths.get(destination);
    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
  }

  // List the contents of a directory.
  public static String[] listFiles(String path) throws FileNotFoundException {
    final File file = new File(path);
    final String[] pathNames = file.list();
    if (pathNames == null) throw new FileNotFoundException(path);
    return Arrays.copyOf(pathNames, pathNames.length);
  }

  // The list method returns a lazily populated Stream of entries in the directory.
  public static Set<String> listFilesUsingFilesList(String directory) throws IOException {
    try (Stream<Path> stream = Files.list(Paths.get(directory))) {
      return stream
          .filter(file -> !Files.isDirectory(file))
          .map(Path::getFileName)
          .map(Path::toString)
          .collect(Collectors.toUnmodifiableSet());
    }
  }

  // Other than listing files, we might want to traverse the directory to one or more levels deeper than its direct
  // file entries. In that case, we can use walk():
  public static Set<String> listFilesUsingFileWalk(String directory, int depth) throws IOException {
    try (Stream<Path> stream = Files.walk(Paths.get(directory), depth)) {
      return stream
          .filter(file -> !Files.isDirectory(file))
          .map(Path::getFileName)
          .map(Path::toString)
          .collect(Collectors.toUnmodifiableSet());
    }
  }

  // Additionally, we might want to take some action as we iterate on each file. In that case, we can use the
  // walkFileTree() method by providing a visitor describing the action we want to take:
  public static Set<String> listFilesUsingFileWalkAndVisitor(String directory) throws IOException {
    final Set<String> fileList = new HashSet<>();
    Files.walkFileTree(Paths.get(directory), new SimpleFileVisitor<>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (!Files.isDirectory(file)) {
          fileList.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
      }
    });
    return Set.copyOf(fileList);
  }

}
