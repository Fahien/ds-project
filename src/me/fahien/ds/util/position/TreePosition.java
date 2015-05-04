package me.fahien.ds.util.position;

import me.fahien.ds.positionlist.PositionList;

public interface TreePosition<E> extends Position<E> {
	void setElement(E element);
	PositionList<Position<E>> getChildren();
	void setChildren(PositionList<Position<E>> children);
	TreePosition<E> getParent();
	void setParent(TreePosition<E> parent);
}