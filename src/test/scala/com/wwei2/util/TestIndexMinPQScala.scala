package com.wwei2.util

import org.scalatest.{Matchers, FlatSpec}

/**
 * Test if IndexMinPQ can be used in Scala.
 */
class TestIndexMinPQScala extends FlatSpec with Matchers {

  case class Task(val name: String, val priority: Int) extends Ordered[Task] {
    override def compare(that: Task): Int = priority - that.priority
  }

  "A minimum oriented priority queue" should "remove the least key in the queue" in {
    var pq = new IndexMinPQ[Integer](5)
    val numbers = Array(2, 1, 5, 4, 3)
    for ((value, idx) <- numbers.zipWithIndex) {
      pq.insert(idx, value)
    }
    numbers(pq.delMin) should be (1)
    numbers(pq.delMin) should be (2)
    numbers(pq.delMin) should be (3)
    numbers(pq.delMin) should be (4)
    numbers(pq.delMin) should be (5)
    a [NoSuchElementException] should be thrownBy {
      pq.delMin
    }
  }

  it should "work with scala class" in {
    var pq = new IndexMinPQ[Task](5)
    val tasks = Array(Task("Football", 1), Task("Basketball", 0),
      Task("Scala", 5), Task("Java", 6), Task("Python", 4))
    for ((value, idx) <- tasks.zipWithIndex) {
      pq.insert(idx, value)
    }
    tasks(pq.delMin) should be (tasks(1))
    tasks(pq.delMin) should be (tasks(0))
    tasks(pq.delMin) should be (tasks(4))
    tasks(pq.delMin) should be (tasks(2))
    tasks(pq.delMin) should be (tasks(3))
    a [NoSuchElementException] should be thrownBy {
      pq.delMin
    }
  }

}
