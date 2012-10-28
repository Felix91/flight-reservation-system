package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightStaff extends Admin {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightStaff object
	 * @param emailAddr	: The FlightStaff's emailAddr
	 * @param password	: The FlightStaff's password in String format
	 */
	public FlightStaff(String emailAddr, String password)
	{
		super(emailAddr, password);
	}

}
