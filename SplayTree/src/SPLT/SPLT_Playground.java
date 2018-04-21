package SPLT;

public class SPLT_Playground {
	  public static void main(String[] args){
	    genTest();
	  }
	  
	  public static void genTest(){		//BST works perfectly 
	    SPLT tree= new SPLT();
	  
	  tree.insert("C");
	  tree.insert("A");
	  tree.insert("D");
	  tree.contains("C");
	  tree.insert("E");
	  tree.remove("A");
	  
	  tree.insert("V");
	  tree.insert("0");
	  tree.contains("E");
	  tree.insert("T");
	  tree.remove("L");
	  
	  tree.findMin();
	  tree.insert("G");
	  tree.insert("P");
	  tree.remove("E");
	  tree.findMax();
	  tree.contains("M");
	  tree.contains("V");
	  //tree.remove("V");
	  
	 System.out.println("size is "+tree.size());
	 printLevelOrder(tree);
	System.out.println();
	 
	 System.out.println("Is tree empty? " + tree.empty());
	 System.out.println();
	 System.out.println("What is the height? " + tree.height());
	 System.out.println();
	 System.out.println("What is the size? " + tree.size());
	 System.out.println();
	 System.out.println("What is the root? " + tree.getRoot().data);
	 
	 
	 System.out.println();
	 
	 System.out.println("Does the tree contain A? " + tree.contains("A"));
	 System.out.println("What is the max of the tree? " + tree.findMax());
	 System.out.println("What is the min of the tree? " + tree.findMin());

	 
	//System.out.println("size is "+tree.size());
	//printLevelOrder(tree);
	//System.out.println();
	    
	    
	  }

	    static void printLevelOrder(SPLT tree){ 
	    //will print your current tree in Level-Order...
	    //https://en.wikipedia.org/wiki/Tree_traversal
	      int h=tree.getRoot().getHeight();
	      for(int i=0;i<=h;i++){
	        System.out.print("Level "+i+":");
	        printGivenLevel(tree.getRoot(), i);
	        System.out.println();
	      }
	      
	    }
	    static void printGivenLevel(BST_Node root,int level){
	      if(root==null)return;
	      if(level==0)System.out.print(root.data+" ");
	      else if(level>0){
	        printGivenLevel(root.left,level-1);
	        printGivenLevel(root.right,level-1);
	      }
	    }
	  
	}
