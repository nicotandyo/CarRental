package data;

import gui.HomeCustomerGUI;
import gui.HomeOwnerGUI;
//import gui.HomeGUI;
import gui.MainGUI;
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
public class ReservationDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;
    
    private List<Reservation> myReservationList;


    /**
     * Reserve
     * @param theId
     * @param theCustId
     * @param theVin
     * @param theDate
     * @return
     * @throws SQLException
     */
    public String reserve(final int theId,final int theCustId, final String theVin,
                        final java.sql.Date theDate) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO RESERVATION (ReservationID, CustomerID, VIN, Dates) VALUES (?, ?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setInt (1, theId);
            preparedStmt.setInt (2, theCustId);
            preparedStmt.setString (3, theVin);
            preparedStmt.setDate (4, theDate);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Succesfully Reserve!",
            "Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to reserve!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
    	return null;
    }
    public String updateReservation(final Reservation theReservation,
    		final String theCol, final Object theData) {

    	int rid = theReservation.getReservationID();
    	String sql = "update RESERVATION set `" + theCol
    			+ "` = ?  where ReservationID = ?";
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
    		preparedStatement.setInt(2, rid);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		//e.printStackTrace(); //for debugging
    		return "Error updating reservation: " + e.getMessage();
    	}
    	return "Updated Reservation Successfully";

    }
    
    public java.sql.Date getReservationDate(final int theReservationID) {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
    	Statement stmt = null;
    	try {
            
    		String sql = "Select * from RESERVATION where ReservationID ='" +
    				theReservationID +"'";
    		if (myConnection == null) {
    			return null;
    		}
    		stmt = myConnection.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			//in this case enter when at least one result comes it means user is valid
    			java.sql.Date date = rs.getDate("Dates");
    			return date;
    		} else {
    			
    			return null;
    		}


        } catch (SQLException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                            + "\nPlease check your internet connection and restart the program!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    public String addReservation(final Reservation theReservation) {
        

        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }

        PreparedStatement preparedStatement = null;
        try {
        	String sql = "insert into RESERVATION(ReservationID, CustomerID, VIN, Dates) values "
                    + "(?, ?, ?, ?) ";
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, theReservation.getReservationID());
            preparedStatement.setInt(2, theReservation.getCustomerID());
            preparedStatement.setString(3, theReservation.getVIN());
            preparedStatement.setDate(4, theReservation.getDate());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Reservation succesfully added!",
                    "Reservation Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
			e.printStackTrace(); //for debugging
            JOptionPane.showMessageDialog(null, "Unable to make reservation, car with that VIN doesn't exist!",
                    "Add failed", JOptionPane.WARNING_MESSAGE);
            return "Error adding reservation: " + e.getMessage();
        }
        return "Added Reservation Successfully";
    }
    /**
     * 
     * @param theId The reservationID
     * @param theCustId customerID
     * @return
     * @throws SQLException
     */
    public String cancelReservation(final int theId, final int theCustId) throws SQLException {
		if (myConnection == null) {
			myConnection = DataConnection.getConnection();
		}
		try {
			String query = "DELETE FROM RESERVATION WHERE ReservationID = ? AND CustomerID = ?";
			PreparedStatement preparedStmt = myConnection.prepareStatement(query);
			preparedStmt.setInt (1, theId);
			preparedStmt.setInt (2, theCustId);
			preparedStmt.execute();
			//myConnection.close();
			JOptionPane.showMessageDialog(null, "Succesfully Cancel Reservation!",
			"Success", JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e) {
			//e.printStackTrace();
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Unable to cancel reservation!",
			        "Failed Warning", JOptionPane.WARNING_MESSAGE);
			}
			return null;
		}
    
    public List<Reservation> getReservations() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "SELECT * " + "FROM RESERVATION ";

        myReservationList = new ArrayList<>();
        try {
            if (myConnection == null) {
                return null;
            }
            stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int rid = rs.getInt("ReservationID");
                int cid = rs.getInt("CustomerID");
                String VIN = rs.getString("VIN");
                java.sql.Date date = rs.getDate("Dates");
                Reservation reservation = null;
                reservation = new Reservation(rid, cid, VIN, date);
                myReservationList.add(reservation);
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
        return myReservationList;
    }
    
}
