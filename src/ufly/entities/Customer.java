package ufly.entities;

import java.util.Vector;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Customer extends User {
	
	/*------------ CONSTRUCTORS ------------*/
	public Customer(String emailAddr, String password, String firstName, String lastName)
	{
		super(emailAddr, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.loyaltyPoints = 0; // Every Customer starts with 0 loyalty points
	}
	
	/*------------ VARIABLES ------------*/
	@Persistent
	String firstName;
	@Persistent
	String lastName;
	@Persistent
	int loyaltyPoints;
	@Persistent(mappedBy = "bookedBy")
	private Vector<FlightBooking> flightBookings;	// The Customer's flight bookings

}
