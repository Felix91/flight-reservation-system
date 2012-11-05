package ufly.frs;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Airport;
import ufly.entities.Customer;
import ufly.entities.Flight;
import ufly.entities.FlightManager;
import ufly.entities.Meal;
import ufly.entities.PMF;
import ufly.entities.SeatingArrangement.AircraftModel;
import ufly.entities.Airport;

@SuppressWarnings("serial")
public class Test extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {
		
		String test=request.getParameter("test");
		response.setContentType("text/html");
		response.getWriter().println("<html><body><h2>welcome to the test servlet</h2>");
		if( test == null )
		{

		}
		else if( test.equalsIgnoreCase("Customer") == true )
		{
			testCustomer(request,response);
		}
		else if( test.equalsIgnoreCase("FlightManager") == true )
		{
			testFlightManager(request,response);
		}
		else if( test.equalsIgnoreCase("Airport") == true )
		{
			testAirport(request,response);

		}
		else if( test.equalsIgnoreCase("Flight") == true )
		{
			testFlight(request, response);
		}
		response.getWriter().println("</body></html>");
	
    }
	

	private void testFlight(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		/**
		 * URIs that to test flight:
		 * Assuminging you have YVR and LAX in your database
		 * http://localhost:8888/entityTest?test=Flight&flightno=abc&origin=YVR&destination=LAX&date=2012/11/11&leaveTime=4:30&arriveTime=8:30
		 */
		response.getWriter().println("<h3>testing flights</h3>");
		
		if(request.getParameter("flightno")==null)
		{
			List<Flight> fl = (List<Flight>) Flight.getAllFlights();
			Iterator<Flight> it = fl.iterator();
			response.getWriter().println("<ul>");
			while (it.hasNext())
			{
				Flight f = it.next();
				response.getWriter().println("<li>"+f.toString()+"</li>");
			}
			response.getWriter().println("</ul>");
		}
		else
		{
			
			String flightno = request.getParameter("flightno");
			String origin = request.getParameter("origin");
			String destination = request.getParameter("destination");
			String departure = request.getParameter("departure");
			String arrival = request.getParameter("arrival");
			String mealTypes = request.getParameter("mealsTypes");
			String aircraftModel = request.getParameter("aircraftModel");
			
			
			if(flightno ==null || origin == null ||
					destination ==null || arrival == null || departure == null ||
					mealTypes ==null || aircraftModel == null)
			{
				response.getWriter().println("Missing entries");
				return;
			}
			
			new Flight(flightno, origin, destination, departure, arrival, mealTypes, aircraftModel);
			
			response.sendRedirect("/entityTest?test=Flight");
		}
	}
	
	
	private void testAirport(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("callSign")==null)
		{
			
			List<Airport> la = Airport.getAllAirports();
			Iterator<Airport> it = la.iterator();
			response.getWriter().println("<ul>");
			while (it.hasNext())
			{
				response.getWriter().println("<li>"+it.next().toString()+"</li>");
			
			}
			response.getWriter().println("</ul>");
		}
		else
		{
			String city = request.getParameter("city");
			String csign = request.getParameter("callSign");
			if(city ==null || csign == null)
			{
				response.getWriter().println("Need both city or callsign");
				return;
			}
			new Airport(csign,city);
			response.sendRedirect("/entityTest?test=Airport");
		}
	}
	
	private void testCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("emailAddr")==null)
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
	            // TODO make this a query a class method
	            Query q = pm.newQuery(Customer.class);
	            /**
	             * in order to check this we need to check every element to see if it 
	             * is of type User, too much work, plus we should not get any
	             * other type. We just suppress the warning
	             */
	            @SuppressWarnings("unchecked")
				List<Customer> results = (List<Customer>) q.execute();
	            Iterator<Customer> it = results.iterator();
	            // Print all Users in the datastore
	            response.getWriter().println("Customers registered: ");
	            response.getWriter().println("<ul>");
	            Customer c;
				while (it.hasNext())
				{
					c = it.next();
					response.getWriter().println("<li>"+c.getFirstName() + " " + c.getLastName() +"</li>");
				
				}
				response.getWriter().println("</ul>");
	        } finally {
	            pm.close();
	        }		
		}
		else
		{
			String emailAddr = request.getParameter("emailAddr");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			if(emailAddr == null || password == null || firstName == null || lastName == null)
			{
				response.getWriter().println("Missing parameters");
				return;
			}
			new Customer(emailAddr,password,firstName,lastName);
			response.sendRedirect("/entityTest?test=Customer");
		}
	}
	
	private void testFlightManager(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("emailAddr")==null)
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				List<FlightManager> results = FlightManager.getAllFlightManager();
	            Iterator<FlightManager> it = results.iterator();
	            // Print all FlightManagers in the datastore
	            response.getWriter().println("FlightManagers registered: ");
	            response.getWriter().println("<ul>");
	            FlightManager fm;
				while (it.hasNext())
				{
					fm = it.next();
					response.getWriter().println("<li>"+ fm.getEmailAddr() +"</li>");
				}
				response.getWriter().println("</ul>");
	        } finally {
	            pm.close();
	        }		
		}
		else
		{
			// Add new FlightManager here. Optional.
		}
	}
}