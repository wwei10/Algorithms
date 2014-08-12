package algo2.pa5.q1

import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by weiwei on 8/11/14.
 */
class TestTSP extends FlatSpec with Matchers {
  "Dynamic programming algorithm" should "have same answers as baseline." in {
    val path = "/algo2/pa5/"
    val files = List("4nodes.txt", "5nodes.txt", "10nodes.txt")
    for (file <- files) {
      val data = Util.fromFile(path + file)
      Solution.dp(data) should be (Solution.exhaust(data))
    }
  }
}
