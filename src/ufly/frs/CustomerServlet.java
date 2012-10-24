package ufly.frs;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.User;

@SuppressWarnings("serial")

/**
 * add all logic that is common to all customer pages here.
 * eg: process whether there is a user logged in
 * @author joel
 *
 */
public abstract class CustomerServlet extends HttpServlet {

	public abstract void doGet(HttpServletRequest req, HttpServletResponse resp);
	public abstract void doPost(HttpServletRequest req, HttpServletResponse resp);
	
	public User processLoggedInUser(HttpServletRequest req,HttpServletResponse resp)
	{
		return null;
	}
}
