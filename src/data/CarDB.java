package data;


import reservation.Car;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to access Reservation table. 
 * @author Nico Tandyo
 */
public class CarDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;
    
    private List<Car> myCarList;


    /**
     * Register
     * @param theVin
     * @param theBrand
     * @param theType
     * @param theAvailable
     * @return
     * @throws SQLException
     */
    public String register(final String theVin, 
    		final Double thePrice, final boolean theAvailable) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO CAR (VIN, PriceDay, IsAvailable) VALUES (?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setString (1, theVin);
            preparedStmt.setDouble (2, thePrice);
            preparedStmt.setBoolean (3, theAvailable);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Succesfully Register!",
            "Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to register!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
    	return null;
    }
    public String updateCar(final Car theCar,
    		final String theCol, final Object theData) {

    	String vin = theCar.getVIN();
    	String sql = "update CAR set `" + theCol
    			+ "` = ?  where VIN = ?";
    	// For debugging - System.out.println(sql);
    	if (myConnection == null) {
    		myConnection = DataConnection.getConnection();
    	}
    	PreparedStatement preparedStatement = null;
    	try {
    		preparedStatement = myConnection.prepareStatement(sql);
    		if (theData instanceof String)
    			preparedStatement.setString(1, (String) theData);
    		else if (theData instanceof Double)
    			preparedStatement.setDouble(1, (Double) theData);
    		preparedStatement.setString(2, vin);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		//e.printStackTrace(); //for debugging
    		return "Error updating car: " + e.getMessage();
    	}
    	return "Updated Car Successfully";

    }
    public String addCar(final Car theCar) {
        String theVin = theCar.getVIN();
        Double thePrice = theCar.getPrice();
        boolean theAvailable = theCar.getAvailability();

        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }

        PreparedStatement preparedStatement = null;
        try {
        	String sql = "insert into CAR(VIN, PriceDay, IsAvailable) values "
                    + "(?, ?, ?) ";
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setString (1, theVin);
            preparedStatement.setDouble (2, thePrice);
            preparedStatement.setBoolean (3, theAvailable);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Car succesfully added!",
                    "Car Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
			e.printStackTrace(); //for debugging
            JOptionPane.showMessageDialog(null, "Unable to add car!",
                    "Add failed", JOptionPane.WARNING_MESSAGE);
            return "Error adding car: " + e.getMessage();
        }
        return "Added Car Successfully";
    }
    
    public List<Car> getCars() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "SELECT * " + "FROM CAR ";

        myCarList = new ArrayList<>();
        try {
            if (myConnection == null) {
                return null;
            }
            stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String vin = rs.getString("VIN");
                Double price = rs.getDouble("PriceDay");
                boolean b = rs.getBoolean("IsAvailable");
                Car car = null;
                car = new Car(vin, price, b);
                myCarList.add(car);
            }
        } catch (SQLException e) {
//            e.printStackTrace(); //For debugging.
            JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                            + "\nPlease check your internet connection and restart the program!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return myCarList;
    }
    
}
