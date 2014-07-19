package com.wwei2.util;

/**
 * Union-find data structure.
 */
public class UF {
  private int count; // Number of components.
  private int[] id; // id[i] = parent of i.
  private int[] sz; // sz[i] = number of nodes in the subtree rooted at i.

  /**
   * Initialization.
   * @param size is the size of the union find data structure.
   */
  public UF(int size) {
    count = size;
    id = new int[size];
    sz = new int[size];
    for (int i = 0; i < size; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  /**
   * Find the representative of node p.
   * @param p (0 to size)
   * @return p's representative.
   */
  public int find(int p) {
    while (id[p] != p) {
      p = id[p];
    }
    return p;
  }

  /**
   * Judge if p and q is connected.
   * @param p
   * @param q
   * @return true if p and q is connected.
   */
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  /**
   * Attach smaller component to the larger component.
   * @param p
   * @param q
   */
  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
      return;
    }
    if (sz[p] < sz[q]) {
      id[rootP] = id[rootQ];
      sz[rootQ] += sz[rootP];
    } else {
      id[rootQ] = id[rootP];
      sz[rootP] += sz[rootQ];
    }
    count--;
  }

  /**
   * Return number of components in the union-find data structure.
   * @return number of components.
   */
  public int count() {
    return count;
  }
}
