package me.fahien.ds.positionlist;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyListException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/** Node Position List Test Case
 * @author Fahien */
public class NodePositionListTest {
	private static final Logger logger = Logger.getLogger(NodePositionListTest.class.getName());

	@Test public void listOperations() {
		PositionList<Integer> list = new NodePositionList<>();
		list.addFirst(3);
		list.set(list.first(), 4);
		try {
			assertEquals(new Integer(4), list.first().getElement());
			assertEquals(list.first().getElement(), list.last().getElement());
			list.addLast(8);
			assertEquals(new Integer(8), list.last().getElement());
			list.addLast(9);
			assertEquals(new Integer(9), list.last().getElement());
			list.addFirst(1);
			assertEquals(new Integer(1), list.first().getElement());
			assertEquals(new Integer(9), list.last().getElement());
			assertFalse(list.isEmpty());
			list.addFirst(2);
			assertEquals(new Integer(9), list.remove(list.last()));
			list.addLast(7);
			assertEquals(new Integer(2), list.first().getElement());
			assertEquals(new Integer(7), list.last().getElement());
			list.addLast(4);
			assertEquals(new Integer(6), new Integer(list.size()));
			assertEquals(new Integer(2), list.remove(list.first()) );
			assertEquals(new Integer(1), list.remove(list.first()) );
			assertEquals(list.toString(), "[4, 8, 7, 4]");
			((NodePositionList)list).reverse();
			list.addAfter(list.prev(list.next(list.first())), 3);
			list.addAfter(list.prev(list.next(list.first())), 2);
			assertEquals(list.toString(), "[4, 2, 3, 7, 8, 4]");
		} catch (EmptyListException e) {
			logger.warning(e.getMessage());
		}
	}
}
