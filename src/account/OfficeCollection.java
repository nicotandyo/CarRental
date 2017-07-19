package account;

import data.CustomerDB;
import data.OfficeDB;

import java.sql.SQLException;

/**
 * It's a collection of all the customer.
 * @author Nico Tandyo
 */
public class OfficeCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static OfficeDB myOfficeDB;
    

    
    
    public static String[] getOffice(final int theId) throws SQLException {
		if (myOfficeDB == null) {
		myOfficeDB = new OfficeDB();
		}
		
		String[] result = myOfficeDB.getOffice(theId);
		return result;
    }
    
   
}
