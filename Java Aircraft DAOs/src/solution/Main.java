package solution;

import java.nio.file.Paths;

import baseclasses.DataLoadingException;
import baseclasses.IAircraftDAO;
import baseclasses.ICrewDAO;
import baseclasses.IPassengerNumbersDAO;
import baseclasses.IRouteDAO;
/**
 * This class allows you to run the code in your classes yourself, for testing and development
 */
public class Main {

	public static void main(String[] args) {	
		IPassengerNumbersDAO passengersN = new PassengerNumbersDAO();
				
		try {
			//Tells your Aircraft DAO to load this particular data file
			passengersN.loadPassengerNumbersData(Paths.get("./data/mini_passengers.db"));
		}
		catch (DataLoadingException dle) {
			System.err.println("Error loading aircraft data");
			dle.printStackTrace();
		}
	}
	
	
	
	

}
