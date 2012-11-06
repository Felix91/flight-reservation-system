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
import ufly.entities.PMF;
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
	
		FlightBooking newFlightBooking = new FlightBooking(bookedBy,bookedFlight,bookedFlightClass,bookedSeat,mealChoice); // TODO: create Customer once User has proven to work
		// Flight's constructor will automaticallly make object persistent
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(newFlightBooking);
            
        } finally {
            pm.close();
        }
		
		//resp.setContentType("text/plain");
		//resp.getWriter().println("City: "+city+" your callsign is "+ callsign);
		resp.sendRedirect("/entityTest?test=FlightBooking");
	}
}
