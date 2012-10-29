package ufly.entities;

import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
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
	}
	
	/*------------MODIFIERS--------------*/
	/**
	 * @param newCallSign	: new CallSign to update to
	 */
	public void changeCallSign(String newCallSign)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.callsign=newCallSign;
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
	 * @param newDepartingFlight	: new Flight to add to list of flights
	 */
	public void addDepartingFlight(Flight newDepartingFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.departures.add(newDepartingFlight);
			pm.makePersistent(this);
			
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newDepartingFlight	: new Flight to add to list of flights
	 */
	public void addArrivalFlight(Flight newArrivalFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.arrivals.add(newArrivalFlight);
			pm.makePersistent(this);
			
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param DepartFlight	: flight to be removed from departures
	 */
	public void removeDepartingFlight(Flight DepartFlight)
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
	public void removeArrivalFlight(Flight ArrivalFlight)
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
	public Vector<Flight> getDepartingFlight()
	{
		return this.departures;
	}
	/**
	 * @return a vector of arrival flights from this
	 */
	public Vector<Flight> getArrivingFlight()
	{
		return this.arrivals;
	}

	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent
	private String callsign;			// The Airport's International Air Transport Association (IATA) airport code. e.g. YVR. Uniquely identifies an Airport.
	@Persistent
	private String city;				// The Airport's city e.g. Vancouver
	@Persistent(mappedBy = "origin")
	private Vector<Flight> departures; 	// The Airport's departing Flights
	@Persistent(mappedBy = "destination")
	private Vector<Flight> arrivals;	// The Airport's arriving Flights
}
