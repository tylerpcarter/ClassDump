package DijkstraDigraph;

import java.util.HashSet;

public class Vertex {
	public long idNum; 
	public String label; 
	
	public HashSet<Edge> inEdges; 
	public HashSet<Edge> outEdges; 
	boolean visited = false;
	//Distance corresponding to the distance from a given vertex.
	//Initially set to infinity for the implementation of Djikstra's algorithm.
	public long distance = Integer.MAX_VALUE;
//Vertex objects contain an identification number, label, and hashsets for "in edges" (edges where the vertex is the destination vertex)
//and "out edges" (edges where the vertex is the source vertex). 
	public Vertex(long idNum, String label) {
		this.idNum = idNum; 
		this.label = label; 
		
		inEdges = new HashSet<Edge>(); 
		outEdges = new HashSet<Edge>();
	}
}