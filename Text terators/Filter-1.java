package iterators;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

// Iterator that uses a Predicate to filter out elements from the input
public class Filter<T> extends FlatApply<T,T> {
  

        
    
	public Filter(Predicate<T> p, Iterator<T> input) {
        super(new FilteringFlatApplyFunction<T>(p), input);
	}	
        

        
    // uses a Predicate to decide whether the input element is output or not
	private static class FilteringFlatApplyFunction<T> implements FlatApplyFunction<T, T> {
            Predicate<T> p;
  
            
            public FilteringFlatApplyFunction(Predicate<T> p){
                this.p = p; 
            }
            
            @Override
            public List<T> apply(T x){
               
                List<T> l = new LinkedList<>();
                if(p.check(x)){
                 
                   l.add(x);
               }
            return l; 
            }
            
	}
        

   public static class IsNotZero implements Predicate<Integer> {

    @Override
    public boolean check(Integer data) {
	return data!=0;
        }			
    } 
        
    public static void main(String[] args){    
        Integer[] inputValues = {0};
			List<Integer> input = Arrays.asList(inputValues);
			Filter<Integer> op = new Filter<>(new IsNotZero(), input.iterator());
                        System.out.println(op.hasNext()); 
                        
                        
    }
    // You DO NOT need to define hasNext() and next(). FlatApply provides them already.
}