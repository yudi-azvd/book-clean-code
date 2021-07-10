package chap14_SuccessiveRefinement.args_finalversion;

import java.util.Arrays;

public class UseOfArgs {
  public static void main(String[] args) {
    String[] realArgs = "-w arr foo bar -l -p 8080 -d /tmp/ ".split(" ");

    try {
      // deveria ser args, mas realArgs fica mais fácil de "testar"
      Args arg = new Args("l,p#,d*,w[*]", realArgs);
      boolean logging = arg.getBoolean('l');
      // int logging = arg.getInt('l');
      int port = arg.getInt('p');
      String directory = arg.getString('d');
      String[] words = arg.getStringArray('w');

      System.out.printf("\n\nlogging %b | port %d | directory %s | words %s\n", 
        logging, port, directory, Arrays.toString(words));
      // System.out.printf("\n\nlogging %d | port %d | directory %s\n", 
      //   logging, port, directory);
    } catch (ArgsException e) {
      System.out.println("Argument error: " + e.errorMessage());
    }
  }
}
