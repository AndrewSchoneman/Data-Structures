package iterators;


import java.util.Iterator;
import java.util.LinkedList;

// Iterator that may produce 0 or more output elements for every input element
public class FlatApply<InT,OutT> implements Iterator<OutT> {
                private final FlatApplyFunction<InT,OutT> f; 
                private final Iterator<InT> input; 
                private LinkedList queue;
            
                
		public FlatApply(FlatApplyFunction<InT,OutT> f, Iterator<InT> input) {
                    if(queue == null && input.hasNext()){
                     queue = new LinkedList((f.apply(input.next())));
                        while(queue.isEmpty() && input.hasNext()){
                            queue = new LinkedList((f.apply(input.next())));
                        }
                    }
                    this.f = f;
                    this.input = input; 
		}

		@Override
		public boolean hasNext() {
                    if(queue == null){
                        return input.hasNext();
                    }
                    else{
                        return input.hasNext() || !queue.isEmpty(); 
                    }
		}

                
		@Override
		public OutT next() {
                    OutT outPut = null; 
                    if(queue == null || !hasNext()) throw new IllegalStateException("Nothing to call next on");
                    if(!queue.isEmpty()){                        
                        outPut = (OutT)queue.pop(); 
                    }
                    while(queue.isEmpty() && hasNext()){
                        queue = new LinkedList(f.apply(input.next())); 
                    }
                    return outPut; 
                }              
        }



