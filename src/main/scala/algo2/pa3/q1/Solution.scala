package algo2.pa3.q1

/**
 * Knapsack problem.
 */
object Solution {
  private val filename = "/algo2/pa3/knapsack1.txt"
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
    def max(a: Int, b: Int): Int = if (a >= b) a else b
    val data = Array((0, 0)) ++ items
    val len = data.length
    var dp = Array.fill[Int](len, knapsackSize + 1)(0)
    for (i <- 1 until len) {
      for (j <- 1 to knapsackSize) {
        val (v, w) = data(i)
        if (j >= w) {
          dp(i)(j) = max(dp(i - 1)(j), dp(i - 1)(j - w) + v)
        } else {
          dp(i)(j) = dp(i - 1)(j)
        }
      }
    }
    dp(len - 1)(knapsackSize)
  }

  def main(args: Array[String]) {
    println("Value: " + knapsack(items.toArray, size))
  }
}
