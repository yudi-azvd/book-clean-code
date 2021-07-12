package chap14_SuccessiveRefinement.args_draft3;


public class UseOfArgs {
  public static void main(String[] args) {
    String[] realArgs = "-l -p 8080 -d /tmp/ ".split(" ");

    try {
      // deveria ser args, mas realArgs fica mais fácil de "testar"
      Args arg = new Args("l,p#,d*", realArgs);
      boolean logging = arg.getBoolean('l');
      // int logging = arg.getInt('l');
      int port = arg.getInt('p');
      String directory = arg.getString('d');
      // String[] words = arg.getStringArray('w');

      System.out.printf(
        "\n\nlogging %b | port %d | directory %s\n", 
        logging, port, directory);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
