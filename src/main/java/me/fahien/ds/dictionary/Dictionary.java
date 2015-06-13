package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

public interface Dictionary<Key, Value> {
	/** Returns the number of entries in the dictionary */
	int size();

	/** Returns a boolean indicating whether the dictionary is empty */
	boolean isEmpty();

	/** Returns the entry with key equal to this one, if such an entry exists */
	Entry<Key, Value> find(Key key) throws InvalidKeyException;

	/** Returns an iterable collection containing all the entries
	 * with key equal to this one */
	Iterable<Entry<Key, Value>> findAll(Key key) throws InvalidKeyException;

	/** Adds an entry (key, value) to the dictionary and returns it */
	Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException;

	/** Removes this entry; if the dictionary
	 * has no such entry, then returns null */
	Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException;

	/** Returns an iterable collection containing
	 * all the key-value entries in the dictionary */
	Iterable<Entry<Key, Value>> entries();
}