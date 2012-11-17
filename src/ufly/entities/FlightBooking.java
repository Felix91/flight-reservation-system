package ufly.entities;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class FlightBooking extends SuperEntity {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightBooking object
	 * @param bookedBy
	 * @param bookedFlight
	 * @param bookedFlightClass	: The FlightBookiing's flight class. Must either be "first", "business", or "economy"
	 * @param bookedSeat		: The FlightBooking's booked seat. In "<row#><columnChar>" format e.g. "03C", "12D"
	 * @param mealChoice		: The FlightBooking's meal choice. E.g. "beef"
	 */
	public FlightBooking(String bookedBy, String bookedFlight, String bookedFlightClass, String bookedSeat, String mealChoice)
	{
		// Key will be generated automatically
		
		// Set customer
		Customer c = Customer.getCustomer(bookedBy);
		this.bookedBy = c.getEmailAddr();
		
		// Set flight
		Flight f = Flight.getFlight(bookedFlight);
		this.bookedFlight = f.getKey();
		
		// Set flight class
		if (bookedFlightClass.equalsIgnoreCase("first")) 
		{
			this.bookedFlightClass = FlightClass.first;
		}
		else if (bookedFlightClass.equalsIgnoreCase("business"))
		{
			this.bookedFlightClass = FlightClass.business;
		}
		else
		{
			this.bookedFlightClass = FlightClass.economy;
		}
		
		// Set seat
		int rowNum = Integer.parseInt(bookedSeat.substring(0, 2));
		char colChar = bookedSeat.charAt(2);
		SeatingArrangement sa = f.getSeatingArrangement();		
		Vector<Seat> seats = sa.getSeats();
		Iterator<Seat> seatsIt = seats.iterator();
		Seat s = null;
		// Attempt to find Seat entity
		while( seatsIt.hasNext() )
		{
			s = seatsIt.next();
			if( s.getRowNumber()==rowNum && s.getColumn()==colChar )
			{
				this.bookedSeat = s.getKey();
				break;
			}
		}
		if( this.bookedSeat == null )
		{
			throw new NullPointerException();
		}
		else
		{
			System.out.println("Seat found: " + this.bookedSeat.toString());
		}
		
		//  Set meal choice
		this.mealChoice = Meal.valueOf(mealChoice);
		
		// Finally, add FlightBooking to datastore
		// TODO use parent class method
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}finally{
			pm.close();
		}
		
		// Add this to Customer's flightBookings
		c.addFlightBooking(this.getConfirmationNumber());
		// Add this to Flight's flightBookings
		f.addFlightBooking(this.getConfirmationNumber());
		// Occupy Seat
		s.setFlightBooking(this.getConfirmationNumber());
	}
	/**
	 * 
	 * Create a FlightBooking, does NOT add this booking to flight,seat, or customer 
	 * It is recommended that you do that
	 * 
	 * @param bookedByUser User assosiated with this booking
	 * @param flight	   flight to book
	 * @param fclass       class of booking ie first, business,economy
	 * @param bookedSeat   seat that will be booked
	 * @param mealChoice   choice of meal for this booking
	 */
	public FlightBooking(Customer bookedByUser,Flight flight, Seat bookedSeat,Meal mealChoice)
	{
		this.bookedBy=bookedByUser.getEmailAddr();
		this.bookedFlight=flight.getKey();
		FlightClass fclass =flight.getSeatingArrangement().getFlightClass(bookedSeat);
		this.bookedFlightClass=fclass;
		this.bookedSeat=bookedSeat.getKey();
		this.mealChoice=mealChoice;
		this.makePersistant();
	}
	/*------------ CLASS METHODS ------------*/
	public static List<FlightBooking> getAllFlightBookings()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(FlightBooking.class);
            @SuppressWarnings("unchecked")
            List<FlightBooking> results = (List<FlightBooking>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}
	
	public static FlightBooking getFlightBooking(Key confirmationNumber)
	{
		if (confirmationNumber == null)
			return null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FlightBooking fb, detached = null;
		try{
		    	fb = pm.getObjectById(FlightBooking.class, confirmationNumber);
		        detached = pm.detachCopy(fb);
		    }
		catch( javax.jdo.JDOException e)
		{
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
		return detached;
	}
	
	public static FlightBooking getFlightBooking(Long confirmationNumber)
	{
		if (confirmationNumber == null)
			return null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FlightBooking fb, detached = null;
		try{
		    	fb = pm.getObjectById(FlightBooking.class, confirmationNumber);
		        detached = pm.detachCopy(fb);
		    }
		catch( javax.jdo.JDOException e)
		{
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
		return detached;
	}
	
	@Override
	public String toString() 
	{
		return "FlightBooking [key=" + confirmationNumber.toString()
				+ ", customer=" + bookedBy.toString()
				+ ", flight=" + bookedFlight.toString()
				+ ", flightClass=" + bookedFlightClass.toString()
				+ ", seat=" + bookedSeat.toString()
				+ ", meal=" + mealChoice 
				+ "]";
	}
	
	/*------------MODIFIERS--------------*/
	/**
	 * @param newConfirmationNumber	: new confirmation number to change to
	 */
	public void changeConfirmationNumber(Key newConfirmationNumber)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.confirmationNumber=newConfirmationNumber;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newCustomer	: new customer to change to
	 */
	/*public void changeCustomer(Customer newCustomer)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedBy=newCustomer;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}*/
	/**
	 * @param newFlight	: new flight booked to change to
	 */
	/*public void changeFlight(Flight newFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedFlight=newFlight;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}*/
	
	/**
	 * @param newFlightClass	: new fight class to change to
	 */
	public void changeFlightClass(FlightClass newFlightClass)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedFlightClass=newFlightClass;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newSeat	: new seat booking to change to
	 */
	public void changeSeat(Seat newSeat)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			// Empty old Seat
			Seat.getSeat(this.bookedSeat).clearFlightBooking();
			
			// Occupy new Seat
			newSeat.setFlightBooking(this.getConfirmationNumber());
			
			// Update key for Seat
			this.bookedSeat = newSeat.getKey();
			
			// Update Flight Class
			Flight f = Flight.getFlight(this.bookedFlight);
			FlightClass fclass = f.getSeatingArrangement().getFlightClass(newSeat);
			this.bookedFlightClass = fclass;
			
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newMeal	: new meal choice to change to
	 */
	public void changeMealChoice(Meal newMeal)
	{
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.mealChoice=newMeal;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * Check in FlightBooking
	 */
	public void checkIn()
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.checkedIn = true;
			pm.makePersistent(this);
		}finally
		{
			pm.close();
		}
	}
	
	
	/*------------ACCESSORS--------------*/
	/**
	 * @return the confirmation number of booking
	 */
	public Key getConfirmationNumber()
	{
		return this.confirmationNumber;
	}
	
	/**
	 * @return the customer object
	 */
	public Customer getBookedBy()
	{
		Customer c = Customer.getCustomer(this.bookedBy);
		return c;
	}
	
	/**
	 * @return the flight booked by customer
	 */
	/*public Flight getBookedFlight()
	{
		return this.bookedFlight;
	}*/
	
	/**
	 * @return the flight class of booked flight
	 */
	public FlightClass getBookedFlightClass()
	{
		return this.bookedFlightClass;
	}
	
	/**
	 * @return the booked seat
	 */
	/*public Seat getBookedSeat()
	{
		return this.bookedSeat;
	}*/
	
	/**
	 * @return the meal choice
	 */
	public Meal getMealChoice()
	{
		return this.mealChoice;
	}
	
	/**
	 * @return the checked-in status
	 */
	public boolean getCheckedIn()
	{
		return this.checkedIn;
	}
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) // automatically generate a numeric ID
	private Key confirmationNumber;			// The FlightBooking's confirmation number. Note that since FlightBooking is a child class of entity relationships, its key must either be a Key or a Key value encoded as a string.
	@Persistent
	private String bookedBy;				// The FlightBooking's owner
	@Persistent
	private Key bookedFlight;			// The FlightBooking's flight
	@Persistent
	private FlightClass bookedFlightClass;	// The FlightBooking's flight class
	@Persistent // Always load the Seat when parent (this) is loaded
	private Key bookedSeat;				// The FlightBooking's booked seat
	@Persistent
	private Meal mealChoice;				// The FlightBooking's meal choice
	@Persistent
	private boolean checkedIn;
	
}
