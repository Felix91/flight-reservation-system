package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightStaff extends User {

	/*------------ CONSTRUCTORS ------------*/
	public FlightStaff(String emailAddr, String password)
	{
		super(emailAddr, password);
	}

}
