package me.fahien.ds.map.hashmap;

import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.composition.PQEntry;

/** ProbeHashMap
 * @author Fahien */
public class ProbeHashMap<Key, Value> extends AbstractHashMap<Key, Value> {
	/** A fixed array of entries */
	private PQEntry<Key, Value>[] table;

	/** Sentinel */
	private final PQEntry<Key, Value> DEFUNCT = new PQEntry<>(null, null);

	public ProbeHashMap() {
		super();
	}

	public ProbeHashMap(int capacity) {
		super(capacity);
	}

	public ProbeHashMap(int capacity, int prime) {
		super(capacity, prime);
	}

	/** Creates an empty table having length equal to current capacity */
	@Override protected void createTable() {
		//noinspection unchecked
		table = (PQEntry<Key, Value>[]) new PQEntry[capacity];
	}

	/** Returns true if location is either empty or "defunct" sentinel */
	private boolean isAvailable(int hash) {
		return table[hash] == null || table[hash] == DEFUNCT;
	}

	/** Returns index with this key or {@code -(index + 1)}
	 * such that this key could be added at this index */
	private int findSlot(int hash, Key key) {
		int available = -1;
		int i = hash;
		do {
			if (isAvailable(i)) {
				if (available == -1) {
					available = i;
				}
				if (table[i] == null) {
					break;
				}
			} else if (table[i].getKey().equals(key)) {
				return i;
			}
			i = (i + 1) % capacity;
		} while (i != hash);
		return -(available + 1);
	}

	/** Returns value associated with this key in bucket with this hash, or else null */
	@Override protected Value bucketGet(int hash, Key key) {
		int i = findSlot(hash, key);
		if (i < 0) {
			return null;
		}
		return table[i].getValue();
	}

	/** Associates this key with this value in bucket with this hash; returns old value */
	@Override protected Value bucketPut(int hash, Key key, Value value){
		int i = findSlot(hash, key);
		if (i >= 0) {
			Value temp = table[i].getValue();
			table[i].setValue(value);
			return temp;
		}
		table[-(i + 1)] = new PQEntry<>(key, value);
		size++;
		return null;
	}

	/** Removes entry having this key from bucket with this hash value */
	@Override protected Value bucketRemove(int hash, Key key) {
		int i = findSlot(hash, key);
		if (i < 0) {
			return null;
		}
		Value temp = table[i].getValue();
		table[i] = DEFUNCT;
		size--;
		return temp;
	}

	@Override public Iterable<Key> keys() {
		PositionList<Key> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (!isAvailable(i)) {
				buffer.addLast(table[i].getKey());
			}
		}
		return buffer;
	}

	@Override public Iterable<Value> values() {
		PositionList<Value> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (!isAvailable(i)) {
				buffer.addLast(table[i].getValue());
			}
		}
		return buffer;
	}

	@Override public Iterable<Entry<Key, Value>> entries() {
		PositionList<Entry<Key, Value>> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (!isAvailable(i)) {
				buffer.addLast(table[i]);
			}
		}
		return buffer;
	}
}