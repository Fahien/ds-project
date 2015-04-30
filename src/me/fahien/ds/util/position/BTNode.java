package me.fahien.ds.util.position;

public class BTNode<E> implements BTPosition<E> {
	private E element;
	private BTPosition<E> left;
	private BTPosition<E> right;
	private BTPosition<E> parent;

	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		this.element = element;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	/** Returns the element */
	@Override public E getElement() {
		return element;
	}

	/** @param element The element */
	@Override public void setElement(E element) {
		this.element = element;
	}

	/** Returns the left child */
	@Override public BTPosition<E> getLeft() {
		return left;
	}

	/** @param left The left child */
	@Override public void setLeft(BTPosition<E> left) {
		this.left = left;
	}

	/** Returns The right child */
	@Override public BTPosition<E> getRight() {
		return right;
	}

	/** @param right The right child */
	@Override public void setRight(BTPosition<E> right) {
		this.right = right;
	}

	/** Returns The parent */
	@Override public BTPosition<E> getParent () {
		return parent;
	}

	/** @param parent The parent */
	@Override public void setParent (BTPosition<E> parent) {
		this.parent = parent;
	}
}