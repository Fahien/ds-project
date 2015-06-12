package me.fahien.ds.map;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/** Map Test Case
 * @author Fahien */
public class MapTest {
	private static final Logger logger = Logger.getLogger(MapTest.class.getName());

	@Test public void simpleTest() {
		Map<Integer, Character> map = new ListMap<>();

		assertTrue(map.isEmpty());

		map.put(5, 'A');
		map.put(7, 'B');
		map.put(2, 'C');
		map.put(8, 'D');

		assertEquals(map.put(2, 'E'), new Character('C'));
		assertEquals(map.get(7), new Character('B'));
		assertNull(map.get(4));
		assertEquals(map.size(), 4);
		assertEquals(map.remove(5), new Character('A'));
		assertEquals(map.remove(2), new Character('E'));
		assertNull(map.remove(2));
		assertFalse(map.isEmpty());

		logger.info(map.entries().toString());
		logger.info(map.keys().toString());
		logger.info(map.values().toString());
	}

	@Test public void probeTest() {
		Map<Integer, Character> map = new HashTableMap<>(17, 21);
		assertTrue(map.isEmpty());

		map = new HashTableMap<>(17);
		assertTrue(map.isEmpty());

		map = new HashTableMap<>();
		assertTrue(map.isEmpty());

		map.put(5, 'A');
		map.put(7, 'B');
		map.put(2, 'C');
		map.put(8, 'D');

		assertEquals(map.put(2, 'E'), new Character('C'));
		assertEquals(map.get(7), new Character('B'));
		assertNull(map.get(4));
		assertEquals(map.size(), 4);
		assertEquals(map.remove(5), new Character('A'));
		assertEquals(map.remove(2), new Character('E'));
		assertNull(map.remove(2));
		assertFalse(map.isEmpty());

		logger.info(map.entries().toString());
		logger.info(map.keys().toString());
		logger.info(map.values().toString());
	}
}