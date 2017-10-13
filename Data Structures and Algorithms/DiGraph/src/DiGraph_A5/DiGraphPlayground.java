package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
    exTest();
    }
  
    public static void exTest(){
    	
      DiGraph d = new DiGraph();
          
   
      
      d.addNode(1,"f");
      d.addNode(2,"x");
      System.out.println(d.numNodes());
      d.addEdge(0,"f","x",0,null);
      System.out.println(d.numEdges());
     
     System.out.println("Node deleted " + d. delNode("x"));
     System.out.println(d.numNodes());
     System.out.println(d.numEdges());
      System.out.println(d.addNode(2,"x"));
      System.out.println( d.addEdge(0,"f","x",0,null));


      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
   //   System.out.println(d.topoSort());
  //printTOPO(d.topoSort());

      
    }
    public static void printTOPO(String[] toPrint){
      System.out.print("TOPO Sort: ");
      for (String string : toPrint) {
      System.out.print(string+" ");
    }
      System.out.println();
    }

}