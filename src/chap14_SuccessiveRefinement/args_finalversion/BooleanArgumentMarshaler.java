package chap14_SuccessiveRefinement.args_finalversion;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
  private boolean value = false;
  
  public void set(Iterator<String> currentArgument) throws ArgsException {
    value = true;
  }

  public static boolean getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof BooleanArgumentMarshaler)
      return ((BooleanArgumentMarshaler) am).value;
    return false;
  }
}
