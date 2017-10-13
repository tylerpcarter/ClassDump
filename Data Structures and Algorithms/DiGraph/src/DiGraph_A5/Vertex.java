package DiGraph_A5;

import java.util.HashSet;

public class Vertex {
	public long idNum; 
	public String label; 
	
	public HashSet<Edge> inEdges; 
	public HashSet<Edge> outEdges; 
	
	boolean visited = false; 

	
	public Vertex(long idNum, String label) {
		this.idNum = idNum; 
		this.label = label; 
		
		inEdges = new HashSet<Edge>(); 
		outEdges = new HashSet<Edge>();
	}
	

}