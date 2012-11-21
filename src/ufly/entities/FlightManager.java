package ufly.entities;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightManager extends Admin {

	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightManager object
	 * @param emailAddr	: The FlightManager's emailAddr
	 * @param password	: The FlightManager's password in String format
	 */
	public FlightManager(String emailAddr, String password)
	{
		super(emailAddr, password);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}finally{
			pm.close();
		}
	
	}
	
	/*------------ CLASS METHODS ------------*/
	public static List<FlightManager> getAllFlightManager()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(FlightManager.class);
            /**
             * in order to check this we need to check every element to see if it 
             * is of type User, too much work, plus we should not get any
             * other type. We just suppress the warning
             */
            @SuppressWarnings("unchecked")
            List<FlightManager> results = (List<FlightManager>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}
	
	public static FlightManager getFlightManager(String emailAddr)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FlightManager c, detached = null;
		try{
		    	c = pm.getObjectById(FlightManager.class, emailAddr);
		        detached = pm.detachCopy(c);
		    }
		catch( javax.jdo.JDOObjectNotFoundException e)
		{
		}
		finally {
			pm.close();
		}
		return detached;
	}
}
