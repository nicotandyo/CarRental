package reservation;

import java.util.List;



/**
 * Account class represents a reservation
 * and role.
 * @author Nico Tandyo
 */
public class Reservation {

    
	private int myID;
    private int myCustID;
    private String myVin;
    private java.sql.Date myDate;
    
    

   
    public Reservation(final int theId, final int theCustId, final String theVin, final java.sql.Date theDate) {
    	myID = theId;
    	myCustID = theCustId;
    	myVin = theVin;
    	myDate = theDate;
    }

    
    public int getReservationID() {
        return myID;
    }

    
    public void setReservationID(int theid) {
        this.myID = theid;
    }

    public int getCustomerID() {
        return myCustID;
    }
    public void setCustomerID(final int theID) {
        this.myCustID = theID;
    }

    public String getVIN() {
        return myVin;
    }

    public void setVIN(String theVin) {
        this.myVin = theVin;
    }
    public java.sql.Date getDate() {
        return myDate;
    }

    public void setDate(java.sql.Date theDate) {
        this.myDate = theDate;
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
