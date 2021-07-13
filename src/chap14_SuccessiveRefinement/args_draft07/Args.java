package chap14_SuccessiveRefinement.args_draft07;

import static chap14_SuccessiveRefinement.args_draft07.ArgsException.ErrorCode;

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
  private Map<Character, ArgumentMarshaler> booleanArgs = new HashMap<>();
  private Map<Character, ArgumentMarshaler> stringArgs = new HashMap<>();
  private Map<Character, ArgumentMarshaler> intArgs = new HashMap<>();
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
      parseBooleanSchemaElement(elementId);
    else if (isStringSchemaElement(elementTail))
      parseStringSchemaElement(elementId);
    else if (isIntegerSchemaElement(elementTail))
      parseIntegerSchemaElement(elementId);
  }

  private void validateSchemaElementId(char elementId) throws ParseException {
    if (!Character.isLetter(elementId))
      throw new ParseException("Bad char: " + elementId + " in Args format: " + schema, 0);
  }

  private void parseStringSchemaElement(char elementId) {
    stringArgs.put(elementId, new StringArgumentMarshaler());
  }

  private boolean isStringSchemaElement(String elementTail) {
    return elementTail.equals("*");
  }

  private boolean isBooleanSchemaElement(String elementTail) {
    return elementTail.length() == 0;
  }

  private void parseBooleanSchemaElement(char elementId) {
    booleanArgs.put(elementId, new BooleanArgumentMarshaler());
  }

  private boolean isIntegerSchemaElement(String elementTail) {
    return elementTail.equals("#");
  }

  private void parseIntegerSchemaElement(char elementId) {
    intArgs.put(elementId, new IntegerArgumentMarshaler());
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
    boolean set = true;
    if (isBoolean(argChar))
      setBooleanArg(argChar, true);
    else if (isStringArg(argChar))
      setStringArg(argChar, "");
    else if (isIntArg(argChar))
      setIntArg(argChar);
    else
      set = false;
    return set;
  }

  private void setStringArg(char argChar, String s) throws ArgsException {
    currentArgument++;
    try {
      stringArgs.get(argChar).set(args[currentArgument]);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_STRING;
    }
  }

  private boolean isStringArg(char argChar) {
    return stringArgs.containsKey(argChar);
  }

  private void setBooleanArg(char argChar, boolean value) {
    try {
      booleanArgs.get(argChar).set("true");
    } catch (ArgsException e) {}
  }

  private boolean isBoolean(char argChar) {
    return booleanArgs.containsKey(argChar);
  }

  private boolean isIntArg(char argChar) {
    return intArgs.containsKey(argChar);
  }

  private void setIntArg(char argChar) throws ArgsException {
    currentArgument++;
    String parameter = null;
    try {
      parameter = args[currentArgument];
      intArgs.get(argChar).set(parameter);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
      // throw new ArgsException(errorCode, errorArgumentId, null); ??
    }
    catch (ArgsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorParameter = parameter;
      errorCode = ErrorCode.INVALID_INTEGER;
      // throw new ArgsException(errorCode, errorArgumentId, errorParameter); ??
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
    ArgumentMarshaler am = booleanArgs.get(arg);
    return am != null && (Boolean) am.get();
  }

  public String getString(char arg) {
    ArgumentMarshaler am = stringArgs.get(arg);
    return am == null ? "" : (String) am.get();
  }

  public int getInt(char arg) {
    ArgumentMarshaler am = intArgs.get(arg);
    return am == null ? 0 : (Integer) am.get();
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
