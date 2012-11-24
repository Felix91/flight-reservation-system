package ufly.frs;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import ufly.entities.*;

@SuppressWarnings("serial")
public class Manifest extends UflyServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException
 {
		SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		String flightNo=req.getParameter("flightNo");
		String datestr = req.getParameter("date");
		FlightStaff loggedInUser=null;
		try {
			loggedInUser = (FlightStaff) this.getLoggedInUser(req.getSession());
		}catch(ClassCastException e){
		} catch (UserInactivityTimeout e) {
			resp.sendRedirect("/?errorMsg=Sorry, you have been logged out because you have been inactive too long");
		}finally {
			if (loggedInUser == null || datestr==null || flightNo== null) {
				//do nothing
				return;
			}
		}
		Date departure = fullDateFormat.parse(datestr, new ParsePosition(0));
		Flight f = Flight.getFlight(flightNo, departure);
		
		Vector<FlightBooking> bookings = f.getBookings();
		Vector<HashMap> bookingsHM= new Vector<HashMap>(bookings.size());
		
		for(FlightBooking fb:bookings){
			bookingsHM.add(fb.getHashMap());
		}
		req.setAttribute("bookings", bookingsHM);
		req.getRequestDispatcher("manifest.jsp").forward(req, resp);
	}
}
