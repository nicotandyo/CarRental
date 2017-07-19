package reservation;

import java.util.List;



/**
 * Account class represents a reservation
 * and role.
 * @author Nico Tandyo
 */
public class Rating {

    
	private int myId;
	private int myRid;
	private int myStar;
    private java.sql.Date myDate;
    private String myComments;
    
    

   
    public Rating(final int theId, final int theRid, 
    		final int theStar, final java.sql.Date theDate, final String theComments) {
    	myId = theId;
    	myRid = theRid;
    	myStar = theStar;
    	myDate = theDate;
    	myComments = theComments;
    }

    
    public int getId() {
        return myId;
    }

    public void setId(int theId) {
        this.myId = theId;
    }
    public int getRid() {
        return myRid;
    }

    public void setRid(int theId) {
        this.myRid = theId;
    }
    
    public int getStar() {
        return myStar;
    }

    public void setStar(int theStar) {
        this.myStar = theStar;
    }
    
    public java.sql.Date getDate() {
        return myDate;
    }

    public void setDate(java.sql.Date theDate) {
        this.myDate = theDate;
    }
    
    
    public void setComments(String theB) {
        this.myComments = theB;
    }

    public String getComments() {
        return myComments;
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
