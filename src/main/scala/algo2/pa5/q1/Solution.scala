package algo2.pa5.q1

/**
 * Traveling Salesman Problem.
 */
object Solution {
  // val filename = "/algo2/pa5/test.txt"
  val filename = "/algo2/pa5/tsp.txt"
  lazy val data = {
    val source = io.Source.fromURL(getClass.getResource(filename))
    val lines = source.getLines.toList.drop(1)
    source.close
    lines.map(line => line.split(" ").map(_.toDouble) match {
      case Array(x, y) => (x, y)
    }).toArray
  }

  def tsp(data: Array[(Double, Double)]): Double = {
    val length = data.size
    var cache = new collection.mutable.HashMap[(Set[Int], Int), Double]()
    def dist(i: Int, j: Int) = {
      val x = data(i)
      val y = data(j)
      math.sqrt((x._1 - y._1) * (x._1 - y._1) + (x._2 - y._2) * (x._2 - y._2))
    }
    def solve(path: Set[Int], dest: Int): Double = {
      require(path.contains(dest), "Requirement failed.")
      // println("<" + path +  ", " + dest + ">")
      if (!cache.contains((path, dest))) {
        if (dest == 0) {
          if (path.size == 1) {
            cache((path, dest)) = 0
          } else {
            cache((path, dest)) = Double.PositiveInfinity
          }
        } else if (path.size == 2) {
          require(path.contains(0))
          val pathArray = path.toArray
          cache((path, dest)) = dist(pathArray(0), pathArray(1))
        } else {
          var minSoFar = Double.PositiveInfinity
          for (k <- path; if k != dest && k != 0) {
            val tmp = solve(path - dest, k) + dist(k, dest)
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
      val tmp = solve((0 until length).toSet, j) + dist(j, 0)
      if (tmp < minSoFar) {
        minSoFar = tmp
      }
    }
    minSoFar
  }

  def main(args: Array[String]) {
    println(tsp(data))
  }
}
