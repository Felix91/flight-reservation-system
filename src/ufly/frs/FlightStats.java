package ufly.frs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Airport;
import ufly.entities.Flight;

@SuppressWarnings("serial")
public class FlightStats extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		
			List<Flight> la = Flight.getAllFlights();
			Iterator<Flight> it = la.iterator();
			Set<String> fn = new HashSet<String>();
			Flight ff;
			while(it.hasNext())
			{
				ff=it.next();
				String flightnum =ff.getFlightNumber();
				fn.add(flightnum);
			}
			req.setAttribute("flightnumber", fn);
			req.getRequestDispatcher("/_flightStats.jsp")
				.include(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		resp.setContentType("text/plain");
		
		
	}
}
