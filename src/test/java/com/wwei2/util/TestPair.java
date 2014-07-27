package com.wwei2.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test the functionality of pair.
 */
public class TestPair {
  @Test
  public void testSimple() {
    Pair<Integer, Integer> pair = new Pair<Integer, Integer>(1, 2);
    int first = pair.getFirst();
    int second = pair.getSecond();
    Assert.assertEquals(1, first);
    Assert.assertEquals(2, second);
  }

  @Test
  public void testMap() {
    Map<Pair<Integer, Integer>, Integer> map = new HashMap<Pair<Integer, Integer>, Integer>();
    map.put(new Pair<Integer, Integer>(1, 2), 1);
    map.put(new Pair<Integer, Integer>(2, 3), 2);
    map.put(new Pair<Integer, Integer>(1, 3), 3);
    map.put(new Pair<Integer, Integer>(4, 5), 4);
    map.put(new Pair<Integer, Integer>(2, 4), 5);
    map.put(new Pair<Integer, Integer>(1, 5), 6);
    int x = map.get(new Pair<Integer, Integer>(1, 2));
    Assert.assertEquals(1, x);
    x = map.get(new Pair<Integer, Integer>(2, 3));
    Assert.assertEquals(2, x);
    x = map.get(new Pair<Integer, Integer>(1, 5));
    Assert.assertEquals(6, x);
  }
}
