package me.fahien.ds.queue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyQueueException;
import me.fahien.ds.exception.NotEnoughElementsException;

import static org.testng.Assert.*;

/** ArrayQueue Test Case
 * @author Fahien */
public class ArrayQueueTest {
	private static final Logger logger = Logger.getLogger(ArrayQueueTest.class.getName());

	@DataProvider public Object[][] strings() {
		return new String[][]{
				new String[]{"12345"}, new String[]{"abcdefg"}
		};
	}

	@Test(dataProvider = "strings") public void testAllMethods(String string) {
		Queue<Character> queue = new ArrayQueue<>(16);
		for (int i = 0; i < string.length(); i++) {
			queue.enqueue(string.charAt(i));
		}
		logger.info("Testing queue: " + queue.toString());
		assertEquals(string.length(), queue.size());
		try {
			assertEquals(new Character(string.charAt(0)), queue.front());
		} catch (EmptyQueueException e) {
			logger.warning(e.getMessage());
		}
		if (string.length() > 0 ) {
			assertFalse(queue.isEmpty());
		}
		for (int i = 0; i < string.length(); i++) {
			try {
				queue.dequeue();
			} catch (EmptyQueueException e) {
				logger.warning(e.getMessage());
			}
		}
		assertTrue(queue.isEmpty());
	}

	@DataProvider public Object[][] integers() {
		return new Object[][]{
				new Object[]{new Integer[] {1, 2, 3, 4, 5}}, new Object[]{new Integer[] {6, 7, 8, 9}}
		};
	}

	private Integer extract(Queue<Integer> queue, int position) throws NotEnoughElementsException {
		if (position > queue.size())
			throw new NotEnoughElementsException();
		Integer integer = null;
		for (int i = 0; i < queue.size(); i++) {
			try {
				if (i == position) {
					integer = queue.dequeue();
					queue.enqueue(integer);
				}
				else { queue.enqueue(queue.dequeue()); }
			} catch (EmptyQueueException e) {
				logger.warning(e.getMessage());
			}
		}
		return integer;
	}

	@Test(dataProvider = "integers") public void testExtractMethod(Integer[] integers) {
		Queue<Integer> queue = new ArrayQueue<>();
		for (Integer integer : integers) {
			queue.enqueue(integer);
		}
		String queueString = queue.toString();
		logger.info("Testing queue: " + queueString);
		for (int i = 0; i < integers.length; i++) {
			try {
				assertEquals(integers[i], extract(queue, i));
			} catch (NotEnoughElementsException e) {
				logger.warning(e.getMessage());
			}
		}
		assertEquals(integers.length, queue.size());
		assertEquals(queueString, queue.toString());
	}
}
