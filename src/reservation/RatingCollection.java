package reservation;

import data.BrandDB;
import data.RatingsDB;
import data.ReservationDB;
import design_pattern.Iterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a collection of all the reservation.
 * @author Nico Tandyo
 */
public class RatingCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static RatingsDB myRatingDB;
    private static List<Rating> myRatingList;

    
     /**
      * Reserve
      * @param theId
      * @param theCustId
      * @param theVin
      * @param theDate
      * @return
      * @throws SQLException
      */
    public static boolean review(final int theId, final int theRid, 
    		final int theStar, final java.sql.Date theDate, final String theComments) throws SQLException {
        if (myRatingDB == null) {
            myRatingDB = new RatingsDB();
        }

        String message = myRatingDB.review(theId, theRid, theStar, theDate, theComments);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
    
    
    public static boolean addReview(final Rating theRating) {
        if (myRatingDB == null) {
        	myRatingDB = new RatingsDB();
        }

        String message = myRatingDB.addReview(theRating);
        return !message.startsWith("Error adding rating:");
    }
    public int getSize() {
        if (myRatingList == null) {
        	myRatingList = showAll();
        }
        return myRatingList.size();
    }
    public Iterator getIterator(final String theKey) {
        if (theKey == null) {
            myRatingList = showAll();
        }
        return new RatingIterator();
    }
    public static List<Rating> showAll() {
    	myRatingList = new ArrayList<>();
        if (myRatingDB == null) {
        	myRatingDB = new RatingsDB();
        }
        try {
        	myRatingList = myRatingDB.getRatings();
            return myRatingList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myRatingList;
    }
    private class RatingIterator implements Iterator {
        int index;

        /**
         * Looking there is next object or not.
         *
         * @return true if there is an object, false otherwise.
         */
        @Override
        public boolean hasNext() {
            if (index < myRatingList.size()) {
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
                return myRatingList.get(index++);
            }
            return null;
        }
    }
    
}
