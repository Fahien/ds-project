package me.fahien.ds.adaptablepriorityqueue;

import org.testng.annotations.Test;

import me.fahien.ds.adaptablepriorityqueue.AdaptablePriorityQueue;
import me.fahien.ds.adaptablepriorityqueue.HeapAdaptablePriorityQueue;
import me.fahien.ds.adaptablepriorityqueue.SortedListAdaptablePriorityQueue;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.graph.GraphVertex;
import me.fahien.ds.util.position.graph.Vertex;

import static org.testng.Assert.assertTrue;

/** AdaptablePriorityQueue Test Case
 * @author Fahien */
public class AdaptablePriorityQueueTest {
	@Test public void heapTest() {
		AdaptablePriorityQueue<Integer, Character> queue = new HeapAdaptablePriorityQueue<>();
		assertTrue(queue.isEmpty());

		queue = new HeapAdaptablePriorityQueue<>(new DefaultComparator<Integer>());
		assertTrue(queue.isEmpty());
	}

	@Test public void replaceHeapTest() {
		AdaptablePriorityQueue<Integer, Vertex<Integer>> queue = new HeapAdaptablePriorityQueue<>();
		Entry<Integer, Vertex<Integer>> entry1 = queue.insert(Integer.MAX_VALUE, new GraphVertex<Integer, Integer>(Integer.MAX_VALUE));
		queue.insert(2, new GraphVertex<Integer, Integer>(2));
		queue.insert(3, new GraphVertex<Integer, Integer>(3));
		queue.removeMin();
		queue.replaceKey(entry1, 4);
	}

	@Test public void sortedTest() {
		AdaptablePriorityQueue<Integer, Character> queue = new SortedListAdaptablePriorityQueue<>();
		assertTrue(queue.isEmpty());

		queue = new SortedListAdaptablePriorityQueue<>(new DefaultComparator<Integer>());
		assertTrue(queue.isEmpty());
	}
}
