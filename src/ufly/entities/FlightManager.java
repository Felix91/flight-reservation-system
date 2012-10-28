package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightManager extends Admin {

	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightManager object
	 * @param emailAddr	: The FlightManager's emailAddr
	 * @param password	: The FlightManager's password in String format
	 */
	public FlightManager(String emailAddr, String password)
	{
		super(emailAddr, password);
	}

}
