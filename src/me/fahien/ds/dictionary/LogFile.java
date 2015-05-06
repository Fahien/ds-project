package me.fahien.ds.dictionary;

import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.PQEntry;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.Position;

public class LogFile<Key, Value> implements Dictionary<Key, Value> {
	private PositionList<Entry<Key, Value>> entries;

	public LogFile () {
		entries = new NodePositionList<>();
	}

	protected void checkKey (Key key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException("Invalid key");
	}

	protected PQEntry<Key, Value> checkEntry (Entry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof PQEntry<?,?>))
			throw new InvalidEntryException("Invalid entry");
		return (PQEntry<Key, Value>) entry;
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
	public Entry<Key, Value> find (Key key) throws InvalidKeyException {
		checkKey(key);
		for (Entry<Key, Value> entry : entries) {
			if (entry.getKey().equals(key))
				return entry;
		}
		return null;
	}

	@Override
	public Iterable<Entry<Key, Value>> findAll (Key key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<Key, Value>> iterable = new NodePositionList<>();
		for (Entry<Key, Value> entry : entries) {
			if (entry.getKey().equals(key))
				iterable.addLast(entry);
		}
		return iterable;
	}

	@Override
	public Entry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		Entry<Key, Value> entry = new PQEntry<>(key, value);
		entries.addLast(entry);
		return entry;
	}

	@Override
	public Entry<Key, Value> remove (Entry<Key, Value> entry) throws InvalidEntryException {
		checkEntry(entry);
		for(Position<Entry<Key, Value>> position : entries.positions()) {
			if (entry.equals(position.getElement())) {
				return entries.remove(position);
			}
		}
		return null;
	}

	@Override
	public Iterable<Entry<Key, Value>> getEntries () {
		PositionList<Entry<Key, Value>> iterable = new NodePositionList<>();
		if (!entries.isEmpty()) {
			for (Position<Entry<Key, Value>> current = entries.first(); current != null; current = entries.next(current)) {
				iterable.addLast(current.getElement());
			}
		}
		return iterable;
	}
}