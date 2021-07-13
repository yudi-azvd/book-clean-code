package chap14_SuccessiveRefinement.args_draft06;

import static chap14_SuccessiveRefinement.args_draft06.ArgsException.ErrorCode;

import java.text.ParseException;
import java.util.*;


/**
 * BOOLEAN and STRING only
 * added INTEGER
 * Added ArgumentMarshaler
 * Added IntegerArgumentMarshaler
 * pushing functionality into the ArgumentMarshaler derivatives.
 */
public class Args {
  private String schema;
  private String[] args;
  private boolean valid = true;
  private Set<Character> unexpectedArguments = new TreeSet<>();
  private Map<Character, ArgumentMarshaler> marshalers = new HashMap<>();
  private Set<Character> argsFound = new HashSet<Character>();
  private int currentArgument;
  private char errorArgumentId = '\0';
  private ErrorCode errorCode = ErrorCode.OK;
  private String errorParameter = "";

  public Args(String schema, String[] args) throws ParseException, ArgsException {
    this.schema = schema;
    this.args = args;

    valid = parse();
  }

  public boolean isValid() {
    return valid;
  }

  private boolean parse() throws ParseException, ArgsException {
    if (schema.length() == 0 && args.length == 0)
      return true;
    parseSchema();
    parseArguments();
    return valid;
  }

  private boolean parseSchema() throws ParseException {
    for (String element : schema.split(",")) {
      String trimmedElement = element.trim();
      parseSchemaElement(trimmedElement);
    }

    return true;
  }

  private void parseSchemaElement(String element) throws ParseException {
    char elementId = element.charAt(0);
    String elementTail = element.substring(1);
    validateSchemaElementId(elementId);
    if (isBooleanSchemaElement(elementTail))
      marshalers.put(elementId, new BooleanArgumentMarshaler());
    else if (isStringSchemaElement(elementTail))
      marshalers.put(elementId, new StringArgumentMarshaler());
    else if (isIntegerSchemaElement(elementTail))
      marshalers.put(elementId, new IntegerArgumentMarshaler());
    else {
      throw new ParseException(String.format(
        "Argument: %c has invalid format: %s", elementId, elementTail), 0);
    }
  }

  private void validateSchemaElementId(char elementId) throws ParseException {
    if (!Character.isLetter(elementId))
      throw new ParseException("Bad char: " + elementId + " in Args format: " + schema, 0);
  }

  private boolean isStringSchemaElement(String elementTail) {
    return elementTail.equals("*");
  }

  private boolean isBooleanSchemaElement(String elementTail) {
    return elementTail.length() == 0;
  }

  private boolean isIntegerSchemaElement(String elementTail) {
    return elementTail.equals("#");
  }
  
  private boolean parseArguments() throws ArgsException {
    for (currentArgument = 0; currentArgument<args.length;++currentArgument) {
      String arg = args[currentArgument];
      parseArgument(arg);
    }
    return true;
  }

  private void parseArgument(String arg) throws ArgsException {
    if (arg.startsWith("-"))  
      parseElements(arg);
  }

  private void parseElements(String arg) throws ArgsException {
    for (int i = 1; i < arg.length(); i++) {
      parseElement(arg.charAt(i));
    }
  }

  private void parseElement(char argChar) throws ArgsException {
    if (setArgument(argChar))
      argsFound.add(argChar);
    else {
      unexpectedArguments.add(argChar);
      valid = false;
    }
  }

  private boolean setArgument(char argChar) throws ArgsException {
    // boolean set = true;
    ArgumentMarshaler m = marshalers.get(argChar);
    try {
      if (m instanceof BooleanArgumentMarshaler)
        setBooleanArg(m);
        // setBooleanArg(argChar);
      else if (m instanceof StringArgumentMarshaler)
        setStringArg(m);
      else if (m instanceof IntegerArgumentMarshaler)
        setIntArg(m);
      else
        return false;
    } catch (ArgsException e) {
      valid = false;
      errorArgumentId = argChar;
      throw e;
    }
    return true;
  }

