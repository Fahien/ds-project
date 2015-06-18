package me.fahien.ds.graph;

import me.fahien.ds.map.HashTableMap;
import me.fahien.ds.map.Map;
import me.fahien.ds.nodelist.NodePositionList;
import me.fahien.ds.nodelist.PositionList;
import me.fahien.ds.partition.ListPartition;
import me.fahien.ds.partition.Partition;
import me.fahien.ds.priorityqueue.HeapPriorityQueue;
import me.fahien.ds.priorityqueue.PriorityQueue;
import me.fahien.ds.set.Set;
import me.fahien.ds.util.composition.Entry;
import me.fahien.ds.util.position.graph.Edge;
import me.fahien.ds.util.position.graph.Vertex;

/** Kruskal's algorithm constructs a minimum spanning tree,
 * maintaining many smaller trees in a forest, repeatedly merging
 * pairs of trees until a single tree spans the graph
 * @author Fahien */
public class Kruskal {
	/** Computes a minimum spanning tree of graph using Kruskal's algorithm */
	public static <V> PositionList<Edge<Integer>> execute(Graph<V, Integer> graph) {
		// tree is where we will store result as it is computed
		PositionList<Edge<Integer>> tree = new NodePositionList<>();
		// pq entries are edges of graph, with weights as keys
		PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();
		// union-find forest of components of the graph
		Partition<Vertex<V>> forest = new ListPartition<>();
		// map each vertex to the forest position
		Map<Vertex<V>, Set<Vertex<V>>> positions = new HashTableMap<>();
		
		for (Vertex<V> v : graph.vertices()) {
			positions.put(v, forest.makeSet(v));
		}
		for (Edge<Integer> e : graph.edges()) {
			pq.insert(e.getElement(), e);
		}
		int size = graph.numVertices();
		// while tree not spanning and unprocessed edges remain
		while (tree.size() != size - 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<V>[] endpoints = graph.endVertices(edge);
			Set<Vertex<V>> a = forest.find(endpoints[0]);
			Set<Vertex<V>> b = forest.find(endpoints[1]);
			if (a != b) {
				tree.addLast(edge);
				forest.union(a,  b);	// Does not work
			}
		}
		return tree;
	}
}
