package trees;

// Binary search tree

// Important points:
// - This class is a raw TreeNode with no encapsulation. Other classes use it, such as AVLTree
// - The insert and remove methods do not automatically rebalance the tree; the task of balancing belongs to classes like AVLTree that use TreeNode
// - Methods that modify the tree, such as remove and insert and rotations, usually return a special TreeNode such as the root of the tree (since it might have changed)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;
import java.util.List;
import java.util.Arrays;



class TreeNode {

	private int data;             // data item (key)
	private TreeNode left;         // this node's left child
	private TreeNode right;        // this node's right child

	// The "external node" is a special node that acts as a sentinel.
	// (see chapter 11 of Goodrich book for illustrations of external nodes)
	private final static TreeNode externalnode = TreeNode.createExternalNode();

	/* Return a TreeNode that represents an "external node"*/
	public static TreeNode getExternalNode() {
			return externalnode;
	}

	/* Returns true iff the TreeNode is an external node */
	public static boolean isExternal(TreeNode t) {
	// the TreeNode.externalnode is the *only* TreeNode object that represents an external node, so we
	// use == to check if the node is an external node.
		return t == externalnode;
	}

	public boolean isEmpty() { return (this == externalnode); }
	
	/* Creates a new TreeNode with no children.
	*/
	public TreeNode(int id) {	  // constructor
	      data = id;
	      left = externalnode;
	      right = externalnode;
    }
    
	/* Returns the number of nodes in the tree */
	public int size() {
        if (this == externalnode) return 0;
        else return 1 + getLeft().size() + getRight().size();
    }
    
	/* Returns the height of the tree */
    public int getHeight() {  
    	if (this == externalnode) return 0;  		
		else return 1 + Math.max(getLeft().getHeight(), getRight().getHeight());
    }
    
	/*
	Perform a tri-node restructuring with a single right rotation
	and return the new root of the tri-node

	e.g.
    If the method is called on 
	    a
	   / \
	  b   e
	 / \
	c   d       
	then the tri-node is a,b,c, so the result is
	  b
	 / \
        c  a
	  / \
	 d   e
	and the method returns, b, the new root 
	*/
    public TreeNode rotateRight() {
        TreeNode pivot = externalnode; 
        
         if(this.left.left != externalnode &&this.left.right.right == externalnode){
            pivot = this.left; 
            TreeNode OldRoot = new TreeNode(this.data); 
            pivot.setRight(OldRoot); 

            return pivot; 
        }        
         else if(this.left.left == externalnode){
             
           pivot = this.left; 
           TreeNode oldRoot = new TreeNode(this.data);
           pivot.setRight(oldRoot); 
            return pivot;
        }
         else if(this.left.left != externalnode && this.left.right.right != externalnode){
             pivot = this.left.right; 
            
             TreeNode newRoot = new TreeNode(this.left.data);
             TreeNode oldRoot = new TreeNode(this.data);
             pivot.setLeft(this.left.left); 
             newRoot.setLeft(pivot);
             newRoot.setRight(oldRoot);
             newRoot.right.setRight(this.right);

             return newRoot; 
         }
        if(pivot != externalnode){
            return pivot;
        }
        else{
            return this; 
        }
            
    }

