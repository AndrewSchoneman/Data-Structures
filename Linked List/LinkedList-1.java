package hw3;

public class LinkedList {
    private ListNode header; 
    private ListNode tail; 
    /* your private fields go here.
     * It is highly recommended that you use a "sentinel node" at the front of the list
     *  to make the methods easier to implement
     */
    

    /*
     * create an empty list
     */
	public LinkedList() {
            header = new ListNode(0);
            tail = header; 
           // private ListNode next; 
        // solution here
	}
       

    /*
     * remove ith element from the list and return it
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * remove(1)
     * after:  [ 100, 300 ]
     * returns 200
     */
	public Object remove(int i) {
            ListNode removedItem; 
            ListNode itemToRemove = header; 
            
            for(int s = 0; s < i; s++){
                itemToRemove = itemToRemove.next; 
                //do something
            }
            removedItem = itemToRemove.next;
            itemToRemove.next = itemToRemove.next.next; 
            tail = itemToRemove.next; 
            return removedItem.data;
        // replace this code with your solution*/
		
	}

    /*
     * add d to the end of the list
     *
     * Example
     * before: [ 100, 200 ]
     * append(300)
     * after:  [ 100, 200, 300 ]
     */
	public void append(Object d) {
            ListNode toAdd = new ListNode(d); 
            ListNode currentNode = header; 
            while (currentNode.next != null){
                currentNode = currentNode.next; 
            }
            currentNode.next = toAdd; 
            tail = toAdd; 
	}




        
       public int getCount(){
           ListNode currentNode = header; 
           int count = 0; 
           while(currentNode.next != null ){
               if (currentNode == header){
                   currentNode = currentNode.next; 
               }
                   currentNode = currentNode.next; 
                   count++;
                             
               }
           
           return count; 
       }

    /*
     * return the ith element
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * get(2)
     * after:  [ 100, 200, 300 ]
     * returns 300
     */
	public Object get(int i) {
            ListNode toGet = header; 
            for(int s = 0; s < i+1; s++){

                toGet = toGet.next; 
            }
		return toGet.data;
	}

    /*
     * move the ith element so that it is now the jth element 
     *
     * assume that the ith and jth elements exist
     *
     * Example
     * before: [ 100, 200, 300 ]
     * move(1, 0)
     * after:  [ 200, 100, 300 ]
     *
     * Example
     * before: [ 100, 200, 300, 400 ]
     * move(1,3)
     * after: [ 100, 300, 400, 200 ]
     */

        
        
        public void move(int i, int j){
            ListNode itemToMove_i = null; 
            ListNode posToMove_j = null; 
            ListNode prev = null; 
            ListNode temp = null; 
            ListNode currNode = header;
            ListNode posI = null;
            ListNode posJ = null;
            int count = 0; 
            while(currNode.next != null){
                count++;
                if(i+1 == count){   
                    posI = currNode.next; 
                    itemToMove_i = new ListNode(currNode.next.data);                     
                    }
                if(j+1 == count){
                    posJ = currNode.next; 
                    posToMove_j = new ListNode(currNode.next.data); 
                    }             
                 currNode = currNode.next;         
            }
            prev = header; 
            currNode = header; 
            count = 0;
            if(i == j){
                System.out.println("Already in position");
            }
            if(i<j){
                currNode = posI; 
                while(currNode != posJ){
                    currNode.data = currNode.next.data; 
                    currNode = currNode.next; 
                    
                }
            currNode.data = itemToMove_i.data; 
            }
            if(i>j){
                Object[] storeBackwards = new Object[i]; 
                currNode = posJ;
                for(int k = j; k<i; k++){
                    storeBackwards[k] = currNode.data; 
                    currNode = currNode.next; 
                }
                currNode = posJ;
                for(int k = j; k<i; k++){ 
                    if(currNode == posJ){
                        currNode = currNode.next; 
                    }
                    currNode.data = storeBackwards[k]; 
                    currNode = currNode.next; 
                    
                }
                    
                } 
                currNode = posJ;
            currNode.data = itemToMove_i.data;
        }
        
/*
     * Returns an array containing the elements of the list in order
     * Do not copy the Object data; just copy the Object references.
     *
     * Example
     * before: [ 100, 200, 300 ]
     * toArray()
     * after: [ 100, 200, 300 ]
     * returns { 100, 200, 300 } 
     */
	public Object[] toArray() {
		int size = 0;
                ListNode currIndex = header;
                while(currIndex.next != null){
                    size++;
                    currIndex = currIndex.next; 
                    
                }
        // solution here
                ListNode itemToArray = header; 
                Object[] r = new Object[size];
		for(int i = 0; i<size; i++){
                    if(itemToArray == header){
                        itemToArray = itemToArray.next;
                    }
                    r[i] = itemToArray.data; 
                    itemToArray = itemToArray.next; 
                }
		

		/* copy the Objects in the list in order into the array r */
        // solution here
		return r;
	}
        

    // you do not need to modify the ListNode class
    private class ListNode {
		private Object data;
		private ListNode next;

		public ListNode(Object data) {
			this.data = data;
			this.next = null;
		}
		
		public Object getData() {
				return data;
		}
		
		public void setNext(ListNode next) {
				this.next = next;
		}
		
		public ListNode getNext() {
				return next;
		}

    }
		
                    }
