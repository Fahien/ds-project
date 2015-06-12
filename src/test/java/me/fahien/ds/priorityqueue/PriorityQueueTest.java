package me.fahien.ds.priorityqueue;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/** ArrayQueue Test Case
 * @author Fahien */
public class PriorityQueueTest {
	@Test public void sortedTest() {
		SortedListPriorityQueue<Integer, Character> queue = new SortedListPriorityQueue<>();
		queue.insert(5, 'a');
		assertEquals(queue.size(), 1);
		queue.insert(9, 'c');
		assertEquals(queue.min().getValue(), new Character('a'));
		queue.insert(3, 'b');
		assertEquals(queue.min().getValue(), new Character('b'));
		assertEquals(queue.removeMin().getValue(), new Character('b'));
		assertEquals(queue.size(), 2);
	}

	@Test public void unsortedTest() {
		PriorityQueue<Integer, Character> queue = new UnsortedListPriorityQueue<>();
		queue.insert(5, 'a');
		assertEquals(queue.size(), 1);
		queue.insert(9, 'c');
		assertEquals(queue.min().getValue(), new Character('a'));
		queue.insert(3, 'b');
		assertEquals(queue.min().getValue(), new Character('b'));
		assertEquals(queue.removeMin().getValue(), new Character('b'));
		assertEquals(queue.size(), 2);
	}
}
