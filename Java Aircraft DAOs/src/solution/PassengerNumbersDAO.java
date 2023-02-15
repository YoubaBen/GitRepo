package solution;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import baseclasses.DataLoadingException;
import baseclasses.IPassengerNumbersDAO;

/**
 * The PassengerNumbersDAO is responsible for loading an SQLite database
 * containing forecasts of passenger numbers for flights on dates
 */
public class PassengerNumbersDAO implements IPassengerNumbersDAO {
	
	ArrayList<ArrayList<String>> Data = new ArrayList<ArrayList<String>>();

	 // HashMap<ArrayList<String>, Integer> Data = new HashMap<>();
	 	
	/**
	 * Loads the passenger numbers data from the specified SQLite database into a cache for future calls to getPassengerNumbersFor()
	 * Multiple calls to this method are additive, but flight numbers/dates previously cached will be overwritten
	 * The cache can be reset by calling reset() 
	 * @param p The path of the SQLite database to load data from
	 * @throws DataLoadingException If there is a problem loading from the database
	 */

	@Override
	public void loadPassengerNumbersData(Path p) throws DataLoadingException {
		
		
		
		try {
			
		
			 String pathString = p.toString();
			 Connection c = DriverManager.getConnection("jdbc:sqlite:"+pathString);
		     Statement s = c.createStatement();
		     ResultSet rs = s.executeQuery("select * from PassengerNumbers;");
	
while (rs.next()) {
    String   date = rs.getString("Date");
    String flightnumber = rs.getString("FlightNumber");
    String load = rs.getString("LoadEstimate");
    ArrayList<String> Pk = new ArrayList<>();

     Pk.add(date);
   Pk.add(flightnumber);
    Pk.add(load);
     Data.add(Pk);
 
}
		        	              	
	//   System.out.println(Arrays.asList(Data));

        	    
		        rs.close();
		        c.close();
		        
		
		}
		        catch (SQLException  |  NumberFormatException x ) {
			// TODO Auto-generated catch block
			x.printStackTrace();
			
			throw new DataLoadingException();
		}
       

	}

	
	
	
	@Override
	public int getNumberOfEntries() {
		// TODO Auto-generated method stub
		return Data.size(); 
		
	}

	/**
	 * Returns the predicted number of passengers for a given flight on a given date, or -1 if no data available
	 * @param flightNumber The flight number of the flight to check for
	 * @param date the date of the flight to check for
	 * @return the predicted number of passengers, or -1 if no data available
	 */
	@Override
	public int getPassengerNumbersFor(int flightNumber, LocalDate date) {
		// TODO Auto-generated method stub


		for ( int i=0 ; i!= Data.size() ; i++) {
		
			ArrayList<String> GPN = Data.get(i) ;
			LocalDate Date = LocalDate.parse(GPN.get(0));
			int FN = Integer.parseInt(GPN.get(1));
			 
			if ( Date.equals(date) &&  FN == flightNumber ) {
				
				int PN = Integer.parseInt(GPN.get(2)); 
				return PN ;
			}
		}
		
		return -1 ;
		
		
		
		
		
	}



	/**
	 * Removes all data from the DAO, ready to start again if needed
	 */
	@Override
	public void reset() {
	Data.clear();
	}

}
