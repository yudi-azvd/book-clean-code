package chap14_SuccessiveRefinement.args_draft06;

public class UseOfArgs {
  public static void main(String[] args) {
    String[] realArgs = "-l -f -s yamane -i 10".split(" ");

    try {
      // deveria ser args, mas realArgs fica mais fácil de "testar"
      Args arg = new Args("l,f,s*,i#", realArgs);
      boolean logging = arg.getBoolean('l');
      boolean fSomething = arg.getBoolean('f');
      int integer = 0; //arg.getInteger('i');
      String surname = arg.getString('s');

      System.out.println("has i " + arg.has('i'));
      System.out.printf(
        "\n\nlogging %b | f %b | surname [%s] | integer %d\n", 
        logging, fSomething, surname, integer);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
