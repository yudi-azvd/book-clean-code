package chap14_SuccessiveRefinement.args_finalversion;

// import static chap14_SuccessiveRefinement.args_v1.ArgsException.ErrorCode.*;

import java.util.ArrayList;
import java.util.Iterator;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
  private String[] value;
  private ArrayList<String> tmp = new ArrayList<>();
  
  public void set(Iterator<String> currentArgument) throws ArgsException {
    // tem bug. ele pega as flags seguintes
    while (currentArgument.hasNext()) {
      tmp.add(currentArgument.next());
    }
    value = (String []) tmp.toArray(new String[0]);
  }

  public static String[] getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof StringArrayArgumentMarshaler)
      return ((StringArrayArgumentMarshaler) am).value;
    return new String[0];
  }
}
