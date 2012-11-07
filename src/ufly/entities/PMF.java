package ufly.entities;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
