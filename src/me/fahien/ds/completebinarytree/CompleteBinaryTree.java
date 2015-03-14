package me.fahien.ds.completebinarytree;

import me.fahien.ds.binarytree.BinaryTree;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.position.Position;

public interface CompleteBinaryTree<E> extends BinaryTree<E> {
	public Position<E> add (E element);
	public E remove () throws EmptyTreeException;
}
