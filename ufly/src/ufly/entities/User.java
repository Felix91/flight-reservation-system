package ufly.entities;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.servlet.http.HttpSession;

@PersistenceCapable
public class User {
	
	private static PersistenceManager pm = PMF.get().getPersistenceManager();	
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
	private static User getUser(String email)
	{
		//DataStore Stuff
		//TODO:add datastore filters instead of iterating
		javax.jdo.Query q = pm.newQuery(User.class);
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
	// Class methods
	/**
	 * Make the user specified by email logged in if the password is correct
	 * @param emailAddr : email address to attempt to login
	 * @param password :password to attempt to login with
	 * @param session :session to make user logged into, usually request.getSession()
	 * @return true if successful, false otherwise
	 */
	public static User login(String emailAddr,String password,HttpSession session)
	{
		User localUser=null;
		//DataStore Stuff
		javax.jdo.Query q = User.pm.newQuery(User.class);
		q.setFilter("emailAddr == "+emailAddr);
		q.declareParameters("String emailAddr");
		@SuppressWarnings("unchecked")
		List<User> result = (List<User>) q.execute();
		if (result.size()  == 1 )
		{	
			localUser= result.get(0);
			if (localUser.password.Matches(password))
				localUser.login(session);		
		}
		
		return localUser;
	}
	
	// Variables
	@PrimaryKey
	@Persistent
	private String emailAddr;

	
	@Persistent(serialized = "true")
	private UflyPassword password;
	
	//not persistent
	private HttpSession session;
	
	// Constructor
	/**
	 * @author felix, joel
	 * 
	 * create a user object
	 * @param emailAddr : email address of the User
	 * @param password  : password of the user
	 * 
	 */
	public User(String emailAddr, String password)
	{
		this.emailAddr = emailAddr;
		this.setPassword(password);
		//TODO: probably need some datastore stuff here
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
	
	/**
	 * 
	 * @param otherUser :other user to compare against
	 * @return true if both users are the same
	 */
	public boolean equals(User otherUser)
	{
		return this.emailAddr == otherUser.emailAddr;
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
	 * Log in an already created user
	 * 
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
	// Helpers
	public void setPassword(String password) {
		this.password = new UflyPassword(password);
	}
}
