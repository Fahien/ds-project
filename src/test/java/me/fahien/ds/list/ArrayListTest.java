package me.fahien.ds.list;


import java.util.logging.Logger;

import org.testng.annotations.Test;

import me.fahien.ds.arraylist.ArrayIndexList;
import me.fahien.ds.arraylist.IndexList;
import me.fahien.ds.exception.EmptyListException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/** Array List Test Case
 * @author Fahien */
public class ArrayListTest {
	private static final Logger logger = Logger.getLogger(ArrayListTest.class.getName());

	@Test public void listOperations() {
		IndexList<Integer> list = new ArrayIndexList<>();
		list.add(0, 3);
		list.set(0, 4);
		try {
			assertEquals(new Integer(4), list.get(0));
			assertEquals(list.get(0), list.get(list.size() - 1));
			list.add(list.size(), 8);
			assertEquals(new Integer(8), list.get(list.size() - 1));
			list.add(list.size(), 9);
			assertEquals(new Integer(9), list.get(list.size() - 1));
			list.add(0,  1);
			assertEquals(new Integer(1), list.get(0));
			assertEquals(new Integer(9), list.get(list.size() - 1));
			assertFalse(list.isEmpty());
			list.add(0,  2);
			assertEquals(new Integer(9), list.remove(list.size() - 1) );
			list.add(list.size(), 7);
			assertEquals(new Integer(2), list.get(0));
			assertEquals(new Integer(7), list.get(list.size() - 1));
			list.add(list.size(), 4);
			assertEquals(new Integer(6), new Integer(list.size()));
			assertEquals(new Integer(2), list.remove(0) );
			assertEquals(new Integer(1), list.remove(0) );
			logger.info(list.toString());
			for (int i = 0; i < list.size(); i++) {
				logger.info(list.get(i) + " ");
			}
		} catch (EmptyListException e) {
			logger.warning(e.getMessage());
		}
	}
}
