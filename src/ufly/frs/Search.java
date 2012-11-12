package ufly.frs;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.Compare;
import ufly.entities.Airport;
import ufly.entities.Flight;


@SuppressWarnings("serial")
public class Search extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException ,ServletException
	{
		doPost(req,resp);
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
			List<Vector<HashMap<String,Object>>> flightsToPass = new Vector<Vector<HashMap<String,Object>>>();
			Iterator<Flight> it = flights.iterator();
			while(it.hasNext())
			{
				Flight f = it.next();
				if(f.getDestination().equals(destination))
				//this flight goes right to the destination
				{
					Vector trip = new Vector<HashMap<String,Object>>(1);
					
					HashMap flightAttributes = f.getHashMap();
					trip.add(flightAttributes);
					flightsToPass.add(trip);
				}else {
					/* This flight does not go directly there,check to see if there are any flights
					 * going from it to our destination 
					 */
					List<Flight> connectingFlights= Flight.getFlightsConnectingDateTime(f.getDestination(),destination,f.getArrival());
					Iterator<Flight> connIt = connectingFlights.iterator();
					HashMap<String,Object> firstLegAttr = f.getHashMap();  
					
					while(connIt.hasNext())
					{
						Vector trip = new Vector<HashMap<String,Object>>(1);
						trip.add(firstLegAttr);
						trip.add(connIt.next().getHashMap());
					}
				}
			}
			Collections.sort(flightsToPass,new TripDurationSort());
			req.setAttribute("thereTrips", flightsToPass);
			req.getRequestDispatcher("searchResults.jsp").forward(req,resp);
		}
		
	}
	private class TripDurationSort implements Comparator<Vector<HashMap<String,Object>>>
	{
		@Override
		public int compare(Vector<HashMap<String, Object>> o1,
				Vector<HashMap<String, Object>> o2) {
			long firstDuration=getTotalDuration(o1);
			long secondDuration=getTotalDuration(o2);
			if(firstDuration<secondDuration)
				return -1;
			if(firstDuration>secondDuration)
				return 1;
			return 0;
		}
		private Long getTotalDuration(Vector<HashMap<String,Object>> arg)
		{
			long d=0;
			Iterator<HashMap<String,Object>> it = arg.iterator();
			while (it.hasNext())
				d+=(Long)it.next().get("durationInMinutes");
			return d;
		}
		
	}

}
