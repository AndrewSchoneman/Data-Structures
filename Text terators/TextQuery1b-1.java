package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import java.util.Iterator;
import readers.TextFileReader;

// return the 12th word in every file
public class TextQuery1b {
	public static void main(String[] args) {
		Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("sci.space");
		Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
                Iterator<String> TwelthWords = new Apply(new TwelfthWord(), contents);

		while (TwelthWords.hasNext()) {
			System.out.println(TwelthWords.next());
		}
	}	

	// The only change you should make to this file:
	//   define TwelfthWord class here
	//   Its apply function should take a String
	//   and return only the 12th word as a String (where words are defined as separated by a " ")
	private static class TwelfthWord implements ApplyFunction<String,String> {		
            @Override
            public String apply(String x){
                String[] theTwelfthWord = x.split(" ");

                
                return theTwelfthWord[11];        
            }
        }

	private static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, R> {
		@Override
		public R apply(Pair<L, R> x) {
			return x.right;	
		}
	}
}