package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightManager extends User {

	/*------------ CONSTRUCTORS ------------*/
	public FlightManager(String emailAddr, String password)
	{
		super(emailAddr, password);
	}

}
