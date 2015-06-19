package me.fahien.ds.binarytree;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.HeightTour;
import me.fahien.ds.binarytree.LinkedBinaryTree;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import static org.testng.AssertJUnit.assertEquals;

/** Euler Tour Test Case
 * @author Fahien */
public class EulerTourTest {
	private static final Logger logger = Logger.getLogger(BinaryTreeTest.class.getName());

	@Test public void testHeightTour() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		try {
			tree.addRoot(0);
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		PositionList<Integer> list = new NodePositionList<>();
		list.addLast(1);
		list.addLast(2);
		tree.attachLeaves(list);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		tree.attachLeaves(list);

		HeightTour<Integer> heightTour = new HeightTour<>(tree);
		Integer rootHeight = heightTour.execute();
		assertEquals(rootHeight, new Integer(2));
	}
}
