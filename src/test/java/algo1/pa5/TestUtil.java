package algo1.pa5;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Test utility function for pa5.
 */
public class TestUtil {
  @Test
  public void testGetData() {
    String filename = "/algo1/pa5/test.txt";
    try {
      Map<Integer, Map<Integer, Integer>> map = Util.getData(filename);
      int value = map.get(1).get(80);
      Assert.assertEquals(982, value);
      value = map.get(5).get(200);
      Assert.assertEquals(4009, value);
      value = map.get(3).get(57);
      Assert.assertEquals(1239, value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
