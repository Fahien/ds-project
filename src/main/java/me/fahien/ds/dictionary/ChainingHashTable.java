package me.fahien.ds.dictionary;

import me.fahien.ds.dictionary.Dictionary;
import me.fahien.ds.dictionary.LogFile;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.composition.Entry;

/** ChainingHashTable
 * @author Fahien */
public class ChainingHashTable<Key, Value> extends AbstractHashMap<Key, Value> {
	private static final float LOAD_FACTOR = 0.9f;

	private Dictionary<Key, Value>[] table;

	public ChainingHashTable() {
		super();
		setLoadFactor(LOAD_FACTOR);
	}

	public ChainingHashTable(int capacity) {
		super(capacity);
		setLoadFactor(LOAD_FACTOR);
	}

	public ChainingHashTable(int capacity, int prime) {
		super(capacity, prime);
		setLoadFactor(LOAD_FACTOR);
	}

	/** Creates an empty table having length equal to current capacity */
	@SuppressWarnings("unchecked")
	@Override protected void createTable() {
		//noinspection unchecked
		table = (Dictionary<Key, Value>[]) new Dictionary[capacity];
	}

	/** Returns value associated with this key
	 * in bucket with this hash, or else null */
	@Override protected Entry<Key, Value> bucketFind(int hash, Key key) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			return null;
		}
		Entry<Key, Value> entry = bucket.find(key);
		return entry;
	}

	/** Associates this key with this value
	 * in bucket with this hash and returns old value */
	@Override protected Entry<Key, Value> bucketInsert(int hash, Key key, Value value) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			bucket = table[hash] = new LogFile<>();
		}
		Entry<Key, Value> entryTemp = bucket.find(key);
		if (entryTemp != null) {
			bucketRemove(hash, key);
		}
		int oldSize = bucket.size();
		bucket.insert(key, value);
		size += bucket.size() - oldSize;
		return entryTemp;
	}

	/** Removes entry having this key from bucket with this hash */
	@Override protected Entry<Key, Value> bucketRemove(int hash, Key key) {
		Dictionary<Key, Value> bucket = table[hash];
		if (bucket == null) {
			return null;
		}
		int oldSize = bucket.size();
		Entry<Key, Value> entryTemp = bucket.find(key);
		if (entryTemp != null) {
			bucket.remove(entryTemp);
		}
		size -= oldSize - bucket.size();
		return entryTemp;
	}

	@Override public Iterable<Entry<Key, Value>> entries() {
		PositionList<Entry<Key, Value>> buffer = new NodePositionList<>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				for (Entry<Key, Value> entry : table[i].entries()) {
					buffer.addLast(entry);
				}
			}
		}
		return buffer;
	}

	@Override public Iterable<Entry<Key, Value>> findAll(Key key) throws InvalidKeyException {
		PositionList<Entry<Key, Value>> list = new NodePositionList<>();
		for (Dictionary<Key, Value> bucket : table) {
			if (bucket != null) {
				Entry<Key, Value> entry = bucket.find(key);
				if (entry != null) {
					list.addLast(entry);
				}
			}
		}
		return list;
	}
}