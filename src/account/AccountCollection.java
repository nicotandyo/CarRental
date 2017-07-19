package account;

import data.AccountDB;

import java.sql.SQLException;

/**
 * It's a collection of all the account.
 * @author Nico Tandyo
 */
public class AccountCollection {

    /**
     * Account database for connecting to the data base.
     */
    private static AccountDB myAccountDB;
    

    /**
     * A login to find account is in the system or not.
     *
     * @param theUsername username.
     * @param thePassword  password.
     * @param theRole     A role 1 is Owner, 2 is Customer.
     * @return true if found, otherwise return false.
     * @throws SQLException if query is not correct.
     */
    public static boolean login(final String theUsername,
                                final String thePassword,
                                final int theRole) throws SQLException {
        if (myAccountDB == null) {
            myAccountDB = new AccountDB();
        }

        String message = myAccountDB.login(theUsername, thePassword, theRole);
        return message != null;
    }
    
    public static boolean register(final String theUsername,
            final String thePassword,
            final int theRole) throws SQLException {
		if (myAccountDB == null) {
			myAccountDB = new AccountDB();
		}
		String message = myAccountDB.register(theUsername, thePassword, theRole);
		return message != null;
	}
}
