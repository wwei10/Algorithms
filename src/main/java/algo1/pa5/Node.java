package algo1.pa5;

/**
 * Node representation in Dijkstra's algorithm.
 */
class Node implements Comparable<Node> {

  final private int dist; // Distance to the source node.
  final private int index; // Index of current node.

  public Node(int index, int dist) {
    this.index = index;
    this.dist = dist;
  }

  public int getDist() {
    return dist;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public int hashCode() {
    return index;
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof Node) {
      Node that = (Node) o;
      if (this == that) {
        return true;
      }
      if (that == null) {
        return false;
      }
      if (index == that.index) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int compareTo(Node o) {
    if (o == null) {
      throw new NullPointerException();
    }
    if (this.dist > o.dist) {
      return -1;
    } else if (this.dist == o.dist) {
      return 0;
    } else {
      return 1;
    }
  }
}
