package com.wwei2.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the functionality of union-find implementation.
 */
public class TestUF {
  static private UF uf;

  @BeforeClass
  public static void beforeClass() {
    uf = new UF(10);
    uf.union(0, 5);
    uf.union(1, 6);
    uf.union(2, 7);
    uf.union(3, 9);
    uf.union(4, 8);
  }

  @Test
  public void testConnected() {
    Assert.assertTrue(uf.connected(0, 5));
    Assert.assertTrue(uf.connected(1, 6));
    Assert.assertTrue(uf.connected(2, 7));
    Assert.assertTrue(uf.connected(3, 9));
    Assert.assertTrue(uf.connected(4, 8));
    Assert.assertFalse(uf.connected(1, 9));
    Assert.assertFalse(uf.connected(2, 8));
    Assert.assertFalse(uf.connected(3, 4));
    Assert.assertFalse(uf.connected(2, 3));
    Assert.assertFalse(uf.connected(1, 7));
  }

  @Test
  public void testUnion() {
    Assert.assertEquals(5, uf.count());
  }
}
