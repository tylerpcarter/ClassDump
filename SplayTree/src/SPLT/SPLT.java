package SPLT;

public class SPLT implements SPLT_Interface{
	  private BST_Node root;
	  private int size;
	  
	  public SPLT() {
	    this.size = 0;
	  } 
	  
	  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
	    return root;
	  }
  	  

	  @Override
	  public void insert(String s) {
	  	if (root == null) {		
	  		root = new BST_Node(s); 
	  		size++; 
	  	}
	  	
	  	
	  	
	  	if(root.insertNode(s) == null){  //if it's a duplicate (it return null), don't increment size 

	  		while (root.par != null) { 
	  			root = root.par; 
	  		}
	  	
	  	}
	  	else {  // current.right = null; 
	  		while (root.par != null) { 
	  			root = root.par; 
	  		}
	  		
	  		size++; 
	  	}

	  	
	  }   

	 
	@Override
	public void remove(String s) {
		if(root==null)return;
		if(this.contains(s) == true){
			
			BST_Node L = root.left; 
			BST_Node R = root.right; 
			
			
			if(L == null && R == null) {
				root = null; 
			}
			
			else if(L != null) {
				L.par = null;
								
				BST_Node newRoot = L.findMax();
				newRoot.right=R;
				if(R != null) {
				R.par = newRoot; 
					
				}

				this.root=newRoot;
			}
			else if(R != null) {
				
				R.par = null; 
			
				
				this.root = R; 
				
				
			}
			
			size--;
		}

		return;
	}

	@Override
	public String findMin() {
		String string; 
		
		if(root==null){
			return null;
		}
		else {
			string = root.findMin().data; 
			
			while (root.par != null) { 
	  			root = root.par; 
	  		}
		}
		return string;
	}

	@Override
	public String findMax() {
		String string; 
		
		if(root==null){
			return null;
		}
		else {
			string = root.findMax().data; 
			
			while (root.par != null) { 
	  			root = root.par; 
	  		}
		}
			
		return string;
	}

	@Override
	public boolean empty() {
		return size==0;
	}

	@Override
	public boolean contains(String s) {   
		boolean result  = false ;
		
		if(empty())return false;
		
		else {
			
			if (root.containsNode(s) == null) { 
				
				result = false; 
			}
			else { 																		//else if (root.containsNode(s).getData().equals(s)) {
			
				
				result = true;
			}
			
		}
		
		while (root.par != null) { 
				root = root.par; 
			}
		
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if(root==null)return -1;
		return root.getHeight();
	}  

	}