	/*
	Perform a tri-node restructuring with a single left rotation
	and return the new root of the tri-node

	e.g.
    If the method is called on 
	  b
	 / \
        c  a
	  / \
	 d   e
	then the tri-node is b,a,d so the result is
	    a
	   / \
	  b   e
	 / \
	c   d       
	and the method returns, a, the new root 
	*/
    public TreeNode rotateLeft() {
       TreeNode pivot = externalnode; 
        if(this.right != externalnode && this.left == externalnode){

            pivot = this.right; 
            TreeNode OldRoot = new TreeNode(this.data); 
            pivot.setLeft(OldRoot); 
            return pivot;        
        }
        else if(this.right.right == externalnode && this.left.left != externalnode){
           TreeNode oldRoot = new TreeNode(this.data); 
           pivot = this.right; 
           pivot.setLeft(oldRoot);
           return pivot;
       }
        else if(this.right.left != externalnode && this.left != externalnode){
            if(this.right.right.right != externalnode && this.left.left == externalnode){
                TreeNode oppositeSide = externalnode; 

                pivot = this.right; 
                oppositeSide = pivot.right; 
                TreeNode OldRoot = new TreeNode(this.data); 
                if(pivot.left != externalnode){
                    oppositeSide = pivot.left; 
                }
                pivot.setLeft(OldRoot); 
                if(this.left != externalnode){
                    pivot.left.setLeft(this.left);
                    pivot.left.setRight(oppositeSide);
                    if(pivot.right.right != externalnode && pivot.right != externalnode){
                    }    
                }
            }
            else{
             TreeNode newMax = new TreeNode(this.right.left.data); 
             TreeNode oldRoot = new TreeNode(this.data); 
             pivot = this.right; 
             pivot.setRight(oldRoot);
             pivot.right.right = pivot.left; 
             pivot.left = externalnode; 
             pivot.setLeft(this.left);
             return pivot; 
            }
         }

        if(pivot != externalnode){
            return pivot;
        }
        else{
            return this; 
        }
    }

	/*
	Perform a tri-node restructuring with a left then right rotation
	and return the new root of the tri-node
	*/
    public TreeNode doubleRotateLeftThenRight() {
		this.setLeft(this.getLeft().rotateLeft());
      return this.rotateRight();
    }

	/*
	Perform a tri-node restructuring with a right then left rotation
	and return the new root of the tri-node
	*/
    public TreeNode doubleRotateRightThenLeft() {
		this.setRight(this.getRight().rotateRight());
      return this.rotateLeft();
    }

	/* Return an iterator that produces the keys in [start, end) 
	in sorted order.

	e.g. If keysInRange(55,200) is called on 
	        100
	  50           200
	     60     110   203

	then the iterator will produce the sequence
	60, 100, 110
	*/
	Iterator<Integer> keysInRange(int start, int end) {
            ArrayList<Integer> buffer = new ArrayList(); 
            if(start < end){
                helper(this,start,end,buffer); 
                Iterator<Integer> inRange = buffer.iterator();
                return inRange;
            }
            else{
                throw new IllegalArgumentException("start needs to be smaller than end"); 
            }
	}
        
        public ArrayList help(int start, int end){
             TreeNode begin = this.findNode(start);

            ArrayList<Integer> x = new ArrayList(); 
   
            x = helper(this, start, end,x); 
            return x; 
        }
        
        private ArrayList helper(TreeNode n, int start, int end, ArrayList L){
            if(n != externalnode ){
                if(n.data < start){
                    
                    helper(n.right, start,end, L); 
                }
                else{
                    helper(n.left,start,end,L);
                    if(n.data <= end){
                        L.add(n.data ); 
                        helper(n.right, start,end, L);
                    }   
                }
                
            }


            return L; 
        }   
        
        private TreeNode findNode(int start){
            TreeNode currNode = this; 
            while(currNode.data != start && currNode != externalnode){
                if(start > currNode.getData()){
                    currNode = currNode.right;          
                }
                else{
                    currNode = currNode.left; 
                }
            }
            return currNode;  
        }
        

	public TreeNode find0(int key) {  // RECURSIVE find node with given key
		if (this == externalnode) return externalnode;
		if (	this.getData() == key)
			return this;
		else if (this.getData() < key) 
			return (this.getRight() == externalnode)? externalnode : this.getRight().find0(key);
		else if (this.getLeft() == externalnode)
			return externalnode;
		else
			return this.getLeft().find0(key);
	}  // end find0()

	/*
	Find the key in the BST and return the path to it as a stack of TreeNodes
	where the *top* of the stack contains
	  - the found TreeNode if the key is in the tree
	  - the external node if the key is not in the tree
	*/
	public Stack<TreeNode> findPath(int key) {
		Stack<TreeNode> path = new Stack<>();
		TreeNode current = this;    
		while (true) {
			path.push(current);
	        if (current == externalnode) { 
                    return path; 
                }  
	        if (current.getData() == key) {
                    return path; 
                }
	        else if (current.getData() < key)
	            current = current.getRight();
               
	        else current = current.getLeft();
		}
	}  // end find()

