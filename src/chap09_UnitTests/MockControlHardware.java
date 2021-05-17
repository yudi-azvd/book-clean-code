package chap09_UnitTests;

public class MockControlHardware {
  boolean heater = true;
  boolean blower = true;
  boolean cooler = true;
  boolean hiTempAlarm = true;
  boolean loTempAlarm = true;

  public String getState() {
    String state = "";
    state += heater ? "H" : "h";
    state += blower ? "B" : "b";
    state += cooler ? "C" : "c";
    state += hiTempAlarm ? "H" : "h";
    state += loTempAlarm ? "L" : "l";
    return state;
  }
}
