package me.fahien.ds.util.position;

import me.fahien.ds.nodelist.PositionList;

public class TreeNode<E> implements TreePosition<E> {
	private E element;
	private TreePosition<E> parent;
	private PositionList<Position<E>> children;

	public TreeNode() {}

	public TreeNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		this.element = element;
		this.parent = parent;
		this.children = children;
	}

	@Override public E getElement() {
		return element;
	}

	@Override public void setElement(E element) {
		this.element = element;
	}

	@Override public PositionList<Position<E>> getChildren() {
		return children;
	}

	@Override public void setChildren(PositionList<Position<E>> children) {
		this.children = children;
	}

	@Override public TreePosition<E> getParent() {
		return parent;
	}

	@Override public void setParent(TreePosition<E> parent) {
		this.parent = parent;
	}
}