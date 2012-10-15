package ufly.frs;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.PMF;
import ufly.entities.User;

@SuppressWarnings("serial")
public class UflySignedUp extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String emailAddr = req.getParameter("email");
		String newPw = req.getParameter("newpassword");
		String confirmPw = req.getParameter("confirmnewpass"); // TO-DO: verify that newPw == confirmPw
		resp.getWriter().println("Hello "+firstName+" your password is "+ newPw);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		User newUser = new User(emailAddr, newPw); // TO-DO: create Customer once User has proven to work
		
		try {
            pm.makePersistent(newUser);
            
            // Test query
            Query q = pm.newQuery(User.class);
            List<User> results = (List<User>) q.execute();
            Iterator<User> it = results.iterator();
            // Print all Users in the datastore
            resp.getWriter().println("Users registered: ");
            while(it.hasNext())
            {
            	resp.getWriter().println(it.next().getEmailAddr());
            }
        } finally {
            pm.close();
        }
	}
}
