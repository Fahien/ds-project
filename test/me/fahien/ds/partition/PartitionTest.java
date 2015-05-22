package me.fahien.ds.partition;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.set.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** Partition Test Case
 * @author Fahien */
public class PartitionTest {
	private static final Logger logger = Logger.getLogger(PartitionTest.class.getName());

	@Test
	public void simpleTest() {
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
	}
}