package me.fahien.ds.util.position;

public class BTIndexedPosition<E> implements Position<E> {
	private E element;
	private int index;

	/** @param element The element
	 * @param index The index */
	public BTIndexedPosition(E element, int index) {
		this.element = element;
		this.index = index;
	}

	/** Returns The element */
	@Override public E getElement () {
		return element;
	}

	/** @param element The element */
	public void setElement (E element) {
		this.element = element;
	}

	/** @return The index */
	public int getIndex () {
		return index;
	}

}
