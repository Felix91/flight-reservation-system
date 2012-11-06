package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import ufly.entities.Customer;

@SuppressWarnings("serial")
public class Login extends UflyServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{	
		if(req.getParameter("logout")!= null)
		{
			req.getSession().setAttribute("loggedInUser", null);
			resp.sendRedirect("/");
		}
		else if (getLoggedInUser(req.getSession())!=null)
		{
			resp.sendRedirect("/search");
		}
		req.setAttribute("date", new java.util.Date());
		req.getRequestDispatcher("login.jsp")
			.forward(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		resp.setContentType("text/plain");
		String email= req.getParameter("username");
		String Password= req.getParameter("password");
		
		Customer localUser = Customer.getCustomer(email);
		if (localUser != null && localUser.checkPassword(Password))
		{
			login(email,req.getSession());
			resp.sendRedirect("/");
		}
		else
		{
			req.setAttribute("failedEmail", email);
			req.setAttribute("loginError", "userNameNotFound");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
}
