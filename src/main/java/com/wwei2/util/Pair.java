package com.wwei2.util;

/**
 * Immutable pair (a, b).
 */
public class Pair<A, B> {
  final private A first;
  final private B second;

  public Pair(A x, B y) {
    first = x;
    second = y;
  }

  @Override
  public int hashCode() {
    int hashFirst = first != null ? first.hashCode() : 0;
    int hashSecond = second != null ? second.hashCode() : 0;
    return (hashFirst + hashSecond) * hashSecond + hashFirst;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Pair) {
      Pair otherPair = (Pair) other;
      return
          ((this.first == otherPair.first ||
              (this.first != null && otherPair.first != null &&
                  this.first.equals(otherPair.first))) &&
              (this.second == otherPair.second ||
                  (this.second != null && otherPair.second != null &&
                      this.second.equals(otherPair.second))));
    }
    return false;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }
}
