package com.cabinet.file;

import java.util.Collections;
import java.util.List;

public class Folder {

  public static void main(String[] args) {
    System.out.println("Hello File Cabinet!");
  }

  // This method will take some file type and return some explicit List<> type.
  // The List<> returned will then be placed in setup.json.
  public List<?> flatten() {
    return Collections.emptyList();
  }

}
