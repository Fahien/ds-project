package me.fahien.ds.util.position;

public interface BTPosition<E> extends Position<E> {
	/** Sets the element */
	void setElement(E element);

	/** Returns the left child */
	BTPosition<E> getLeft();

	/** Sets the left child */
	void setLeft(BTPosition<E> left);

	/** Returns the right child */
	BTPosition<E> getRight();

	/** Sets the right child */
	void setRight(BTPosition<E> right);

	/** Returns the parent */
	BTPosition<E> getParent();
}