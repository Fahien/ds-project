package me.fahien.ds.util.position;

import me.fahien.ds.positionlist.PositionList;

public interface TreePosition<E> extends Position<E> {
	/** Updates the element stored at this position */
	void setElement(E element);

	/** Returns a list of children */
	PositionList<Position<E>> getChildren();

	/** Updates the list of children */
	void setChildren(PositionList<Position<E>> children);

	/** Returns the parent position */
	TreePosition<E> getParent();
}