    public TreeNode findMin( ) {  // return the node with minimum key
        if (	getLeft() != externalnode) return getLeft().findMin();
        return this;
    }

    public TreeNode findMax( ) { // return the node with maximum key
        if (getRight() != externalnode) return getRight().findMax();
        return this;
    }
    
    public TreeNode predecessor( int x ) {
        TreeNode currNode = this; 
        TreeNode prev = this; 
        while(currNode.data != x && currNode != externalnode){
            
            if(x > currNode.getData()){
                prev = currNode; 
                currNode = currNode.right;          
            }
            else{
                prev = currNode; 
                currNode = currNode.left; 
            }
        }
            return prev;
    }
    
    public TreeNode successor( int x ) {
    	// Search for the successor node of x in the tree rooted by this node.
    	// The successor is the minimal node among all nodes in the tree whose iData is greater than x.
        TreeNode currNode = this; 
        TreeNode next = this; 
        while(currNode.data != x && currNode != externalnode){
            
            if(x > currNode.getData()){
                
                currNode = currNode.right;
                next = currNode.left; 
            }
            else{                
                currNode = currNode.left; 
                next = currNode; 
            }
        }
            return next;
    }
    
	// Remove the maximum node in this tree and return it.
	// Gives the removed node's left child to its parent.
    public TreeNode deleteMax( TreeNode parent ) { 
    	// delete the link from parent and return the node with maximum key
        if (	getRight() != externalnode) return getRight().deleteMax(this);
        if (	parent.getRight() == this) parent.setRight(getLeft());
        else 	parent.setLeft(getLeft());
        return this;
    }
    
    
     public TreeNode deleteMmin( TreeNode parent ) { 
    	// delete the link from parent and return the node with maximum key
        if (	getLeft() != externalnode) return getLeft().deleteMax(this);
        if (	parent.getLeft() == this) parent.setRight(getRight());
        else 	parent.setRight(getRight());
        return this;
    }
       
    
    public TreeNode insert(int k) {
    	if (this == externalnode) return new TreeNode(k);    	
 	    if (k < this.getData()) {
				this.setLeft(this.getLeft().insert(k));
 	    } else { // branch right
				this.setRight(this.getRight().insert(k));
 	    } 
 	    return this;
 	}  // end insert
    
	/*
	If a node in the tree has the data x, then removes that node from the tree.
	Returns the new root of this tree (i.e., the root changes if we are removing
	the root).
	*/
    public TreeNode removeA(int x) {
        if (this == externalnode ) return externalnode;   // Item not found; do nothing
        if (x < this.getData()) {
				this.setLeft(this.getLeft().removeA(x));
        } else if (x > this.getData()) {
				this.setRight(this.getRight().removeA(x));
		// Below here means "this" is the node to be removed
        } else if (this.getLeft() == externalnode) {
        	return 		this.getRight();
        } else if (this.getRight() == externalnode ) { 
        	return 		this.getLeft();
        } else { 
				// TreeNode for the removed data gets recycled by
				// setting its value to the maximum value in the left subtree.
				// Therefore, we delete the TreeNode containing the maximum value in the left subtree.
				this.setData(this.getLeft().deleteMax(this).getData());
        }
        return this;
    }
	// In Part 1a you must write tests for removeA
	// In Part 1b you must write removeB, a non-recursive implementation of remove
    
