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
      int value = map.get(0).get(79);
      Assert.assertEquals(982, value);
      value = map.get(4).get(199);
      Assert.assertEquals(4009, value);
      value = map.get(2).get(56);
      Assert.assertEquals(1239, value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
