package chap14_SuccessiveRefinement.args_draft04;


public class UseOfArgs {
  public static void main(String[] args) {
    String[] realArgs = "-l -f -s yamane".split(" ");

    try {
      // deveria ser args, mas realArgs fica mais fácil de "testar"
      Args arg = new Args("l,f,s*", realArgs);
      boolean logging = arg.getBoolean('l');
      boolean fSomething = arg.getBoolean('f');
      String surname = arg.getString('s');

      System.out.println("has s " + arg.has('s'));
      System.out.printf(
        "\n\nlogging %b | f %b | surname [%s]\n", 
        logging, fSomething, surname);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
