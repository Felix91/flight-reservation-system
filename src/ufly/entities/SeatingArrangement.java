package ufly.entities;

import java.util.Vector;

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
		for( int i=1; i<=this.numRows; i++ )
		{
			for( int j=1; j<=this.numColumns; j++)
			{
				this.seats.add(new Seat(i, j));
			}
		}
	}
	/*------------ACCESSORS--------------*/
	
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
	@Persistent
	private Vector<Seat> seats;			// The SeatingArrangment's Seats. I do not think it's possible to store 2D collections in JDO. So just make use of the number of rows and columns to step through the 1D vector.
}
