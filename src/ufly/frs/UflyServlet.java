package ufly.frs;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import ufly.entities.Customer;
import ufly.entities.User;

@SuppressWarnings("serial")
public class UflyServlet extends HttpServlet {
	public User getLoggedInUser(HttpSession s)
	{
		User localUser =null;
		String email = (String) s.getAttribute("loggedInUser");
		if (email == null)
			return null;
		localUser = Customer.getCustomer(email);
		//add check for admin if localuser is null
		return localUser;			
	}
	public void login(String email,HttpSession s)
	{
		s.setAttribute("loggedInUser", email);	
	}
}
