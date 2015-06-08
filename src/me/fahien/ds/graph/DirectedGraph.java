package me.fahien.ds.graph;

import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Directed Graph ADT
 * @author Fahien */
public interface DirectedGraph<V, E> extends Graph<V, E> {
	/** Tests whether the graph is directed */
	boolean isDirected(Edge<E> edgePosition);

	/** Creates and returns a new directed edge connecting these two vertices, storing this element */
	Edge<E> insertDirectedEdge(Vertex<V> u, Vertex<V> v, V vertex);
}