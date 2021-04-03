package chap2_MeaningfulNames;

/**
 * A classe provê contexto para as variáveis number,
 * verb e pluralModifier.
 */
public class GuessStatistcsMessage {
  private String number;
  private String verb;
  private String pluralModifier;

  public String make(char candidate, int count) {
    createPluralDependentMessageParts(count);
    return String.format(
      "There %s %s %s%s", verb, number, candidate, pluralModifier
    );
  }

  private void createPluralDependentMessageParts(int count) {
    if (count == 0) {
      thereAreNoLetters();
    }
    else if (count == 1) {
      thereIsOneLetter();
    }
    else {
      thereAreManyLetters(count);
    }
  }

  private void thereAreNoLetters() {
    number = "no";
    verb = "are";
    pluralModifier = "s";
  }
  
  private void thereIsOneLetter() {
    number = "1";
    verb = "is";
    pluralModifier = "";
  }
  
  private void thereAreManyLetters(int count) {
    number = Integer.toString(count);
    verb = "are";
    pluralModifier = "s";
  }

  public static void main(String[] args) {
    String str = new GuessStatistcsMessage().make('h', 0);
    System.out.println(str);
  }
}
