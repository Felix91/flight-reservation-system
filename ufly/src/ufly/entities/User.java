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
	public static void login(User user)
	{
		user.loggedIn = true;
	}
	
	public static void logout(User user)
	{
		user.loggedIn = false;
	}
	
	// Modifiers
	public void changePw(String newPw)
	{
		this.password = newPw;
	}
	
	// Accessors
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
