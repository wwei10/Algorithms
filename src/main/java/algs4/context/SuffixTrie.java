package algs4.context;

/**
 * Suffix trie.
 */
public class SuffixTrie {
  private static String EMPTY = "";
  private static final int K = 256;

  private Node root;

  private static class Node {
    public Node[] children = new Node[K];
    public Node suffixLink;
  }

  /**
   * Initializes a suffix trie using s.
   * @param s the string
   */
  public SuffixTrie(String s) {
    int length = s.length();
    assert length > 0; // ensure s is not an empty string

    // explicitly build the two-node suffix trie
    root = new Node();
    Node node = new Node();
    root.children[s.charAt(0)] = node;
    node.suffixLink = root;
    Node longestSuffix = node;

    // build suffix trie incrementally
    int idx = 1;
    while (idx < length) {
      char c = s.charAt(idx);
      Node curr = longestSuffix.suffixLink;
      longestSuffix.children[c] = new Node();
      longestSuffix = longestSuffix.children[c];
      Node prev = longestSuffix;

      while (curr != null) {
        if (curr.children[c] == null) {
          curr.children[c] = new Node();
        }
        prev.suffixLink = curr.children[c];
        prev = curr.children[c];
        curr = curr.suffixLink;
      }
      prev.suffixLink = root;

      idx++;
    }
  }

  /**
   * Check whether query is a substring of T.
   * @param query the string
   * @return true if query is a substring of T
   */
  public boolean isSubstring(String query) {
    Node x = get(root, query, 0);
    return x != null;
  }

  private Node get(Node x, String query, int idx) {
    if (x == null) {
      return null;
    }
    if (idx == query.length()) {
      return x;
    }
    char c = query.charAt(idx);
    return get(x.children[c], query, idx + 1);
  }

  /**
   * Check whether q is a suffix of T.
   * @param query the string
   * @return true if q is a suffix of T
   */
  public boolean isSuffix(String query) {
    return isSubstring(query + "$");
  }

  /**
   * Returns the number of occurrences of query in T.
   * @param query the string
   * @return the number of occurrences of query in T
   */
  public int countOccurrencesOf(String query) {
    Node x = get(root, query, 0);
    int[] result = {0};
    count(x, result);
    return result[0];
  }

  private void count(Node x, int[] result) {
    if (x == null) {
      return;
    }
    boolean hasNoChildren = true;
    for (char c = 0; c < K; c++) {
      if (x.children[c] != null) {
        hasNoChildren = false;
        count(x.children[c], result);
      }
    }
    if (hasNoChildren) {
      result[0]++;
    }
  }


  /**
   * Finds the longest repeat in T.
   * @return the longest repeat
   */
  public String longestRepeat() {
    if (root == null) {
      return EMPTY;
    }
    return longestRepeat(root, new StringBuilder());
  }

  // post order traversal to find the deepest node that has at least 2 leaves.
  private String longestRepeat(Node x, StringBuilder result) {
    String longestSoFar = "";
    int cnt = 0;
    for (char c = 0; c < K; c++) {
      if (x.children[c] != null) {
        result.append(c);
        String longest = longestRepeat(x.children[c], result);
        if (longest.length() > longestSoFar.length()) {
          longestSoFar = longest;
        }
        result.deleteCharAt(result.length() - 1);
        cnt++;
      }
    }
    if (cnt >= 2) {
      if (result.length() > longestSoFar.length()) {
        longestSoFar = result.toString();
      }
    }
    return longestSoFar;
  }
}
