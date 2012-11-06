package ufly.entities;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;



@PersistenceCapable (detachable="true")
public class Customer extends User {
	
	/*------------ CONSTRUCTORS ------------*/
	public Customer(String emailAddr, String password, String firstName, String lastName)
	{
		super(emailAddr, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.loyaltyPoints = 0; // Every Customer starts with 0 loyalty points
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}finally{
			pm.close();
		}
	}
	
	/*------------CLASS METHODS---------------*/
	public static List<Customer> getAllCustomers()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(Customer.class);
            @SuppressWarnings("unchecked")
            List<Customer> results = (List<Customer>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}
	
	public static Customer getCustomer(String emailAddr)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Customer c, detached = null;
		try{
		    	c = pm.getObjectById(Customer.class, emailAddr);
		        detached = pm.detachCopy(c);
		    } finally {
			pm.close();
		}
		return detached;
	}
	
	
	/*------------MODIFIERS---------------*/
	/**
	 * @param newFirstName	: new first name to update to
	 */
	public void changeFirstName(String newFirstName)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			this.firstName=newFirstName;
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newLastName	: new last name to update to
	 */
	public void changeLastName(Customer c,String newLastName)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		//TODO: Implement a check to see this operation is legal
		try
		{
			this.lastName=newLastName;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param loyaltypoints	: add loyalty points
	 */
	public void addLoyaltyPoints(Customer c,int loyaltypoints)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{
			this.loyaltyPoints+=loyaltypoints;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param loyalpoints	: use loyalty points
	 */
	public void useLoyaltyPoints (int loyaltypoints){
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			
			this.loyaltyPoints-=loyaltypoints;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param fb	: add fb into flightbooking vector
	 */
	public void addBooking(FlightBooking fb)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.flightBookings.add(fb);
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param fb	: remove fb from flightbooking vector
	 */
	public void removeBooking(FlightBooking fb)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{	
			Iterator<FlightBooking> itr=this.flightBookings.iterator();

			
			while(itr.hasNext()){
				FlightBooking cur_fb = itr.next();
				if(cur_fb.getConfirmationNumber().equals(fb.getConfirmationNumber()))
				{
					this.flightBookings.remove(cur_fb);//since a flightbooking is unique this works
					pm.makePersistent(this);
					break;
				}
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
