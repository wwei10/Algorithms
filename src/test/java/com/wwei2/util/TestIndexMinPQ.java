package com.wwei2.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test the functionality of IndexMinPQ.
 */
public class TestIndexMinPQ {
  @Test
  public void testNumberArray() {
    int[] numbers = {4, 2, 3, 1, 5};
    IndexMinPQ<Integer> pq = new IndexMinPQ<Integer>(numbers.length);
    for (int i = 0; i < numbers.length; i++) {
      pq.insert(i, numbers[i]);
    }
    List<Integer> results = new ArrayList<>();
    while (!pq.isEmpty()) {
      results.add(numbers[pq.delMin()]);
    }
    Assert.assertEquals(results, Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));
  }

  @Test
  public void testStringArray() {
    String[] strs = {"bac", "abc", "xyz", "ww", "lbj"};
    IndexMinPQ<String> pq = new IndexMinPQ<String>(strs.length);
    for (int i = 0; i < strs.length; i++) {
      pq.insert(i, strs[i]);
    }
    List<String> results = new ArrayList<>();
    while (!pq.isEmpty()) {
      results.add(strs[pq.delMin()]);
    }
    Assert.assertEquals(results, Arrays.asList(new String[]{"abc", "bac", "lbj", "ww", "xyz"}));
  }

  @Test
  public void testDelete() {
    int[] numbers = {4, 2, 3, 1, 5, 8, 6, 7};
    IndexMinPQ<Integer> pq = new IndexMinPQ<Integer>(numbers.length);
    for (int i = 0; i < 5; i++) {
      pq.insert(i, numbers[i]);
    }
    List<Integer> results = new ArrayList<>();
    results.add(numbers[pq.delMin()]);
    pq.delete(0);
    numbers[0] = 0;
    pq.insert(0, numbers[0]);
    results.add(numbers[pq.delMin()]);
    pq.delete(2);
    for (int i = 5; i < numbers.length; i++) {
      pq.insert(i, numbers[i]);
    }
    while (!pq.isEmpty()) {
      results.add(numbers[pq.delMin()]);
    }
    Assert.assertEquals(results, Arrays.asList(new Integer[]{1, 0, 2, 5, 6, 7, 8}));
  }
}
