package me.fahien.ds.iterator;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.list.ArrayList;
import me.fahien.ds.list.List;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.position.Position;

import static org.testng.Assert.assertEquals;

/** Iterator Test Case
 * @author Fahien */
public class IteratorTest {
	private static final Logger logger = Logger.getLogger(IteratorTest.class.getName());

	private void remove(PositionList<Object> list, Object element) {
		for (Position<Object> position : list.positions()) {
			if (position.getElement().equals(element)) {
				list.remove(position);
				return;
			}
		}
	}

	@Test public void removeTest() {
		PositionList<Object> list = new NodePositionList<>();
		for (int i = 0; i < 10; i++) {
			list.addLast(i);
		}
		remove(list, 1);
		assertEquals(list.size(), 9);
		assertEquals(list.toString(), "[0, 2, 3, 4, 5, 6, 7, 8, 9]");
	}

	@Test public void positionListIterator() {
		PositionList<Integer> list = new NodePositionList<>();
		for (int i = 0; i < 10; i++) {
			list.addLast(i);
		}
		for(int i : list) {
			logger.info("Iterating through the list: " + i);
		}
		assertEquals(list.size(), 10);
	}

	@Test public void arrayListIterator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i, i);
		}
		for (int i : list) {
			logger.info("Iterating through the list: " + i);
		}
		assertEquals(list.size(), 10);
	}
}
