package me.fahien.ds.graph;

import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Directed Graph ADT
 * @author Fahien */
public interface DirectedGraph<V, E> extends Graph<V, E> {
	boolean isDirected(Edge<E> edgePosition);
	Edge<E> insertDirectedEdge(Vertex<V> u, Vertex<V> v, V vertex);
}
