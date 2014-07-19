package com.wwei2.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the functionality of priority queue.
 */
public class TestPQ {

  private static List<Task> tasks;

  /**
   * Task class for testing purpose.
   */
  private static class Task implements Comparable<Task> {
    private String name;
    private int priority;
    public Task(String name, int priority) {
      this.name = name;
      this.priority = priority;
    }
    @Override
    public int hashCode() {
      int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }
    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      final Task that = (Task) o;
      if (name == null) {
        if (that.name != null) {
          return false;
        }
      } else if (!name.equals(that.name)) {
        return false;
      }
      return true;
    }
    @Override
    public int compareTo(Task o) {
      if (o == null) {
        throw new NullPointerException("Compare to null pointer.");
      }
      return new Integer(priority).compareTo(o.priority);
    }
    @Override
    public String toString() {
      return "<Task: " + name + ", " + priority + " >";
    }
  }

  @BeforeClass
  public static void beforeClass() {
    tasks = new ArrayList<Task>(5);
    tasks.add(new Task("Alice", 4));
    tasks.add(new Task("Bob", 3));
    tasks.add(new Task("Chris", 2));
    tasks.add(new Task("Doug", 5));
    tasks.add(new Task("Emma", 1));
  }


  @Test
  public void testBuildHeap() {
    PQ pq = new PQ(tasks);
    Assert.assertEquals("Doug", ((Task) pq.delMax()).name);
    Assert.assertEquals("Alice", ((Task) pq.delMax()).name);
    Assert.assertEquals("Bob", ((Task) pq.delMax()).name);
    Assert.assertEquals("Chris", ((Task) pq.delMax()).name);
    Assert.assertEquals("Emma", ((Task) pq.delMax()).name);
  }

  @Test
  public void testInsert() {
    PQ pq = new PQ(tasks);
    pq.insert(new Task("Fred", 10));
    pq.insert(new Task("George", 8));
    pq.insert(new Task("Havana", 9));
    Assert.assertEquals("Fred", ((Task) pq.delMax()).name);
    Assert.assertEquals("Havana", ((Task) pq.delMax()).name);
    Assert.assertEquals("George", ((Task) pq.delMax()).name);
    Assert.assertEquals("Doug", ((Task) pq.delMax()).name);
    Assert.assertEquals("Alice", ((Task) pq.delMax()).name);
    Assert.assertEquals("Bob", ((Task) pq.delMax()).name);
    Assert.assertEquals("Chris", ((Task) pq.delMax()).name);
    Assert.assertEquals("Emma", ((Task) pq.delMax()).name);
  }

  @Test
  public void testDelete() {
    PQ pq = new PQ(tasks);
    pq.delete(new Task("Doug", 10));
    Assert.assertEquals("Alice", ((Task) pq.delMax()).name);
    pq.delete(new Task("Bob", 10));
    Assert.assertEquals("Chris", ((Task) pq.delMax()).name);
    Assert.assertEquals("Emma", ((Task) pq.delMax()).name);
  }

  @Test
  public void testChangeKey() {
    PQ pq = new PQ(tasks);
    pq.changeKey(new Task("Doug", 0));
    Assert.assertEquals("Alice", ((Task) pq.delMax()).name);
    pq.changeKey(new Task("Doug", 10));
    Assert.assertEquals("Doug", ((Task) pq.delMax()).name);
    pq.changeKey(new Task("Chris", 100));
    Assert.assertEquals("Chris", ((Task) pq.delMax()).name);
    pq.changeKey(new Task("Bob", 0));
    Assert.assertEquals("Emma", ((Task) pq.delMax()).name);
    Assert.assertEquals("Bob", ((Task) pq.delMax()).name);
  }
}
