package ufly.entities;

import java.util.Date;
import java.util.Vector;

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
	public Flight(String flightNumber, Airport origin, Airport destination, Date depature, Date arrival, Vector<Meal> allowableMealTypes, String seatingArrangmentLayout)
	{
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.allowableMealTypes = allowableMealTypes; // TO-DO: Will a reference to the original vector suffice?
		// this.seatingArragement = new SeatingArrangment(seatingArrangmentLayout); TO-DO: seatingArrangement constructor not defined yet
	}
	
	/*------------ MODIFIERS ------------*/
	
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
		return this.origin;
	}
	
	/**
	 * @return the destination airport
	 */
	public Airport getDestination()
	{
		return this.destination;
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
	private String flightNumber;					// The Flight's flight number e.g. CX838. This and the departure Date determines the Flight.
	private Airport origin;							// The Flight's place of origin. Note a new Airport entity should not be created.
	private Airport destination;					// The Flight's place of destination. Note a new Airport entity should not be created.
	private Date departure;							// The Flight's departure date (defined with year, month, date, hrs, min)
	private Date arrival;							// The Flight's arrival date (defined with year, month, date, hrs, min)
	private Vector<Meal> allowableMealTypes;		// The meals available on this Flight
	private SeatingArrangement seatingArragement;	// Each Flight will have one seating arrangement layout
	
}
