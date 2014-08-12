package algo2.pa5.q1

/**
 * Created by weiwei on 8/11/14.
 */
object Util {

  // Read data from file.
  def fromFile(filename: String) = {
    val source = io.Source.fromURL(getClass.getResource(filename))
    val lines = source.getLines.toList.drop(1)
    source.close
    lines.map(line => line.split(" ").map(_.toDouble) match {
      case Array(x, y) => (x, y)
    }).toArray
  }

  // Calculate distance given two points.
  def dist(x: (Double, Double), y: (Double, Double)) = {
    math.sqrt((x._1 - y._1) * (x._1 - y._1) + (x._2 - y._2) * (x._2 - y._2))
  }

  // Generate permutations.
  def perm[T](elems: List[T]): List[List[T]] = {
    if (elems.isEmpty) {
      List(List())
    } else {
      val head = elems.head
      for (ret <- perm(elems.tail); i <- 0 to ret.size)
      yield ret.splitAt(i) match {
        case (fst, snd) => fst ++ List(head) ++ snd
      }
    }
  }
}
