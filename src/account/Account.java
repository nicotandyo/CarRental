package account;

/**
 * Account class represents a single account with username, password,
 * and role.
 * @author Nico Tandyo
 */
public class Account {

    
    private String myUserName;
    private String myPassword;
    private int myRole;

    /**
     * Creates an Account with the specified username and password.
     *
     * @param theUserName A String containing the Account username.
     * @param thePassword A String containing the Account password.
     */
    public Account(final String theUserName, final String thePassword) {
        if (theUserName == null || thePassword == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        this.myUserName = theUserName;
        this.myPassword = thePassword;
    }

    /**
     * Gets the Account username.
     *
     * @return A string representing the Account username.
     */
    public String getMyUserName() {
        return myUserName;
    }

    /**
     * Sets the Account username.
     *
     * @param myUserName A String containing
     *                   the Account username.
     */
    public void setMyUserName(String myUserName) {
        this.myUserName = myUserName;
    }

    /**
     * Gets the Account password.
     *
     * @return A string representing the Account password.
     */
    public String getMyPassword() {
        return myPassword;
    }

    /**
     * Sets the Account password.
     *
     * @param thePassword A String containing the Account password.
     */
    public void setMyPassword(final String thePassword) {
        this.myPassword = thePassword;
    }

    /**
     * Gets the Account role.
     *
     * @return An integer representing the Account role.
     */
    public int getMyRole() {
        return myRole;
    }

    /**
     * Sets the Account role.
     *
     * @param theRole An integer containing the Account role.
     */
    public void setMyRole(final int theRole) {
        this.myRole = theRole;
    }

//    /**
//     * It's for debugging.
//     *
//     * @return a String of all data
//     */
//    @Override
//    public String toString() {
//        return "Employee{" +
//                "myUserName='" + myUserName + '\'' +
//                ", myPassword='" + myPassword + '\'' +
//                ", myRole=" + myRole +
//                '}';
//    }
}
