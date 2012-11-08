package ufly.entities;

import javax.jdo.PersistenceManager;


abstract class SuperEntity {
	void makePersistant()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(this);
		}catch( javax.jdo.JDOException e){
			e.printStackTrace();
			//re calling the method on failure seems to work, but someone should
			//figure out if you can close the pm twice
			this.makePersistant();
		}finally{
			pm.close();
		}
	}
}
