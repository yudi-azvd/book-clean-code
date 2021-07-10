package chap14_SuccessiveRefinement.args_finalversion;

import static chap14_SuccessiveRefinement.args_finalversion.ArgsException.ErrorCode.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
  private String value = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      value = currentArgument.next();
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static String getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof StringArgumentMarshaler)
      return ((StringArgumentMarshaler) am).value;
    return "";
  }
}
