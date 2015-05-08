package me.fahien.ds.binarytree;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** Binary Tree Test Case
 * @author Fahien */
public class BinaryTreeTest {
	private static final Logger logger = Logger.getLogger(BinaryTreeTest.class.getName());

	@Test public void emptyTest() {
		BinaryTree<Object> tree = new LinkedBinaryTree<>();
		assertTrue(tree.isEmpty());
	}

	@Test public void sizeTest() {
		LinkedBinaryTree<Object> tree = new LinkedBinaryTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		assertEquals(tree.size(), 1);
	}

	@Test public void replaceTest() {
		LinkedBinaryTree<Object> tree = new LinkedBinaryTree<>();
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

	@Test public void childTest() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		try {
			tree.insertLeft("Child1", tree.root());
			tree.insertRight("Child2", tree.root());
			assertEquals(tree.size(), 3);
			String element = tree.left(tree.root()).getElement();
			assertEquals(element, "Child1");
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
	}

	@Test public void attachLeavesTest() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
		assertEquals(tree.size(), 0);
		try {
			tree.addRoot("Root");
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		try {
			tree.insertLeft("Child1", tree.root());
			tree.insertRight("Child2", tree.root());
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		PositionList<String> list = new NodePositionList<>();
		list.addLast("Child3");
		list.addLast("Child4");
		list.addLast("Child5");
		list.addLast("Child6");
		tree.attachLeaves(list);
		logger.info(tree.positions().toString());
	}
}