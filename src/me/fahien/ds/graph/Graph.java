package me.fahien.ds.graph;

import me.fahien.ds.exception.InvalidPositionException;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Graph ADT
 * @author Fahien */
public interface Graph<V, E> {
	/** Returns the number of vertices of the graph */
	int numVertices();

	/* Returns the edges of the graph */
	int numEdges();

	/* Returns an iteration of all the vertices of the graph */
	Iterable<Vertex<V>> vertices();

	/* Returns an iteration of all the edges of the graph */
	Iterable<Edge<E>> edges();

	/* Replaces the element of this vertex */
	V replace(Vertex<V> vertex, V element) throws InvalidPositionException;

	/* Replaces the element of this edge */
	E replace(Edge<E> edge, E element) throws InvalidPositionException;

	/* Returns an iteration of all incoming edges to this vertex */
	Iterable<Edge<E>> incidentEdges(Vertex<V> vertex) throws InvalidPositionException;

	/* Returns an array containing the two endpoint vertices of this edge */
	Vertex<V>[] endVertices(Edge<E> edge) throws InvalidPositionException;

	/* For this edge incident to this vertex, returns the other vertex of the edge */
	Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) throws InvalidPositionException;

	/* Tests whether these two vertices are adjacent */
	boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidPositionException;

	/* Creates and returns a new vertex storing this element */
	Vertex<V> insertVertex(V element);

	/* Creates and returns a new edge connecting these two vertices, storing this element */
	Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws InvalidPositionException;

	/* Removes this vertex and all its incident edges from the graph */
	V removeVertex(Vertex<V> vertex) throws InvalidPositionException;

	/* Removes edge e from the graph */
	E removeEdge(Edge<E> edge) throws InvalidPositionException;
}
