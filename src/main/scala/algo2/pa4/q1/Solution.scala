package algo2.pa4.q1

import java.util

/**
 * All-pairs shortest paths algorithm: Floyd Warshall Algorithm.
 */
object Solution {
  val INTMAX = 1000000
  val filename = "/algo2/pa4/g3.txt"
  val (n, m, edges) = {
    val source = io.Source.fromURL(getClass().getResource(filename))
    val lines = source.getLines.toList
    source.close
    val (numVertices, numEdges) = lines.head.split(" ") match {
      case Array(x, y) => (x.toInt, y.toInt)
    }
    val edges = lines.tail.map(_.split(" ") match {
      case Array(x, y, z) => (x.toInt - 1, y.toInt - 1) -> z.toInt
    }).toMap
    (numVertices, numEdges, edges)
  }

  def min(x: Int, y: Int) = if (x <= y) x else y

  def floyd: Array[Array[Int]] = {
    // Intialize 2-D array.
    var dp = Array.fill[Int](n, n)(INTMAX)
    for (i <- 0 to n - 1) {
      for (j <- 0 to n - 1) {
        if (i == j) {
          dp(i)(j) = 0
        } else if (edges.contains((i, j))) {
          dp(i)(j) = edges((i, j))
        }
      }
    }
    // Calculate.
    for (k <- 0 to n - 1) {
      for (i <- 0 to n - 1) {
        for (j <- 0 to n - 1) {
          if (dp(i)(k) != INTMAX && dp(k)(j) != INTMAX) {
            dp(i)(j) = min(dp(i)(j), dp(i)(k) + dp(k)(j))
          }
        }
      }
    }
    // Look for negative-cost cycles.
    for (i <- 0 to n - 1) {
      if (dp(i)(i) < 0) {
        throw new Exception("This graph contains negative-cost cycles!")
      }
    }
    dp
  }

  def main(args: Array[String]) {
    val dist = floyd
    val shortestPathCost = dist.foldLeft(INTMAX)((minSoFar, arr) => {
      min(minSoFar, arr.min)
    })
    println(shortestPathCost)
  }
}
