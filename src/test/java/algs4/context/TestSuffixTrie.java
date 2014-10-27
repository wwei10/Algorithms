package algs4.context;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suffix trie.
 */
public class TestSuffixTrie {
  @Test
  public void testIsSubstringOf() {
    SuffixTrie suffixTrie = new SuffixTrie("abaaba$");
    Assert.assertEquals(true, suffixTrie.isSubstring("aba"));
    Assert.assertEquals(true, suffixTrie.isSubstring("baab"));
    Assert.assertEquals(true, suffixTrie.isSubstring("aaba"));
    Assert.assertEquals(true, suffixTrie.isSubstring("aba$"));
    Assert.assertEquals(false, suffixTrie.isSubstring("abb"));
    Assert.assertEquals(false, suffixTrie.isSubstring("bab"));
    Assert.assertEquals(false, suffixTrie.isSubstring("bb"));
    Assert.assertEquals(false, suffixTrie.isSubstring("c"));

    Assert.assertEquals(true, suffixTrie.isSuffix("a"));
    Assert.assertEquals(true, suffixTrie.isSuffix("ba"));
    Assert.assertEquals(true, suffixTrie.isSuffix("aba"));
    Assert.assertEquals(true, suffixTrie.isSuffix("aaba"));
    Assert.assertEquals(true, suffixTrie.isSuffix("baaba"));
    Assert.assertEquals(true, suffixTrie.isSuffix("abaaba"));
    Assert.assertEquals(false, suffixTrie.isSuffix("b"));
    Assert.assertEquals(false, suffixTrie.isSuffix("aa"));
    Assert.assertEquals(false, suffixTrie.isSuffix("bba"));

    suffixTrie = new SuffixTrie("abcdefg$");
    Assert.assertEquals(true, suffixTrie.isSubstring("defg"));
    Assert.assertEquals(true, suffixTrie.isSubstring("def"));
    Assert.assertEquals(true, suffixTrie.isSubstring("e"));

    suffixTrie = new SuffixTrie("abcdefadsfzcxvcdef$");
    Assert.assertEquals(true, suffixTrie.isSubstring("zcxvc"));
    Assert.assertEquals(true, suffixTrie.isSubstring("xvcdef"));
    Assert.assertEquals(true, suffixTrie.isSubstring("abcdef"));
    Assert.assertEquals(true, suffixTrie.isSubstring("adsf"));
    Assert.assertEquals(false, suffixTrie.isSubstring("acdef"));
  }

  @Test
  public void testCountOccurrencesOf() {
    SuffixTrie suffixTrie = new SuffixTrie("abaaba$");
    Assert.assertEquals(2, suffixTrie.countOccurrencesOf("aba"));
    Assert.assertEquals(2, suffixTrie.countOccurrencesOf("ab"));
    Assert.assertEquals(4, suffixTrie.countOccurrencesOf("a"));
    Assert.assertEquals(2, suffixTrie.countOccurrencesOf("b"));
    Assert.assertEquals(1, suffixTrie.countOccurrencesOf("abaa"));
    Assert.assertEquals(1, suffixTrie.countOccurrencesOf("aaba"));
    Assert.assertEquals(0, suffixTrie.countOccurrencesOf("c"));
  }

  @Test
  public void testLongestRepeat() {
    SuffixTrie suffixTrie = new SuffixTrie("abaaba$");
    Assert.assertEquals("aba", suffixTrie.longestRepeat());

    suffixTrie = new SuffixTrie("abcdaabccba$");
    Assert.assertEquals("abc", suffixTrie.longestRepeat());

    suffixTrie = new SuffixTrie("abcdefadsfzcxvcdef$");
    Assert.assertEquals("cdef", suffixTrie.longestRepeat());
  }
}
