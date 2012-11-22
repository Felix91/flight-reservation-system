package ufly.frs_test;
 
import static org.junit.Assert.*;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
 
import ufly.entities.*;
 
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
 
public class JUnitTest_Airport {
 
private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
 
@Before
    public void setUp() {
        helper.setUp();
    }
 
@After
    public void tearDown() {
        helper.tearDown();
    }
 
/**
 * Testing Airport.java
 */
@Test
public void testAirport_Airport() {
 
try {
 
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to create airport with constructors!");
}
 
}
 
@Test
public void testAirport_changeCallSign() {
try {
 
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
YYZ.changeCallSign("YVR");
} catch (Exception e) {
e.printStackTrace();
fail("Unable to change callsign of an airport!");
}
 
}
 
@Test
public void testAirport_addDepartingFlight() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);
Flight testFlightD = new Flight("CX123", "YVR", "YYC", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addDepartingFlight(testFlightD.getKey());
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to add departing flights for airports!");
}
 
}
 
@Test
public void testAirport_addArrivalingFlight() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);                
Flight testFlightA = new Flight("CX123", "YYC", "YVR", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addArrivalFlight(testFlightA.getKey());
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to add arriving flights for airports!");
}
 
}
 
@Test
public void testAirport_removeDepartingFlight() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);
Flight testFlightD = new Flight("CX123", "YVR", "YYC", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addDepartingFlight(testFlightD.getKey());
YVR.removeDepartingFlight(testFlightD.getKey());
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to delete departing flights for airports!");
}
 
}
 
@Test
public void testAirport_removeArrivalFlight() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);
Flight testFlightA = new Flight("CX123", "YYC", "YVR", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addArrivalFlight(testFlightA.getKey());
YVR.removeArrivalFlight(testFlightA.getKey());
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to delete arriving flights for airports!");
}
 
}
 
@Test
public void testAirport_changeCity() {
try {
 
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
YYZ.changeCity("Vancouver");
} catch (Exception e) {
e.printStackTrace();
fail("Unable to change callsign or change city of an airport!");
}
 
}
 
@Test
public void testAirport_getAllAirports() {
try {
 
List<Airport> testList = Airport.getAllAirports();
assertTrue(testList.isEmpty());
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to get a list of all airports which were added!");
}
 
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
List<Airport> testList = Airport.getAllAirports();
for (Airport a : testList) {
assertTrue(a.getCallSign() == "YYZ" || a.getCallSign() == "YYC" || a.getCallSign() == "YVR");
}
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to get a list of all airports which were added!");
}
}
 
@Test
public void testAirport_toString() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
String test = "Airport [callsign=YYZ, city=Toronto\n\tdepartures=[]\n\tarrivals=[]]";
 
assertTrue(test.equals(YYZ.toString()));
 
} catch (Exception e) {
fail("Unable to convert airport to string!");
}
}
 
@Test
public void testAirport_getAirport() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
 
assertTrue(Airport.getAirport(YYZ.getKey()) instanceof Airport );
assertTrue(Airport.getAirport(YYZ.getKey()).getCallSign().equals("YYZ"));
assertTrue(Airport.getAirport(YYZ.getKey()).getCity().equals("Toronto"));
 
} catch (Exception e) {
fail("Unable to get an airport with a key!");
}
 
}
 
@Test
public void testAirport_equals() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YYZ2 = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
 
assertTrue(Airport.getAirport(YYZ.getKey()).equals(YYZ2));
 
 
} catch (Exception e) {
fail("Unable to test if one airport is the same as another!");
}
 
}
 
@Test
public void testAirport_getCallSign() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YYZ2 = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
 
assertTrue(YYZ.getCallSign().equals("YYZ"));
 
 
} catch (Exception e) {
fail("Unable to get an airport's callsign!");
}
 
}
 
@Test
public void testAirport_getCity() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YYZ2 = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
 
assertTrue(YYZ.getCity().equals("Toronto"));
 
 
} catch (Exception e) {
fail("Unable to get an airport's city!");
}
 
}
 
@Test
public void testAirport_getnumDepartures() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);
Flight testFlightD = new Flight("CX123", "YVR", "YYC", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addDepartingFlight(testFlightD.getKey());
 
assertTrue(YVR.getnumDepartures() == 1);
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to add departing flights for airports!");
}
 
}
 
@Test
public void testAirport_getnumArrivals() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);                
Flight testFlightA = new Flight("CX123", "YYC", "YVR", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
YVR.addArrivalFlight(testFlightA.getKey());
 
assertTrue(YVR.getnumArrivals() == 1);
 
} catch (Exception e) {
e.printStackTrace();
fail("Unable to add arriving flights for airports!");
}
 
}
 
@Test
public void testAirport_getKey() {
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
assertFalse(YYZ.getKey().equals(YVR.getKey()));
 
 
} catch (Exception e) {
fail("Unable to get an airport with a key!");
}
 
 
}
 
//-----------------------------------------------------------------
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
 
try {
Airport YYZ = new Airport("YYZ", "Toronto", "43.6817,-79.6120");
Airport YVR = new Airport("YVR", "Vancouver", "49.1955,-123.1778");
Airport YCC = new Airport("YYC", "Calgary", "51.1139,-114.0203");
 
Integer testFlightPrice = new Integer(500000);
Flight testFlight = new Flight("CX123", "YVR", "YYZ", "2013.01.01 08:00", "2013.01.02 08:00", "BF", "BOEING_777", testFlightPrice);
 
 
} catch (Exception e) {
fail("Unable to create Flight object!");
}
}
 
/**
 * Testing FlightBooking.java
 */
@Test
public void testFlightBooking() {
assertTrue(true);
}
 
 
 
}
