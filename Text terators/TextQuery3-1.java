package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.Predicate;
import java.util.Iterator;
import readers.TextFileReader;

// return all filenames that contain the word "Mars" 
public class TextQuery3 {
	public static void main(String[] args) {
		Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("sci.space");
                Iterator<String> contents = new Apply(new TextQuery3.TakeRight<>(), filenameAndContents);
                Filter<String> op = new Filter<>(new containsMars(), contents);
		
		/* finish the query using a combination of Applys and Filters */
		while (op.hasNext()) {
			System.out.println(op.next());
		}
	}	

	
    private static class containsMars implements Predicate<String> {

	@Override
	public boolean check(String data) {
		return !data.equals("mars not found");
				}
				
		} 
      
      
	
        
        public static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, L> {
		@Override
		public L apply(Pair<L, R> x) {
                    String test = (String)x.right; 
                    if(test.contains("Mars")){
			return x.left;	
                    }
                    else{
                        return (L)"mars not found"; 
                    }
		}
	}
}
	// put your classes that implement ApplyFunction and Predicate here

