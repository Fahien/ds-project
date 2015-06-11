package me.fahien.ds.tree.binarytree.completebinarytree;

import me.fahien.ds.tree.binarytree.BinaryTree;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.position.Position;

public interface CompleteBinaryTree<E> extends BinaryTree<E> {
	Position<E> add(E element);
	E remove() throws EmptyTreeException;
}
