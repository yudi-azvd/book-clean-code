package chap7_ErrorHandling;

import java.io.FileReader;
import java.io.IOException;

class Class1 {
  public static String readFile() throws IOException {
    FileReader reader = new FileReader("does-not-matter");
    reader.close();
    return "";
  }
}