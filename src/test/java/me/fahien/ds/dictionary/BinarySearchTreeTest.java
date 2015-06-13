package me.fahien.ds.dictionary;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.BSTEntry;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** BinarySearchTree Test Case
 * @author Fahien */
public class BinarySearchTreeTest {
	private static final Logger logger = Logger.getLogger(BinarySearchTreeTest.class.getName());

	@Test public void emptyTest() {
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();
		assertTrue(tree.isEmpty());
		tree = new BinarySearchTree<>(new DefaultComparator<Integer>());
		assertTrue(tree.isEmpty());
	}

	@Test public void sizeTest() {
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();
		assertEquals(tree.size(), 0);
		tree.addRoot(new BSTEntry<>(0, 'A', null));
		assertEquals(tree.size(), 1);
	}

	@Test public void replaceTest() {
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();
		assertEquals(tree.size(), 0);
		tree.addRoot(new BSTEntry<>(0, 'A', null));
		try {
			assertEquals(tree.root().getElement().getValue(), new Character('A'));
			tree.replace(tree.root(), new BSTEntry<>(1, 'B', null));
			assertEquals(tree.root().getElement().getValue(), new Character('B'));
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
	}

	@Test public void insertTest() {
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();
		tree.insert(0, 'A');
		assertEquals(tree.size(), 1);

		assertEquals(tree.find(0).getValue(), new Character('A'));

		tree.insert(0, 'B');
		tree.insert(1, 'C');

		for (Entry<Integer, Character> e : tree.findAll(0)) {
			logger.info(e.toString());
		}

		for (Entry<Integer, Character> e : tree.entries()) {
			logger.info(e.toString());
		}
	}
}