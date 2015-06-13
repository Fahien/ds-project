package me.fahien.ds.dictionary;

import java.util.Comparator;
import java.util.logging.Logger;

import me.fahien.ds.binarytree.LinkedBinaryTree;
import me.fahien.ds.dictionary.Dictionary;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidEntryException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.BSTEntry;
import me.fahien.ds.util.position.BTNode;
import me.fahien.ds.util.position.Position;

/** BinarySearchTree
 * @author Fahien */
public class BinarySearchTree<Key, Value> extends LinkedBinaryTree<Entry<Key, Value>> implements Dictionary<Key, Value> {
	private static final Logger logger = Logger.getLogger(BinarySearchTree.class.getName());

	private Comparator<Key> comparator;
	private int numEntries;

	public BinarySearchTree() {
		comparator = new DefaultComparator<>();
		addRoot(null);
	}

	public BinarySearchTree(Comparator<Key> comparator) {
		this.comparator = comparator;
		addRoot(null);
	}

	/** Validates the entry */
	protected BSTEntry<Key, Value> checkEntry(Entry<Key, Value> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof BSTEntry)) {
			throw new InvalidEntryException("Entry not valid");
		}
		return (BSTEntry<Key, Value>) entry;
	}

	/** Validates the key */
	protected void checkKey(Key key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Key is null");
		}
		try {
			comparator.compare(key, key);
		} catch (Exception e) {
			throw new InvalidKeyException("Key not valid");
		}
	}

	/** Returns the position in this position subtree having given key, or else the terminal leaf */
	protected Position<Entry<Key, Value>> treeSearch(Key key, Position<Entry<Key, Value>> position) {
		if (isExternal(position)) {
			return position;    // key not found; return the final leaf
		} else {
			Key tempKey = position.getElement().getKey();
			int compResult = comparator.compare(key, tempKey);
			if (compResult < 0) {
				return treeSearch(key, left(position));    // search left subtree
			} else if (compResult > 0) {
				return treeSearch(key, right(position)); // search right subtree
			}
			return position;    // key found, return its position
		}
	}

	/** Removes a leaf at this position and its parent */
	protected void removeExternal(Position<Entry<Key, Value>> position) throws InvalidPositionException {
		if (!isExternal(position)) {
			throw new InvalidPositionException("This position is not a leaf");
		}
		BTNode<Entry<Key, Value>> parent = (BTNode<Entry<Key, Value>>) parent(position);
		BTNode<Entry<Key, Value>> sibling = (BTNode<Entry<Key, Value>>) sibling(position);
		if (isRoot(parent)) {
			sibling.setParent(null);
			root = sibling;
		} else {
			BTNode<Entry<Key, Value>> grandparent = (BTNode<Entry<Key, Value>>) parent(parent);
			if (parent == left(grandparent)) {
				grandparent.setLeft(sibling);
			} else {
				grandparent.setRight(sibling);
			}
			sibling.setParent(grandparent);
		}
		size = size - 2;
	}

	@Override public int size() {
		return numEntries;
	}

	@Override public boolean isEmpty() {
		return size() == 0;
	}

	@Override public Position<Entry<Key, Value>> addRoot(Entry<Key, Value> entry) {
		Position<Entry<Key, Value>> root;
		try {
			root = super.addRoot(entry);
			if (entry != null) {
				numEntries = 1;
			}
		} catch (NonEmptyTreeException e) {
			return super.root;
		}
		return root;
	}

	@Override public Entry<Key, Value> find(Key key) throws InvalidKeyException {
		try {
			return treeSearch(key, root()).getElement();
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
			return null;
		}
	}

	@Override public Iterable<Entry<Key, Value>> findAll(Key key) throws InvalidKeyException {
		PositionList<Entry<Key, Value>> list = new NodePositionList<>();
		for (Entry<Key, Value> entry : entries()) {
			if (entry != null && entry.getKey() != null && comparator.compare(key, entry.getKey()) == 0) {
				list.addLast(entry);
			}
		}
		return list;
	}

	@Override public Entry<Key, Value> insert(Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		try {
			Position<Entry<Key, Value>> position = treeSearch(key, root());
			while (!isExternal(position)) {
				position = treeSearch(key, left(position));
			}
			return insertAtExternal(position, new BSTEntry<>(key, value, position));
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
			return insertAtExternal(root, new BSTEntry<>(key, value, root));
		}
	}

	protected Entry<Key, Value> insertAtExternal(Position<Entry<Key, Value>> position, Entry<Key, Value> entry) {
		expandExternal(position, null, null);
		replace(position, entry);
		numEntries++;
		return entry;
	}

	@Override public Entry<Key, Value> remove(Entry<Key, Value> entry) throws InvalidEntryException {
		BSTEntry<Key, Value> node = checkEntry(entry);
		Position<Entry<Key, Value>> position = node.getPosition();
		if (isExternal(left(position))) {
			position = left(position);
		} else if (isExternal(right(position))) {
			position = right(position);
		} else {
			Position<Entry<Key, Value>> temp = position;
			position = right(temp);
			do {
				position = left(position);
			} while (isInternal(position));
			replace(temp, parent(position).getElement());
		}
		removeExternal(position);
		size--;
		return node;
	}

	@Override public Iterable<Entry<Key, Value>> entries() {
		PositionList<Entry<Key, Value>> list = new NodePositionList<>();
		for (Position<Entry<Key, Value>> position : super.positions()) {
			if (position.getElement() != null) {
				list.addLast(position.getElement());
			}
		}
		return list;
	}
}