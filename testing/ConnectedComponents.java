package testing;

import java.util.ArrayList;
import java.util.Random;

import graph.Edge;
import graph.GraphGenerator;
import graph.UndirectedGraphAPI;

public class ConnectedComponents {
	
	private static int WHITE = 0;
	private static int GRAY = 1;
	private static int BLACK = 2;
	private static int[] color;
	private static int[] idArray;
	private static int count = 0;

	public static void main(String[] args) {
		UndirectedGraphAPI graph = GraphGenerator.generateUndirectedGraph(5000);
		checkConnectedComponents(graph);
	}
	
	public static void checkConnectedComponents(UndirectedGraphAPI graph){		
		int CC = 0;
		color = new int[graph.V()];
		idArray = new int[graph.V()];
		
		for(int i=0; i<graph.V(); i++){
			color[i] = WHITE;			
		}
		for(int i=0; i<graph.V(); i++){
			if(color[i] == WHITE){
				CC++;
				count++;
				DFS(i, CC, graph);
			}
		}
		if(CC != 1){
			System.out.println("CC: " + CC);
			//Identify one vertex of each connected component
			int[] array = new int[CC];			
			for(int j=1; j<= CC; j++){
				for(int i=0; i< graph.V(); i++){				
					if(idArray[i] == j){
						array[j] = i;
						break;
					}
				}	
			}		
			//Connect the disjoint components
			Random randomGenerator = new Random();
			for(int k=1; k<array.length; k++){
				int weight = randomGenerator.nextInt(1000) + 1;
				graph.addEdge(array[0], array[k], weight);
			}
		}else{
			System.out.println("The entire graph is connected");
		}
	}

	private static void DFS(int v, int CC, UndirectedGraphAPI graph) {	
//		System.out.println("Count: " + count);
		color[v] = GRAY;
		idArray[v] = CC;
		ArrayList<Edge> list = graph.getAdj()[v];
		for(Edge edge : list){
			int w = edge.getOtherVertex(v);
			if(color[w] == WHITE){
				count++;
				DFS(w, CC, graph);
			}
		}
		color[v] = BLACK;
	}
}
