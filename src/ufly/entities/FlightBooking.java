package ufly.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class FlightBooking {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightBooking object
	 * @param bookedBy			: The FlightBooking's bookedBy Customer
	 * @param bookedFlight 		: The FlightBooking's bookedFlight
	 * @param bookedFlightClass	: The FlightBooking's flight class
	 * @param bookedSeat		: The FlightBooking's booked seat
	 * @param mealChoice		: The FlightBooking's selected meal
	 */
	public FlightBooking(Customer bookedBy, Flight bookedFlight, FlightClass bookedFlightClass, Seat bookedSeat, Meal mealChoice)
	{
		this.bookedBy = bookedBy;
		this.bookedFlight = bookedFlight;
		this.bookedFlightClass = bookedFlightClass; // TODO is this enough for storing an enum in the datastore?
		this.bookedSeat = bookedSeat;
		this.mealChoice = mealChoice; // TODO enum. similar to bookedFlightClass's TODO
	}
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) // automatically generate a numeric ID
	private Key confirmationNumber;			// The FlightBooking's confirmation number. Note that since FlightBooking is a child class of entity relationships, its key must either be a Key or a Key value encoded as a string.
	@Persistent
	private Customer bookedBy;				// The FlightBooking's owner
	@Persistent
	private Flight bookedFlight;			// The FlightBooking's flight
	@Persistent
	private FlightClass bookedFlightClass;	// The FlightBooking's flight class
	@Persistent(defaultFetchGroup = "true") // Always load the Seat when parent (this) is loaded
	private Seat bookedSeat;				// The FlightBooking's booked seat
	@Persistent
	private Meal mealChoice;				// The FlightBooking's meal choice
	
}
