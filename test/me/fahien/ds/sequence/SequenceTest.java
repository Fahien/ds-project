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

	/** Exercise 1 */
	private boolean search(Sequence<Object> sequence, Object element) {
		Object temp;
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
	private void reverse(Sequence<Object> sequence) {
		if (sequence.size() > 1) {
			try {
				Object temp = sequence.removeFirst();
				reverse(sequence);
				sequence.addLast(temp);
			} catch (EmptySequenceException e) {
				logger.warning(e.getMessage());
			}
		}
	}

	@Test public void testSearchOnNode() {
		Sequence<Object> sequence = new NodeSequence<>();
		sequence.addFirst(3);
		sequence.set(sequence.first(), 4);
		try {
			assertEquals(4, sequence.getFirst());
			assertEquals(sequence.getFirst(), sequence.getLast());
			sequence.addLast(8);
			assertEquals(8, sequence.getLast());
			sequence.addLast(9);
			assertEquals(9, sequence.getLast());
			sequence.addFirst(1);
			assertEquals(1, sequence.getFirst());
			assertEquals(9, sequence.getLast());
			assertFalse(sequence.isEmpty());
			sequence.addFirst(2);
			assertEquals(9, sequence.removeLast());
			sequence.addLast(7);
			assertEquals(2, sequence.getFirst());
			assertEquals(7, sequence.getLast());
			sequence.addLast(4);
			assertEquals(6, sequence.size());
			assertEquals(2, sequence.removeFirst());
			assertEquals(1, sequence.removeFirst());
			assertEquals(sequence.toString(), "[4, 8, 7, 4]");

			reverse(sequence);
			sequence.addAfter(sequence.prev(sequence.next(sequence.first())), 3);
			assertEquals(sequence.toString(), "[4, 3, 7, 8, 4]");

			assertTrue(search(sequence, 8));
			assertFalse(search(sequence, 2));
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
		Sequence<Object> sequence = new ArraySequence<>(16);
		sequence.addFirst(3);
		sequence.set(sequence.first(), 4);
		try {
			assertEquals(4, sequence.getFirst());
			assertEquals(sequence.getFirst(), sequence.getLast());
			sequence.addLast(8);
			assertEquals(8, sequence.getLast());
			sequence.addLast(9);
			assertEquals(9, sequence.getLast());
			sequence.addFirst(1);
			assertEquals(1, sequence.atIndex(0).getElement());
			assertEquals(9, sequence.getLast());
			assertFalse(sequence.isEmpty());
			sequence.addFirst(2);
			assertEquals(9, sequence.removeLast());
			sequence.addLast(7);
			assertEquals(2, sequence.atIndex(sequence.indexOf(sequence.first())).getElement());
			assertEquals(7, sequence.getLast());
			sequence.addLast(4);
			assertEquals(6, sequence.size());
			assertEquals(2, sequence.removeFirst());
			assertEquals(1, sequence.removeFirst());
			assertEquals(sequence.toString(), "[4, 8, 7, 4]");

			reverse(sequence);
			sequence.addAfter(sequence.prev(sequence.next(sequence.first())), 3);
			assertEquals(sequence.toString(), "[4, 3, 7, 8, 4]");

			assertTrue(search(sequence, 8));
			assertFalse(search(sequence, 2));
			assertEquals(5, sequence.size());
		} catch (EmptySequenceException e) {
			logger.warning(e.getMessage());
		}
	}
}