package account;

/**
 * Account class represents a single Owner
 * and role.
 * @author Nico Tandyo
 */
public class Owner {

    
	private String myName;
    private String myAddress;
    private String myEmail;
    private String myPhone;
    private String myUser;
    
    

   
    public Owner(final String theName, final String theAddress, 
    		final String theEmail, final String thePhone, final String theUser) {
        myName = theName;
        myAddress = theAddress;
        myEmail = theEmail;
        myPhone = thePhone;
        myUser = theUser;
    }

    public String getUser() {
        return myUser;
    }
    public String getName() {
        return myName;
    }

    /**
     * Sets the name.
     *
     * @param theName Name.
     */
    public void setName(String theName) {
        this.myName = theName;
    }

    public String getAddress() {
        return myAddress;
    }
    public void setAddress(final String theAddress) {
        this.myAddress = theAddress;
    }

    public String getEmail() {
        return myEmail;
    }

    public void setEmail(String theEmail) {
        this.myEmail = theEmail;
    }
    public String getPhone() {
        return myPhone;
    }

    public void setPhone(String thePhone) {
        this.myPhone = thePhone;
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
