package dp;

import java.util.ArrayList;
import java.util.Arrays;

class Graph{
	int vertices;
	int edges;
	ArrayList<Integer[]> edgesMatrix;
	
	public Graph(int V, int E) {
		vertices = V;
		edges = E;
		edgesMatrix = new ArrayList<Integer[]>(E);
	}
	
	public void addEdge(int src, int dest, int weight) {
		Integer[] pair = {src, dest, weight};
		edgesMatrix.add(pair);
	}
}

public class BellmanFord {
	public static int[] getShortestPathBellmanFord(Graph graph, int source){
		int[] dists = new int[graph.vertices];
		for(int i = 0; i < dists.length; i++){
			dists[i] = Integer.MAX_VALUE; // Infinity
		}
		dists[source] = 0;
		for(int i = 0; i <= dists.length - 1; i++){
			for (Integer[] edge : graph.edgesMatrix) {
				int src = edge[0], dest = edge[1], weight = edge[2];
				if(dists[src] != Integer.MAX_VALUE && dists[dest] > dists[src] + weight){
					dists[dest] = dists[src] + weight;
				}
			}
		}
		return dists;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(5, 8);
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 4, 2);
		graph.addEdge(3, 2, 5);
		graph.addEdge(3, 1, 1);
		graph.addEdge(4, 3, -3);
		int[] dests = getShortestPathBellmanFord(graph, 4);
		System.out.println(Arrays.toString(dests));
	}
}
