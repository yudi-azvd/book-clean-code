package chap14_SuccessiveRefinement.args_finalversion;

import java.util.Iterator;

public interface ArgumentMarshaler {
  void set(Iterator<String> currentArgument) throws ArgsException;
}
