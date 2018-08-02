package trees;

import java.util.Stack;


public class AVLTree {
	private TreeNode root;             // first node of tree
	
	public AVLTree() { root = TreeNode.getExternalNode(); }   // constructor; no nodes in tree yet
	public void clear() { root = TreeNode.getExternalNode(); }
	  
	protected TreeNode getRoot() { return root; }
	
	public boolean isEmpty() { return root.isEmpty(); }
	
	public boolean find(int key) {
		if (root.isEmpty()) return false;
		Stack<TreeNode> s = root.findPath(key);
		return !TreeNode.isExternal(s.pop());
	}

	public int getHeight() { 
        if (root.isEmpty()) return -1;
		else return root.getHeight();
    }
	
	/* Insert the given data into this tree. Leaves the tree balanced */
	public void insert(int data) {
		// insert the data into the tree rooted at root
		// and update the root in case it got changed
               
		root = root.insert(data);
		// get the path from the inserted node to the root
		Stack<TreeNode> s = root.findPath(data);
		// balance the tree according to the AVL height property
		// and update the root in case it got changed
		root = AVLTree.balance(s);
	}

	/* Remove the given data from this tree. Leaves the tree balanced */
    public void remove(int id) {
        Stack<TreeNode> s = root.findPath(id);
        TreeNode ri = root.getRight(); 
        root.removeA(id); 
        root = AVLTree.balance(s);
        if(id == root.getData() && root.getLeft() == root.getExternalNode() && root.getRight() == root.getExternalNode()){
            root = root.getExternalNode(); 
        }
        else if(id == root.getData() && root.getLeft() != root.getExternalNode() && root.getRight() != root.getExternalNode()){
            TreeNode rSide = root.getRight();
            root = root.getLeft();
            root.setRight(rSide); 
            
         }
        else if(this.find(id) && id != root.getData()){
            TreeNode toDelete = s.pop(); 
            toDelete = s.pop(); 
            if(toDelete.getLeft() == root.getExternalNode() && toDelete.getRight() == root.getExternalNode()){
                root = root.getLeft().getRight();
                System.out.println(root.getData()); 
                root.setRight(root.getExternalNode());
            }
           
            
        }
        else{
            System.out.println("node not found");
        }
		
    }

	/* Throws an assertion error if this AVLTree is not a valid binary search tree */
	public void checkIsBST() {
		assert(root.isBST());
	}

	/* Throws an assertion error if the height balance property is violated anywhere
	   in this AVLTree */
	public void checkBalance() {
			checkBalanceHelper(root);
	}

	private static void checkBalanceHelper(TreeNode n) {
		if (!TreeNode.isExternal(n)) {
				assert(Math.abs(n.getLeft().getHeight()-n.getRight().getHeight()) <= 1);
				checkBalanceHelper(n.getLeft());
				checkBalanceHelper(n.getRight());
		}
	}
    
    private static TreeNode balance(Stack<TreeNode> path) {
		TreeNode n = null;
		boolean alreadyRebalanced = false;
		boolean alreadyAdjustParent = false;
		while(!path.isEmpty()) {
			if (alreadyRebalanced && !alreadyAdjustParent) {
				// if we just rebalanced but haven't set the parent of
				// the rebalanced nodes, then do so
				TreeNode parent = path.pop();
				if (parent.getData() > n.getData()) {
					parent.setLeft(n);
				} else {
					parent.setRight(n);
				}
				alreadyAdjustParent = true;
				n = parent;
			} else {
				// usually, just look at the next node in the path
				n = path.pop();
			}
			int hleft = n.getLeft().getHeight();
			int hright = n.getRight().getHeight();
			if (alreadyRebalanced) {
					// if we already did the tri-node restructuring then
					// the balanced property should be true here
					assert(Math.abs(hleft-hright) <= 1);
			} else {
					if (hleft - hright > 1) {  // left too tall
							if (n.getLeft().getLeft().getHeight() >= n.getLeft().getRight().getHeight()) {
									n = n.rotateRight();
							} else {
									n = n.doubleRotateLeftThenRight();
							}
							alreadyRebalanced = true;
					} else if (hright - hleft > 1) { // right too tall
							if (n.getRight().getRight().getHeight() >= n.getRight().getLeft().getHeight()) {
									System.out.println("rotate Left: "+n.getData());n.printTree();
									n = n.rotateLeft();
									System.out.println("after rotate Left:");n.printTree();
							} else {
									n = n.doubleRotateRightThenLeft();
							}
							alreadyRebalanced = true;
					}
			}
		}

		// if we rebalanced the root of the tree, then t now points to the new root
		// otherwise t points to the existing root
		return n;
    }

}
