package chap7_ErrorHandling;

import java.io.IOException;

public class Class2 {
  public static void readFile() throws IOException {
    Class1.readFile();
  }
}
