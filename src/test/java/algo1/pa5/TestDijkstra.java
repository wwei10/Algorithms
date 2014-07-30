package algo1.pa5;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test Dijkstra' algorithm.
 */
public class TestDijkstra {
  @Test
  public void testLarge() {
    Dijkstra dijkstra = new Dijkstra(0, "/algo1/pa5/dijkstraData.txt");
    int d7 = dijkstra.getDistance(6);
    int d37 = dijkstra.getDistance(36);
    int d59 = dijkstra.getDistance(58);
    int d82 = dijkstra.getDistance(81);
    int d99 = dijkstra.getDistance(98);
    int d115 = dijkstra.getDistance(114);
    int d133 = dijkstra.getDistance(132);
    int d165 = dijkstra.getDistance(164);
    int d188 = dijkstra.getDistance(187);
    int d197 = dijkstra.getDistance(196);
    Assert.assertEquals(2599, d7);
    Assert.assertEquals(2610, d37);
    Assert.assertEquals(2947, d59);
    Assert.assertEquals(2052, d82);
    Assert.assertEquals(2367, d99);
    Assert.assertEquals(2399, d115);
    Assert.assertEquals(2029, d133);
    Assert.assertEquals(2442, d165);
    Assert.assertEquals(2505, d188);
    Assert.assertEquals(3068, d197);
  }
}
