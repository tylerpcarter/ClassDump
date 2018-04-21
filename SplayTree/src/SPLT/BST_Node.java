package SPLT;

public class BST_Node {
	  String data;
	  BST_Node left;
	  BST_Node right;
	  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
	  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
	            //I personally use it to indicate to my SPLT insert whether or not we increment size.
	  
	  BST_Node(String data){ 
	    this.data=data;
	    this.justMade=true;
	  }
	  
	  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
	    this.data=data;
	    this.left=left;
	    this.right=right;
	    this.par=par;
	    this.justMade=true;
	  }

	  // --- used for testing  ----------------------------------------------
	  //
	  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

	  public String getData(){ return data; }
	  public BST_Node getLeft(){ return left; }
	  public BST_Node getRight(){ return right; }

	  // --- end used for testing -------------------------------------------

	  
	  // --- Some example methods that could be helpful ------------------------------------------
	  //
	  // add the meat of correct implementation logic to them if you wish

	  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
	  // make them take more or different parameters
	  // have them return different types
	  //
	  // you may use recursive or iterative implementations


	  
	  public BST_Node containsNode(String s){
		  if(data.equals(s)) {
			  
			  splay(this); 
			  
			  return this;
		  }
			
		  if(data.compareTo(s)>0){
				if(left==null) {
					
					splay(this); 
					
					
					return null; 
				}
				return left.containsNode(s);
			}
			if(data.compareTo(s)<0){
				if(right==null) {
					
						splay(this); 
				
					
					return null;
				}
				return right.containsNode(s);
			}
			return null; //shouldn't hit
		}
	  //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), 
	  //you are however free to do what you wish.
	  
	  
	  
	  
	  
	  public BST_Node insertNode(String s){ 
		  if (this.data.compareTo(s) > 0) {
			  if(left == null) {
				  	left = new BST_Node(s);
				  	this.left.par = this; 
				
				  	BST_Node temp = left; 
				  	
				  	splay(temp); 
				  	
				  	return this;  //return new BST_Node(s);
			  }
			  else {
				  return left.insertNode(s);
			  }
		  }
		  else if (this.data.compareTo(s) < 0) {
			  if (right == null) {
				  right = new BST_Node(s);
				  this.right.par = this; 
				  
				  BST_Node temp = right; 
				  	
				  	splay(temp); 
				  
				  return this;  //new BST_Node(s)
			  }
			  else {
				  return right.insertNode(s); 
			  }
		  }
		 
		 splay(this);  
		  
		  return null;  //handle the null later --> its a duplicate 
		  
		  } //Really same logic as above note
	  
	  
	  
	  
	 /* public void insertNode(BST_Node z){ 
		 
	      splay(z);
		  }  */
	  
	  public boolean removeNode(String s){ 
		  if(data==null)return false;
			if(data.equals(s)){
				if(left!=null){
					data=left.findMax().data;
					left.removeNode(data);
					if(left.data==null)left=null;
				}
				else if(right!=null){
					data=right.findMin().data;
					right.removeNode(data);
					if(right.data==null)right=null;
				}
				else data=null;
				return true;
			}
			else if(data.compareTo(s)>0){
				if(left==null)return false;
				if(!left.removeNode(s))return false;
				if(left.data==null)left=null;
				return true;
			}
			else if(data.compareTo(s)<0){
				if(right==null)return false;
				if(!right.removeNode(s))return false;
				if(right.data==null)right=null;
				return true;
			}
			return false; 
		  } 
	  //I personal do not use the removeNode internal method in my impl since it is rather
	  //easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like
	  //we do in BST
	  
	  public BST_Node findMin(){ 
		  if(left!=null)return left.findMin();
		  
		  splay(this); 
		  
			return this;
		  } 
	  public BST_Node findMax(){ 
		  if(right!=null)return right.findMax();
		  
		  	
		  	
		  	splay(this); 	//Not working 
		  
		  
			return this;
		  }
	  
	  public int getHeight(){ 
		  int l=0;
			int r=0;
			if(left!=null)l+=left.getHeight()+1;
			if(right!=null)r+=right.getHeight()+1;
			return Integer.max(l, r);
		  }

	  
	  public void splay(BST_Node current) { 
		 
		 while(current.par != null) {		//keep rotating until the parent is null
			 									//probably put this elsewhere...
		  BST_Node parent = current.par; 
		  BST_Node grandParent = parent.par; 
		  
		  if (grandParent == null) { 			//if there is no grandparent 
			  if (parent.left == current) { 		//and the left of parent is the inserted node 
				  left_Zig(current);			//then left_zig (rotate right)
			  }
			  else { 						//if the inserted node is on the right of parent 
				  right_Zig(current); 			//then right_zig (rotate left) 
			  }
		  }
		  else { 								//if there IS a grandparent
			  if (grandParent.left == parent		//and the chain of inheritance is a straight line to 
					  	&& parent.left == current) { 	//the left, then leftzig_zig (rotate right) 
				  left_ZigZig(current); 
			  }
			  else if (grandParent.right == parent		//but if the chain is at the right 
					  	&& parent.right == current) { 			
				  right_ZigZig(current); 				//then right_zigzig (rotate left) 
			  }
			  
			  else if (grandParent.left == parent		//if the parent is left of grandparent
					  	&& parent.right == current) {		//and current is right of parent 
				  	left_rightZigZag(current); 				//then left_right zig_zag -- rotate right 
			  }	
			  else if (grandParent.right == parent 		//if the parent is right of grandparent 
					  	&& parent.left == current) { 	//and the current is left of grandparent 
				  right_leftZigZag(current); 			//then right_left zig_zag -- rotate left 
			  }
		  }

		 
		 }///
	  }
	//----------------------------------
	  //Assuming there is no grandparent 
	//re-arranging pointers, but is it working? 
	  
	  private void left_Zig (BST_Node current) { //rotate right 
		  BST_Node parent = current.par;  //reference to original parent
		  
		  BST_Node grandParent = parent.par; 
		  
		  parent.left = current.right;		//original parent's left is now current's original right 
		  if (current.right != null) { 			//if the original's right is not null
			  current.right.par = parent; 			//then you will assign its parent to be the parent node
		  }
		  parent.par = current ;	//but if it is null, then you can't assign a null a parent reference 
		  current.right = parent; 		//the parent's parent is now current, the right of current is now
		  									// the parent 
		  current.par = grandParent; 	//we lastly set the current's parent to be null, the splay is done
	 
	  
		  if (grandParent !=null && grandParent.left == parent) { 
				grandParent.left = current; 
			}
			else if (grandParent !=null && grandParent.right == parent)  {
				grandParent.right = current; 
			}
	  }
	  
	  
	 private void right_Zig (BST_Node current) { //rotate left 	//same logic as above, vice versa 
		  BST_Node parent = current.par; 
		  
		  BST_Node grandParent = parent.par;
			  
		  parent.right = current.left; 
		  if (current.left != null) {
			  current.left.par = parent; 
		  }
		  parent.par = current; 
		  current.left = parent; 
		 
		  
		 current.par = grandParent; 
		 
		if (grandParent !=null && grandParent.left == parent) { 
			grandParent.left = current; 
		}
		else if (grandParent !=null && grandParent.right == parent)  {
			grandParent.right = current; 
		}
		  
		 
	  }
	//-----------------------------

	 	//rotate right -- IS THE ROOT VALUE CHANGED AS WELL? 
	  private void left_rightZigZag (BST_Node current) { //current is the last node of the chain 
		  
		  right_Zig(current);
		  left_Zig(current); 
		  

		 
	  }
	  
	  //rotate left 
	  private void right_leftZigZag (BST_Node current) {	//same logic as above, vice versa 
		  
		  left_Zig(current); //right rotation 
		  right_Zig(current); 	//left rotation 
		  
	  }
	  
	  //POSSIBLE ISSUE, WILL THE PARENT OF GRANDPARENT MAKE A NULL POINTER EXCEPTION WHEN ASSIGNNING?	
	  private void left_ZigZig (BST_Node current) { //current is the last node of the chain
		  
		  BST_Node parent = current.par; 
		  
		  left_Zig(parent); 
		  left_Zig(current); 
		  
	  }
	  
	  //POSSIBLE ISSUE, WILL THE PARENT OF GRANDPARENT MAKE A NULL POINTER EXCEPTION WHEN ASSIGNNING?	
	  private void right_ZigZig (BST_Node current) {	//same logic as above, vice versa 
		  
		  BST_Node parent = current.par; 
		  
		  right_Zig(parent); 
		  right_Zig(current); 
		  
	  }
	  

	  
	  
	  
	//you could have this return or take in whatever you want..so long as it will do the job internally.
	//As a caller of SPLT functions, I should really have no idea if you are "splaying or not"

	                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
	                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
	  
	  
	  

	  // --- end example methods --------------------------------------

	  
	  

	  // --------------------------------------------------------------------
	  // you may add any other methods you want to get the job done
	  // --------------------------------------------------------------------
	  
	  
	}
