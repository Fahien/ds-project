package me.fahien.ds.tree;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.NonEmptyTreeException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** Tree Test Case
 * @author Fahien */
public class TreeTest {
	private static final Logger logger = Logger.getLogger(TreeTest.class.getName());

	@Test public void emptyTest() {
		Tree<Object> tree = new LinkedTree<>();
		assertTrue(tree.isEmpty());

		Tree<Object> fullTree = new FullBinaryTree<>();
		assertTrue(fullTree.isEmpty());
	}

	@Test public void sizeTest() {
		LinkedTree<Object> tree = new LinkedTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		assertEquals(tree.size(), 1);
		try {
			tree.remove(tree.root());
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		assertEquals(tree.size(), 0);

		FullBinaryTree<Object> fullTree = new FullBinaryTree<>();
		assertEquals(fullTree.size(), 0);
		try {
			fullTree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		assertEquals(fullTree.size(), 1);
		try {
			fullTree.addChild("Child", fullTree.root());
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		assertEquals(fullTree.size(), 2);
	}

	@Test public void replaceTest() {
		LinkedTree<Object> tree = new LinkedTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		try {
			assertEquals(tree.root().getElement(), "Root");
			tree.replace(tree.root(), "Node");
			assertEquals(tree.root().getElement(), "Node");
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
	}

	@Test public void removeExternalChildTest() {
		LinkedTree<String> tree = new LinkedTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		try {
			tree.addChild("Child1", tree.root());
			tree.addChild("Child2", tree.root());
			assertEquals(tree.size(), 3);
			String element = tree.removeExternalChild(tree.root());
			assertEquals(tree.size(), 2);
			assertEquals(element, "Child1");
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
	}
}