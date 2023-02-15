package solution;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import baseclasses.Aircraft;
import baseclasses.CabinCrew;
import baseclasses.Crew;
import baseclasses.DataLoadingException;
import baseclasses.ICrewDAO;
import baseclasses.Pilot;

/**
 * The CrewDAO is responsible for loading data from JSON-based crew files 
 * It contains various methods to help the scheduler find the right pilots and cabin crew
 */
public class CrewDAO implements ICrewDAO {
	
	
	
	
	
	List<Pilot> pilot = new ArrayList<>();
	List <CabinCrew> Cabincrew = new ArrayList<>() ;
	List <Crew> Crew = new ArrayList<>() ;
	
	
 	/**
	 * Loads the crew data from the specified file, adding them to the currently loaded crew
	 * Multiple calls to this function, perhaps on different files, would thus be cumulative
	 * @param p A Path pointing to the file from which data could be loaded
	 * @throws DataLoadingException if anything goes wrong. The exception's "cause" indicates the underlying exception
	 */
	@Override
	public void loadCrewData(Path p) throws DataLoadingException {
		try {
			
			BufferedReader br = Files.newBufferedReader(p);
				String json = "" ; String line = "" ;
				while ((line = br.readLine()) != null ) {
					json = json + line ; }
					
		
				JSONObject obj = new JSONObject(json) ;
			
			
				JSONArray pilots = obj.getJSONArray("pilots");
			
				
				
				
				for (int i=0 ; i< pilots.length() ; i++ ) {
					
					
					
					String forename  = pilots.getJSONObject(i).getString("forename") ;
					String surname = pilots.getJSONObject(i).getString("surname") ;
					String rank  = pilots.getJSONObject(i).getString("rank") ;
					String airportCode = pilots.getJSONObject(i).getString("home_airport") ;
                   		
					
					JSONArray typeratings=  pilots.getJSONObject(i).getJSONArray("type_ratings");
				
					
					Pilot pi = new Pilot();
			
					pi.setForename(forename);
					pi.setSurname(surname);
					pi.setRank(Pilot.Rank.valueOf(rank));
			        pi.setHomeBase(airportCode);
			     
			        Iterator<Object> tprp = typeratings.iterator();
					while (tprp.hasNext()) {
						pi.setQualifiedFor((String) tprp.next());
					}
				
					pilot.add(pi);
					 
					//System.out.println(forename + "   " +surname +"   "  + "   " + rank +"   " + "   "+homeBase + typeCode   ) ;
				
				}
				
				
			
				JSONArray cabincrew = obj.getJSONArray("cabincrew");
				
				
					
				for (int i=0 ; i< cabincrew.length() ; i++ ) {
				
					String forename   = cabincrew.getJSONObject(i).getString("forename") ;	
					String surname   = cabincrew.getJSONObject(i).getString("surname") ;	
					String airportCode = cabincrew.getJSONObject(i).getString("home_airport") ;
					JSONArray typeratings=  cabincrew.getJSONObject(i).getJSONArray("type_ratings");					 
					
					CabinCrew cc = new CabinCrew () ;
					
					cc.setForename(forename);
					cc.setSurname(surname);
					cc.setHomeBase(airportCode);
				   
					Iterator<Object> tpr = typeratings.iterator();
					while (tpr.hasNext()) {
						cc.setQualifiedFor((String) tpr.next());
					}
					
				 
					
					Cabincrew.add(cc) ;
							
					
				}
				
				
				 
				
				 Crew.addAll(pilot);
				 Crew.addAll(Cabincrew) ;
				
				}
		
		
		
		catch (IOException | JSONException e ) {
			System.err.println("Problem: " + e);
			throw new DataLoadingException(e);
			
		}
		
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub

	}
	
	/**
	 * Returns a list of all the cabin crew based at the airport with the specified airport code
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the cabin crew based at the airport with the specified airport code
	 */
	@Override
	public List<CabinCrew> findCabinCrewByHomeBase(String airportCode) {
		List<CabinCrew> AirportCode  = new ArrayList<>();
	    for (int i = 0; i < Cabincrew.size(); i++) {
	        if ( Cabincrew.get(i).getHomeBase().equalsIgnoreCase(airportCode)) {
	        	AirportCode.add(Cabincrew.get(i));
	        } 
	    }
	    return AirportCode;
	}


