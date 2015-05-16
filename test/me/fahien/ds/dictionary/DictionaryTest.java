package me.fahien.ds.dictionary;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.util.composition.Entry;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** Dictionary Test Case
 * @author Fahien */
public class DictionaryTest {
	private static final Logger logger = Logger.getLogger(DictionaryTest.class.getName());

	@Test public void simpleTest() {
		Dictionary<Integer, Character> log = new LogFile<>();
		assertTrue(log.isEmpty());

		log.insert(0, 'A');
		assertEquals(log.size(), 1);

		assertEquals(log.find(0).getValue(), new Character('A'));

		log.insert(0, 'B');
		log.insert(1, 'C');

		for (Entry<Integer, Character> e : log.findAll(0)) {
			logger.info(e.toString());
		}

		for (Entry<Integer, Character> e : log.getEntries()) {
			logger.info(e.toString());
		}
	}
}
