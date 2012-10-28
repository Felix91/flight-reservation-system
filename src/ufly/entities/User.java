package ufly.entities;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpSession;

@PersistenceCapable
public class User {
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a User object
	 * @param emailAddr : email address of the User
	 * @param password  : password of the user
	 */
	public User(String emailAddr, String password)
	{
		this.emailAddr = emailAddr;
		this.setPassword(password);
	}
	
	
	/*------------ METHODS ------------*/
	/**
	 * Check if one User is equal to another
	 * @param otherUser :other user to compare against
	 * @return true if both users are the same
	 */
	public boolean equals(User otherUser)
	{
		return this.emailAddr == otherUser.emailAddr;
	}
	
	/**
	 * Log in an already created user
	 * @param session :session to make user logged into, usually request.getSession()
	 * @return :this
	 */
	public User login(HttpSession session)
	{
		this.session=session;
		session.setAttribute("loggedInUser", this.emailAddr);
		return this;
	}
	
	/**
	 * Make the user logged out
	 * @return void
	 */
	public User logout()
	{
		this.session.setAttribute("loggedInUser",null);
		return this;
	} 
	
	/*------------ MODIFIERS ------------*/
	// TODO: All modifiers: need to modify datastore object, not local copy
	/**
	 * Change the password
	 * @param newPw : string to make the new password
	 */
	public void changePw(String newPw)
	{
		this.setPassword(newPw);
	}
	
	/**
	 * @param name	: new first name to update to
	 * @return
	 */
	public User setFirstName(String name)
	{
		this.firstName=name;
		return this; // [Felix] Why are we returning the object?
	}
	
	/**
	 * @param name	: new last name to update to
	 * @return
	 */
	public User setLastName(String name)
	{
		this.lastName=name;
		return this;
	}
	
	
	/*------------ ACCESSORS ------------*/
	/**
	 * Get first name
	 * @return firstName
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/**
	 * Get last name
	 * @return lastName
	 */
	public String getLastName()
	{
		return this.lastName;
	}
	
	/**
	 * Get email address of User
	 * @return emailAddr
	 */
	public String getEmailAddr()
	{
		return this.emailAddr;
	}
		
	
	/*------------ CLASS METHODS ------------*/
	/**
	 * Make the user specified by email logged in if the password is correct
	 * @param emailAddr : email address to attempt to login
	 * @param password 	: password to attempt to login with
	 * @param session 	: session to make user logged into, usually request.getSession()
	 * @return true if successful, false otherwise
	 */
	public static User login(String emailAddr,String password,HttpSession session)
	{
		User localUser=null;
		localUser=User.getUser(emailAddr);
		if(localUser!=null && localUser.password.Matches(password))
		{
			localUser.login(session);
		}
		
		return localUser;
	}
	
	/**
	 * Get the user that is currently logged in
	 * @return return null if no user is logged in, return User if there is a user logged in.
	 */
	public static User getLoggedInUser(HttpSession session)
	{
		User loggedInUser=null; 
		String email = (String) session.getAttribute("loggedInUser"); 
		if (email !=null)
		{
			loggedInUser=User.getUser(email);
		}
		
		if (loggedInUser != null)
		{
			loggedInUser.session = session;
		}
		return loggedInUser;
	}
	
	/**
	 * Get a User from the datastore
	 * @param email	: search key
	 * @return the User if found, else, null
	 */
	private static User getUser(String email)
	{
		//DataStore Stuff
		//TODO:add datastore filters instead of iterating
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query q = pm.newQuery(User.class);
		try
		{
			@SuppressWarnings("unchecked")
			List<User>result=(List<User>) q.execute();
			
			//Our filthy filter
			Iterator<User> it = result.iterator();
			while(it.hasNext())
			{
				User u = it.next();
				if (u.getEmailAddr().equals(email))
					return u;	
			}
			return null;
		}
		finally // Note that this finally block will still run even if try block has return statements.
		{
			q.closeAll();
		}
	}
	
	
	/*------------ HELPER METHODS ------------*/
	/**
	 * Change String into UflyPassword object
	 * @param password	: String password to convert
	 */
	public void setPassword(String password)
	{
		this.password = new UflyPassword(password);
	}
	
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent
	private String emailAddr;		// The User's email address. Uniquely identifies the entity.
	@Persistent
	private String firstName;		// The User's first name
	@Persistent
	private String lastName;		// The User's last name
	@Persistent(serialized = "true")
	private UflyPassword password;	// The User's password
	@NotPersistent
	private HttpSession session;
	//@NotPersistent
	//private static PersistenceManager pm = PMF.get().getPersistenceManager(); // [Felix] I don't think we should do this. There is only one PersistenceManager and each time we use it we have to close it. We should get the singleton instance and close it only when we need it.
}
