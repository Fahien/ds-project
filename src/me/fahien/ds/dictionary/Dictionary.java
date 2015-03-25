package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.composition.IEntry;

public interface Dictionary<Key, Value> {
	public int size ();
	public boolean isEmpty ();
	public IEntry<Key, Value> find (Key key) throws InvalidKeyException;
	public Iterable<IEntry<Key, Value>> findAll (Key key) throws InvalidKeyException;
	public IEntry<Key, Value> insert (Key key, Value value) throws InvalidKeyException;
	public IEntry<Key, Value> remove (IEntry<Key, Value> entry) throws InvalidEntryException;
	public Iterable<IEntry<Key, Value>> getEntries ();
}