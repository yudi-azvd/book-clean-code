package chap14_SuccessiveRefinement.args_draft1;


/**
 * BOOLEAN only
 */
public class UseOfArgs {
  public static void main(String[] args) {
    String[] realArgs = "-l -f".split(" ");

    try {
      // deveria ser args, mas realArgs fica mais fácil de "testar"
      Args arg = new Args("l,f", realArgs);
      boolean logging = arg.getBoolean('l');
      boolean fSomething = arg.getBoolean('f');

      System.out.printf(
        "\n\nlogging %b | f %b\n", 
        logging, fSomething);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
