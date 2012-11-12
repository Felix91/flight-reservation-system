package ufly.entities;

import static org.junit.Assert.*;

import javax.jdo.annotations.PersistenceCapable;

import org.junit.Test;
import ufly.entities.Airport;


/* JUnit Test Cases for determining functionality of primitive user-defined classes
 * for the uFly Flight Booking Reservation System.
 * 
 * This class will glass-box test all the constructors and modifier methods
 * of each of the publically accessible methods in the ufly.entities package
 * 
 * Created by the uFly QA Team
 * November 11, 2012
 * 
 * 
 * 
 */

public class EntityJUnitTest {

	/**
	 * Testing Airport.java
	 */
	@Test
	public void testAirport() {
		try {
			// Constructors
			Airport a = new Airport("YYZ", "Toronto");
			fail("test");
			//Airport YVR = new Airport("YVR", "Vancouver");
			//Airport YCC = new Airport("YCC", "Calgary");
			
			// Modifiers
			//YYZ.changeCallSign("YVR");
			//YYZ.changeCity("Vancouver");
			
			//assertTrue(YYZ.equals(YVR));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unable to create airport");
		}
	}
	
	/**
	 * Testing Customer.java
	 */
	@Test
	public void testCustomer() {
		assertTrue(true);
	}
	
	/**
	 * Testing FlightManager.java
	 */
	@Test
	public void testFlightManager() {
		assertTrue(true);
	}
	
	/**
	 * Testing FlightStaff.java
	 */
	@Test
	public void testFlightStaff() {
		assertTrue(true);
	}

	/**
	 * Testing Flight.java
	 */
	@Test
	public void testFlight() {
		assertTrue(true);
	}
	
	/**
	 * Testing FlightBooking.java
	 */
	@Test
	public void testFlightBooking() {
		assertTrue(true);
	}
	
	
	
}
