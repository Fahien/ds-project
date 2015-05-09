package me.fahien.ds.binarytree.eulertour.height;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.BinaryTree;
import me.fahien.ds.binarytree.eulertour.EulerTour;
import me.fahien.ds.binarytree.eulertour.TourResult;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.position.Position;

/** Evaluates height for every node of the tree using the Euler tour
 * @author Fahien */
public class HeightTour<E> extends EulerTour<E, Integer> {
	private static final Logger logger = Logger.getLogger(HeightTour.class.getName());

	public HeightTour(BinaryTree<E> tree) {
		super(tree);
	}

	@Override public Integer execute() {
		try {
			return eulerTour(tree.root());
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
			return 0;
		}
	}

	@Override protected void visitRight(Position<E> node, TourResult<Integer> result) {
		if (tree.isExternal(node)) {
			result.setOut(0);
		}
		else {
			int left = (result.getLeft() != null) ? result.getLeft() : 0;
			int right = (result.getRight() != null) ? result.getRight() : 0;
			result.setOut(Math.max(left, right) + 1);
		}
		logger.info("This node[" + node + "] has height: " + result.getOut());
	}
}
