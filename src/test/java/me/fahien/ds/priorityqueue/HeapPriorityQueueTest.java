package me.fahien.ds.priorityqueue;

import org.testng.annotations.Test;

import java.util.Comparator;

import me.fahien.ds.util.comparator.DefaultComparator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/** HeapPriorityQueue Test Case
 * @author Fahien */
public class HeapPriorityQueueTest {

	@Test public void simpleTest() {
		PriorityQueue<Integer, String> queue = new HeapPriorityQueue<>();
		assertTrue(queue.isEmpty());
		assertEquals(queue.size(), 0);
	}

	@Test public void constructorTest() {
		Integer[] keys = new Integer[] {0, 2, 5, 1, 4, 3};
		String[] values  = new String[] {"zero", "two", "five", "one", "four", "three"};
		Comparator<Integer> comparator = new DefaultComparator<>();
		PriorityQueue<Integer, String> queue = new HeapPriorityQueue<>(keys, values, comparator);
		assertFalse(queue.isEmpty());
		assertEquals(queue.size(), 6);
		assertEquals(queue.removeMin().getValue(), "zero");
		assertEquals(queue.size(), 5);
		assertEquals(queue.min().getValue(), "one");
	}
}
