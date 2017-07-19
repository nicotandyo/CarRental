package reservation;

import data.ReservationDB;
import design_pattern.Iterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a collection of all the reservation.
 * @author Nico Tandyo
 */
public class ReservationCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static ReservationDB myReservationDB;
    private static List<Reservation> myReservationList;

    
     /**
      * Reserve
      * @param theId
      * @param theCustId
      * @param theVin
      * @param theDate
      * @return
      * @throws SQLException
      */
    public static boolean reserve(final int theId, final int theCustId, 
    		final String theVin, final java.sql.Date theDate) throws SQLException {
        if (myReservationDB == null) {
            myReservationDB = new ReservationDB();
        }

        String message = myReservationDB.reserve(theId, theCustId, theVin, theDate);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
    /**
     * Cancel Reserve
     * @param theId
     * @param theCustId
     * @return
     * @throws SQLException
     */
    
    public static boolean cancelReservation(final int theId, final int theCustId) throws SQLException {
        if (myReservationDB == null) {
            myReservationDB = new ReservationDB();
        }
        try {
        	String message = myReservationDB.cancelReservation(theId, theCustId);
        	if(message.equals("success")) {
        		return true;
        	} else {
        		return false;
        	}
        } catch (NullPointerException npe) {
           System.out.println("");
        }
        return true;
    }
    public static java.sql.Date getReservationDate(final int theReservationID) {
    	if (myReservationDB == null) {
    		myReservationDB = new ReservationDB();
    	}
    	java.sql.Date date = myReservationDB.getReservationDate(theReservationID);
    	return date;
    }
    public static boolean updateReservation(final Reservation theReservation,
    		final String theColumn, final Object theData) {
    	if (myReservationDB == null) {
    		myReservationDB = new ReservationDB();
    	}
    	String message = myReservationDB.updateReservation(theReservation, theColumn, theData);
    	return !message.startsWith("Error updating reservation: ");
    }
    public static boolean addReservation(final Reservation theReservation) {
        if (myReservationDB == null) {
        	myReservationDB = new ReservationDB();
        }

        String message = myReservationDB.addReservation(theReservation);
        return !message.startsWith("Error adding reservation:");
    }
    public int getSize() {
        if (myReservationList == null) {
        	myReservationList = showAll();
        }
        return myReservationList.size();
    }
    public Iterator getIterator(final String theKey) {
        if (theKey == null) {
            myReservationList = showAll();
        }
        return new ReservationIterator();
    }
    public static List<Reservation> showAll() {
    	myReservationList = new ArrayList<>();
        if (myReservationDB == null) {
        	myReservationDB = new ReservationDB();
        }
        try {
        	myReservationList = myReservationDB.getReservations();
            return myReservationList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myReservationList;
    }
    private class ReservationIterator implements Iterator {
        int index;

        /**
         * Looking there is next object or not.
         *
         * @return true if there is an object, false otherwise.
         */
        @Override
        public boolean hasNext() {
            if (index < myReservationList.size()) {
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
                return myReservationList.get(index++);
            }
            return null;
        }
    }
    
}
