package me.fahien.ds.completebinarytree;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import me.fahien.ds.exception.BoundaryViolationException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.graph.DFSTest;
import me.fahien.ds.util.position.Position;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CompleteBinaryTreeTest {
	private static final Logger logger = Logger.getLogger(DFSTest.class.getName());

	@Test public void simpleTest() {
		CompleteBinaryTree<Integer> tree = new ArrayListCompleteBinaryTree<>();
		assertTrue(tree.isEmpty());

		tree.add(1);
		assertFalse(tree.isEmpty());
		assertEquals(tree.size(), 1);

		try {
			assertEquals(tree.root().getElement(), (Integer)1);
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		Position<Integer> position2 = tree.add(2);
		Position<Integer> position3 = tree.add(3);
		Position<Integer> position4 = tree.add(4);
		Position<Integer> position5 = tree.add(5);
		Position<Integer> position6 = tree.add(6);
		try {
			assertEquals(tree.parent(position2), tree.root());
			assertEquals(tree.parent(position3), tree.root());
			assertEquals(tree.parent(position4), position2);
			assertEquals(tree.parent(position5), position2);
			assertEquals(tree.parent(position6), position3);
		} catch (InvalidPositionException | BoundaryViolationException | EmptyTreeException e) {
			logger.info(e.getMessage());
		}
	}
}