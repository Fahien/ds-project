package me.fahien.ds.util.position;

public interface BTPosition<E> extends Position<E> {
	void setElement(E element);
	BTPosition<E> getLeft();
	void setLeft(BTPosition<E> left);
	BTPosition<E> getRight();
	void setRight(BTPosition<E> right);
	BTPosition<E> getParent();
	void setParent(BTPosition<E> parent);
}