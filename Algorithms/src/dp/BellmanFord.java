package dp;

import java.util.ArrayList;
import java.util.Arrays;

class Graph{
	int vertices;
	int edges;
	ArrayList<Integer[]> edgesMatrix;
	
	int[] shortestPathParentIndices;
	
	public Graph(int V, int E) {
		vertices = V;
		edges = E;
		edgesMatrix = new ArrayList<Integer[]>(E);
		shortestPathParentIndices = new int[vertices];
		Arrays.fill(shortestPathParentIndices, Integer.MIN_VALUE);
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
		graph.shortestPathParentIndices[source] = -1;
		for(int atMostEdge = 1; atMostEdge <= graph.vertices - 1; atMostEdge++){
			for (Integer[] edge : graph.edgesMatrix) {
				int src = edge[0], dest = edge[1], weight = edge[2];
				if(dists[src] != Integer.MAX_VALUE && dists[dest] > dists[src] + weight){
					dists[dest] = dists[src] + weight;
					graph.shortestPathParentIndices[dest] = src;
				}
			}
		}
		for (int dest = 0; dest < dists.length; dest++) {
			System.out.print(source+ " to " + dest + " : ");
			printPathsFromSource(graph, dest);
			System.out.println();
		}
		return dists;
	}
	
	private static void printPathsFromSource(Graph graph, int dest) {
		int parent = graph.shortestPathParentIndices[dest];
		if(parent != -1 && parent != Integer.MIN_VALUE){
			printPathsFromSource(graph, parent);
		}
		if(parent != Integer.MIN_VALUE){
			System.out.print(dest + " ");
		}else{
			System.out.print("No Path.");
		}
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