	@Override
	public List<CabinCrew> findCabinCrewByHomeBaseAndTypeRating(String typeCode, String airportCode) {
		// TODO Auto-generated method stub
		List<CabinCrew> CabincrewBHBandTR = new ArrayList<>() ;
	    for (int i = 0; i < Cabincrew.size(); i++) {
	       if ( Cabincrew.get(i).getTypeRatings().contains(typeCode)  && Cabincrew.get(i).getHomeBase().equalsIgnoreCase(airportCode) ) {
	        	CabincrewBHBandTR.add(Cabincrew.get(i));
	        } 
	    }
	    return CabincrewBHBandTR ;
	}

	
	@Override
	public List<CabinCrew> findCabinCrewByTypeRating(String typeCode) {
		
		
		List<CabinCrew> TypeCode  = new ArrayList<>();
	    for (int i = 0; i < Cabincrew.size(); i++) {
	        if ( Cabincrew.get(i).getTypeRatings().contains(typeCode) ){
	        	TypeCode.add(Cabincrew.get(i));
	        } 
	    }
	    return TypeCode;
	} 

	/**
	 * Returns a list of all the pilots based at the airport with the specified airport code
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the pilots based at the airport with the specified airport code
	 */
	@Override
	public List<Pilot> findPilotsByHomeBase(String airportCode) {

		List<Pilot> AirportCode  = new ArrayList<>();
		  for (int i = 0; i < pilot.size(); i++) {
		        if ( pilot.get(i).getHomeBase().equalsIgnoreCase(airportCode)) {
		        	AirportCode.add(pilot.get(i));
		        } 
		    }
	    return AirportCode;
	}

	/**
	 * Returns a list of all the pilots based at a specific airport AND qualified to fly a specific aircraft type
	 * @param typeCode the type of plane to find pilots for
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the pilots based at a specific airport AND qualified to fly a specific aircraft type
	 */
	@Override
	public List<Pilot> findPilotsByHomeBaseAndTypeRating(String typeCode, String airportCode) {
		// TODO Auto-generated method stub
		List<Pilot> PilotsBHBandTR = new ArrayList<>() ;
	    for (int i = 0; i < pilot.size(); i++) {
	       if ( pilot.get(i).getTypeRatings().contains(typeCode)  && pilot.get(i).getHomeBase().equalsIgnoreCase(airportCode) ) {
	        	PilotsBHBandTR.add(pilot.get(i));
	        } 
	    }
	    return PilotsBHBandTR ;
	}
		
		
		
	

	/**
	 * Returns a list of all the pilots currently loaded who are qualified to fly the specified type of plane
	 * @param typeCode the type of plane to find pilots for
	 * @return a list of all the pilots currently loaded who are qualified to fly the specified type of plane
	 */
	@Override
	public List<Pilot> findPilotsByTypeRating(String typeCode) {
		// TODO Auto-generated method stub
		List<Pilot> TypeCodeP  = new ArrayList<>();
	    for (int i = 0; i < pilot.size(); i++) {
	        if ( pilot.get(i).getTypeRatings().contains(typeCode) ){
	        	TypeCodeP.add(pilot.get(i));
	        } 
	    }
	    return TypeCodeP;
	}

	/**
	 * Returns a list of all the cabin crew currently loaded
	 * @return a list of all the cabin crew currently loaded
	 */
	@Override
	public List<CabinCrew> getAllCabinCrew() {
		// TODO Auto-generated method stub
		return  Cabincrew;
	}

	/**
	 * Returns a list of all the crew, regardless of type
	 * @return a list of all the crew, regardless of type
	 */
	@Override
	public List<Crew> getAllCrew() {
		  
		   
		  return Crew ;
	}

	/**
	 * Returns a list of all the pilots currently loaded
	 * @return a list of all the pilots currently loaded
	 */
	@Override
	public List<Pilot> getAllPilots() {
		// TODO Auto-generated method stub
		return pilot ;
	}

	@Override
	public int getNumberOfCabinCrew() {
		// TODO Auto-generated method stub
		return Cabincrew.size();
	}

	/**
	 * Returns the number of pilots currently loaded
	 * @return the number of pilots currently loaded
	 */
	@Override
	public int getNumberOfPilots() {
		// TODO Auto-generated method stub
		return pilot.size();
	}

	/**
	 * Unloads all of the crew currently loaded, ready to start again if needed
	 */
	@Override
	public void reset() {
		pilot.clear();
		Cabincrew.clear();
		Crew.clear();
		

	}

}
