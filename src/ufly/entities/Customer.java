package ufly.entities;

import java.util.Vector;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Customer extends User {

	public Customer(String emailAddr, String password) {
		super(emailAddr, password);
		// TODO Auto-generated constructor stub
	}
	
	/*------------ VARIABLES ------------*/
	@Persistent(mappedBy = "bookedBy")
	private Vector<FlightBooking> flightBookings;	// The Customer's flight bookings

}
