package ufly.entities;

import java.util.HashMap;
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
public class SeatingArrangement {
	
	public enum AircraftModel
	{
		BOEING_737, BOEING_777, AIRBUS_A320, AIRBUS_A340
	}
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a SeatingArrangment
	 * @param aircraftModel	: The SeatingArrangment's aircraft model which will determine the object's numRows, numColumns, numRowsFirstClass, numRowsBusinessClass, and numRowsEconomyClass
	 */
	public SeatingArrangement(String aircraftModel)
	{
		if( aircraftModel.equalsIgnoreCase("BOEING_737") )
		{
			this.numRows = 25;
			this.numColumns = 6;
			this.numRowsFirstClass = 3;
			this.numRowsBusinessClass = 5;
		}
		else if( aircraftModel.equalsIgnoreCase("BOEING_777") )
		{
			this.numRows = 43;
			this.numColumns = 9;
			this.numRowsFirstClass = 3;
			this.numRowsBusinessClass = 12;
		}
		else if( aircraftModel.equalsIgnoreCase("AIRBUS_A320") )
		{
			this.numRows = 30;
			this.numColumns = 6;
			this.numRowsFirstClass = 6;
			this.numRowsBusinessClass = 12;
		}
		else if( aircraftModel.equalsIgnoreCase("AIRBUS_A340") )
		{
			this.numRows = 48;
			this.numColumns = 8;
			this.numRowsFirstClass = 6;
			this.numRowsBusinessClass = 3;
		}
		
		this.numRowsEconomyClass = this.numRows - this.numRowsFirstClass - this.numRowsBusinessClass;
		
		// Add Seats
		seats = new Vector<Seat>();
		for( int i=1; i<=this.numRows; i++ )
		{
			for( int j=1; j<=this.numColumns; j++)
			{
				this.seats.add(new Seat(i, j));
			}
		}
		
		// debugging
		//System.out.println("Number of elements in Seats: " + this.seats.size());
	}
	/*------------ACCESSORS--------------*/
	/**
	 * 
	 * @return return the set of seats that are available
	 */
	public Vector<Seat> getAvailableSeats()
	{
		Vector<Seat> toRet=new Vector<Seat>();
		for(Seat seat : getSeats())
		{
			if (seat.getFlightBooking() == null)
			{
				toRet.add(seat);
			}
		}
		return toRet;
	}
	/**
	 * @return the key of seating arrangement
	 */
	public Key getKey()
	{
		return this.key;
	}
	
	/**
	 * @return the total number of rows
	 */
	public int getNumRows()
	{
		return this.numRows;
	}
	/**
	 * get a HashMap of the fields
	 */
	public HashMap<String,Integer> getHashMap(){
		HashMap toRet=new HashMap() ;
		toRet.put("numRows", this.numRows);
		toRet.put("numColumns", this.numColumns);
		toRet.put("numRowsFirstClass", this.numRowsFirstClass);
		toRet.put("numRowsBusinessClass",this.numRowsBusinessClass);
		toRet.put("numRowsEconomyClass", this.numRowsEconomyClass);
		return toRet;
	}
	/**
	 * @return the total number of columns
	 */
	public int getNumColumns()
	{
		return this.numColumns;
	}
	
	/**
	 * @return the number of first class rows
	 */
	public int getNumRowsFirstClass()
	{
		return this.numRowsFirstClass;
	}
	
	/**
	 * @return the number of business class rows
	 */
	public int getNumRowsBusinessClass()
	{
		return this.numRowsBusinessClass;
	}
	
	/**
	 * @return the number of economy class rows
	 */
	public int getNumRowsEconomyClass()
	{
		return this.numRowsEconomyClass;
	}
	/**
	 * get the flight class of seat
	 * @param seat
	 * @return the flight class of seat
	 */
	public FlightClass getFlightClass(Seat seat)
	{
		int seatrow=seat.getRowNumber();
		int curRow=this.getNumRowsFirstClass();
		if (seatrow<=curRow){
			return FlightClass.first;
		}
		curRow+=this.getNumRowsBusinessClass();
		if(seatrow <= curRow){
			return FlightClass.business;
		}
		return FlightClass.economy;
	}
	/**
	 * return an individual seat.
	 * @param row
	 * @param col
	 * @return
	 */
	public Seat getSeatByRowCol(Integer row,Character col)
	{
		int index = (row-1)*this.getNumColumns()+Character.getNumericValue(col)-Character.getNumericValue('A');
		return this.getSeats().get(index);
	}
	/**
	 * Get seats vector
	 * @return the vector containing all the Seats in this SeatingArrangement
	 */
	public Vector<Seat> getSeats()
	{
		return this.seats;
	}
	
	/*------------ CLASS METHODS --------------*/
	public static List<SeatingArrangement> getAllSeatingArrangement()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(SeatingArrangement.class);
            @SuppressWarnings("unchecked")
            List<SeatingArrangement> results = (List<SeatingArrangement>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}
	
	public static SeatingArrangement getSeatingArrangement(Key id)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SeatingArrangement sa, detached = null;
		try{
		    	sa = pm.getObjectById(SeatingArrangement.class, id);
		        detached = pm.detachCopy(sa);
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
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) // automatically generate a numeric ID
	private Key key;					// The SeatingArrangment's identifier. Note that since SeatingArrangment is a child class of the relationship with Flight, its key must either be a Key or a Key value encoded as a string.
	@Persistent
	private int numRows;				// The number of rows in the SeatingArrangement
	@Persistent
	private int numColumns;				// The number of columns in the SeatingArrangement
	@Persistent
	private int numRowsFirstClass;		// The number of rows in the first class of the SeatingArrangement
	@Persistent
	private int numRowsBusinessClass;	// The number of rows in the business class of the SeatingArrangement
	@Persistent
	private int numRowsEconomyClass;	// The number of rows in the economy class of the SeatingArrangement. numRowsEconomyClass = numRows-numRowsFirstClass-numRowsBusinessClass
	@Persistent(defaultFetchGroup = "true")
	private Vector<Seat> seats;			// The SeatingArrangment's Seats. I do not think it's possible to store 2D collections in JDO. So just make use of the number of rows and columns to step through the 1D vector.
}
