package me.fahien.ds.partition;

import me.fahien.ds.set.Set;

/** Partition
 * @author Fahien */
public interface Partition<E> {
	/** Returns the number of set the partition */
	int size();

	/** Tests whether the partition is empty */
	boolean isEmpty();

	/** Returns the set containing this element only */
	Set<E> makeSet(E element);

	/** Returns the union of A and B */
	Set<E> union(Set<E> a, Set<E> b);

	/** Returns the set containing this element */
	Set<E> find(E element);
}