package ufly.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpSession;

@PersistenceCapable
public class User {
	
	// Constructor
	/**
	 * @author felix, joel
	 * 
	 * create a user object, it will be automatically be
	 * logged in if it is in the session variable
	 * @param emailAddr : email address of the User
	 * @param password  : password of the user
	 * @param session	: current session
	 */
	public User(String emailAddr, String password)
	{
		this.emailAddr = emailAddr;
		this.setPassword(password);
		//TODO: probably need some datastore stuff here
	}	
	// Class methods
	/**
	 * Make the user logged in.
	 * @param password :password to attempt to login with
	 * @return true if successful, false otherwise
	 */
	public User login(String emailAddr,String password)
	{
		
		//DataStore Stuff
		//this.session.setAttribute("loggedInUser", /*User from Datastore*/);
		
		//this return is just to suppress errors
		return new User("","");
	}
	
	/**
	 * Make the user logged out
	 * @return void
	 */
	public void logout()
	{
		this.session.setAttribute("loggedInUser",null);
	}
	
	// Modifiers
	/**
	 * change the password to newPw
	 * @param newPw :string to make the new password
	 */
	public void changePw(String newPw)
	{
		this.setPassword(newPw);
	}
	
	// Accessors
	/**
	 * Get email address of User
	 * @return emailAddr
	 */
	public String getEmailAddr()
	{
		return this.emailAddr;
	}
	
	/**
	 * Ask this if it is logged in
	 * @return true if user is logged in
	 */
	public static User getLoggedInUser(HttpSession session)
	{
		User loggedInUser = (User) session.getAttribute("loggedInUser"); 
		loggedInUser.session = session;
		return loggedInUser;
	}
	/**
	 * 
	 * @param otherUser :other user to compare against
	 * @return true if both users are the same
	 */
	public boolean equals(User otherUser)
	{
		return this.emailAddr == otherUser.emailAddr;
	}
	
	// Helpers
	public void setPassword(String password) {
		this.password = new UflyPassword(password);
	}
	
	// Variables
	@PrimaryKey
	@Persistent
	private String emailAddr;
	
	@SuppressWarnings("unused")
	@Persistent(serialized = "true")
	private UflyPassword password; // Store unencrypted for now.
	//not persistent
	private HttpSession session;
}
