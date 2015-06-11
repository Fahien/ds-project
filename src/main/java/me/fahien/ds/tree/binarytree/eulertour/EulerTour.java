package me.fahien.ds.tree.binarytree.eulertour;

import me.fahien.ds.tree.binarytree.BinaryTree;
import me.fahien.ds.util.position.Position;

/** Euler tour traversal of a tree T starts by going from the root
 * towards its leftmost child, viewing the edges of T as being "walls"
 * that always keeps to its left.
 * @author Fahien */
public abstract class EulerTour<E, R> {
	protected BinaryTree<E> tree;

	public EulerTour(BinaryTree<E> tree) {
		this.tree = tree;
	}

	/** Executes the traversal */
	public abstract R execute();

	/** Template method */
	protected R eulerTour(Position<E> node) {
		TourResult<R> result = new TourResult<>();
		visitLeft(node, result);
		if(tree.hasLeft(node)) {
			result.setLeft(eulerTour(tree.left(node)));
		}
		visitBelow(node, result);
		if (tree.hasRight(node)) {
			result.setRight(eulerTour(tree.right(node)));
		}
		visitRight(node, result);
		return result.getOut();
	}

	/** Pre visit */
	protected void visitLeft(Position<E> node, TourResult<R> result) {}

	/** In visit */
	protected void visitBelow(Position<E> node, TourResult<R> result) {}

	/** Post visit */
	protected void visitRight(Position<E> node, TourResult<R> result) {}
}