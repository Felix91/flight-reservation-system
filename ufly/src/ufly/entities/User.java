package ufly.entities;

import java.util.List;

import javax.jdo.PersistenceManager;
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
	 * 
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
	public User login(HttpSession session)
	{
		this.session=session;
		session.setAttribute("loggedInUser", this);
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
	 * @return return null if no user is logged in, return User if there is a user logged in.
	 */
	public static User getLoggedInUser(HttpSession session)
	{
		User loggedInUser = (User) session.getAttribute("loggedInUser"); 
		if (loggedInUser != null)
		{
			loggedInUser.session = session;
		}
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
	private static PersistenceManager pm = PMF.get().getPersistenceManager();
	// Variables
	@PrimaryKey
	@Persistent
	private String emailAddr;
	
	@Persistent(serialized = "true")
	private UflyPassword password; 
	//not persistent
	private HttpSession session;
}
