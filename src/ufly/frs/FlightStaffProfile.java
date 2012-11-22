package ufly.frs;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.*;

@SuppressWarnings("serial")
public class FlightStaffProfile extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		FlightStaff loggedInUser = null;
		try {
			loggedInUser = (FlightStaff) this.getLoggedInUser(req.getSession());
		}catch(ClassCastException e){
		}finally {
			if (loggedInUser == null) {
				// not logged in, send to root page
				resp.sendRedirect("/");
			}
		}
		SimpleDateFormat convertToDate = new SimpleDateFormat(
				"MM-dd-yyyy");
		Date daytocheck = null;

		if (req.getParameter("Date") == null) {

			daytocheck = convertToDate.parse("11-13-2012",
					new ParsePosition(0));

		} else {
			daytocheck = convertToDate.parse(req.getParameter("Date"),
					new ParsePosition(0));
		}

		Vector<HashMap> flights = new Vector();
		List<Flight> flightList = Flight.getFlightsOnDate(daytocheck);
		for (Flight f : flightList) {
			HashMap<String, Object> hm = f.getHashMap();
			hm.put("capacity", new Integer(f.getCapacity()));
			hm.put("numBooked", new Integer(f.getNumBookedFlights()));
			flights.add(hm);
		}
		req.setAttribute("flights", flights);
		req.getRequestDispatcher("flightStaffProfile.jsp").forward(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.setContentType("text/plain");

	}
}
