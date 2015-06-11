package me.fahien.ds.util.position;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;

public class TreeNode<E> implements TreePosition<E> {
	private E element;
	private TreePosition<E> parent;
	private PositionList<Position<E>> children = new NodePositionList<>();

	public TreeNode(E element, TreePosition<E> parent) {
		this.element = element;
		this.parent = parent;
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
}