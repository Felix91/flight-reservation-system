package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public abstract class Admin extends User {

	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create an Admin object
	 * @param emailAddr	: The Admin's emailAddr
	 * @param password	: The Admin's password in String format
	 */
	public Admin(String emailAddr, String password)
	{
		super(emailAddr, password);
	}

}
