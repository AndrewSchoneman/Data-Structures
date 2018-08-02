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
public class ExtraCreditFlightsQuery2 {

		public static void main(String[] args) throws IOException {
			Iterator<String> lines = new LineFileReader("flights-small.csv"); // expects answer: 520718
			//Iterator<String> lines = new LineFileReader("flights-tiny.csv"); // expects answer: 5
			Iterator<Object[]> recordsGeneric = new Apply<>(new ParseCSVLine(), lines);
			Iterator<FlightRecord> records = new Apply<>(new ConvertToRecord(), recordsGeneric);
                        // I put an input here so you can modify the flight filter if you want
                        Filter<FlightRecord> totalFlights = new Filter<>(new flightFilter("New York NY", "Los Angeles CA"), records);
                        Reduce<FlightRecord,Integer[]> finalPercentage = new Reduce<>(new ExtraCreditFlightsQuery2.flightSum(), totalFlights);
                        Integer[] a = finalPercentage.next(); 
                        
                        System.out.println("Flight Percentage: " +(float)a[0]/(float)a[1] );
                        
  
     


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
               
                
    private static class flightSum implements ReduceFunction<FlightRecord, Integer[]>{
        Integer[] toReturn; 

         @Override
        public Integer[] combine(Integer[] soFar, FlightRecord x){
            if(soFar[0] == -1){
                soFar = new Integer[2]; 
                soFar[0] = 0;
                soFar[1] = 0; 
            }
            if(x.cancelled == 1){
                soFar[0] +=1; 
            }
            soFar[1] += 1; 

           return soFar; 
            
        }
        
        Integer[] initial = new Integer[1];
        @Override
        public Integer[] initialValue(){
            initial[0] = -1; 
            return initial; 
        }
    }        
    
    
    
    
             
                
    private static class flightFilter implements Predicate<FlightRecord> {
        String a;
        String b;
        public flightFilter(String cityOne, String cityTwo){
            a = cityOne;
            b = cityTwo;
        }

         @Override
         public boolean check(FlightRecord data) {
             return(data.destCity.equals(a) && data.originCity.equals(b)) || (data.originCity.equals(a) && data.destCity.equals(b) );
        //return (data[6].equals("New York NY") && data[5].equals("Los Angeles CA")) || (data[5].equals("New York NY") && data[6].equals("Los Angeles CA"))  ;
				}
				
		}  
		//  Converts a CSV record from an Object[] to a FlightRecord
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
