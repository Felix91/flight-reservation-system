package ufly.frs;

import java.io.IOException;
import java.util.Date;
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
import ufly.entities.Meal;
import ufly.entities.PMF;
import ufly.entities.SeatingArrangement.AircraftModel;
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
			String arrival = request.getParameter("arrival");
			String departure = request.getParameter("departure");
			String meals = request.getParameter("meals");
			String aircraft = request.getParameter("aircraft");
			
			if(flightno ==null || origin == null ||
					destination ==null /*|| arrival == null ||
					meals ==null || aircraft == null*/)
			{
				response.getWriter().println("Missing entries");
				return;
			}
			
			
			Airport origin1=null;
			Airport destination1=null;
			List<Airport> la = Airport.getAllAirports();
			Iterator<Airport> it = la.iterator();
			response.getWriter().println("<ul>");
			
			// This is last ditch effort
			//origin1 = it.next();
			//dsestination1 = it.next();
			
			while (it.hasNext() && (origin1==null || destination1==null))
			{
				Airport temp = it.next();
				if (temp.getCallSign().equals(origin))
				{
					origin1 = temp;
				}
				else if (temp.getCallSign().equals(destination)) 
				{
					destination1 = temp;
				}
			}
			// for testing
			Date a = new Date();
			Date b = new Date();
			Vector<Meal> c = new Vector<Meal>();
			c.add(Meal.chicken);
			
			
			
			new Flight(flightno, origin1, destination1,a, b, c, AircraftModel.BOEING_777 );
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
		String emailAddr = request.getParameter("emailAddr");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		if(password == null || firstName == null || lastName == null)
		{
			if(emailAddr != null )
			{
				Customer c=Customer.getCustomer(emailAddr);
				if (c != null)
				{
					response.getWriter().println(c.toString());
				}else
				{
					response.getWriter().println("Customer("+emailAddr+") does not exist ");
				}
				
			}else{
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
						response.getWriter().println("<li>"+c.toString() +"</li>");
					}
				}finally{
					pm.close();
				}
			}
		}else{
			new Customer(emailAddr,password,firstName,lastName);
			response.sendRedirect("/entityTest?test=Customer");
		}
			
	}
}