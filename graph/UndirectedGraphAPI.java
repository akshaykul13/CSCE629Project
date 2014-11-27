package graph;

import java.util.ArrayList;

public class UndirectedGraphAPI {

	private final int V;
	private int E;	
	private ArrayList<Edge>[] adj;
	
	public UndirectedGraphAPI(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Edge>[])new ArrayList[V];
		for(int v=0; v<V; v++){
			adj[v] = new ArrayList<Edge>();
		}
	}
	
	public ArrayList<Edge>[] getAdj() {
		return adj;
	}

	public void setAdj(ArrayList<Edge>[] adj) {
		this.adj = adj;
	}

	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void setE(int E) {
		this.E = E;
	}		
	
	public void addEdge(int v, int w, int weight){
		Edge edge1 = new Edge(v, w, weight);
		adj[v].add(edge1);
		Edge edge2 = new Edge(w, v, weight);
		adj[w].add(edge2);
		E = E + 2;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public static int degree(UndirectedGraphAPI G, int v) 
	{ 
		int degree = 0; 
		for (Edge w : G.adj(v)) degree++; 
		return degree; 
	}
	
	public static int maxDegree(UndirectedGraphAPI G) 
	{
		int max = 0; 
	 	for (int v = 0; v < G.V(); v++){
	 		if (degree(G, v) > max)
	 			 max = degree(G, v);	
	 	}		 
	 	return max;
	}
	
	public static double averageDegree(UndirectedGraphAPI G) 
	{ 
		return 2.0 * G.E() / G.V(); 
	}
	
	public static int numberOfSelfLoops(UndirectedGraphAPI G) 
	{
		int count = 0;
		for (int v = 0; v < G.V(); v++)
			for (Edge edge : G.adj(v)) 
				if (edge.getV() == edge.getW()) count++; 
		return count/2; // each edge counted twice 
	}
}
