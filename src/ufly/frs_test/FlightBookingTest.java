package ufly.frs_test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Airport;
import ufly.entities.FlightBooking;
import ufly.entities.User;
import ufly.entities.Flight;

@SuppressWarnings("serial")
public class FlightBookingTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("FlightBookingTest.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		String bookedBy = req.getParameter("bookedBy");
		String bookedFlight = req.getParameter("bookedFlight");
		String bookedFlightClass = req.getParameter("bookedFlightClass");
		String bookedSeat = req.getParameter("bookedSeat");
		String mealChoice = req.getParameter("mealChoice");
	
		new FlightBooking(bookedBy,bookedFlight,bookedFlightClass,bookedSeat,mealChoice); // TODO: create Customer once User has proven to work
		// FlightBooking's constructor will automatically make object persistant

		resp.sendRedirect("/entityTest?test=FlightBooking");
	}
}
