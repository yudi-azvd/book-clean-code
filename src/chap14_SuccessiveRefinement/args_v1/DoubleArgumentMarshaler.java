package chap14_SuccessiveRefinement.args_v1;

import static chap14_SuccessiveRefinement.args_v1.ArgsException.ErrorCode.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
  private double value;  
  
  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      value = Double.parseDouble(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_DOUBLE);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_DOUBLE, parameter);
    }
  }

  public static double getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof DoubleArgumentMarshaler)
      return ((DoubleArgumentMarshaler) am).value;
    return 0;
  }
}
