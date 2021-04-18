package chap2_MeaningfulNames;


/**
 * No listing original, o método {@link printGuessStatistics}
 * nem estava dentro de uma classe,  por isso foi mostrado como
 * um exemplo de variáveis sem contexto.
 */
public class GuessStatisticsMessageBad {
  private static void printGuessStatistics(char candidate, int count) {
    String number;
    String verb;
    String pluralModifier;
    if (count == 0) {
      number = "no";
      verb = "are";
      pluralModifier = "s";
    }
    else if (count == 1) {
      number = "1";
      verb = "is";
      pluralModifier = "";
    }
    else {
      number = Integer.toString(count);
      verb = "are";
      pluralModifier = "s";
    }
    String guessMessage = String.format(
      "There %s %s %s%s", verb, number, candidate, pluralModifier
    );
    System.out.println(guessMessage);
  }

  public static void main(String[] args) {
    printGuessStatistics('h', 0);
  }
}