    public TreeNode removeB(int x){
        if(this == externalnode) return externalnode; 
        TreeNode currNode = this; 
        TreeNode prev = this; 
        while(currNode.data != x && currNode != externalnode){
            
            if(x > currNode.getData()){
                prev = currNode; 
                currNode = currNode.right;          
            }
            else{
                prev = currNode; 
                currNode = currNode.left; 
            }
        }
        if(currNode.getRight() == externalnode && currNode.getLeft() == externalnode){
            currNode.setData(externalnode.data);
        }
        else if(x < this.data && currNode.getLeft() == externalnode){
            prev.setLeft(currNode.right);
                   
        }
        else if(x > this.data && currNode.getLeft() == externalnode){
            prev.setRight(currNode.left);
        }

        else if(currNode.right != externalnode || currNode.left != externalnode){
            if(this.data > currNode.data && currNode.getRight() == externalnode){
               
                TreeNode temp = currNode.findMax(); 
                currNode.data = temp.data; 
                temp.setData(externalnode.data);
            }
            else if(this.data > currNode.data && currNode.left == externalnode){
                if(currNode.right != externalnode){
                 TreeNode temp = currNode.right.findMax(); 
                    currNode.data = temp.data; 
                 temp.setData(externalnode.data);
                }
            }

            else{
                if(currNode.getLeft() != externalnode ){
                    
                    TreeNode temp = currNode.left.findMax(); 
                    currNode.data = temp.data; 
                    temp.setData(externalnode.data);
                }

               
            }
        }

        return this; 
    }

    
	/* return true if this tree is a search tree */
    public boolean isBST() { 
    	if (	getLeft() != externalnode) {
    		if (getLeft().getData() > this.getData()) return false;
    		if (getLeft().isBST() == false) return false;
    	}
    	if (	getRight() != externalnode) {
    		if (		getRight().getData() < this.getData()) return false;
    		if (		getRight().isBST() == false) return false;
    	}
    	return true;
    }

	/* String representation of the tree rooted at this TreeNode

	The representation is inorder, with each subtree enclosed in parentheses
	e.g. The tree

	        100
	  50           200
	     60     110   203

	would be printed as  (100,(50,(),(60,(),())),(200,(110,(),()),(203,(),())))
	*/
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toStringHelper(s);
		return s.toString();
	}
    
    public void printTree() {
		System.out.println(this.toString());
    }

	private void toStringHelper(StringBuilder s) {
		s.append("(");
		if (this != externalnode)  {
				s.append(this.getData());
				s.append(",");
				this.getLeft().toStringHelper(s);
				s.append(",");
				this.getRight().toStringHelper(s);
		}
		s.append(")");
	}

	/* Creates a BST from the given array. To get the BST that you
	expect, the order of the data should be breadth-first order of the resulting tree
	
	e.g. The tree

	        100
	  50           200
	     60     110   203
	would be achieved by passing in
	{100,50,200,60,110,203}
	*/
	public static TreeNode bulkInsert(int[] data) {
		TreeNode root = externalnode;
		for (int i=0; i<data.length; i++) {
			root = root.insert(data[i]);
		}
		return root;
	}
	
	/* Two TreeNodes are t1.equals(t2) if their data are equal and left and right
	children are equals().
	*/
	@Override
	public boolean equals(Object obj) {
			if (this == obj) {
					return true;
			}
			if (obj == null) {
					return false;
			}
			if (getClass() != obj.getClass()) {
					return false;
			}
			final TreeNode other = (TreeNode) obj;
			if (this.data != other.data) {
					return false;
			}
			if (!Objects.equals(this.left, other.left)) {
					return false;
			}
			if (!Objects.equals(this.right, other.right)) {
					return false;
			}
			return true;
	}

		/**
		 * @return the data
		 */
		public int getData() {
				return data;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(int data) {
				this.data = data;
		}

		/**
		 * @return the left
		 */
		public TreeNode getLeft() {
				return left;
		}

		/**
		 * @param left the left to set
		 */
		public void setLeft(TreeNode left) {
				assert(left!=null);
				this.left = left;
		}

		/**
		 * @return the right
		 */
		public TreeNode getRight() {
				return right;
		}

		/**
		 * @param right the right to set
		 */
		public void setRight(TreeNode right) {
				assert(right!=null);
				this.right = right;
		}
	
		/* A method for constructing the one TreeNode object that represents the external node */
	private static TreeNode createExternalNode() {
		TreeNode t = new TreeNode(0); // data doesn't actually matter
		t.setLeft(t);
		t.setRight(t);
		return t;
	}

/*public static void main(String[] args){
    int[] tree = {100,50,150,25,75,125,175,60,79};
    TreeNode t = TreeNode.bulkInsert(tree);
    ArrayList l = new ArrayList(); 
   // int[] ex = {62,44,78,17,50,88,48,54};
   // TreeNode a = TreeNode.bulkInsert(ex);
    // a.printTree();
   //  t = t.rotateLeft();
 //    t = t.doubleRotateLeftThenRight();
       l = t.help(100, 250);
       System.out.println(l.toString());
       
       
}*/

        
}
