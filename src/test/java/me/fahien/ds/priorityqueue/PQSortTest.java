package me.fahien.ds.priorityqueue;

import org.testng.annotations.Test;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;

import static org.testng.Assert.assertEquals;

/** PQSort Test Case
 * @author Fahien */
public class PQSortTest {
	/** Sorts elements of a position list
	 * using an initially empty priority queue to produce the ordering */
	private <E> void pqSort(PositionList<E> list, PriorityQueue<E, ?> queue) {
		int n = list.size();
		for (int j = 0; j < n; j++) {
			E element = list.remove(list.first());
			queue.insert(element, null);
		}
		for (int j = 0; j < n; j++) {
			E element = queue.removeMin().getKey();
			list.addLast(element);
		}
	}

	@Test public void sortTest() {
		PositionList<Integer> list = new NodePositionList<>();
		list.addLast(7);
		list.addLast(4);
		list.addLast(8);
		list.addLast(2);
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		PriorityQueue<Integer, Character> queue = new HeapPriorityQueue<>();
		pqSort(list, queue);
		assertEquals(list.toString(), "[2, 3, 4, 5, 7, 8, 9]");
	}
}
