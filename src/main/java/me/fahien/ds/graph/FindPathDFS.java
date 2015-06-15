package me.fahien.ds.graph;

import java.util.HashSet;
import java.util.Set;

import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Template method that returns an ordered list of edges
 * comprising the directed path from startVertex to endVertex
 * @author Fahien */
public class FindPathDFS extends DFS {
	public static <V, E> PositionList<Edge<E>> execute(
			Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new HashTableMap<>();
		execute(graph, startVertex, known, forest);
		PositionList<Edge<E>> path = new NodePositionList<>();
		if (forest.get(endVertex) != null) {
			Vertex<V> current = endVertex;
			while (current != startVertex) {
				Edge<E> edge = forest.get(current);
				path.addFirst(edge);
				current = graph.opposite(current, edge);
			}
		}
		return path;
	}
}