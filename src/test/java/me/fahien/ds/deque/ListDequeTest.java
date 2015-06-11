package me.fahien.ds.deque;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyDequeException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/** List Deque Test Case
 * @author Fahien */
public class ListDequeTest {
	private static final Logger logger = Logger.getLogger(ListDequeTest.class.getName());

	/** Reinforcement 6.12 */
	@Test public void dequeOperations() {
		Deque<Integer> deque = new ListDeque<>();
		deque.addFirst(3);
		try {
			assertEquals(new Integer(3), deque.getFirst());
			assertEquals(deque.getFirst(), deque.getLast());
			deque.addLast(8);
			assertEquals(new Integer(8), deque.getLast());
			deque.addLast(9);
			assertEquals(new Integer(9), deque.getLast());
			deque.addFirst(1);
			assertEquals(new Integer(1), deque.getFirst());
			assertEquals(new Integer(9), deque.getLast());
			assertFalse(deque.isEmpty());
			deque.addFirst(2);
			assertEquals(new Integer(9), deque.removeLast());
			deque.addLast(7);
			assertEquals(new Integer(2), deque.getFirst());
			assertEquals(new Integer(7), deque.getLast());
			deque.addLast(4);
			assertEquals(new Integer(6), new Integer(deque.size()));
			assertEquals(new Integer(2), deque.removeFirst());
			assertEquals(new Integer(1), deque.removeFirst());
			logger.info(deque.toString());
		} catch (EmptyDequeException e) {
			logger.warning(e.getMessage());
		}
	}
}
