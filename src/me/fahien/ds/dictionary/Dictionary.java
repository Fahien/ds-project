package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.Entry;

public interface Dictionary<Key, Value> {
	int size();
	boolean isEmpty();
	Entry<Key, Value> find(Key key) throws InvalidKeyException;
	Iterable<Entry<Key, Value>> findAll(Key key) throws InvalidKeyException;
	Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException;
	Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException;
	Iterable<Entry<Key, Value>> getEntries();
}