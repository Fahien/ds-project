package me.fahien.ds.util.position;

public interface BTPosition<E> extends Position<E> {
	public void setElement(E element);
	public BTPosition<E> getLeft();
	public void setLeft(BTPosition<E> left);
	public BTPosition<E> getRight();
	public void setRight(BTPosition<E> right);
	public BTPosition<E> getParent();
	public void setParent(BTPosition<E> parent);
}