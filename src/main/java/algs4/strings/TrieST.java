package algs4.strings;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The TrieST class represents an symbol table of key-value
 * pairs, with string keys and generic values.
 * It supports get, put, contains, delete, size, and isEmpty methods.
 * It also provides character-based methods for finding the string in
 * the symbol table that is the longest prefix of a given prefix,
 * finding all strings in the symbol table that start with a given prefix.
 */
public class TrieST<Value> {
  private static final int K = 256; // extended ASCII

  private Node root; // root of trie
  private int N; // number of keys in trie

  private static class Node {
    private Object val;
    private Node[] children = new Node[K];
  }

  /**
   * Initializes an empty trie symbol table.
   */
  public TrieST() {
  }

  /**
   * Gets the value of key in the symbol table.
   * @param key they key
   * @return the value associated with the key
   */
  public Value get(String key) {
    Node x = get(root, key, 0);
    if (x == null) {
      return null;
    }
    return (Value) x.val;
  }

  private Node get(Node x, String key, int idx) {
    if (x == null) {
      return null;
    }
    if (idx == key.length()) {
      if (x.val == null) {
        return null;
      }
      return x;
    }
    char c = key.charAt(idx);
    return get(x.children[c], key, idx + 1);
  }

  /**
   * Returns true if the key exists in the symbol table.
   * @param key the key
   * @return true if key exists
   */
  public boolean contains(String key) {
    return get(key) != null;
  }

  /**
   * Inserts the key-value pair into the symbol table.
   * If the value is null, this deletes the key from the symbol table.
   * @param key the key
   * @param val the value
   */
  public void put(String key, Value val) {
    if (val == null) delete(key);
    else root = put(root, key, val, 0);
  }

  private Node put(Node x, String key, Value val, int idx) {
    if (x == null) {
      x = new Node();
    }
    if (idx == key.length()) {
      if (x.val == null) {
        N++;
      }
      x.val = val;
      return x;
    }
    char c = key.charAt(idx);
    x.children[c] = put(x.children[c], key, val, idx + 1);
    return x;
  }

  /**
   * Deletes a key-value pair from the symbol table.
   * If it doesn't have any children, deletes the node as well.
   * @param key the key
   */
  public void delete(String key) {
    root = delete(root, key, 0);
  }

  private Node delete(Node x, String key, int idx) {
    if (x == null) {
      return null;
    }
    if (idx == key.length()) {
      if (x.val != null) {
        N--;
      }
      x.val = null;
    } else {
      char c = key.charAt(idx);
      x.children[c] = delete(x.children[c], key, idx + 1);
    }

    if (x.val != null) {
      return x;
    }
    for (char c = 0; c < K; c++) {
      if (x.children[c] != null) {
        return x;
      }
    }
    return null;
  }

  /**
   * Returns the number of key-value pairs in the symbol table.
   * @return the number of key-value pairs in the symbol table
   */
  public int size() {
    return N;
  }

  /**
   * Returns true if the symbol table is empty.
   * @return true if the symbol table is empty
   */
  public boolean isEmpty() {
    return N == 0;
  }

  /**
   * Returns the string in the symbol table that is longest prefix
   * of query.
   * @param query the query string
   * @return the string in the symbol table that is longest prefix of query
   */
  public String longestPrefixOf(String query) {
    int length = longestPrefixOf(root, query, 0, 0);
    return query.substring(0, length);
  }

  private int longestPrefixOf(Node x, String query, int idx, int length) {
    if (x == null) {
      return length;
    }
    if (x.val != null) {
      length = idx;
    }
    if (idx == query.length()) {
      return length;
    }
    char c = query.charAt(idx);
    return longestPrefixOf(x.children[c], query, idx + 1, length);
  }

  /**
   * Returns an iterator of keys with prefix query
   * @param query the prefix
   * @return an iterator of keys with a certain prefix
   */
  public Iterable<String> keysWithPrefix(String query) {
    Queue<String> queue = new LinkedList<>();
    Node x = get(root, query, 0);
    if (x != null) {
      collect(x, query, queue);
    }
    return queue;
  }

  private void collect(Node x, String str, Queue<String> queue) {
    if (x == null) {
      return;
    }
    if (x.val != null) {
      queue.add(str);
    }
    for (char c = 0; c < K; c++) {
      collect(x.children[c], str + (char) c, queue);
    }
  }

  public Iterable<String> keysThatMatch(String pattern) {
    Queue<String> queue = new LinkedList<>();
    collect(root, new StringBuilder(), pattern, queue);
    return queue;
  }

  private void collect(Node x, StringBuilder str, String pattern, Queue<String> queue) {
    if (x == null) {
      return;
    }
    int length = str.length();
    if (length == pattern.length()) {
      if (x.val != null) {
        queue.add(str.toString());
      }
      return;
    }
    assert length < pattern.length() : "length: " + length + " pattern length: " + pattern.length();
    char ch = pattern.charAt(length);
    if (ch == '.') {
      for (char c = 0; c < K; c++) {
        str.append(c);
        collect(x.children[c], str, pattern, queue);
        str.deleteCharAt(str.length() - 1);
      }
    } else {
      str.append(ch);
      collect(x.children[ch], str, pattern, queue);
      str.deleteCharAt(str.length() - 1);
    }
  }
}
