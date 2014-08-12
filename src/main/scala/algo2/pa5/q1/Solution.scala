package algo2.pa5.q1

import Util.{dist, perm}
/**
 * Traveling Salesman Problem.
 */
object Solution {
  // Exhaustive search.
  def exhaust(data: Array[(Double, Double)]): Double = {
    def calcDist(p: List[Int]): Double = {
      val (last, distance) = p.foldLeft((0, 0.0))((pair, curr) => pair match {
        case (prev, distance) => (curr, distance + dist(data(prev), data(curr)))
      })
      distance + dist(data(last), data(0))
    }
    // Iterate through all possible permutations.
    (for (p <- perm((1 until data.size).toList))
      yield calcDist(p)).min
  }

  // Dynamic programming approach.
  def dp(data: Array[(Double, Double)]): Double = {
    val length = data.size
    var cache = new collection.mutable.HashMap[(Set[Int], Int), Double]()
    def solve(path: Set[Int], dest: Int): Double = {
      // println("<" + path +  ", " + dest + ">")
      if (!cache.contains((path, dest))) {
        if (dest == 0) {
          if (path.size == 1) {
            cache((path, dest)) = 0
          } else {
            cache((path, dest)) = Double.PositiveInfinity
          }
        } else if (path.size == 2) {
          val pathArray = path.toArray
          cache((path, dest)) = dist(data(pathArray(0)), data(pathArray(1)))
        } else {
          var minSoFar = Double.PositiveInfinity
          for (k <- path; if k != dest && k != 0) {
            val tmp = solve(path - dest, k) + dist(data(k), data(dest))
            if (tmp < minSoFar) {
              minSoFar = tmp
            }
          }
          cache((path, dest)) = minSoFar
        }
      }
      cache((path, dest))
    }
    var minSoFar = Double.PositiveInfinity
    for (j <- 1 until length) {
      val tmp = solve((0 until length).toSet, j) + dist(data(j), data(0))
      if (tmp < minSoFar) {
        minSoFar = tmp
      }
    }
    minSoFar
  }
}
