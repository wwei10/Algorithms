package algo2.pa2.q2

import com.wwei2.util.UF

/**
 * Clustering algorithm on a bigger graph.
 */
object Solution {

  private val filename = "/algo2/pa2/clustering_big.txt"
  lazy val (numNodes, numBits, nodes, mapping) = {
    val source = io.Source.fromURL(getClass.getResource(filename))
    val lines = source.getLines.toList
    source.close
    val (numNodes, numBits) = lines.head.split(" ").map(_.toInt) match {
      case Array(x, y) => (x, y)
    }
    val data = lines.tail.map(line => {
      val v = line.split(" ").map(_.toInt).toList
      v
    })
    (numNodes, numBits, data.toArray, data.zip(0 until data.length).toMap)
  }

  def getNeighbors(x: List[Int]) = {
    def flip(x: Int) = {
      if (x == 0) 1 else 0
    }
    def getNeighbor(i: Int, j: Int) = {
      if (i == j) {
        (x.slice(0, i) :+ flip(x(i))) ++ x.slice(i + 1, x.length).toList
      } else {
        (x.slice(0, i) :+ flip(x(i))) ++ (x.slice(i + 1, j) :+
          flip(x(j))) ++ x.slice(j + 1, x.length).toList
      }
    }
    (for (i <- 0 until x.length; j <- i until x.length)
      yield getNeighbor(i, j)) ++ List(x)
  }

  def cluster = {
    var uf = new UF(numNodes)
    def less(x: List[Int], y: List[Int]): Boolean = {
      (for ((a, b) <- x.zip(y)) yield if (a == b) 0 else 1).sum < 3
    }
    for (i <- 0 until nodes.length; y <- getNeighbors(nodes(i))) {
      val x = nodes(i)
      if (mapping.contains(y) && less(x, y)) {
        uf.union(i, mapping(y))
      }
    }
    uf.count()
  }

  def main(args: Array[String]) {
    println(numNodes)
    println(numBits)
    // println(mapping)
    println(cluster)
  }
}