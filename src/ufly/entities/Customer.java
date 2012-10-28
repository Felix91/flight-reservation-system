package ufly.entities;

import java.util.Iterator;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable (detachable="true")
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
	public void changeFirstName(Customer c,String newFirstName)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			c.firstName=newFirstName;
			pm.makePersistent(c);
		
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
	public void changeLastName(Customer c,String newLastName)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			c.lastName=newLastName;
			pm.makePersistent(c);
		
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
	public void addLoyaltyPoints(Customer c,int loyaltypoints)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{
			c.loyaltyPoints+=loyaltypoints;
			pm.makePersistent(c);
		
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
	public void useLoyaltyPoints (Customer c,int loyaltypoints){
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			
			c.loyaltyPoints-=loyaltypoints;
			pm.makePersistent(c);
		
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
	public void addBooking(Customer c,FlightBooking fb)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			c.flightBookings.add(fb);
			pm.makePersistent(c);
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
	public void removeBooking(Customer c,FlightBooking fb)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{	
			Iterator<FlightBooking> itr=flightBookings.iterator();

			
			while(itr.hasNext()){
				FlightBooking cur_fb = itr.next();
				if(cur_fb.getConfirmationNumber().equals(fb.getConfirmationNumber()))
					c.flightBookings.remove(cur_fb);//since a flightbooking is unique this works
					pm.makePersistent(c);
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
