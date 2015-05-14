package me.fahien.ds.priorityqueue;

import org.testng.annotations.Test;

import java.util.Comparator;

import me.fahien.ds.util.comparator.StringLengthComparator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
		queue.insert(5, 'a');
		queue.insert(9, 'c');
		queue.insert(3, 'b');
		queue.delete();
		assertTrue(queue.isEmpty());
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

	@Test public void unsortedTestWithStringLengthComparator() {
		Comparator<String> comparator = new StringLengthComparator();
		PriorityQueue<String, Character> queue = new UnsortedListPriorityQueue<>(comparator);
		queue.insert("Hello", 'a');
		assertEquals(queue.size(), 1);
		queue.insert("Good morning", 'c');
		assertEquals(queue.min().getValue(), new Character('a'));
		queue.insert("Hi", 'b');
		assertEquals(queue.min().getValue(), new Character('b'));
		assertEquals(queue.removeMin().getValue(), new Character('b'));
		assertEquals(queue.size(), 2);
	}
}
