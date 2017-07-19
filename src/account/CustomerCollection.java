package account;

import data.CustomerDB;

import java.sql.SQLException;

/**
 * It's a collection of all the customer.
 * @author Nico Tandyo
 */
public class CustomerCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static CustomerDB myCustomerDB;
    

    
    public static boolean addCustomer(final String theName,
                                final String theAddress,
                                final String theEmail,
                                final String thePhone, final String theUser) throws SQLException {
        if (myCustomerDB == null) {
            myCustomerDB = new CustomerDB();
        }

        String message = myCustomerDB.addCustomer(theName, theAddress, theEmail, thePhone, theUser);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
    public static String[] getCustomer(final String theUser) throws SQLException {
		if (myCustomerDB == null) {
		myCustomerDB = new CustomerDB();
		}
		
		String[] result = myCustomerDB.getCustomer(theUser);
		return result;
    }
    
    public static int getCustomerID(final String theUser)throws SQLException {
		if (myCustomerDB == null) {
		myCustomerDB = new CustomerDB();
		}
		
		int result = myCustomerDB.getCustomerID(theUser);
		return result;
    }
}
