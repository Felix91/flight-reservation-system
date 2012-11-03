package ufly.entities;

import java.util.Date;
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

import ufly.entities.SeatingArrangement.AircraftModel;

@PersistenceCapable (detachable="true")
public class Flight {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a Flight object
	 * @param flightNumber 				: The Flight's flight number
	 * @param origin					: The Flight's place of origin
	 * @param destination				: The Flight's place of destination
	 * @param departure					: The Flight's departure date
	 * @param arrival					: The Flight's arrival date
	 * @param allowableMealTypes		: A vector of Meals available on this Flight
	 * @param seatingArrangementLayout	: The string which determines the Flight's SeatingArrangment instance
	 */
	public Flight(String flightNumber, Airport origin, Airport destination, Date departure, Date arrival, Vector<Meal> allowableMealTypes, AircraftModel aircraftModel)
	{
		this.k = KeyFactory.createKey(Flight.class.getSimpleName(), flightNumber+departure);
		this.flightNumber = flightNumber;
		this.origin = origin.getKey();
		this.destination = destination.getKey();
		this.departure = departure;
		this.arrival = arrival;
		this.allowableMealTypes = allowableMealTypes; // TODO: Will a reference to the original vector suffice?
		//this.seatingArragement = new SeatingArrangement(aircraftModel);
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}finally{
			pm.close();
		}
	}
	
	/*------------ MODIFIERS ------------*/
	/**
	 * @param newFlightNumber	: new flight number to update to
	 */
	public void changeFlightNumber(String newFlightNumber)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.flightNumber=newFlightNumber;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param orig	: new origin to update to
	 */
	public void changeOrigin(Airport orig)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.origin=orig.getKey();
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param dest	: new destination to update to
	 */
	public void changeDestination(Airport dest)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.destination=dest.getKey();
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newDeparture	: new departure date to update to
	 */
	public void changeDeparture(Date newDeparture)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.departure=newDeparture;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newArrival	: new arrival date to update to
	 */
	public void changeArrival(Date newArrival)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.arrival=newArrival;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newMeals	: new list of meals to update to
	 */
	public void changeAllowableMeals(Vector<Meal> newMeals )
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.allowableMealTypes=newMeals;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newSeatingArrangement	: new seating arrangement to update to
	 */
	public void changeSeatingArrangement(SeatingArrangement newSeatingArrangement )
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.seatingArragement=newSeatingArrangement;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	public static List<Flight> getAllFlights()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Flight.class);
        /**
         * in order to check this we need to check every element to see if it
         * is of type User, too much work, plus we should not get any
         * other type. We just suppress the warning
         */
        @SuppressWarnings("unchecked")
		List<Flight> results = (List<Flight>) q.execute();
        return results;
	}
	
	/*------------ ACCESSORS ------------*/
	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber()
	{
		return this.flightNumber;
	}
	
	/**
	 * @return the origin airport
	 */
	public Airport getOrigin()
	{
		return Airport.getAirport(this.origin);
	}
	
	/**
	 * @return the destination airport
	 */
	public Airport getDestination()
	{
		return Airport.getAirport(this.destination);
	}
	
	/**
	 * @return the departure date
	 */
	public Date getDeparture()
	{
		return this.departure;
	}
	
	/**
	 * @return the arrival date
	 */
	public Date getArrival()
	{
		return this.arrival;
	}
	
	public Key getKey()
	{
		return this.k;
	}
	
	/**
	 * @return the allowableMealTypes
	 */
	public Vector<Meal> getAllowableMeals()
	{
		return this.allowableMealTypes;
	}
	
	/**
	 * @return the seatingArragement
	 */
	public SeatingArrangement getSeatingArragement()
	{
		return this.seatingArragement;
	}


	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key k; 	// Use a flightNumber and departure concatenated string to serve as entity key (to avoid using numeric ID) for now
	@Persistent
	private String flightNumber;					// The Flight's flight number e.g. CX838. This and the departure Date determines the Flight.
	@Persistent
	private Key origin;							// The Flight's place of origin. Note a new Airport entity should not be created.
	@Persistent
	private Key destination;					// The Flight's place of destination. Note a new Airport entity should not be created.
	@Persistent
	private Date departure;							// The Flight's departure date (defined with year, month, date, hrs, min)
	@Persistent
	private Date arrival;							// The Flight's arrival date (defined with year, month, date, hrs, min)
	@Persistent
	private Vector<Meal> allowableMealTypes;		// The meals available on this Flight
	@Persistent
	private SeatingArrangement seatingArragement;	// Each Flight will have one seating arrangement layout
	@Persistent(mappedBy = "bookedFlight") // bidirectional relationship
	private Vector<FlightBooking> flightBookings;	// The bookings made on this Flight
	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", origin=" + origin.toString()
				+ ", destination=" + destination.toString()+ ", departure=" + departure
				+ ", arrival=" + arrival + ", allowableMealTypes="
				+ allowableMealTypes + ", seatingArragement="
				+ seatingArragement + ", flightBookings=" + flightBookings
				+ "]";
	}
}
