package me.fahien.ds.partition;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import me.fahien.ds.set.Set;
import me.fahien.ds.util.position.graph.GraphVertex;
import me.fahien.ds.util.position.graph.Vertex;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** Partition Test Case
 * @author Fahien */
public class PartitionTest {
	private static final Logger logger = Logger.getLogger(PartitionTest.class.getName());

	@Test public void simpleTest() {
		Partition<Integer> partition = new ListPartition<>();

		assertTrue(partition.isEmpty());

		partition.makeSet(1);
		Set<Integer> set2 = partition.makeSet(2);
		Set<Integer> set3 = partition.makeSet(3);
		Set<Integer> set4 = partition.makeSet(4);

		logger.info(partition.toString());

		assertEquals(partition.size(), 4);

		partition.union(set2, set3);
		assertEquals(partition.size(), 3);

		logger.info(partition.toString());

		assertEquals(partition.find(4), set4);
		
		Set<Integer> tempSet1 = partition.find(1);
		Set<Integer> tempSet2 = partition.find(2);
		
		partition.union(tempSet1, tempSet2);
		assertEquals(partition.size(), 2);
	}

	@Test public void normalTest() {
		ListPartition<Vertex<Integer>> partition = new ListPartition<>();

		assertTrue(partition.isEmpty());
		Vertex<Integer> vertex1 = new GraphVertex<Integer, Character>(1);
		Vertex<Integer> vertex2 = new GraphVertex<Integer, Character>(2);
		Vertex<Integer> vertex3 = new GraphVertex<Integer, Character>(3);
		Vertex<Integer> vertex4 = new GraphVertex<Integer, Character>(4);
		partition.makeGroup(vertex1);
		Set<Vertex<Integer>> set2 = partition.makeGroup(vertex2);
		Set<Vertex<Integer>> set3 = partition.makeGroup(vertex3);
		Set<Vertex<Integer>> set4 = partition.makeGroup(vertex4);
		
		logger.info(partition.toString());

		assertEquals(partition.size(), 4);

		partition.union(set2, set3);
		assertEquals(partition.size(), 3);

		logger.info(partition.toString());

		assertEquals(partition.find(vertex4), set4);
	}
}