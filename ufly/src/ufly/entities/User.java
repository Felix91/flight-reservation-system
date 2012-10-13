package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {
	
	// Constructor
	public User(String emailAddr, String password)
	{
		this.emailAddr = emailAddr;
		this.password = password;
	}
	
	// Class methods
	/**
	 * Make the user logged in.
	 * @return void
	 * @param user :user to make logged in
	 *
	 */
	public static void login(User user)
	{
	
		
		user.loggedIn = true;
	}
	
	/**
	 * Make the user logged out
	 * @return void
	 * @param user :user to log out
	 */
	public static void logout(User user)
	{
		user.loggedIn = false;
	}
	
	// Modifiers
	/**
	 * change the password to newPw
	 * @param newPw :string to make the new password
	 */
	public void changePw(String newPw)
	{
		this.password = newPw;
	}
	
	// Accessors
	/**
	 * Ask this if it is logged in
	 * @return true if user is logged in
	 */
	public boolean loginStatus()
	{
		return this.loggedIn;
	}
	
	// Variables
	@PrimaryKey
	@Persistent
	private String emailAddr;
	
	@Persistent
	private String password; // Store unencrypted for now.
	
	@Persistent
	private boolean loggedIn; // Use a simple loggedIn flag for now. Will make use of Google Accounts later.

}
