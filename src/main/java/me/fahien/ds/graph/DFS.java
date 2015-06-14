package me.fahien.ds.graph;

import java.util.Set;

import me.fahien.ds.map.Map;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Depth First Search is a traversal algorithm that is
 * useful for testing a number of properties of graphs,
 * including whether there is a path from one vertex to
 * another and whether or not a graph is connected.
 * @author Fahien */
public class DFS {
	/** Performs depth-first search of Graph g starting at Vertex u. */
	public static <V,E> void depthFirstSearch(Graph<V,E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
		known.add(u); // u has been discovered
		// for every incident edge in u
		for (Edge<E> e : g.incidentEdges(u)) { 
			Vertex<V> v = g.opposite(u, e);
			if (!known.contains(v)) {
				forest.put(v, e); // e is the tree edge that discovered v
				depthFirstSearch(g, v, known, forest); // recursively explore from v
			}
		}
	}
}