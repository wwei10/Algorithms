package algo1.pa5;

import com.wwei2.util.IndexMinPQ;

import java.io.IOException;
import java.util.*;

/**
 * Dijkstra's algorithm.
 */
public class Dijkstra {
  private static int INTMAX = 1000000;
  private Map<Integer, Map<Integer, Integer>> adjList; // Adjacency list.
  private int num; // Number of nodes.
  private int source; // The index of the source node.
  private Map<Integer, Integer> dist; // Distance between source and all other nodes.

  /**
   * Constructor for Dijkstra's algorithm.
   * @param src the index of the source node
   * @param filename the file that stores the adjacency list
   */
  public Dijkstra(int src, String filename) {
    try {
      adjList = Util.getData(filename);
      num = adjList.size();
      source = src;
      dist = new HashMap<>();
      shortestPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void shortestPath() {
    IndexMinPQ<Integer> pq = new IndexMinPQ<Integer>(adjList.size());
    for (Map.Entry<Integer, Map<Integer, Integer>> entry : adjList.entrySet()) {
      int node = entry.getKey();
      if (node != source) {
        pq.insert(node, INTMAX);
      } else {
        pq.insert(node, 0);
      }
    }
    while (!pq.isEmpty()) {
      int distance = pq.minKey();
      int index = pq.delMin();
      dist.put(index, distance);
      Set<Integer> neighbors = adjList.get(index).keySet();
      for (int neighbor : neighbors) {
        if (!dist.containsKey(neighbor)) {
          int tmpDist = pq.delete(neighbor);
          int betweenDist = adjList.get(index).get(neighbor);
          if (distance + betweenDist < tmpDist) {
            pq.insert(neighbor, distance + betweenDist);
          } else {
            pq.insert(neighbor, tmpDist);
          }
        }
      }
    }
  }

  public int getDistance(int index) {
    return dist.get(index);
  }
}