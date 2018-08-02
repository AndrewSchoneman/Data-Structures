
import java.util.*;

public class BinaryTree<T> {

		// the root of the tree
		private TreeNode<T> root;

		// the queue of TreeNodes in the tree that have 0 or 1 children
		private final List<TreeNode<T>> nodesToInsertAt;

		// number of TreeNodes in the tree
		public int size;

		public BinaryTree() {
				root = null;
				nodesToInsertAt = new LinkedList<>();
				size = 0;
		}

		/*
	Insert the element d into the BinaryTree at the
	"next available location"
		 */
		public void insertNode(T d) {
				TreeNode<T> newNode = new TreeNode(d);
				nodesToInsertAt.add(newNode);
				size++;

				if (root == null) {
						root = newNode;
						return;
				}

				TreeNode curr;
				curr = (TreeNode) nodesToInsertAt.get(0);

				if (curr.left == null) {
						curr.left = newNode;
						return;
				}

				if (curr.right == null) {
						curr.right = newNode;
						nodesToInsertAt.remove(0);
				}
		}

		public void printTree() {
				Object[] nodeArray = this.toArray();
				for (int i = 0; i < nodeArray.length; i++) {
						System.out.println(nodeArray[i]);
				}
		}

		public Object[] toArray() {
				Object[] r = new Object[size];
				if (root == null) {
						return r;
				}

				// traverse the tree to visit all nodes,
				// adding them to r
				List<TreeNode> frontier = new LinkedList<>();
				frontier.add(root);
				int soFar = 0;

				while (frontier.size() > 0) {

						TreeNode v = (TreeNode) frontier.get(0);
						r[soFar] = v.data;
						soFar++;

						if (v.left != null) {
								frontier.add(v.left);
						}
						if (v.right != null) {
								frontier.add(v.right);
						}

						frontier.remove(0);
				}
				return r;
		}
                

		/////////////////// Start Editing here ///////////////////////

		/* Takes a depth n and a ReduceFunction f
		and returns the "sum" of all elements in the tree at depth n,
		where depth=0 is the root.

		"sum" means the result of starting with f.initialValue
		and "adding" each element using f.combine

		Requirement: must be recursive
                
		*/


    
		public T sumOfDepthRecursive(int n, ReduceFunction<T,T> f) {
                    return root.helper(root,n,f); 
                }

		/* Takes a depth n and a ReduceFunction f
		and returns the "sum" of all elements in the tree at depth n,
		where depth=0 is the root.

		"sum" means the result of starting with f.initialValue
		and "adding" each element using f.combine

		Requirement: must use a loop
		*/
		public T sumOfDepthLooping(int n, ReduceFunction<T,T> f) {
                    LinkedList<TreeNode> queue = new LinkedList<>(); 
                    T sum = null;
                    
                    
                    if(root == null)throw new IllegalStateException("Method cannot be run on a tree with a null root");
                        
                    queue.push(root);                    
                    if(n == 0){
                        return (T)root.data; 
                    }

                    else{
                        Integer level = 0; 
                        LinkedList<Integer> levelList = new LinkedList<>();
                        levelList.addFirst(1);
                        sum = f.combine(f.initialValue(), f.initialValue());
                       
                        while(!queue.isEmpty()){
                            
                          TreeNode currNode = queue.pop();
                          level = levelList.pop();   
                          if(currNode.left != null){                              
                              if(n == level){
                                sum = f.combine(sum, (T)currNode.left.data);                                
                              }
                              levelList.addLast(level + 1);
                              queue.addLast(currNode.left);                              
                            }
                          if(currNode.right != null){                              
                               if(n == level){
                               sum = f.combine(sum, (T)currNode.right.data);
                               }
                            levelList.addLast(level +1);
                            queue.addLast(currNode.right);   
                            }   
                        if(!levelList.isEmpty() && levelList.getFirst() > n ){
                            break;
                            }  
                        }                                                  
                    }
                        return sum;
		}
		
		/* Takes a Predicate p and returns the list of all elements
		for which p.check is true. The elements must be returned in
		"in order" traversal order.
		
		Requirement: must use a loop
		*/
		public List<T> wantedNodesLooping(Predicate<T> p) {
                    LinkedList<TreeNode> queue = new LinkedList<>(); 
                    LinkedList<T> answer = new LinkedList<>();                     
                    queue.push(root);
                    if(root == null)throw new IllegalStateException("Method cannot be run on a tree with a null root");
                    while(!queue.isEmpty()){
                        TreeNode currNode = queue.pop();
                        if(p.check((T)currNode.data)){
                            answer.addLast((T)currNode.data);
                            }                             
                        if(currNode.right != null){       
                            queue.addFirst(currNode.right);                                
                            }
                        if(currNode.left != null){
                            queue.addFirst(currNode.left);                              
                            }                          
                    }     
                    return answer;                
		}

		/* Takes a Predicate p and returns the list of all elements
		for which p.check is true. The elements must be returned in
		"in order" traversal order.
		
		Requirement: must be recursive
		*/
		public List<T> wantedNodesRecursive(Predicate<T> p) {
                    List<T> answer = new ArrayList<>(); 
                    root.helper2(root, answer, p);                                     
                    return answer;
		}
                




		//////////////// Dont edit after here //////////////////////
                

		private static class TreeNode<T> {

				public T data;
				public TreeNode<T> left;
				public TreeNode<T> right;

				public TreeNode(T d) {
						data = d;
						left = null;
						right = null;
				}
                                
                                

                                private T helper(TreeNode node, int n, ReduceFunction<T,T> f) {
                                    T sum = f.combine(f.initialValue(), f.initialValue()); 
                                    if(n > 0){
                                        if(node.left != null){
                                          sum = f.combine(sum, helper(node.left, n-1,f));
                                        }
                                        if(node.right !=null){
                                          sum = f.combine(sum,helper(node.right, n-1, f)); 
                                        }
                                    }                                       
                                    else{
                                        return (T)node.data; 
                                    }
                                     return sum; 

                                    }
                                    

                                
                                
                                private List<T> helper2(TreeNode node, List<T> result, Predicate<T> p){
                                    List<T> preOrder = new ArrayList<>(); 
                                     
                                     if(node == null){
                                         return Collections.EMPTY_LIST;
                                     }
                                     if(p.check((T)node.data)){
                                        result.add((T)node.data);
                                     }
                                     helper2(node.left, result, p); 
                                     helper2(node.right, result, p); 
                                     return preOrder; 


                                                                
		}
                                

    }
}
