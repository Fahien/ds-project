package me.fahien.ds.deque;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import me.fahien.ds.exception.EmptyDequeException;

import static org.testng.Assert.*;

/** NodeDeque Test Case
 * @author Fahien */
public class NodeDequeTest {
	private static final Logger logger = Logger.getLogger(NodeDequeTest.class);

	/** Reinforcement 6.12 */
	@Test public void dequeOperations() {
		Deque<Integer> deque = new NodeDeque<>();
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
			logger.error(e.getMessage());
		}
	}
}
