package me.fahien.ds.map.hashmap;

import me.fahien.ds.dictionary.Dictionary;
import me.fahien.ds.dictionary.LogFile;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;
import me.fahien.ds.util.composition.Entry;

/** ChainHashMap
 * @author Fahien */
public class ChainHashMap<Key, Value> extends AbstractHashMap<Key, Value> {
	private Dictionary<Key, Value>[] table;

	public ChainHashMap() {
		super();
	}

	public ChainHashMap(int capacity) {
		super(capacity);
	}

	public ChainHashMap(int capacity, int prime) {
		super(capacity, prime);
	}

	/** Creates an empty table having length equal to current capacity */
	@Override protected void createTable() {
		//noinspection unchecked
		table = (Dictionary<Key, Value>[]) new Dictionary[capacity];
	}

	/** Returns value associated with this key
	 * in bucket with this hash, or else null */
	@Override protected Value bucketGet(int hash, Key key) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			return null;
		}
		Entry<Key, Value> entry = bucket.find(key);
		if (entry != null) {
			return entry.getValue();
		}
		return null;
	}

	/** Associates this key with this value
	 * in bucket with this hash and returns old value */
	@Override protected Value bucketPut(int hash, Key key, Value value) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			bucket = table[hash] = new LogFile<>();
		}
		Entry<Key, Value> entryTemp = bucket.find(key);
		Value temp = null;
		if (entryTemp != null) {
			temp = bucketRemove(hash, key);
		}
		int oldSize = bucket.size();
		bucket.insert(key, value);
		size += bucket.size() - oldSize;
		return temp;
	}

	/** Removes entry having this key from bucket with this hash */
	@Override protected Value bucketRemove(int hash, Key key) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			return null;
		}
		int oldSize = bucket.size();
		Entry<Key, Value> entryTemp = bucket.find(key);
		Value temp = null;
		if (entryTemp != null) {
			temp = bucket.remove(entryTemp).getValue();
		}
		size -= oldSize - bucket.size();
		return temp;
	}

	@Override public Iterable<Key> keys() {
		PositionList<Key> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				for (Entry<Key, Value> entry : table[i].getEntries()) {
					buffer.addLast(entry.getKey());
				}
			}
		}
		return buffer;
	}

	@Override public Iterable<Value> values() {
		PositionList<Value> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				for (Entry<Key, Value> entry : table[i].getEntries()) {
					buffer.addLast(entry.getValue());
				}
			}
		}
		return buffer;
	}

	@Override public Iterable<Entry<Key, Value>> entries() {
		PositionList<Entry<Key, Value>> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				for (Entry<Key, Value> entry : table[i].getEntries()) {
					buffer.addLast(entry);
				}
			}
		}
		return buffer;
	}
}