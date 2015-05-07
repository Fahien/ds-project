package me.fahien.ds.tree;

/** An abstract base class providing some functionality of the Tree interface
 * @author Fahien */
public abstract class AbstractTree<E> implements Tree<E> {
	public boolean isEmpty() {
		return size() == 0;
	}
}
