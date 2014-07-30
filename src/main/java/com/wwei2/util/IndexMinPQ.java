package com.wwei2.util;

import java.util.NoSuchElementException;

/**
 * Minimum oriented priority queue implementation.
 */
public class IndexMinPQ<T extends Comparable<T>> {
  private int size;
  private int capacity;
  private int[] pq;
  private int[] qp;
  private T[] keys;

  /**
   * Initializes the priority queue with the specified capacity.
   * @param initialCapacity the maximum number of elements it can hold
   */
  public IndexMinPQ(int initialCapacity) {
    size = 0;
    capacity = initialCapacity;
    pq = new int[capacity + 1];
    qp = new int[capacity + 1];
    keys = (T[]) new Comparable[capacity + 1];
    for (int i = 0; i <= capacity; i++) {
      pq[i] = -1;
      qp[i] = -1;
    }
  }

  /**
   * Returns the number of elements in the priority queue.
   * @return the number of elements in the priority queue
   */
  public int size() {
    return size;
  }

  /**
   * Is the priority queue empty?
   * @return true if the priority queue is empty; false, otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Is item i already in the priority queue?
   * @param i an index
   * @return true if the priority queue contains i; false, otherwise
   */
  public boolean contains(int i) {
    if (i < 0 || i > capacity) {
      throw new IndexOutOfBoundsException(
          "i should be within the range of 0 to " + (capacity - 1));
    }
    return qp[i] != -1;
  }

  /**
   * Insert key at index i.
   * @param i an index
   * @param key key associated with index i
   */
  public void insert(int i, T key) {
    if (i < 0 || i > capacity) {
      throw new IndexOutOfBoundsException(
          "i should be within the range of 0 to " + (capacity - 1));
    }
    if (contains(i)) {
      throw new IllegalArgumentException(
          i + " is already in the priority queue");
    }
    size++;
    qp[i] =  size;
    pq[size] = i;
    keys[i] = key;
    siftUp(size);
  }

  /**
   * Returns the minimum element.
   * @return the minimum if the priority queue is not empty; Null, otherwise.
   */
  public int delMin() {
    if (isEmpty()) {
      throw new NoSuchElementException("Priority queue is empty now");
    }
    int idx = pq[1];
    swap(1, size);
    qp[idx] = -1;
    keys[idx] = null;
    size--;
    siftDown(1);
    return idx;
  }

  /**
   * Return the index of the minimum.
   * @return the index of the minimum
   */
  public int minIndex() {
    if (isEmpty()) {
      throw new NoSuchElementException("Priority queue is empty now");
    }
    return pq[1];
  }

  /**
   * Return the minimum key.
   * @return the minimum key if not empty; Null, otherwise.
   */
  public T minKey() {
    return isEmpty() ? null : keys[pq[1]];
  }

  /**
   * Delete the key at index i.
   * @param i an index
   * @return key at index i
   */
  public T delete(int i) {
    if (i < 0 || i > capacity) {
      throw new IndexOutOfBoundsException(
          "i should be within the range of 0 to " + (capacity - 1));
    }
    if (!contains(i)) {
      throw new NoSuchElementException(i + " doesn't exist in the priority queue");
    }
    int idx = qp[i];
    swap(idx, size);
    size--;
    siftUp(idx);
    siftDown(idx);
    qp[i] = -1;
    T key = keys[i];
    keys[i] = null;
    return key;
  }

  // Swap two elements.
  private void swap(int i, int j) {
    int idx1 = pq[i];
    int idx2 = pq[j];
    pq[i] = idx2;
    pq[j] = idx1;
    qp[idx1] = j;
    qp[idx2] = i;
  }

  // Judge if i is less than j.
  private boolean lessThan(int i, int j) {
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
  }

  // Move i up.
  private void siftUp(int i) {
    int parentIdx = i / 2;
    while (parentIdx > 0 && lessThan(i, parentIdx)) {
      swap(i, parentIdx);
      i = parentIdx;
      parentIdx = i / 2;
    }
  }

  // Move i down.
  private void siftDown(int i) {
    int left = i * 2;
    int right = i * 2 + 1;
    int smallest = i;
    if (left > 0 && left <= size && lessThan(left, smallest)) {
      smallest = left;
    }
    if (right > 0 && right <= size && lessThan(right, smallest)) {
      smallest = right;
    }
    if (smallest != i) {
      swap(i, smallest);
      siftDown(smallest);
    }
  }
}
