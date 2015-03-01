package me.fahien.ds.position;

public class ArrayPosition<E> implements Position<E> {
	private int index;
	private E element;

	public ArrayPosition (int index, E element) {
		this.index = index;
		this.element = element;
	}

	@Override
	public E getElement () {
		return element;
	}

	public void setElement (E element) {
		this.element = element;
	}

	public int getIndex () {
		return index;
	}

	public void setIndex (int index) {
		this.index = index;
	}
}