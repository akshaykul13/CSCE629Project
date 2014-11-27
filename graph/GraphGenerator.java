package graph;

import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {

	private static int NUMBER_OF_VERTICES = 5000;
	private static int UNDIRECTED_GRAPH_DEGREE = 6;
	
	public static UndirectedGraphAPI generateUndirectedGraph(int numberOfVertices) {
		UndirectedGraphAPI graph = new UndirectedGraphAPI(numberOfVertices);
		Random randomGenerator = new Random();
		for(int i=0; i<numberOfVertices; i++){
//			System.out.println("Vertex: " + i);
//			System.out.println("Degree left: " + (UNDIRECTED_GRAPH_DEGREE-UndirectedGraphAPI.degree(graph, i)));
			int degreeRemaining = UNDIRECTED_GRAPH_DEGREE-UndirectedGraphAPI.degree(graph, i);
			for(int j=0; j<degreeRemaining; j++){
				int count = 0, temp = 0;
				boolean flag = false;
				while(true){	
					count++;
					int vertex = -1;
					if(count < numberOfVertices){
						vertex = randomGenerator.nextInt(numberOfVertices);	
					}else{
						for(int k=0; k<numberOfVertices; k++){
							if(k != i && UndirectedGraphAPI.degree(graph, k) < UNDIRECTED_GRAPH_DEGREE && !(containsEdgeTo(i, k, graph.getAdj()[i]))){
								vertex = k;
								break;
							}
						}
					}
//					System.out.println("Vertex generated: " + vertex);
					if(vertex == -1){
						flag = true;
						break;						
					}
					if(UndirectedGraphAPI.degree(graph, vertex) < UNDIRECTED_GRAPH_DEGREE){
						if(!(containsEdgeTo(i, vertex, graph.getAdj()[i])) && i != vertex){
							int weight = randomGenerator.nextInt(1000) + 1;
							graph.addEdge(i, vertex, weight);
//							System.out.println("Added edge from " + i + " to " + vertex + " of weight " + weight);
							break;
						}	
					}								
				}	
				if(flag){
					continue;
				}
			}
		}
		System.out.println("INFO: Generated Undirected Sparse Graph");
		return graph;
	}
	
	private static boolean containsEdgeTo(int i, int vertex, ArrayList<Edge> arrayList) {
		for(Edge edge : arrayList){
			int w = edge.getOtherVertex(i);
			if(vertex == w){
				return true;
			}
		}
		return false;
	}

	public static DirectedGraphAPI generateDirectedGraph(int numberOfVertices){
		DirectedGraphAPI graph = new DirectedGraphAPI(numberOfVertices);
		Random randomGenerator = new Random();
		for(int i=0; i<numberOfVertices; i++){
			System.out.println("Vertex: " + i);
			for(int j=0; j<1000; j++){
				while(true){
					int vertex = randomGenerator.nextInt(numberOfVertices);
					if(!(graph.getAdj()[i].contains(vertex)) && i != vertex){
						int weight = randomGenerator.nextInt(1000) + 1;
						graph.addEdge(i, vertex, weight);
						System.out.println("Added edge from " + i + " to " + vertex + " of weight " + weight);
						break;
					}	
				}				
			}
		}
		return graph;
	}
		
	public static void main(String[] args) {
		UndirectedGraphAPI graph = generateUndirectedGraph(NUMBER_OF_VERTICES);
		boolean flag = true;
		for(int i=0; i<NUMBER_OF_VERTICES; i++){
			if(graph.degree(graph, i) != UNDIRECTED_GRAPH_DEGREE){
				System.out.println("Vertex " + i + " does has degree " + graph.degree(graph, i));
				flag = false;
			}
		}
		if(flag)
			System.out.println("Valid graph");
	}
	
//	public static void main(String[] args) {
//		DirectedGraphAPI graph = generateDirectedGraph(NUMBER_OF_VERTICES);
//		boolean flag = true;
//		for(int i=0; i<NUMBER_OF_VERTICES; i++){
//			if(graph.degree(graph, i) != 1000){
//				System.out.println("Vertex " + i + " does has degree " + graph.degree(graph, i));
//				flag = false;
//			}
//		}
//		if(flag)
//			System.out.println("Valid graph");
//	}
}
