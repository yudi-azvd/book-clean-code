package chap09_UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Esse teste não deve ser executado de verdade.
 */
@Ignore
public class TestEnvironmentControllerRefactored {
  static final double WAY_TOO_COLD = 0;
  static HW hw = new HW();
  static EnvironmentController controller = new EnvironmentController();

  @Test
  public void turnOnLowTempAlarmAtThreshold() throws Exception {
    wayTooCold();
    assertEquals("HBchL", hw.getState());
  }

  @Test
  public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
    tooHot();
    assertEquals("hBChl", hw.getState());
  }

  @Test
  public void turnOnHeaterAndBlowerIfTooCold() {
    tooCold();
    assertEquals("HBchl", hw.getState());
  }
  
  // poderia estar em outro módulo também
  private void wayTooCold() {
    hw.setTemp(WAY_TOO_COLD);
    controller.tic();
  }

  private void tooHot() {}
  
  private void tooCold() {}
}
