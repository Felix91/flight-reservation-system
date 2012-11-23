package ufly.frs;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ufly.entities.Customer;
import ufly.entities.FlightManager;
import ufly.entities.FlightStaff;
import ufly.entities.User;

@SuppressWarnings("serial")
class UflyServlet extends HttpServlet {
	static protected class NullLoginUser extends Error {
	}

	public User getLoggedInUser(HttpSession s) {
		User localUser = null;
		final int ONE_MINUTE = 60*1000;
		final int longestPeriodOfInactivity=5*ONE_MINUTE;
		String email = (String) s.getAttribute("loggedInUser");
		Date lastModified = (Date) s.getAttribute("lastModified");
		if (email == null || (lastModified != null && lastModified.getTime()+longestPeriodOfInactivity < new Date().getTime())){
			return null;
		}else{
			s.setAttribute("lastModified", new Date());
		}
		// Check if it is Customer
		try {
			localUser = Customer.getCustomer(email);
		} catch (javax.jdo.JDOObjectNotFoundException e) {
		}
		if (localUser != null) {
			return localUser;
		}
		// Check if it is Flight Manager
		try {
			localUser = FlightManager.getFlightManager(email);
		} catch (javax.jdo.JDOObjectNotFoundException e) {
		}
		if (localUser != null) {
			return localUser;
		}

		// Check if it is Flight Staff
		try {
			localUser = FlightStaff.getFlightStaff(email);
		} catch (javax.jdo.JDOObjectNotFoundException e) {
		}
		if (localUser != null) {
			return localUser;
		}
		return null;
	}

	public void login(String email, HttpSession s) {
		s.setAttribute("loggedInUser", email);
		s.setAttribute("lastModified", new Date());
	}

	/**
	 * print out GET/POST key/values, Useful for debugging
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void printParam(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Enumeration e = req.getParameterNames();
		String s;
		for (; e.hasMoreElements();) {
			s = (String) e.nextElement();

			resp.getWriter().println(
					s
							+ "="
							+ ((String) req.getParameter(s) == "" ? "_EMPTY_"
									: (String) req.getParameter(s)));

		}
	}
}
