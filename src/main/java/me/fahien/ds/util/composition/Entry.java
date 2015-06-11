package me.fahien.ds.util.composition;

/** Interface for a key-value pair
 * Composition pattern
 * @author Fahien */
public interface Entry<Key, Value> {
	/** Returns the key stored in this entry */
	Key getKey();
	/** Returns the value stored in this entry */
	Value getValue();
}