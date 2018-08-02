package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Reduce;
import iterators.Filter;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import iterators.Predicate;
import iterators.ReduceFunction;
import readers.LineFileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// return the number of flights that occurred in the year 2015
public class FlightsQuery {

		public static void main(String[] args) throws IOException {
			Iterator<String> lines = new LineFileReader("flights-small.csv"); // expects answer: 520718
			//Iterator<String> lines = new LineFileReader("flights-tiny.csv"); // expects answer: 5
			Iterator<Object[]> recordsGeneric = new Apply<>(new ParseCSVLine(), lines);
                        
                        // I never used the records created here becuase of the way this query works and the indexing
                        // you don't need to use it
			Iterator<FlightRecord> records = new Apply<>(new ConvertToRecord(), recordsGeneric);
                        Filter<Object[]> op = new Filter<>(new year(2015), recordsGeneric);
                        Reduce<Object[],Integer> sum = new Reduce<>(new flightSum(), op);

            // add more iterators to complete the query

			while (sum.hasNext()) {
				System.out.println(sum.next()); 
			}
		}

        // define classes you will need for the query here
        
        // object for storing one line in the flights data
		private static class FlightRecord {
			public final int year;
			public final int month;
			public final int dayOfMonth;
			public final String airline;
			public final int flightNumber;
			public final String originCity;
			public final String destCity;
			public final int cancelled; // 0=not cancelled, 1=cancelled
			public final int time;  // flight time in minutes

				public FlightRecord(int year, int month, int dayOfMonth, String airline, int flightNumber, String originCity, String destCity, int cancelled, int time) {
						this.year = year;
						this.month = month;
						this.dayOfMonth = dayOfMonth;
						this.airline = airline;
						this.flightNumber = flightNumber;
						this.originCity = originCity;
						this.destCity = destCity;
						this.cancelled = cancelled;
						this.time = time;
				}

				@Override
				public String toString() {
						return "FlightRecord{" + "year=" + year + ", month=" + month + ", dayOfMonth=" + dayOfMonth + ", airline=" + airline + ", flightNumber=" + flightNumber + ", originCity=" + originCity + ", destCity=" + destCity + ", cancelled=" + cancelled + ", time=" + time + '}';
				}
		}
    private static class flightSum implements ReduceFunction<Object[], Integer>{
        
        @Override
        public Integer combine(Integer soFar, Object[] x){
           soFar++;
           return soFar; 
            
        }
        @Override
        public Integer initialValue(){
            return 0; 
        }
    }                
                
                
                private static class year implements Predicate<Object[]> {

                    int year; 
                    public year(int r){
                        year =r; 
                    }
                    @Override
                    public boolean check(Object[] data) {
                    return data[0].equals(year);
				}
				
		}  
		// Converts a CSV record from an Object[] to a FlightRecord
		private static class ConvertToRecord implements ApplyFunction<Object[], FlightRecord> {

				@Override
				public FlightRecord apply(Object[] r) {
                                            if(r.length < 9){
						return new FlightRecord((int)r[0],
								(int)r[1], 
								(int)r[2], 
								(String)r[3],
								(int)r[4], 
								(String)r[5], 
								(String)r[6],
								(int)r[7], 
								0);
                                                }
                                            else{
                                                return new FlightRecord((int)r[0],
								(int)r[1], 
								(int)r[2], 
								(String)r[3],
								(int)r[4], 
								(String)r[5], 
								(String)r[6],
								(int)r[7], 
								(int)r[8]);
                                            }
				}
				
		}

		// takes a string that is a comma separated list of values and returns an array of Strings and Integers.
		// 
		// Examples
		// "1,2,3,4"
		// [1,2,3,4]
        //
		// "10,cat,dog,22"
		// [10, "cat", "dog", 22]
                
		private static class ParseCSVLine implements ApplyFunction<String, Object[]> {

				@Override
				public Object[] apply(String x) {
					String[] fields = x.split(",");
					Object[] r = new Object[fields.length];
					for (int i=0; i<fields.length; i++) {
						// try to convert to integer
						try {
							r[i] = Integer.parseInt(fields[i]);
						} catch (NumberFormatException ex) {
							// if it fails, then leave a string
							r[i] = fields[i];
						}
					}
					return r;
				}
		}
		
}