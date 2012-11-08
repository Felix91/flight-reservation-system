package ufly.entities;

import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class Airport {

	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create an Airport object
	 * @param callsign	: The Airport's callsign
	 * @param city		: The Airport's city
	 */
	public Airport(String callsign, String city)
	{
		this.callsign = callsign;
		this.city = city;
		this.departures = new Vector<Key>();
		this.arrivals = new Vector<Key>();
		this.k = KeyFactory.createKey(Airport.class.getSimpleName(), callsign);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}finally{
			pm.close();
		}
	}

	/*------------MODIFIERS--------------*/
	/**
	 * @param newCallSign	: new  to update to
	 */
	public void changeCallSign(String newCallSign)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.callsign=newCallSign;
			// TODO change key as well
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param newCity	: new City to update to
	 */
	public void changeCity(String newCity)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.city=newCity;
			pm.makePersistent(this);

		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param newDepartingFlight	: new Key of Flight to add to list of flights
	 */
	public void addDepartingFlight(Key newDepartingFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Keep elements unique
			if( !this.departures.contains(newDepartingFlight) )
			{
				this.departures.add(newDepartingFlight);
				pm.makePersistent(this);
			}
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param newArrivalFlight	: new Key of Flight to add to list of flights
	 */
	public void addArrivalFlight(Key newArrivalFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Keep elements unique
			if( !this.arrivals.contains(newArrivalFlight) )
			{
				this.arrivals.add(newArrivalFlight);
				pm.makePersistent(this);
			}
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param DepartFlight	: flight to be removed from departures
	 */
	public void removeDepartingFlight(Key DepartFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.departures.remove(DepartFlight);
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param ArrivalFlight	: flight to be removed from arrivals
	 */
	public void removeArrivalFlight(Key ArrivalFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.arrivals.remove(ArrivalFlight);
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}


	/*------------CLASS METHODS--------------*/
	public static List<Airport> getAllAirports()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(Airport.class);
            @SuppressWarnings("unchecked")
            List<Airport> results = (List<Airport>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}
	
	/**
	 * 
	 * @param k key that corresponds to an Airport
	 * @return Airport that has key k
	 */
	public static Airport getAirport(Key k)
	{
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Airport airport, detached = null;
	    try {
	    	airport = pm.getObjectById(Airport.class, k);
	        detached = pm.detachCopy(airport);
	    } finally {
	        pm.close();
	    }
	    return detached;
	}
	public static Airport getAirportByCallSign(String callsign)
	{
		Key k =KeyFactory.createKey(Airport.class.getSimpleName(), callsign);
		return getAirport(k);
	}
	@Override
	public String toString() {
		return "Airport [callsign=" + callsign + ", city=" + city + "\n\tdepartures="+departures.toString()+
				"\n\tarrivals="+arrivals.toString()+"]";
	}

	public boolean equals(Airport a){
		return a.callsign.equals(this.callsign);
	}
	/*------------ACCESSORS--------------*/
	/**
	 * @return the callsign
	 */
	public String getCallSign()
	{
		return this.callsign;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return this.city;
	}

	/**
	 * @return a vector of departure flights from this
	 */
	//JDV 02/11/2012 Do we need these accessors? they require lots db work
//	public Vector<Flight> getDepartingFlight()
//	{
//		return this.departures;
//	}
//	/**
//	 * @return a vector of arrival flights from this
//	 */
//	public Vector<Flight> getArrivingFlight()
//	{
//		return this.arrivals;
//	}
	/**
	 * 
	 * @return return the datastore key for this Airport
	 */
	public Key getKey() {
		return this.k;
	}

	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key k;
	
	@Persistent
    private String callsign;			// The Airport's International Air Transport Association (IATA) airport code. e.g. YVR. Uniquely identifies an Airport.
	@Persistent
	private String city;				// The Airport's city e.g. Vancouver
	@Persistent//(mappedBy = "origin")
	private Vector<Key> departures; 	// The Airport's departing Flights
	@Persistent//(mappedBy = "destination")
	private Vector<Key> arrivals;	// The Airport's arriving Flights
	
}
