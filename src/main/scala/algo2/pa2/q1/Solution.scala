package algo2.pa2.q1

import com.wwei2.util.UF

import scala.annotation.tailrec
;

/**
 * Max-spacing k-clustering algorithm.
 */
object Solution {

  private val filename = "/algo2/pa2/clustering1.txt"

  lazy val edges = {
    val source = io.Source.fromURL(getClass.getResource(filename))
    val text = source.getLines.toList.drop(1)
    source.close
    text.map(line => line.split(" ") match {
      case Array(x, y, z) => ((x.toInt, y.toInt), z.toInt)
      case _ => throw new Error("Cannot parse file.")
    }).sortBy(_._2);
  }

  lazy val nodes = {
    edges.foldLeft(Set[Int]())((set, pair) => {
      set + pair._1._1 + pair._1._2
    })
  }

  def solve: Int = {
    var uf = new UF(nodes.size)
    var list = scala.collection.mutable.Queue() ++ edges
    while (uf.count() > 4 && !list.isEmpty) {
      val (edge, cost) = list.dequeue
      if (!uf.connected(edge._1 - 1, edge._2 - 1)) {
        uf.union(edge._1 - 1, edge._2 - 1)
      }
    }
    while (!list.isEmpty) {
      val (edge, cost) = list.dequeue
      if (!uf.connected(edge._1 - 1, edge._2 - 1)) {
        return cost
      }
    }
    Integer.MAX_VALUE
  }

  def main(args: Array[String]) {
    println(solve)
  }
}
