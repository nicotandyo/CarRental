package reservation;

import java.util.List;



/**
 * Account class represents a reservation
 * and role.
 * @author Nico Tandyo
 */
public class Car {

    
	private String myVin;
    private Double myPrice;
    private boolean myAvailable;
    
    

   
    public Car(final String theVin, final Double thePrice, final boolean theAvailable) {
    	myVin = theVin;
    	myPrice = thePrice;
    	myAvailable = theAvailable;
    }

    
    public String getVIN() {
        return myVin;
    }

    public void setVIN(String theVin) {
        this.myVin = theVin;
    }

    
    public void setPrice(Double thePrice) {
        this.myPrice = thePrice;
    }

    public Double getPrice() {
        return myPrice;
    }
    
    public void setAvailability(boolean theB) {
        this.myAvailable = theB;
    }

    public boolean getAvailability() {
        return myAvailable;
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
