package account;

import data.CustomerDB;
import data.OwnerDB;

import java.sql.SQLException;

/**
 * It's a collection of all the customer.
 * @author Nico Tandyo
 */
public class OwnerCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static OwnerDB myOwnerDB;
    

    /**
     * A login to find account is in the system or not.
     *
     * @param theName username.
     * @param theAddress  password.
     * @param theRole     A role 1 is Owner, 2 is Customer.
     * @return true if found, otherwise return false.
     * @throws SQLException if query is not correct.
     */
    public static boolean addOwner(final String theName,
                                final String theAddress,
                                final String theEmail,
                                final String thePhone, final String theUser) throws SQLException {
        if (myOwnerDB == null) {
            myOwnerDB = new OwnerDB();
        }

        String message = myOwnerDB.addOwner(theName, theAddress, theEmail, thePhone, theUser);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
    public static String[] getOwner(final String theUser) throws SQLException {
		if (myOwnerDB == null) {
			myOwnerDB = new OwnerDB();
		}
		
		String[] result = myOwnerDB.getOwner(theUser);
		return result;
    }
    
}
