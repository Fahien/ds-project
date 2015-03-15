package me.fahien.ds.priorityqueue;

import java.util.Comparator;

import me.fahien.ds.completebinarytree.ArrayListCompleteBinaryTree;
import me.fahien.ds.completebinarytree.CompleteBinaryTree;
import me.fahien.ds.exception.EmptyPriorityQueueException;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.exception.InvalidKeyException;
import me.fahien.ds.util.comparator.DefaultComparator;
import me.fahien.ds.util.composition.IEntry;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.Position;

public class HeapPriorityQueue<Key, Value> implements PriorityQueue<Key, Value> {
	protected CompleteBinaryTree<IEntry<Key, Value>> heap;
	protected Comparator<Key> comparator;

	public HeapPriorityQueue () {
		heap = new ArrayListCompleteBinaryTree<IEntry<Key, Value>>();
		comparator = new DefaultComparator<Key>();
	}

	public HeapPriorityQueue (Comparator<Key> comparator) {
		heap = new ArrayListCompleteBinaryTree<IEntry<Key, Value>>();
		this.comparator = comparator;
	}

	public HeapPriorityQueue (Key keys[], Value values[], Comparator<Key> comparator) {
		heap = new ArrayListCompleteBinaryTree<IEntry<Key, Value>>();
		this.comparator = comparator;
		for (int i = 0; i < keys.length || i < values.length; i++) {
			checkKey(keys[i]);
			IEntry<Key, Value> entry = new Entry<Key, Value>(keys[i], values[i]);
			upHeap(heap.add(entry));
		}
	}

	protected void checkKey (Key key) throws InvalidKeyException {
		try {
			comparator.compare(key, key);
		} catch (Exception e) {
			throw new InvalidKeyException("The key is not valid");
		}
	}

	protected void downHeap (Position<IEntry<Key, Value>> root) {
		while(heap.isInternal(root)) {
			Position<IEntry<Key, Value>> smallestChild;
			if(!heap.hasRight(root)) {
				smallestChild = heap.getLeft(root);
			}
			else if (comparator.compare(heap.getLeft(root).getElement().getKey(), heap.getRight(root).getElement().getKey()) <= 0) {
				smallestChild = heap.getLeft(root);
			} else {
				smallestChild = heap.getRight(root);
			}
			if (comparator.compare(smallestChild.getElement().getKey(), root.getElement().getKey()) < 0) {
				swap (root, smallestChild);
				root = smallestChild;
			} else { break; }
		}
	}

	protected void upHeap (Position<IEntry<Key, Value>> position) {
		Position<IEntry<Key, Value>> parent;
		while(!heap.isRoot(position)) {
			parent = heap.parentOf(position);
			if (comparator.compare(parent.getElement().getKey(), position.getElement().getKey()) <= 0)
				break;
			swap (parent, position);
			position = parent;
		}
	}

	protected void swap (Position<IEntry<Key, Value>> positionX, Position<IEntry<Key, Value>> positionY) {
		IEntry<Key, Value> temp = positionX.getElement();
		heap.replace(positionX, positionY.getElement());
		heap.replace(positionY, temp);
	}

	@Override
	/** @return The size of the heap */
	public int size () {
		return heap.size();
	}

	@Override
	/** @return Wheter the heap is empty */
	public boolean isEmpty () {
		return heap.size() == 0;
	}

	@Override
	public IEntry<Key, Value> min () throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		try {
			return heap.getRoot().getElement();
		} catch (EmptyTreeException e) {
			throw new EmptyPriorityQueueException("The heap is empty");
		}
	}

	@Override
	public IEntry<Key, Value> insert (Key key, Value value) throws InvalidKeyException {
		checkKey(key);
		IEntry<Key, Value> entry = new Entry<Key, Value>(key, value);
		upHeap(heap.add(entry));
		return entry;
	}

	@Override
	public IEntry<Key, Value> removeMin () throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("The priority queue is empty");
		IEntry<Key, Value> min;
		try {
			min = heap.getRoot().getElement();
			if (size() == 1)
				heap.remove();
			else {
				heap.replace(heap.getRoot(), heap.remove());
				downHeap(heap.getRoot());
			}
		} catch (EmptyTreeException e) {
			throw new EmptyPriorityQueueException("The heap is empty");
		}
		return min;
	}
}