package ufly.entities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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
		// Leave gender set as male, address and dob unset
		// Customer can change these in the profile page
		this.male = true;
		this.address = null;
		this.dob = null;
		this.loyaltyPoints = 0; // Every Customer starts with 0 loyalty points
		this.flightBookings = new Vector<Key>();
		
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
		    }
		catch( javax.jdo.JDOException e)
		{
		}
		finally {
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
	public void changeLastName(String newLastName)
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
	public void addLoyaltyPoints(int loyaltypoints)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			//this.flightBookings.add(fbKey);
			//pm.makePersistent(this);
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			c.loyaltyPoints+=loyaltypoints;
			pm.makePersistent(c);
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
			//this.flightBookings.add(fbKey);
			//pm.makePersistent(this);
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			c.loyaltyPoints-=loyaltypoints;
			pm.makePersistent(c);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newMale	: whether Customer is male
	 */
	public void changeGender(boolean newMale)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			this.male = newMale;
			pm.makePersistent(c);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newDob	: DOB to change to
	 */
	public void changeDob(Date newDob)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			this.dob = newDob;
			pm.makePersistent(c);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newAddress	: address to change to
	 */
	public void changeAddress(String newAddress)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			this.address = newAddress;
			pm.makePersistent(c);
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param fb	: add fb into flightbooking vector
	 */
	public void addFlightBooking(Key fbKey)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			//this.flightBookings.add(fbKey);
			//pm.makePersistent(this);
			// Apparently, the above 2 lines do not update the object in the datastore
			// But getting a fresh copy and making that fresh copy persistent works. Oh well...
			Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
			c.flightBookings.add(fbKey);
			pm.makePersistent(c);
		}finally
		{
			pm.close();
		}
	}

	/**
	 * @param fb	: remove fb from flightbooking vector
	 */
	public void removeBooking(Key fb)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();

		try
		{	
			Iterator<Key> itr=this.flightBookings.iterator();

			
			while(itr.hasNext()){
				Key cur_fb = itr.next();
				if( cur_fb.toString().equals(fb.toString()) )
				{
					// Yet another unable to update problem...
					// I think Customer is not actually detachable.
					Customer c = (Customer)pm.getObjectById(Customer.class, this.getEmailAddr());
					c.flightBookings.remove(cur_fb);//since a flightbooking is unique this works
					pm.makePersistent(c);
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

	@Override
	public String getDisplayName()
	{
		return this.getFirstName()+" "+this.getLastName();
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
	public Vector<Key> getFlightBookings()
	{
		return this.flightBookings;
	}
	
	@Override
	public String toString() 
	{
		return "Customer [email=" + this.getEmailAddr()
				+ ", fname=" + this.getFirstName()
				+ ", lname=" + this.getLastName()
				+ ", loyalty_pts=" + this.getLoyaltyPoints()
				+ ", flightBookings=" + flightBookings.toString() + " #elems: " + flightBookings.size()
				+ "]";
	}
	
	
	/*------------ VARIABLES ------------*/
	@Persistent
	String firstName;
	@Persistent
	String lastName;
	@Persistent
	boolean male;			// Gender. true for male, false for female. I know, I know...
	@Persistent
	String address;
	@Persistent
	Date dob;
	@Persistent
	int loyaltyPoints;
	@Persistent(defaultFetchGroup = "true")
	private Vector<Key> flightBookings;	// The Customer's flight bookings

}
