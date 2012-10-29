package ufly.entities;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Seat {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a Seat object
	 * @param rowNumber	: The Seat's rowNumber
	 * @param colNumber	: The Seat's column number which will determine columnChar
	 */
	public Seat(int rowNumber, int columnNumber)
	{
		this.rowNumber = rowNumber;
		this.columnChar = (char) ('A' + (columnNumber-1));
		flightBooking = null; // Seat initially not taken
	}
	
	/*------------ MODIFIERS ------------*/
	/**
	 * Set the Seat's flightBooking
	 * @param flightBooking	: The Seat's flightBooking to be set
	 */
	public void setFlightBooking(FlightBooking flightBooking)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try{
			this.flightBooking = flightBooking;
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}
	
	/*------------ACCESSORS------------*/
	
	/**
	 * @return the key
	 */
	public Key getKey()
	{
		return this.key;
	}
	
	/**
	 * @return the row number
	 */
	public int getRowNumber()
	{
		return this.rowNumber;
	}
	
	/**
	 * @return the column character
	 */
	public char getColumn()
	{
		return this.columnChar;
	}
	
	/**
	 * @return the flight booking of seat
	 */
	public FlightBooking getFlightBooking()
	{
		return this.flightBooking;
	}
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) // automatically generate a numeric ID
	private Key key;			// The Seat's identifier. Note that since Seat is a child class of entity relationships, its key must either be a Key or a Key value encoded as a string.
	@Persistent
	private int rowNumber;
	@Persistent
	private char columnChar;
	@Persistent(mappedBy = "bookedSeat")
	private FlightBooking flightBooking;	// The Seat's flightBooking. If not equal to null, implies that Seat has been booked.
}
