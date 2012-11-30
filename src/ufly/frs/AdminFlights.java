package ufly.frs;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Flight;
import ufly.entities.Meal;
import ufly.entities.Airport;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class AdminFlights extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		
		//if (getLoggedInUser(req.getSession())!=null)
		//{
		//	Customer loggedInCustomer = Customer.getCustomer(getLoggedInUser(req.getSession()).getEmailAddr());
		//	req.setAttribute("customerFirstName", loggedInCustomer.getFirstName());
		//	req.setAttribute("customerLastName", loggedInCustomer.getLastName());
		
		String pageToInclude= getServletConfig().getInitParameter("action");
		if(pageToInclude.equals("create") )
		{
			req.getRequestDispatcher("/adminFlights_create.jsp")
			.forward(req,resp);
		}else if (pageToInclude.equals("edit") )
		{
			String flightKey = req.getParameter("flightKey");
			
			Flight editFlight = Flight.getFlight(flightKey);
			if(editFlight != null){
				req.setAttribute("editFlight", editFlight);
				req.getRequestDispatcher("/adminFlights_edit.jsp")
					.include(req,resp);
			}
		}else{
		
		List<Flight> allFlights = Flight.getAllFlights();
		req.setAttribute("allFlights", allFlights);
			req.getRequestDispatcher("/_adminFlights.jsp")
			.include(req,resp);
		}
		//}
		//else{
		//	resp.sendRedirect("/");
		//}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		String pageToInclude= getServletConfig().getInitParameter("action");
		
		if (pageToInclude.equals("edit") )
		{
			String flightKey = req.getParameter("flightKey");
			
			Flight editFlight = Flight.getFlight(flightKey);
			if(editFlight != null){
				
				String origin = req.getParameter("origin");
				String destination = req.getParameter("destination");
				String departure = req.getParameter("departure");
				String arrival = req.getParameter("arrival");
				String price = req.getParameter("price"); 
				String allowableMealTypes = req.getParameter("allowableMealTypes"); 
				
				// Set departure date and arrival date 
				SimpleDateFormat convertToDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm");

				//Format of the allowMeal vector input string: "CK-BF-PK"
				StringTokenizer st = new StringTokenizer(allowableMealTypes, "-");
				Vector<Meal> thisFlightMeals = new Vector<Meal>();
				while (st.hasMoreTokens())
				{
			         String mealType = st.nextToken();
			         if (mealType.equalsIgnoreCase("ck")) {
							thisFlightMeals.add(Meal.chicken);
						}
						else if (mealType.equalsIgnoreCase("bf")) {
							thisFlightMeals.add(Meal.beef);

						}
						else if (mealType.equalsIgnoreCase("pk")) {
							thisFlightMeals.add(Meal.pork);
						}
						else if (mealType.equalsIgnoreCase("FH")) {
							thisFlightMeals.add(Meal.fish);

						}
						else if (mealType.equalsIgnoreCase("VG")) {
							thisFlightMeals.add(Meal.veggie);
						}
			     }
				
					if(convertToDate.parse(departure, new ParsePosition(0)) == null || convertToDate.parse(arrival, new ParsePosition(0)) == null){
						req.setAttribute("editFlight", editFlight);
						req.setAttribute("errorMsg", "Invalid date format!");
						req.getRequestDispatcher("/adminFlights_edit.jsp")
							.forward(req,resp);
					}else if(thisFlightMeals.size() == 0){
						req.setAttribute("editFlight", editFlight);
						req.setAttribute("errorMsg", "Invalid Flight Meals Field!");
						req.getRequestDispatcher("/adminFlights_edit.jsp")
							.forward(req,resp);
					}else if(origin.equals("") || destination.equals("") || departure.equals("") || arrival.equals("") || price.equals("") || allowableMealTypes.equals("") ) {
						req.setAttribute("editFlight", editFlight);
						req.setAttribute("errorMsg", "Missing Fields!");
						req.getRequestDispatcher("/adminFlights_edit.jsp")
							.forward(req,resp);
					}else
					{
						req.setAttribute("origin", origin);
						req.setAttribute("destination", destination);
						req.setAttribute("departure", departure);
						req.setAttribute("arrival", arrival);
						req.setAttribute("price", price);
						req.setAttribute("allowableMealTypes", allowableMealTypes);
						
						// To Do Change flight
						
						//Flight origin
						Airport a = Airport.getAirportByCallSign(origin);
						Key originKey = a.getKey();
						editFlight.changeOrigin(originKey);
						
						//Flight Destination
						Airport b = Airport.getAirportByCallSign(destination);
						Key destinationKey = b.getKey();
						editFlight.changeDestination(destinationKey);
						
						//Departure
						editFlight.changeDeparture(convertToDate.parse(departure, new ParsePosition(0)));
						//Arrival
						editFlight.changeArrival(convertToDate.parse(arrival, new ParsePosition(0)));

						// Change price
						editFlight.changePrice(Integer.valueOf(price));
						
						editFlight.changeAllowableMeals(thisFlightMeals);
						req.setAttribute("editFlight", editFlight);
						req.setAttribute("successMsg", "Successfully Updated!");
						req.getRequestDispatcher("/adminFlights_edit.jsp")
							.forward(req,resp);
					}
					
			}
		}
		
		
	}
}
