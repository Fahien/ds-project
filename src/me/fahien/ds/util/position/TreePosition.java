package me.fahien.ds.util.position;

import me.fahien.ds.nodelist.PositionList;

public interface TreePosition<E> extends Position<E> {
	public void setElement(E element);
	public PositionList<Position<E>> getChildren();
	public void setChildren(PositionList<Position<E>> children);
	public TreePosition<E> getParent();
	public void setParent(TreePosition<E> parent);
}