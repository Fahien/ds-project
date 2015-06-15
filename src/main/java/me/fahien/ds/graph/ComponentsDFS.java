package me.fahien.ds.graph;

import java.util.HashSet;
import java.util.Set;

import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Template method that performs DFS for the entire graph
 * and returns the number of connected components 
 * @author Fahien */
public class ComponentsDFS extends DFS {
	public static <V,E> int execute(Graph<V, E> graph) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new HashTableMap<>();
		for (Vertex<V> vertex : graph.vertices()) {
			execute(graph, vertex, known, forest);
		}
		return graph.numVertices() - forest.size();
	}
}