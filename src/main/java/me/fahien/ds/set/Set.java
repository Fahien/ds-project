package me.fahien.ds.set;

import me.fahien.ds.util.position.Position;

/** Set
 * @author Fahien */
public interface Set<E> {
	/** Returns the number of the elements of the set */
	int size();

	/** Tests whether the set is empty */
	boolean isEmpty();

	/** Joins with this sets */
	Set<E> union(Set<E> set);

	/** Intersects with this set */
	Set<E> intersect(Set<E> set);

	/** Subtracts this set */
	Set<E> subtract(Set<E> set);

	/** Fast insert */
	E fastInsert(E element);

	/** Fast union */
	Set<E> fastUnion(Set<E> b);

	/** Returns the position */
	Position<Set<E>> getPosition();

	/** Sets the position */
	void setPosition(Position<Set<E>> position);
}