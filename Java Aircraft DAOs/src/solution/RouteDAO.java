package solution;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import baseclasses.Aircraft;
import baseclasses.CabinCrew;
import baseclasses.DataLoadingException;
import baseclasses.IRouteDAO;
import baseclasses.Pilot;
import baseclasses.Route;

/**
 * The RouteDAO parses XML files of route information, each route specifying
 * where the airline flies from, to, and on which day of the week
 */
public class RouteDAO implements IRouteDAO {
	
	
	List<Route> Route = new ArrayList<> () ;

	
	
	/**
	 * Loads the route data from the specified file, adding them to the currently loaded routes
	 * Multiple calls to this function, perhaps on different files, would thus be cumulative
	 * @param p A Path pointing to the file from which data could be loaded
	 * @throws DataLoadingException if anything goes wrong. The exception's "cause" indicates the underlying exception
	 */
	@Override
	public void loadRouteData(Path p) throws DataLoadingException {
		
			try {
		
		File f = new File (String.valueOf(p));
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    Document doc = db.parse(f);
		    doc.getDocumentElement().normalize();
		    
		    
		    
		    Element root = doc.getDocumentElement() ;
		    NodeList RouteNodes = root.getElementsByTagName("Route") ;
		   
		    
		    for (int i=0;  i<RouteNodes.getLength() ; i++) {
		    
		    	Node RNode = RouteNodes.item(i) ;
		    
		      Element eElement = (Element) RNode ;
		    	
		
		    
		    Route r = new Route();
		    
		   int flightNumber = Integer.parseInt( eElement.getElementsByTagName("FlightNumber").item(0).getTextContent() );
		   
		   String dayOfWeek =    eElement.getElementsByTagName("DayOfWeek").item(0).getTextContent() ;
		   
		   LocalTime departureTime = LocalTime.parse(eElement.getElementsByTagName("DepartureTime").item(0).getTextContent()) ;
		   
		   String departureAirport =  eElement.getElementsByTagName("DepartureAirport").item(0).getTextContent() ;
		   
		   String departureAirportCode = eElement.getElementsByTagName("DepartureAirportIATACode").item(0).getTextContent() ;
		   
		   LocalTime arrivalTime =  LocalTime .parse(eElement.getElementsByTagName("ArrivalTime").item(0).getTextContent()) ;
		   
		   String arrivalAirport = eElement.getElementsByTagName("ArrivalAirport").item(0).getTextContent();
		   
		   String arrivalAirportCode = eElement.getElementsByTagName("ArrivalAirportIATACode").item(0).getTextContent() ; 
		   
		   Duration duration =  Duration.parse(eElement.getElementsByTagName("Duration").item(0).getTextContent());
		    
		    
		    
		      r.setFlightNumber(flightNumber);
		      r.setDayOfWeek(dayOfWeek);
		      r.setDepartureTime(departureTime);
		      r.setDepartureAirport(departureAirport);
		      r.setDepartureAirportCode(departureAirportCode);
		      r.setArrivalTime(arrivalTime);
		      r.setArrivalAirport(arrivalAirport);
		      r.setArrivalAirportCode(arrivalAirportCode);
		      r.setDuration(duration);
		    
		    Route.add(r) ;
		    
		    //System.out.println() ;
		 
		    
			}
 //  LocalDate dt = LocalDate.parse("2018-01-09"); 
		    
	        // Prints the day 
	       // System.out.println(dt.getDayOfWeek().toString().substring(0, 3)); 
		}
		
		catch (ParserConfigurationException | SAXException |IOException  | NumberFormatException x )   { 
		System.err.println("error " + x) ;
		x.printStackTrace();
		throw new DataLoadingException(x);
		
	}

	} 



	/**
	 * Finds all flights that depart on the specified day of the week
	 * @param dayOfWeek A three letter day of the week, e.g. "Tue"
	 * @return A list of all routes that depart on this day
	 */
	@Override
	public List<Route> findRoutesByDayOfWeek(String dayOfWeek) {
		// TODO Auto-generated method stub
		List<Route> DayOfWeek = new ArrayList<>();
	    for (int i = 0; i < Route.size(); i++) {
	        if ( Route.get(i).getDayOfWeek().equalsIgnoreCase(dayOfWeek) ) {
	        	DayOfWeek.add(Route.get(i));
	        } 
	    }
	    return DayOfWeek;
	}

	/**
	 * Finds all of the flights that depart from a specific airport on a specific day of the week
	 * @param airportCode the three letter code of the airport to search for, e.g. "MAN"
	 * @param dayOfWeek the three letter day of the week code to searh for, e.g. "Tue"
	 * @return A list of all routes from that airport on that day
	 */
	@Override
	public List<Route> findRoutesByDepartureAirportAndDay(String airportCode, String dayOfWeek) {
		// TODO Auto-generated method stub


		List<Route> fRoutesBYDPandAP = new ArrayList<>() ;
	    for (int i = 0; i < Route.size(); i++) {
	       if ( Route.get(i).getDepartureAirportCode().equalsIgnoreCase(airportCode) && Route.get(i).getDayOfWeek().equalsIgnoreCase(dayOfWeek) ) {
	    	   fRoutesBYDPandAP.add(Route.get(i));
	        } 
	    }
	    return fRoutesBYDPandAP;
	}
		
	
	/**
	 * Finds all of the flights that depart from a specific airport
	 * @param airportCode the three letter code of the airport to search for, e.g. "MAN"
	 * @return A list of all of the routes departing the specified airport
	 */
	@Override
	public List<Route> findRoutesDepartingAirport(String airportCode) {
		// TODO Auto-generated method stub
		List<Route> AirportCode = new ArrayList<>();
	    for (int i = 0; i < Route.size(); i++) {
	        if ( Route.get(i).getDepartureAirportCode().equalsIgnoreCase(airportCode) ) {
	        	AirportCode.add(Route.get(i));
	        } 
	    }
	    return AirportCode;
	}
	

	/**
	 * Finds all of the flights that depart on the specified date
	 * @param date the date to search for
	 * @return A list of all routes that dpeart on this date
	 */
	@Override
	public List<Route> findRoutesbyDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Route>RByDate = new ArrayList<>() ;
	    for (int i = 0; i < Route.size(); i++) {
	       if ( Route.get(i).getDayOfWeek().toUpperCase().equalsIgnoreCase(date.getDayOfWeek().toString().substring(0, 3))) {
	    	   RByDate.add(Route.get(i));
	        } 
	    }
	    return RByDate ;
	}
	

	/**
	 * Returns The full list of all currently loaded routes
	 * @return The full list of all currently loaded routes
	 */
	@Override
	public List<Route> getAllRoutes() {
		// TODO Auto-generated method stub
		return Route;
	}

	/**
	 * Returns The number of routes currently loaded
	 * @return The number of routes currently loaded
	 */
	@Override
	public int getNumberOfRoutes() {
		// TODO Auto-generated method stub
		return Route.size();
	}

	
	/**
	 * Unloads all of the crew currently loaded, ready to start again if needed
	 */
	@Override
	public void reset() {
		Route.clear();

	} 

}

