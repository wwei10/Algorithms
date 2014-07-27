package algo2.lectures.week3

/**
 * Use dynamic programming to solve optimal binary search tree.
 */
object OptimalBST {
  def optbst(nodes: Array[Double]): Double = {
    val num = nodes.length
    var p = Array.fill[Double](num, num)(0.0)
    for (i <- 0 until num) {
      for (j <- 0 until num) {
        if (i <= j) {
          p(i)(j) = nodes.slice(i, j + 1).sum
        } else {
          p(i)(j) = 0
        }
      }
    }
    var dp = Array.fill[Double](num, num)(0.0)
    for (j <- 0 to num - 1) {
      for (i <- j to 0 by -1) {
        dp(i)(j) = (for (r <- i to j)
          yield {
            val left = if (i > r - 1) 0 else dp(i)(r - 1)
            val right = if (r + 1 > j) 0 else dp(r + 1)(j)
            p(i)(j) + left + right
          }).min
        // println(s"i: $i, j: $j = ${dp(i)(j)}")
      }
    }
    dp(0)(num - 1)
  }
  def main(args: Array[String]): Unit = {
    val nodes1: Array[Double] = Array(2.0, 24.0, 73.0, 1.0)
    println(optbst(nodes1))
    val nodes2 = Array(1.0, 34.0, 33.0, 32.0)
    println(optbst(nodes2))
  }
}
