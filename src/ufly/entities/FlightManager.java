package ufly.entities;

import java.util.ArrayList;
import java.util.Iterator;
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
}
