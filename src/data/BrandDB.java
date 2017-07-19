package data;

import gui.HomeCustomerGUI;
import gui.HomeOwnerGUI;
//import gui.HomeGUI;
import gui.MainGUI;
import reservation.Brand;
import reservation.Reservation;

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
public class BrandDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;
    
    private List<Brand> myBrandList;


    /**
     * Reserve
     * @param theId
     * @param theCustId
     * @param theVin
     * @param theDate
     * @return
     * @throws SQLException
     */
    public String register(final String theModels, final String theMakes, 
    		final String theBS, final int theCapacity, final String theVin) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO BRAND (Models, Makes, BodyStyle, Capacity, VIN) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setString (1, theModels);
            preparedStmt.setString (2, theMakes);
            preparedStmt.setString (3, theBS);
            preparedStmt.setInt(4, theCapacity);
            preparedStmt.setString(5, theVin);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Succesfully Add!",
            "Success", JOptionPane.WARNING_MESSAGE);
            return "success";
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to reserve!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
    	return null;
    }
public String addBrand(final Brand theBrand) {
        

        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }

        PreparedStatement preparedStatement = null;
        try {
        	String sql = "insert into BRAND(Models, Makes, BodyStyle, Capacity, VIN) values "
                    + "(?, ?, ?, ?, ?) ";
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setString(1, theBrand.getModel());
            preparedStatement.setString(2, theBrand.getMake());
            preparedStatement.setString(3, theBrand.getBS());
            preparedStatement.setInt(4, theBrand.getCapacity());
            preparedStatement.setString(5, theBrand.getVIN());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Brand succesfully added!",
                    "Add Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
			e.printStackTrace(); //for debugging
            JOptionPane.showMessageDialog(null, "Unable to add brand",
                    "Add failed", JOptionPane.WARNING_MESSAGE);
            return "Error adding brand: " + e.getMessage();
        }
        return "Added Brand Successfully";
    }
    public List<Brand> getBrands() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "SELECT * " + "FROM BRAND ";

        myBrandList = new ArrayList<>();
        try {
            if (myConnection == null) {
                return null;
            }
            stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String model = rs.getString("Models");
                String make = rs.getString("Makes");
                String bs = rs.getString("BodyStyle");
                int capacity = rs.getInt("Capacity");
                String VIN = rs.getString("VIN");
                Brand brand = null;
                brand = new Brand(model, make, bs, capacity, VIN);
                myBrandList.add(brand);
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
        return myBrandList;
    }
    
}
