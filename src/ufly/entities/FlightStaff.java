package ufly.entities;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class FlightStaff extends Admin {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightStaff object
	 * @param emailAddr	: The FlightStaff's emailAddr
	 * @param password	: The FlightStaff's password in String format
	 */
	public FlightStaff(String emailAddr, String password)
	{
		super(emailAddr, password);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(this);
            
        } finally {
            pm.close();
        }
	}
	
	/*------------ CLASS METHODS ------------*/
	public static List<FlightStaff> getAllFlightStaff()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			Query q = pm.newQuery(FlightStaff.class);
            /**
             * in order to check this we need to check every element to see if it 
             * is of type User, too much work, plus we should not get any
             * other type. We just suppress the warning
             */
            @SuppressWarnings("unchecked")
            List<FlightStaff> results = (List<FlightStaff>) q.execute();
            return results;
		}finally{
			pm.close();
		}
	}

}
