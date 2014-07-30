package com.wwei2.util

/**
 * Test if IndexMinPQ can be used in Scala.
 */
object TestIndexMinPQScala {
  def main(args: Array[String]) {
    var pq = new IndexMinPQ[Integer](5)
    val numbers = Array(2, 1, 5, 4, 3)
    for ((value, idx) <- numbers.zipWithIndex) {
      pq.insert(idx, value)
    }
    while (!pq.isEmpty()) {
      println(numbers(pq.delMin()))
    }
  }
}
