package reservation;

import data.BrandDB;
import data.ReservationDB;
import design_pattern.Iterator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a collection of all the reservation.
 * @author Nico Tandyo
 */
public class BrandCollection {

    /**
     * Customer database for connecting to the data base.
     */
    private static BrandDB myBrandDB;
    private static List<Brand> myBrandList;

    
     /**
      * Reserve
      * @param theId
      * @param theCustId
      * @param theVin
      * @param theDate
      * @return
      * @throws SQLException
      */
    public static boolean register(final String theModels, final String theMakes, 
    		final String theBS, final int theCapacity, final String theVin) throws SQLException {
        if (myBrandDB == null) {
            myBrandDB = new BrandDB();
        }

        String message = myBrandDB.register(theModels, theMakes, theBS, theCapacity, theVin);
        if(message.equals("success")) {
        	return true;
        } else {
        	return false;
        }
    }
    
    
    public static boolean addBrand(final Brand theBrand) {
        if (myBrandDB == null) {
        	myBrandDB = new BrandDB();
        }

        String message = myBrandDB.addBrand(theBrand);
        return !message.startsWith("Error adding brand:");
    }
    public int getSize() {
        if (myBrandList == null) {
        	myBrandList = showAll();
        }
        return myBrandList.size();
    }
    public Iterator getIterator(final String theKey) {
        if (theKey == null) {
            myBrandList = showAll();
        }
        return new BrandIterator();
    }
    public static List<Brand> showAll() {
    	myBrandList = new ArrayList<>();
        if (myBrandDB == null) {
        	myBrandDB = new BrandDB();
        }
        try {
        	myBrandList = myBrandDB.getBrands();
            return myBrandList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myBrandList;
    }
    private class BrandIterator implements Iterator {
        int index;

        /**
         * Looking there is next object or not.
         *
         * @return true if there is an object, false otherwise.
         */
        @Override
        public boolean hasNext() {
            if (index < myBrandList.size()) {
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
                return myBrandList.get(index++);
            }
            return null;
        }
    }
    
}
