package me.fahien.ds.map;

import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface Map<Key, Value> {
	public int size ();
	public boolean isEmpty ();
	public Value put (Key key, Value value) throws InvalidKeyException;
	public Value get (Key key) throws InvalidKeyException;
	public Value remove (Key key) throws InvalidKeyException;
	public Iterable<Key> keys ();
	public Iterable<Value> values ();
	public Iterable<IEntry<Key, Value>> entries ();
}