package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface Map<Key, Value> {
	int size();
	boolean isEmpty();
	Value put(Key key, Value value) throws InvalidKeyException;
	Value get(Key key) throws InvalidKeyException;
	Value remove(Key key) throws InvalidKeyException;
	Iterable<Key> keys();
	Iterable<Value> values();
	Iterable<IEntry<Key, Value>> entries();
}