  private void setStringArg(ArgumentMarshaler m) throws ArgsException {
    currentArgument++;
    try {
      // stringArgs.get(argChar).set(args[currentArgument]);
      m.set(args[currentArgument]);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorCode = ErrorCode.MISSING_STRING;
      throw new ArgsException();
    }
  }

  // private boolean isStringArg(ArgumentMarshaler m) {
  //   // return stringArgs.containsKey(argChar);
  //   return m instanceof StringArgumentMarshaler;
  // }

  private void setBooleanArg(ArgumentMarshaler m) {
  // private void setBooleanArg(char argChar) {
    try {
      m.set("true"); 
      // booleanArgs.get(argChar).set("true");
    } catch (ArgsException e) {}
  }

  // private boolean isBoolean(ArgumentMarshaler m) {
  //   // return booleanArgs.containsKey(argChar);
  //   return m instanceof BooleanArgumentMarshaler;
  // }

  // private boolean isIntArg(ArgumentMarshaler m) {
  //   // return intArgs.containsKey(argChar);
  //   return m instanceof IntegerArgumentMarshaler;
  // }

  private void setIntArg(ArgumentMarshaler m) throws ArgsException {
    currentArgument++;
    String parameter = null;
    try {
      parameter = args[currentArgument];
      m.set(parameter);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
    }
    catch (ArgsException e) {
      valid = false;
      errorParameter = parameter;
      errorCode = ErrorCode.INVALID_INTEGER;
      throw e;
    }
  }

  public int cardinality() {
    return argsFound.size();
  }

  public String usage() {
    if (schema.length() > 0)
      return "-[" + schema + "]";
    return "";
  }

  public String errorMessage() throws Exception {
    if (unexpectedArguments.size() > 0) {
      return unexpectedArgumentsMessage();
    }
    else {
      switch (errorCode) {
        case MISSING_STRING:
          return String.format("Could not find string parameter for -%c", errorArgumentId);
        case MISSING_INTEGER:
          return String.format("Could not find int parameter for -%c", errorArgumentId);
        case INVALID_INTEGER:
          return String.format("Argument -%c expects an integer but was '$s'", errorArgumentId, errorParameter);
        case OK:
          throw new Exception("TILT: should not get here");
      }
    }
    return "";
  }

  private String unexpectedArgumentsMessage() {
    StringBuffer message = new StringBuffer("Argument(s) -");
    for (char  c : unexpectedArguments)
      message.append(c);
    message.append(" unexpected");
    return message.toString();
  }

  public boolean getBoolean(char arg) {
    ArgumentMarshaler am = marshalers.get(arg);
    boolean b = false;
    try {
      b = am != null && (Boolean) am.get();
    } catch (ClassCastException e) {
      b = false;
    }
    return b;
  }

  public String getString(char arg) {
    ArgumentMarshaler am = marshalers.get(arg);
    try {
      return am == null ? "" : (String) am.get();
    } catch (ClassCastException e) {
      return "";
    }
  }

  public int getInt(char arg) {
    ArgumentMarshaler am = marshalers.get(arg);
    try {
      return am == null ? 0 : (Integer) am.get();
    } catch (Exception e) {
      return 0;
    }
  }

  public double getDouble(char arg) {
    return 0.0;
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  private abstract class ArgumentMarshaler {

    public abstract Object get();

    public abstract void set(String s) throws ArgsException;
  }

  private class BooleanArgumentMarshaler extends ArgumentMarshaler {
    private boolean booleanValue = false;

    public void set(String s) {
      booleanValue = true;
    }

    public Object get() {
      return booleanValue;
    }
  }
  
  private class StringArgumentMarshaler extends ArgumentMarshaler {
    private String stringValue = "";

    public void set(String s) {
      stringValue = s;
    }

    public Object get() {
      return stringValue;
    }
  }
  
  private class IntegerArgumentMarshaler extends ArgumentMarshaler {
    private int intValue = 0;

    public void set(String s) throws ArgsException {
      try {
        intValue = Integer.parseInt(s);
      } catch (NumberFormatException e) {
        throw new ArgsException();
      }
    }

    public Object get() {
      return intValue;
    }
  }
}
