package me.fahien.ds.queue;

import me.fahien.ds.exception.EmptyQueueException;

public class ArrayQueue<E> implements Queue<E> {
	private static final int CAPACITY = 1024;

	private int capacity;
	private E queue[];
	private int front, rear;

	public ArrayQueue() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		queue = (E[]) new Object[capacity];
	}

	@Override public int size() {
		return (capacity - front + rear) % capacity;
	}

	@Override public boolean isEmpty() {
		return front == rear;
	}

	@Override public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return queue[front];
	}

	@Override public void enqueue(E element) {
		if (size() == capacity - 1) {
			@SuppressWarnings("unchecked")
			E temp[] = (E[]) new Object[capacity * 2];
			if (front < rear) {
				System.arraycopy(queue, 0, temp, 0, queue.length);
			} else {
				System.arraycopy(queue, 0, temp, 0, rear);
				System.arraycopy(queue, front, temp, front + capacity, queue.length - front);
				front += capacity;
			}
			queue = temp;
			capacity *= 2;
		}
		queue[rear] = element;
		rear = ++rear % capacity;
	}

	@Override public E dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		front = ++front % capacity;
		return queue[front - 1];
	}

	@Override public String toString() {
		String string = "[";
		if (!isEmpty()) {
			string += queue[front];
			if (front < rear) {
				for (int i = front + 1; i < rear; i++) {
					string += ", " + queue[i];
				}
			}
			else {
				for (int i = front + 1; i < capacity; i++) {
					string += ", " + queue[i];
				}
				for (int i = 0; i < rear; i++) {
					string += ", " + queue[i];
				}
			}
		}
		string += "]";
		return string;
	}
}