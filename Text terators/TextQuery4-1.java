package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import iterators.Reduce;
import queries.Pair;
import iterators.Predicate;
import iterators.ReduceFunction;
import readers.TextFileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Return the number total occurences of the word "Mars"
public class TextQuery4 {
	public static void main(String[] args) {
		Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("sci.space");
		Iterator<String> contents = new Apply(new TextQuery4.TakeRight<>(), filenameAndContents);
		Iterator<String> words = new FlatApply(new TextQuery4.SplitBy(" "), contents); 
                Filter<String> op = new Filter<>(new isMars(), words);
                Reduce<String,Integer> sum = new Reduce<>(new totalSum(), op);
             
		
		/* finish the query using a combination of Applys and Filters */
		while (sum.hasNext()) {
                    System.out.println(sum.next());
		}
	}	




    private static class totalSum implements ReduceFunction<String, Integer>{
        
        int soFar = 0; 
        @Override
        public Integer combine(Integer soFar, String x){
           soFar++;
           return soFar; 
            
        }
        @Override
        public Integer initialValue(){
            return 0; 
        }
    }
 
        
        
    private static class isMars implements Predicate<String> {

	@Override
	public boolean check(String data) {
		return data.equals("Mars");
				}
				
		}   
        
	public static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, R> {
		@Override
		public R apply(Pair<L, R> x) {
			return x.right;	
		}
	}

	public static class SplitBy implements FlatApplyFunction<String, String> {
		private String ch;
		public SplitBy(String c) {
			this.ch = c;
		}
		@Override
		public List<String> apply(String x) {
			return Arrays.asList(x.split(ch));
		}
	}
    }
