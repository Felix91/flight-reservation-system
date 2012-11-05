package ufly.entities;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
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
