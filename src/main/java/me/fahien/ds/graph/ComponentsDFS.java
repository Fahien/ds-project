package me.fahien.ds.graph;

import java.util.HashSet;
import java.util.Set;

import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

public class ComponentsDFS extends DFS {
	/** Performs DFS for the entire graph and returns the number of connected components */
	public static <V,E> int connectedComponents(Graph<V, E> graph) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new HashTableMap<>();
		for (Vertex<V> vertex : graph.vertices()) {
			depthFirstSearch(graph, vertex, known, forest);
		}
		return graph.numVertices() - forest.size();
	}
}