package graph;

public class Edge implements Comparable<Edge>{
	
	private final int v;
	private final int w;
	private final int weight;
	
	public Edge(int v, int w, int weight) {
		if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
	    if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");	    
	    this.v = v;
	    this.w = w;
	    this.weight = weight;
	}	

	public int getV() {
		return v;
	}

	public int getW() {
		return w;
	}

	public int getWeight() {
		return weight;
	}
	
	public int getOneVertex(){
		return v;
	}
	
	public int getOtherVertex(int x) {
		if(x == v) return w;
		else if(x == w) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}

	@Override
	public int compareTo(Edge e) {
		if (this.getWeight() < e.getWeight()) return -1;
        else if (this.getWeight() > e.getWeight()) return 1;
        else return  0;
	}

	@Override
	public String toString() {
		return "Edge [v=" + v + ", w=" + w + ", weight=" + weight + "]";
	}
	
	public static void main(String[] args) {
		Edge e = new Edge(12, 23, 3);
		System.out.println(e);
	}

}
