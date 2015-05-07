package me.fahien.ds.sequence;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptySequenceException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/** Sequence Test Case
 * @author Fahien */
public class SequenceTest {
	private static final Logger logger = Logger.getLogger(SequenceTest.class.getName());

	private class Command<T> {
		/** Exercise 1 */
		public boolean search(Sequence<T> sequence, T element) {
			T temp;
			try {
				temp = sequence.removeFirst();
				if (temp.equals(element)) {
					sequence.addFirst(temp);
					return true;
				}
				else {
					boolean result = search(sequence, element);
					sequence.addFirst(temp);
					return result;
				}
			} catch (EmptySequenceException e) {
				return false;
			}
		}

		/** Exercise 2 */
		private void reverse(Sequence<T> sequence) {
			if (sequence.size() > 1) {
				try {
					T temp = sequence.removeFirst();
					reverse(sequence);
					sequence.addLast(temp);
				} catch (EmptySequenceException e) {
					logger.warning(e.getMessage());
				}
			}
		}
	}

	@Test public void testSearchOnNode() {
		Sequence<Integer> sequence = new NodeSequence<>();
		sequence.addFirst(3);
		sequence.set(sequence.first(), 4);
		try {
			assertEquals(sequence.getFirst(), new Integer(4));
			assertEquals(sequence.getFirst(), sequence.getLast());
			sequence.addLast(8);
			assertEquals(sequence.getLast(), new Integer(8));
			sequence.addLast(9);
			assertEquals(sequence.getLast(), new Integer(9));
			sequence.addFirst(1);
			assertEquals(sequence.getFirst(), new Integer(1));
			assertEquals(sequence.getLast(), new Integer(9));
			assertFalse(sequence.isEmpty());
			sequence.addFirst(2);
			assertEquals(sequence.removeLast(), new Integer(9));
			sequence.addLast(7);
			assertEquals(sequence.getFirst(), new Integer(2));
			assertEquals(sequence.getLast(), new Integer(7));
			sequence.addLast(4);
			assertEquals(sequence.size(), 6);
			assertEquals(sequence.removeFirst(), new Integer(2));
			assertEquals(sequence.removeFirst(), new Integer(1));
			assertEquals(sequence.toString(), "[4, 8, 7, 4]");

			Command<Integer> command = new Command<>();
			command.reverse(sequence);
			sequence.addAfter(sequence.prev(sequence.next(sequence.first())), 3);
			assertEquals(sequence.toString(), "[4, 3, 7, 8, 4]");

			assertTrue(command.search(sequence, 8));
			assertFalse(command.search(sequence, 2));
			assertEquals(5, sequence.size());
		} catch (EmptySequenceException e) {
			logger.warning(e.getMessage());
		}
	}

	@Test public void simpleTest() {
		Sequence<Object> sequence = new ArraySequence<>();
		assertTrue(sequence.isEmpty());
	}

	@Test public void testSearchOnArray() {
		Sequence<Integer> sequence = new ArraySequence<>(16);
		sequence.addFirst(3);
		sequence.set(sequence.first(), 4);
		try {
			assertEquals(sequence.getFirst(), new Integer(4));
			assertEquals(sequence.getFirst(), sequence.getLast());
			sequence.addLast(8);
			assertEquals(sequence.getLast(), new Integer(8));
			sequence.addLast(9);
			assertEquals(sequence.getLast(), new Integer(9));
			sequence.addFirst(1);
			assertEquals(sequence.getFirst(), new Integer(1));
			assertEquals(sequence.getLast(), new Integer(9));
			assertFalse(sequence.isEmpty());
			sequence.addFirst(2);
			assertEquals(sequence.removeLast(), new Integer(9));
			sequence.addLast(7);
			assertEquals(sequence.getFirst(), new Integer(2));
			assertEquals(sequence.getLast(), new Integer(7));
			sequence.addLast(4);
			assertEquals(sequence.size(), 6);
			assertEquals(sequence.removeFirst(), new Integer(2));
			assertEquals(sequence.removeFirst(), new Integer(1));
			assertEquals(sequence.toString(), "[4, 8, 7, 4]");

			Command<Integer> command = new Command<>();
			command.reverse(sequence);
			sequence.addAfter(sequence.prev(sequence.next(sequence.first())), 3);
			assertEquals(sequence.toString(), "[4, 3, 7, 8, 4]");

			assertTrue(command.search(sequence, 8));
			assertFalse(command.search(sequence, 2));
			assertEquals(5, sequence.size());
		} catch (EmptySequenceException e) {
			logger.warning(e.getMessage());
		}
	}
}