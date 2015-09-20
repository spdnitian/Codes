package graphalgos;

public class CountPathLengthK {
	static final int INF = Integer.MAX_VALUE;
	public static int countPathLengthK(int[][] graph, int source, int destination, int k) {
		int V = graph.length;
		int[][][] pathLenMatrix = new int[V][V][k + 1];
		for (int pathLength = 0; pathLength <= k; pathLength++) {
			for (int src = 0; src < V; src++) {
				for (int dest = 0; dest < V; dest++) {
					if((pathLength == 0 && src == dest) || (pathLength == 1 && graph[src][dest] != -1)){
						pathLenMatrix[src][dest][pathLength] = 1;
					}else if(pathLength > 1){
						int count = 0;
						for (int interMediateVertex = 0; interMediateVertex < V; interMediateVertex++) {
							if(graph[src][interMediateVertex] != -1){
								count += pathLenMatrix[interMediateVertex][dest][pathLength - 1];
							}
						}
						pathLenMatrix[src][dest][pathLength] = count;
					}
				}
			}
		}
		return pathLenMatrix[source][destination][k];
	}

	public static int shortestPathOfLengthK(int[][] graph, int source, int destination, int k) {
		int V = graph.length;
		int[][][] shortestPathLengthMatrix = new int[V][V][k + 1];
		for (int pathLength = 0; pathLength <= k; pathLength++) {
			for (int src = 0; src < V; src++) {
				for (int dest = 0; dest < V; dest++) {
					shortestPathLengthMatrix[src][dest][pathLength] = INF;
					if(pathLength == 0 && src == dest){
						shortestPathLengthMatrix[src][dest][pathLength] = 0;
					}else if(pathLength == 1){
						shortestPathLengthMatrix[src][dest][pathLength] = graph[src][dest];
					}else if(pathLength > 1){
						int minCost = INF;
						for (int interMediateVertex = 0; interMediateVertex < V; interMediateVertex++) {
							if(graph[src][interMediateVertex] != INF && shortestPathLengthMatrix[interMediateVertex][dest][pathLength - 1] != INF && interMediateVertex != src && interMediateVertex != dest){
								if(graph[src][interMediateVertex] + shortestPathLengthMatrix[interMediateVertex][dest][pathLength - 1] < minCost){
									minCost = graph[src][interMediateVertex] + shortestPathLengthMatrix[interMediateVertex][dest][pathLength - 1];
								}
							}
						}
						shortestPathLengthMatrix[src][dest][pathLength] = minCost;
					}
				}
			}
		}
		return shortestPathLengthMatrix[source][destination][k];
	}

	public static void main(String[] args) {
		int[][] graph = new int[][]{ {-1, 1, 1, 1},
				{-1, -1, -1, 1},
				{-1, -1, -1, 1},
				{-1, -1, -1, -1}
		};
		int[][] graph2 = new int[][]{ {0, 10, 3, 2},
				{INF, 0, INF, 7},
				{INF, INF, 0, 6},
				{INF, INF, INF, 0}
		};
		System.out.println(countPathLengthK(graph, 0, 3, 2));
		System.out.println(shortestPathOfLengthK(graph2, 0, 3, 2));
	}
}
