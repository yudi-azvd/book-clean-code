package chap2_MeaningfulNames;

public class Sample2_copy {
  public static void copyChars(char a1[], char a2[]) {
    for (int i = 0; i < a2.length; i++) {
      a2[i] = a1[i];
    }    
  }

  /**
   * Refatorado
   * Eu poderia usar src e dst?
   */
  public static void betterCopyChars(char source[], char destination[]) {
    for (int i = 0; i < destination.length; i++) {
      destination[i] = source[i];
    }    
  }

  public static void main(String[] args) {
    System.out.println("hey");
  }
}
