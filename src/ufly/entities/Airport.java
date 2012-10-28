package ufly.entities;

import java.util.Vector;

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
