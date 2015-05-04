package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface Dictionary<Key, Value> {
	int size();
	boolean isEmpty();
	IEntry<Key, Value> find(Key key) throws InvalidKeyException;
	Iterable<IEntry<Key, Value>> findAll(Key key) throws InvalidKeyException;
	IEntry<Key, Value> insert(Key key, Value value) throws InvalidKeyException;
	IEntry<Key, Value> remove(IEntry<Key, Value> entry) throws InvalidEntryException;
	Iterable<IEntry<Key, Value>> getEntries();
}