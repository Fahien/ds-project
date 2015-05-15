package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

/** This ADT stores a collection of key-value pairs
 * @author Fahien */
public interface Map<Key, Value> {
	/** Returns the number of entries in the map */
	int size();

	/* Returns a boolean indicating whether the map is empty */
	boolean isEmpty();

	/** If the map does not have an entry with key equal to this one,
	 * then adds a new entry to the map and returns null; else,
	 * replaces with this value the existing value of the entry with
	 * key equal to this one and returns the old value */
	Value put(Key key, Value value) throws InvalidKeyException;

	/** Returns the value associated with this key, if such an entry exists;
	 * otherwise returns null
	 * @throws InvalidKeyException */
	Value get(Key key) throws InvalidKeyException;

	/** Removes from the map the entry with key equal to this one, and
	 * returns its value; if the map has no such entry, then returns null
	 * @throws InvalidKeyException */
	Value remove(Key key) throws InvalidKeyException;

	/** Returns an iterable collection containing all the keys stored in the map */
	Iterable<Key> keys();

	/** Returns an iterable collection containing all the values of entries stored in the map */
	Iterable<Value> values();

	/** Returns an iterable collection containing all the key-value entries in the map */
	Iterable<Entry<Key, Value>> entries();
}