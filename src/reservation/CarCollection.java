package reservation;

import data.CarDB;
import design_pattern.Iterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a collection of all the car.
 * @author Nico Tandyo
 */
public class CarCollection {

    /**
     * Car database for connecting to the data base.
     */
    private static CarDB myCarDB;
    private static List<Car> myCarList;

    
     /**
      * Reserve
      * @param theId
      * @param theCustId
      * @param theVin
      * @param theDate
      * @return
      * @throws SQLException
      */
    public static boolean register(final String theVin, 
    		final Double thePrice, final boolean theAvailable) throws SQLException {
        if (myCarDB == null) {
            myCarDB = new CarDB();
        }

        String message = myCarDB.register(theVin, thePrice, theAvailable);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
//    /**
//     * Cancel Reserve
//     * @param theId
//     * @param theCustId
//     * @return
//     * @throws SQLException
//     */
//    public static boolean notAvailable(final String theVin) throws SQLException {
//        if (myCarDB == null) {
//            myCarDB = new ReservationDB();
//        }
//
//        String message = myCarDB.notAvailable(theVin);
//        if(message.equals("success")) {
//        	return true;
//        } else {
//        	return false;
//        }
//    }
//    public static boolean available(final String theVin) throws SQLException {
//        if (myCarDB == null) {
//            myCarDB = new CarDB();
//        }
//
//        String message = myCarDB.available(theVin);
//        if(message.equals("success")) {
//        	return true;
//        } else {
//        	return false;
//        }
//    }
    public static boolean addCar(final Car theCar) {
        if (myCarDB == null) {
        	myCarDB = new CarDB();
        }

        String message = myCarDB.addCar(theCar);
        return !message.startsWith("Error adding car:");
    }
    public int getSize() {
        if (myCarList == null) {
        	myCarList = showAll();
        }
        return myCarList.size();
    }
    public Iterator getIterator(final String theKey) {
        if (theKey == null) {
            myCarList = showAll();
        }
        return new CarIterator();
    }
    public static List<Car> showAll() {
    	myCarList = new ArrayList<>();
        if (myCarDB == null) {
        	myCarDB = new CarDB();
        }
        try {
        	myCarList = myCarDB.getCars();
            return myCarList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myCarList;
    }
    private class CarIterator implements Iterator {
        int index;

        /**
         * Looking there is next object or not.
         *
         * @return true if there is an object, false otherwise.
         */
        @Override
        public boolean hasNext() {
            if (index < myCarList.size()) {
                return true;
            }
            return false;
        }

        /**
         * Iterate to the next object.
         *
         * @return next object.
         */
        @Override
        public Object next() {
            if (this.hasNext()) {
                return myCarList.get(index++);
            }
            return null;
        }
    }
    
}
