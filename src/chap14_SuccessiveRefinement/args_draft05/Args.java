package chap14_SuccessiveRefinement.args_draft05;

import java.text.ParseException;
import java.util.*;

/**
 * BOOLEAN and STRING only
 * added INTEGER
 * Added ArgumentMarshaler
 * Added IntegerArgumentMarshaler
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

  enum ErrorCode {
    OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER
  }

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

  private void setStringArg(char argChar, String s) {
    currentArgument++;
    try {
      stringArgs.get(argChar).setString(args[currentArgument]);
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
    booleanArgs.get(argChar).setBoolean(value);
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
      intArgs.get(argChar).setInteger(Integer.parseInt(parameter));
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
    }
    catch (NumberFormatException e) {
      valid = false;
      errorArgumentId = argChar;
      errorParameter = parameter;
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
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
    return am != null && am.getBoolean();
  }

  public String getString(char arg) {
    ArgumentMarshaler am = stringArgs.get(arg);
    return am == null ? "" : am.getString();
  }

  public int getIntger(char arg) {
    ArgumentMarshaler am = intArgs.get(arg);
    return am == null ? 0 : am.getInteger();
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  private class ArgumentMarshaler {
    private boolean booleanValue = false;
    private String stringValue = "";
    private int intValue = 0;

    public void setBoolean(boolean value) {
      booleanValue = value;
    }

    public boolean getBoolean() {
      return booleanValue; 
    }

    public void setString(String value) {
      stringValue = value;
    }

    public String getString() { 
      return stringValue == null ? "" : stringValue;
    }

    public void setInteger(int value) {
      intValue = value;
    }

    public int getInteger() { return intValue; }
  }

  private class BooleanArgumentMarshaler extends ArgumentMarshaler {}
  private class StringArgumentMarshaler extends ArgumentMarshaler {}
  private class IntegerArgumentMarshaler extends ArgumentMarshaler {}
}
