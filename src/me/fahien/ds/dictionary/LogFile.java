package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.position.Position;

public class LogFile<Key, Value> implements Dictionary<Key, Value> {
	private PositionList<IEntry<Key, Value>> entries;

	public LogFile () {
		entries = new NodePositionList<IEntry<Key, Value>>();
	}

	protected void checkKey (Key key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("Invalid key");
	}

	protected Entry<Key, Value> checkEntry (IEntry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof Entry<?,?>)) 
			throw new InvalidEntryException("Invalid entry");
		return (Entry<Key, Value>) entry;
	}

	@Override
	public int size () {
		return entries.size();
	}

	@Override
	public boolean isEmpty () {
		return entries.isEmpty();
	}

	@Override
	public IEntry<Key, Value> find (Key key) throws InvalidKeyException {
		checkKey(key);
		for (IEntry<Key, Value> entry : entries) {
			if (entry.getKey().equals(key))
				return entry;
		}
		return null;
	}

	@Override
	public Iterable<IEntry<Key, Value>> findAll (Key key) throws InvalidKeyException {
		checkKey(key);
		PositionList<IEntry<Key, Value>> iterable = new NodePositionList<IEntry<Key, Value>>();
		for (IEntry<Key, Value> entry : entries) {
			if (entry.getKey().equals(key))
				iterable.addLast(entry);
		}
		return iterable;
	}

	@Override
	public IEntry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		IEntry<Key, Value> entry = new Entry<Key, Value>(key, value);
		entries.addLast(entry);
		return entry;
	}

	@Override
	public IEntry<Key, Value> remove (IEntry<Key, Value> entry) throws InvalidEntryException {
		checkEntry(entry);
		for(Position<IEntry<Key, Value>> position : entries.getPositions()) {
			if (entry.equals(position.getElement())) {
				return entries.remove(position);
			}
		}
		return null;
	}

	@Override
	public Iterable<IEntry<Key, Value>> getEntries () {
		PositionList<IEntry<Key, Value>> iterable = new NodePositionList<IEntry<Key, Value>>();
		if (!entries.isEmpty()) {
			for (Position<IEntry<Key, Value>> current = entries.first(); current != null; current = entries.next(current)) {
				iterable.addLast(current.getElement());
			}
		}
		return iterable;
	}
}