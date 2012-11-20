package ufly.frs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.*;

@SuppressWarnings("serial")
public class SignedUp extends UflyServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException
	{
		
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String emailAddr = req.getParameter("email");
		String newPw = req.getParameter("newpassword");
		String confirmPw = req.getParameter("confirmnewpass"); // TO-DO: verify that newPw == confirmPw
		if(Customer.getCustomer(emailAddr)!=null){
			req.setAttribute("defFName", firstName);
			req.setAttribute("defLName", lastName);
			req.setAttribute("newpassword", newPw);
			req.setAttribute("confirmnewpass", confirmPw);
			req.setAttribute("errorMsg", "EmailExists");
			req.getRequestDispatcher("loginnew.jsp")
				.forward(req,resp);
			return;
		}
		if(!newPw.equals(confirmPw))
		{
			req.setAttribute("defFName", firstName);
			req.setAttribute("defLName", lastName);
			req.setAttribute("defEmail", emailAddr);
			req.setAttribute("errorMsg", "passwordMismatch");
			req.getRequestDispatcher("loginnew.jsp")
				.forward(req,resp);
			return;
		}
		//Call the toString so that it throws an exception if you give empty strings
		//should not happen if js works
		User newUser = new Customer(emailAddr.toString(), newPw.toString(),firstName.toString(),lastName.toString()); 
		newUser.login(req.getSession());
		/**
		 * if we interupted a booking to create a user, resume booking
		 * else redirect to homepage
		 */
		if(req.getSession().getAttribute("departopt")!=null )
		{
			resp.sendRedirect("/select");
		}else{
			resp.sendRedirect("/");
		}
	}
}
