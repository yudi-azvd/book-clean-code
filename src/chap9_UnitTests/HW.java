package chap9_UnitTests;

public class HW {
  public void setTemp(double temp) {}
  public boolean heaterState() { return true; }
  public boolean blowerState() { return true; }
  public boolean coolerState() { return true; }
  public boolean loTempAlarm() { return true; }
  public boolean hiTempAlarm() { return true; }

  /**
   * Retorna uma string com uma letra para cada componente do estado de HW, onde
   * maiúsculo significa ligado e minúsculo significa desligado na seguinte 
   * ordem: {heater, blower, cooler, hi-temp-alarm, lo-temp-alarm}.
   * 
   * @return Representação do estado em string
   */
  public String getState()     { return ""; }
}
