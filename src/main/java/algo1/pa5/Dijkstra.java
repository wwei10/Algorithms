package algo1.pa5;

import com.wwei2.util.PQ;

import java.io.IOException;
import java.util.*;

/**
 * Dijkstra's algorithm.
 */
public class Dijkstra {
  private static int MAXINT = 1000000;
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
    PQ<Node> pq = new PQ<>(num);
    for (Map.Entry<Integer, Map<Integer, Integer>> entry : adjList.entrySet()) {
      int node = entry.getKey();
      if (node != source) {
        pq.insert(new Node(node, MAXINT));
      } else {
        pq.insert(new Node(node, 0));
      }
    }
    while (pq.size() != 0) {
      Node node = pq.delMax();
      int index = node.getIndex();
      int distance = node.getDist();
      dist.put(index, distance);
      Set<Integer> neighbors = adjList.get(index).keySet();
      for (int neighbor : neighbors) {
        if (!dist.containsKey(neighbor)) {
          Node tmp = pq.delete(new Node(neighbor, 0));
          int tmpIndex = tmp.getIndex();
          int tmpDist = tmp.getDist();
          int betweenDist = adjList.get(index).get(tmpIndex);
          if (distance + betweenDist < tmpDist) {
            pq.insert(new Node(tmpIndex, distance + betweenDist));
          } else {
            pq.insert(new Node(tmpIndex, tmpDist));
          }
        }
      }
    }
  }

  public int getDistance(int index) {
    return dist.get(index);
  }
}