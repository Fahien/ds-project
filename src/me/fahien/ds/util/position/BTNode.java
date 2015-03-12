package me.fahien.ds.util.position;

public class BTNode<E> implements BTPosition<E> {
	private E element;
	private BTPosition<E> left, right, parent;

	public BTNode (E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		this.element = element;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	@Override
	/** @return The element */
	public E getElement () {
		return element;
	}

	@Override
	/** @param The element */
	public void setElement (E element) {
		this.element = element;
	}

	@Override
	/** @return The left child */
	public BTPosition<E> getLeft () {
		return left;
	}

	@Override
	/** @param The left child */
	public void setLeft (BTPosition<E> left) {
		this.left = left;
	}

	@Override
	/** @return The right child */
	public BTPosition<E> getRight () {
		return right;
	}

	@Override
	/** @param The right child */
	public void setRight (BTPosition<E> right) {
		this.right = right;
	}

	@Override
	/** @return The parent */
	public BTPosition<E> getParent () {
		return parent;
	}

	@Override
	/** @param The parent */
	public void setParent (BTPosition<E> parent) {
		this.parent = parent;
	}
}