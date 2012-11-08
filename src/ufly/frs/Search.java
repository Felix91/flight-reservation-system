package ufly.frs;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Airport;
import ufly.entities.Flight;


@SuppressWarnings("serial")
public class Search extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException ,ServletException
	{
		req.getRequestDispatcher("flightSearch.jsp")
		.forward(req,resp);
		
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException,ServletException {
		//printParam(req,resp);
		Date departureDate = null;
		Airport origin = null;
		//String directOrConnect = req.getParameter("directOrConnect");
		//String returnDate= req.getParameter("returnDate");
		//String numPax	= req.getParameter("numPax");
		Airport destination = null;
		String a = req.getParameter("departureDate");
		SimpleDateFormat convertToDate = new SimpleDateFormat("MM-dd-yyyy");
		if ( a != null ){
			departureDate = convertToDate.parse(a, new ParsePosition(0));
		}if(req.getParameter("origin")!=null){
			origin = Airport.getAirportByCallSign(req.getParameter("origin"));
		}if(req.getParameter("destination") != null){
			destination = Airport.getAirportByCallSign(req.getParameter("destination"));
		}
		if (origin != null && departureDate != null)
		{
			List<Flight> flights= Flight.getFlightsOriginDate(origin,departureDate);
			Vector<Vector<HashMap<String,Object>>> flightsToPass = new Vector<Vector<HashMap<String,Object>>>();
			Iterator<Flight> it = flights.iterator();
			while(it.hasNext())
			{
				Flight f = it.next();
				if(f.getDestination().equals(destination))
				//this flight goes right to the destination
				{
					Vector trip = new Vector<HashMap<String,Object>>(1);
					
					HashMap flightAttributes = new HashMap<String,Object>();
					flightAttributes.put("flightNo", f.getFlightNumber());
					
					GregorianCalendar cald = new GregorianCalendar();
					cald.setTime(f.getDeparture());
					flightAttributes.put("departs", cald);
					
					GregorianCalendar cala = new GregorianCalendar();
					cala.setTime(f.getArrival());
					flightAttributes.put("arrives",cala);
					
					flightAttributes.put("stops", new Integer(1));
					
					long durationInMins = (cala.getTimeInMillis()-cald.getTimeInMillis())/1000/60;
					flightAttributes.put("durationInMins", new Long(durationInMins));
					
					flightAttributes.put("Price", new Integer(-1));
					trip.add(flightAttributes);
					flightsToPass.add(trip);
				}
			}
			req.setAttribute("trips", flightsToPass);
			req.getRequestDispatcher("searchResults.jsp").forward(req,resp);
			//resp.getWriter().println(flightsToPass.toString());
		}
		
	}
}
