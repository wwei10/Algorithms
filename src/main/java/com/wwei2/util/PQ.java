package com.wwei2.util;

import java.util.*;

/**
 *  Indexed priority queue of generic keys. Support delete and changeKey
 *  operations.
 */

public class PQ<T extends Comparable<T>> {
  private int size;
  private List<T> pq;
  private Map<T, Integer> map;

  /**
   * Constructs an empty list with the specified initial capacity.
   * @param initialCapacity
   */
  public PQ(int initialCapacity) {
    size = 0;
    pq = new ArrayList<T>(initialCapacity);
    map = new HashMap<T, Integer>();
    pq.add(null); // To make it count from 1.
  }

  public PQ(Collection<? extends T> c) {
    pq = new ArrayList<T>(c.size());
    map = new HashMap<T, Integer>();
    pq.add(null);
    size = 0;
    for (T e : c) {
      size++;
      pq.add(e);
      map.put(e, size);
    }
    buildHeap();
  }

  public T peek() {
    return (size > 0) ? pq.get(1) : null;
  }

  public T delMax() {
    if (size <= 0) {
      return null;
    }
    T max = (size > 0) ? pq.get(1) : null;
    swap(1, size);
    pq.remove(size);
    map.remove(max);
    size--;
    sink(1);
    return max;
  }

  public void insert(T elem) {
    if (map.containsKey(elem)) {
      return;
    }
    size++;
    pq.add(elem);
    map.put(elem, size);
    swim(size);
  }

  /**
   * Remove a specified element.
   * @param elem
   */
  public T delete(T elem) {
    if (!map.containsKey(elem)) {
      throw new NoSuchElementException("key is not found in the priority queue");
    }
    int index = map.get(elem);
    T tmp = pq.get(index);
    swap(index, size);
    pq.remove(size);
    map.remove(elem);
    size--;
    swim(index);
    sink(index);
    return tmp;
  }

  /**
   * Change the priority associated with a specified key.
   * @param elem an element
   */
  public void changeKey(T elem) {
    if (!map.containsKey(elem)) {
      throw new NoSuchElementException("key is not found in the priority queue");
    }
    int index = map.get(elem);
    pq.set(index, elem);
    swim(index);
    sink(index);
  }

  /**
   * Increase key. If key is not increased, do nothing.
   * @param elem
   */
  public void increaseKey(T elem) {
    if (!map.containsKey(elem)) {
      throw new NoSuchElementException("Key is not found in the priority queue");
    }
    int index = map.get(elem);
    T oldElem = pq.get(index);
    if (elem.compareTo(oldElem) > 0) {
      pq.set(index, elem);
      swim(index);
    }
  }

  /**
   * Decrease key. If key is not decreased, do nothing.
   * @param elem
   */
  public void decreaseKey(T elem) {
    if (!map.containsKey(elem)) {
      throw new NoSuchElementException("Key is not found in the priority queue");
    }
    int index = map.get(elem);
    T oldElem = pq.get(index);
    if (elem.compareTo(oldElem) < 0) {
      pq.set(index, elem);
      sink(index);
    }
  }

  /**
   * Number of elements in the heap.
   * @return the number of elements in the heap.
   */
  public int size() {
    return size;
  }

  /**************************************************************
   * General helper functions
   **************************************************************/

  /**
   * Compares priorities between i and j.
   * @param i an index.
   * @param j an index.
   * @return true if pq[i] < pq[j]
   */
  private boolean less(int i, int j) {
    return pq.get(i).compareTo(pq.get(j)) < 0;
  }

  /**
   * Wrapper function to swap two slots.
   * @param i an index.
   * @param j an index.
   */
  private void swap(int i, int j) {
    T tmp = pq.get(i);
    pq.set(i, pq.get(j));
    pq.set(j, tmp);
    map.put(pq.get(i), i);
    map.put(pq.get(j), j);
  }

  /**
   * After reading in a list of elements, build the heap from
   * scratch.
   */
  private void buildHeap() {
    int bound = size / 2;
    for (int i = bound; i >= 1; i--) {
      sink(i);
    }
  }

  /**************************************************************
   * Heap helper functions
   **************************************************************/

  /**
   * Shift up the element at index.
   * @param index an index.
   */
  private void swim(int index) {
    if (index <= 0 || index > size) {
      return;
    }
    int parent = index / 2;
    while (parent > 0 && less(parent, index)) {
      swap(index, parent);
      index = parent;
      parent = index / 2;
    }
  }

  private void sink(int index) {
    if (index <= 0 || index > size) {
      return;
    }
    int left = index * 2;
    int right = index * 2 + 1;
    int largest = index;
    if (left <= size && less(index, left)) {
      largest = left;
    }
    if (right <= size && less(largest, right)) {
      largest = right;
    }
    if (largest != index) {
      swap(index, largest);
      sink(largest);
    }
  }
}