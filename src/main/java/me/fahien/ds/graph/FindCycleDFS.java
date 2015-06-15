package me.fahien.ds.graph;

import java.util.HashSet;
import java.util.Set;

import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Template method that returns an ordered list of edges comprising a cycle from root 
 * @author Fahien */
public class FindCycleDFS extends DFS {
	public static <V, E> PositionList<Edge<E>> execute(Graph<V, E> graph, Vertex<V> root) {
		PositionList<Edge<E>> cycle = new NodePositionList<>();
		Set<Vertex<V>> known = new HashSet<>();
		known.add(root);
		PositionList<Vertex<V>> children = new NodePositionList<>();
		Map<Vertex<V>, Edge<E>> forest = new HashTableMap<>();
		for (Edge<E> edge : graph.incidentEdges(root)) {
			children.addLast(graph.opposite(root, edge));
		}
		while (!children.isEmpty()) {
			Vertex<V> firstChild = children.remove(children.first());
			for(Vertex<V> lastChild : children) {
				execute(graph, firstChild, known, forest);
				if (forest.get(lastChild) != null) {
					Vertex<V> current = lastChild;
					while (current != firstChild) {
						Edge<E> edge = forest.get(current);
						cycle.addFirst(edge);
						current = graph.opposite(current, edge);
					}
					for (Edge<E> edge : graph.incidentEdges(root)) {
						Vertex<V> opposite = graph.opposite(root, edge);
						if (opposite.equals(firstChild)) {
							cycle.addFirst(edge);
						} else if (opposite.equals(lastChild)) {
							cycle.addLast(edge);
						}
					}
					return cycle;
				}
			}
		}
		return cycle;
	}
}