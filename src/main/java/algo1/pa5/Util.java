package algo1.pa5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility function for pa5 -- Dijkstra's algorithm.
 */
public class Util {
  /**
   * Read in adjacency list from a file.
   * @param filename the specified data file
   */
  public static Map<Integer, Map<Integer, Integer>> getData(String filename) throws IOException {
    Map<Integer, Map<Integer, Integer>> adjList = new HashMap<>();
    InputStream is = Util.class.getResourceAsStream(filename);
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line = null;
    while ((line = br.readLine()) != null) {
      String[] strs = line.split("\t");
      int node = Integer.parseInt(strs[0]) - 1;
      Map<Integer, Integer> tmp = new HashMap<>();
      for (int i = 1; i < strs.length; i++) {
        String[] pair = strs[i].split(",");
        int vertex = Integer.parseInt(pair[0]) - 1;
        int edgeCost = Integer.parseInt(pair[1]);
        tmp.put(vertex, edgeCost);
      }
      adjList.put(node, tmp);
    }
    return adjList;
  }
}
