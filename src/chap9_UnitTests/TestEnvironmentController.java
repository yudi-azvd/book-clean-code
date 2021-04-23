package chap9_UnitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

/**
 * Esse teste não deve ser executado de verdade.
 */
public class TestEnvironmentController {
  static final double WAY_TOO_COLD = 0;
  static HW hw = new HW();
  static EnvironmentController controller = new EnvironmentController();

  @Test
  public void turnOnLowTempAlarmAtThreshold() throws Exception {
    hw.setTemp(WAY_TOO_COLD);
    controller.tic();
    assertTrue(hw.heaterState());
    assertTrue(hw.blowerState());
    assertFalse(hw.coolerState());
    assertFalse(hw.hiTempAlarm());
    assertTrue(hw.loTempAlarm());
  }
}
