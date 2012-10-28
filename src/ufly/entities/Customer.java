package ufly.entities;

import java.util.Iterator;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Customer extends User {
	
	/*------------ CONSTRUCTORS ------------*/
	public Customer(String emailAddr, String password, String firstName, String lastName)
	{
		super(emailAddr, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.loyaltyPoints = 0; // Every Customer starts with 0 loyalty points
	}
	/*------------MODIFIERS---------------*/
	/**
	 * @param newFirstName	: new first name to update to
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void changeFirstName(String newFirstName,Key key)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			Customer c= pm.getObjectById(Customer.class, key);
			c.firstName=newFirstName;
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newLastName	: new last name to update to
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void changeLastName(String newLastName,Key key)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			Customer c= pm.getObjectById(Customer.class, key);
			c.lastName=newLastName;
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param loyaltypoints	: add loyalty points
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void addLoyaltyPoints(int loyaltypoints,Key key)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{
			Customer c= pm.getObjectById(Customer.class, key);
			c.loyaltyPoints+=loyaltypoints;
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param loyalpoints	: use loyalty points
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void useLoyaltyPoints (int loyaltypoints,Key key){
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			Customer c= pm.getObjectById(Customer.class, key);
			c.loyaltyPoints-=loyaltypoints;
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param fb	: add fb into flightbooking vector
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void addBooking(FlightBooking fb,Key key)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			Customer c= pm.getObjectById(Customer.class, key);
			c.flightBookings.add(fb);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param fb	: remove fb from flightbooking vector
	 * @param key : key that matches object on datastore
	 * @return
	 */
	public void removeBooking(FlightBooking fb, Key key)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{	
			Iterator<FlightBooking> itr=flightBookings.iterator();
			Customer c= pm.getObjectById(Customer.class, key);
			
			while(itr.hasNext()){
				FlightBooking cur_fb = itr.next();
				if(cur_fb.getConfirmationNumber().equals(fb.getConfirmationNumber()))
					c.flightBookings.remove(cur_fb);//since a flightbooking is unique this works
			}
		}finally
		{
			pm.close();
		}
	}
	
	
	/*------------ACCESSORS--------------*/
	/**
	 * @return customer first name
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/**
	 * @return customer last name
	 */
	public String getLastName()
	{
		return this.lastName;
	}
	
	/**
	 * @return loyalty points
	 */
	public int getLoyaltyPoints()
	{
		return this.loyaltyPoints;
	}
	
	/**
	 * @return the flight bookings
	 */
	public Vector<FlightBooking> getFlightBookings()
	{
		return this.flightBookings;
	}
	
	
	/*------------ VARIABLES ------------*/
	@Persistent
	String firstName;
	@Persistent
	String lastName;
	@Persistent
	int loyaltyPoints;
	@Persistent(mappedBy = "bookedBy")
	private Vector<FlightBooking> flightBookings;	// The Customer's flight bookings

}
