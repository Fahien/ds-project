package me.fahien.ds.set;

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
}