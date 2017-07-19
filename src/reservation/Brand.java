package reservation;

import java.util.List;



/**
 * Account class represents a reservation
 * and role.
 * @author Nico Tandyo
 */
public class Brand {

    
	private String myModels;
	private String myMakes;
	private String myBS;
    private int myCapacity;
    private String myVin;
    
    

   
    public Brand(final String theModels, final String theMakes, final String theBS, final int theCapacity, final String theVin) {
    	myModels = theModels;
    	myMakes = theMakes;
    	myBS = theBS;
    	myCapacity = theCapacity;
    	myVin = theVin;
    }

    
    public String getVIN() {
        return myVin;
    }

    public void setVIN(String theVin) {
        this.myVin = theVin;
    }

    
    public void setCapacity(int theCapacity) {
        this.myCapacity = theCapacity;
    }

    public int getCapacity() {
        return myCapacity;
    }
    
    public void setBS(String theB) {
        this.myBS = theB;
    }

    public String getBS() {
        return myBS;
    }
    
    public void setMake(String theB) {
        this.myMakes = theB;
    }

    public String getMake() {
        return myMakes;
    }
    public void setModel(String theB) {
        this.myModels = theB;
    }

    public String getModel() {
        return myModels;
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
