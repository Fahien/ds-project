package me.fahien.ds.priorityqueue.adaptablepriorityqueue;

import org.testng.annotations.Test;

import me.fahien.ds.util.comparator.DefaultComparator;

import static org.testng.Assert.assertTrue;

/** AdaptablePriorityQueue Test Case
 * @author Fahien */
public class AdaptablePriorityQueueTest {
	@Test public void heapTest() {
		AdaptablePriorityQueue<Integer, Character> queue = new HeapAdaptablePriorityQueue<>();
		assertTrue(queue.isEmpty());

		queue = new HeapAdaptablePriorityQueue<>(new DefaultComparator<Integer>());
		assertTrue(queue.isEmpty());
	}

	@Test public void sortedTest() {
		AdaptablePriorityQueue<Integer, Character> queue = new SortedListAdaptablePriorityQueue<>();
		assertTrue(queue.isEmpty());

		queue = new SortedListAdaptablePriorityQueue<>(new DefaultComparator<Integer>());
		assertTrue(queue.isEmpty());
	}
}
