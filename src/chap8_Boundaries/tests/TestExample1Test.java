package chap8_Boundaries.tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class TestExample1Test {
  @Test
  public void testLogCreate() {
    Logger logger = Logger.getLogger("MyLogger");
    logger.info("hello");
  }
}
