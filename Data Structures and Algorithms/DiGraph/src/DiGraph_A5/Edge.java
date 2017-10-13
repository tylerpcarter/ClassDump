package DiGraph_A5;

public class Edge {
	public long idNum; 
	public String sLabel; 
	public String dLabel; 
	public long weight; 
	public String eLabel; 
	
	public Vertex srcVert;
	public Vertex destVert; 
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum; 
		this.sLabel = sLabel; 
		this.dLabel = dLabel; 
		this.weight = weight; 
		this.eLabel = eLabel; 
	}
	
	
}