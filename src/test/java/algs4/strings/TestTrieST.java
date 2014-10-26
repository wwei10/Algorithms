package algs4.strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test TrieST
 */
public class TestTrieST {
  @Test
  public void testPutAndGet() {
    TrieST<Integer> trie = new TrieST<>();
    trie.put("abc", 10);
    trie.put("def", 10);
    trie.put("abd", 20);
    trie.put("deghijk", 30);
    trie.put("degh", 50);
    Assert.assertEquals(10, (int) trie.get("abc"));
    Assert.assertEquals(10, (int) trie.get("def"));
    Assert.assertEquals(20, (int) trie.get("abd"));
    Assert.assertEquals(30, (int) trie.get("deghijk"));
    Assert.assertEquals(50, (int) trie.get("degh"));
    Assert.assertEquals(null, trie.get("ab"));
    Assert.assertEquals(null, trie.get("abcd"));
  }

  @Test
  public void testDelete() {
    TrieST<Integer> trie = new TrieST<>();
    trie.put("abc", 10);
    trie.put("def", 20);
    trie.put("abcd", 20);
    trie.put("abce", 20);
    Assert.assertEquals(10, (int) trie.get("abc"));
    Assert.assertEquals(20, (int) trie.get("def"));
    Assert.assertEquals(20, (int) trie.get("abcd"));
    Assert.assertEquals(20, (int) trie.get("abce"));
    trie.delete("abce");
    Assert.assertEquals(null, trie.get("abce"));
    trie.delete("abc");
    Assert.assertEquals(null, trie.get("abc"));
    trie.delete("abcd");
    Assert.assertEquals(null, trie.get("abcd"));
    Assert.assertEquals(20, (int) trie.get("def"));
  }

  @Test
  public void testLongestPrefixOf() {
    TrieST<Integer> trie = new TrieST<>();
    trie.put("a", 1);
    trie.put("ab", 2);
    trie.put("abc", 3);
    trie.put("abd", 4);
    trie.put("abcdef", 5);
    trie.put("abcdefghijklmn", 6);
    trie.put("b", 7);
    trie.put("bc", 8);
    trie.put("bcde", 9);
    trie.put("bcdf", 10);
    Assert.assertEquals("bcde", trie.longestPrefixOf("bcdef"));
    Assert.assertEquals("abcdef", trie.longestPrefixOf("abcdefghijklm"));
    Assert.assertEquals("abcdefghijklmn", trie.longestPrefixOf("abcdefghijklmn"));
    Assert.assertEquals("abc", trie.longestPrefixOf("abcd"));
    Assert.assertEquals("abd", trie.longestPrefixOf("abde"));
  }

  @Test
  public void testKeysWithPrefix() {
    TrieST<Integer> trie = new TrieST<>();
    trie.put("play-off", 1);
    trie.put("play", 1);
    trie.put("playing", 1);
    trie.put("playwright", 1);
    trie.put("playstation", 1);
    trie.put("pig", 1);
    trie.put("dig", 1);
    trie.put("plane", 1);
    Set<String> expected = new HashSet<>();
    expected.add("play-off");
    expected.add("play");
    expected.add("playing");
    expected.add("playwright");
    expected.add("playstation");
    Set<String> set = new HashSet<>();
    for (String s : trie.keysWithPrefix("play")) {
      set.add(s);
    }
    Assert.assertEquals(expected, set);
  }

  @Test
  public void testKeysThatMatch() {
    TrieST<Integer> trie = new TrieST<>();
    trie.put("abc", 1);
    trie.put("abd", 2);
    trie.put("abe", 5);
    trie.put("abcd", 3);
    trie.put("abcde", 4);
    trie.put("bcd", 6);
    trie.put("acd", 7);
    trie.put("ccd", 8);

    Set<String> expected = new HashSet<>();
    expected.add("abc");
    expected.add("abd");
    expected.add("abe");
    Set<String> set = new HashSet<>();
    for (String s : trie.keysThatMatch("ab.")) {
      set.add(s);
    }
    Assert.assertEquals(expected, set);

    expected.clear();
    set.clear();

    expected.add("bcd");
    expected.add("acd");
    expected.add("ccd");
    for (String s : trie.keysThatMatch(".cd")) {
      set.add(s);
    }
    Assert.assertEquals(expected, set);

  }
}
