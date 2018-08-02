package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import queries.Pair;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import readers.TextFileReader;

// Returns all the words longer than 24 characters
public class TextQuery2 {
	public static void main(String[] args) {
		Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("sci.space");
		Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
		Iterator<String> words = new FlatApply<>(new SplitBy("[ .!,:<>@=*_()/\\t~#\"|-]"), contents); 
		Iterator<String> longWords = new FlatApply<>(new LongerThan(24), words);

		while (longWords.hasNext()) {
			System.out.println(longWords.next());
		}
	}	

	// The only change you should make to this file:
	//   define LongerThan class here (see FlatApplyTest.java for examples
	//
	//   Its constructor should take an int, called len, the size a String needs to be to get to the output
	//   Its apply function should take String and return a List<String> containing
	//   the String if its length is greater than len and empty otherwise.
	//
	//   Example: len is 5
	//        apply("The") returns []
	//        apply("CS2230") returns "CS2230"
	//        apply("I love CS2230") returns "I love CS2230"
	private static class LongerThan implements FlatApplyFunction<String,String> {
            private final int wordsLongerThan;
            
            public LongerThan(int r){
                wordsLongerThan = r; 
            }
            
            
            @Override
            public List<String> apply(String x){
                List<String> l = new LinkedList<>(); 
                if(x.length() > wordsLongerThan){
                    l.add(x);
                }
                return l; 
            }
            
	}
            
          
        
	
	

    // Split String by instances of a given string
	//   Example: delimiter is "A"
	//        apply("An apple A day") returns ["","n apple ", " day"]
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




	private static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, R> {
		@Override
		public R apply(Pair<L, R> x) {
			return x.right;	
		}
	}
}
