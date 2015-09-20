package dp;

public class FloydWarshall {
	public static final int INF = Integer.MAX_VALUE;
	public static int[][] FloydWarshallShortestPath(int[][] graph){
		int V = graph.length;
		int[][] shortestPathCostGraph = new int[V][V];
		System.arraycopy(graph, 0, shortestPathCostGraph, 0, V);
		for(int interMediateVertex = 0; interMediateVertex < V; interMediateVertex++){
			for (int src = 0; src < V; src++) {
				for (int dest = 0; dest < V; dest++) {
					if(shortestPathCostGraph[src][interMediateVertex] != INF && shortestPathCostGraph[interMediateVertex][dest] != INF){
						if(shortestPathCostGraph[src][interMediateVertex] + shortestPathCostGraph[interMediateVertex][dest] < shortestPathCostGraph[src][dest]){
							shortestPathCostGraph[src][dest] = shortestPathCostGraph[src][interMediateVertex] + shortestPathCostGraph[interMediateVertex][dest];
						}
					}
				}
			}
		}
		return shortestPathCostGraph;
	}
	public static void main(String[] args) {
		
	}

}
