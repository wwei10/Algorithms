package algo2.pa3.q2

/**
 * Knapsack problem at a bigger scale.
 *
 * Straightforward iterative implementation uses an infeasible amount of
 * time and space. (Naive implementation will require O(nW) time and space.)
 *
 * This solution utilizes caching to avoid redundant work.
 *
 * Note:
 * You may need to increase the stack size by adding -Xss200m flag.
 *
 */
object Solution {
  private val filename = "/algo2/pa3/knapsack_big.txt"
  lazy val (size, num, items) = {
    val source = io.Source.fromURL(getClass().getResource(filename))
    val (first, rest) = source.getLines.toList.splitAt(1)
    val (size, num) = first.head.split(" ") match {
      case Array(x, y) => (x.toInt, y.toInt)
      case _ => throw new Error("Cannot parse the file.")
    }
    source.close
    val data = rest.map(line => line.split(" ") match {
      case Array(x, y) => (x.toInt, y.toInt)
      case _ => throw new Error("Cannot parse the file.")
    })
    (size, num, data)
  }

  def knapsack(items: Array[(Int, Int)], knapsackSize: Int) = {
    val data = Array((0, 0)) ++ items
    var cache = new collection.mutable.HashMap[(Int, Int), Int]()
    def max(a: Int, b: Int) = if (a >= b) a else b
    def solve(i: Int, j: Int): Int = {
      if (i == 0 || j == 0) {
        0
      } else {
        if (cache.contains((i, j))) {
          cache((i, j))
        } else {
          val (v, w) = data(i)
          if (j >= w) {
            val x = cache.getOrElseUpdate((i - 1, j), solve(i - 1, j))
            val y = cache.getOrElseUpdate((i - 1, j - w), solve(i - 1, j - w)) + v
            cache((i, j)) = max(x, y)
            max(x, y)
          } else {
            val x = cache.getOrElseUpdate((i - 1, j), solve(i - 1, j))
            cache((i, j)) = x
            x
          }
        }
      }
    }
    solve(data.length - 1, knapsackSize)
  }

  def main(args: Array[String]) {
    println("Value: " + knapsack(items.toArray, size))
  }
}
