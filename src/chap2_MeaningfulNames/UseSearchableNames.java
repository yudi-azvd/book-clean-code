package chap2_MeaningfulNames;

public class UseSearchableNames {
  int s = 0;
  int[] t;

  public void method1() {
    for (int j = 0; j < 34; j++) {
      s += (t[j]*4)/5;
    }
  }

  int sum = 0;
  int[] taskEstimate;
  final int NUMBER_OF_TASKS = 2;

  /**
   * Refatorado
   */
  public void method2() {
    int realDaysPerIdealDay = 4; 
    final int WORK_DAYS_PER_WEEK = 5;
    for (int j = 0; j < NUMBER_OF_TASKS; j++) {
      int realTaskDays = taskEstimate[j] * realDaysPerIdealDay;
      int realTaskWeeks = (realTaskDays / WORK_DAYS_PER_WEEK);
      sum += realTaskWeeks;
    }
  }
}
