package algo1.pa5;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test Dijkstra' algorithm.
 */
public class TestDijkstra {
  @Test
  public void testLarge() {
    Dijkstra dijkstra = new Dijkstra(1, "/algo1/pa5/dijkstraData.txt");
    int d7 = dijkstra.getDistance(7);
    int d37 = dijkstra.getDistance(37);
    int d59 = dijkstra.getDistance(59);
    int d82 = dijkstra.getDistance(82);
    int d99 = dijkstra.getDistance(99);
    int d115 = dijkstra.getDistance(115);
    int d133 = dijkstra.getDistance(133);
    int d165 = dijkstra.getDistance(165);
    int d188 = dijkstra.getDistance(188);
    int d197 = dijkstra.getDistance(197);
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
