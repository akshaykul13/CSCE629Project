package graph;

import java.util.ArrayList;

public class DirectedGraphAPI {

	private final int V;
	private int E;	
	private ArrayList<Edge>[] adj;
		
	public DirectedGraphAPI(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Edge>[])new ArrayList[V];
		for(int v=0; v<V; v++){
			adj[v] = new ArrayList<Edge>();
		}
	}
	
	public void addEdge(int v, int w, int weight){
		Edge edge = new Edge(v, w, weight);
		adj[v].add(edge);
		E = E + 1;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
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
	
	public static int degree(DirectedGraphAPI G, int v) 
	{ 
		int degree = 0; 
		for (Edge w : G.adj(v)) degree++; 
		return degree; 
	}
	
}
