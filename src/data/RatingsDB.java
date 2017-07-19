package data;

import gui.HomeCustomerGUI;
import gui.HomeOwnerGUI;
//import gui.HomeGUI;
import gui.MainGUI;
import reservation.Brand;
import reservation.Rating;
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
public class RatingsDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;
    
    private List<Rating> myRatingList;


    /**
     * Ratings
     * @param theId
     * @param theCustId
     * @param theVin
     * @param theDate
     * @return
     * @throws SQLException
     */
    public String review(final int theId, final int theRid, 
    		final int theStar, final java.sql.Date theDate, final String theComments) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO RATINGS (RatingsID, ReservationID, Star, DateRented, Comments) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setInt (1, theId);
            preparedStmt.setInt (2, theRid);
            preparedStmt.setInt (3, theStar);
            preparedStmt.setDate(4, theDate);
            preparedStmt.setString(5, theComments);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Succesfully Review!",
            "Success", JOptionPane.WARNING_MESSAGE);
            return "success";
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to review!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
    	return null;
    }
    public String addReview(final Rating theRatings) {
        

        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }

        try {
        	String sql = "insert into RATINGS(RatingsID, ReservationID, Star, DateRented, Comments) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(sql);
        	preparedStmt.setInt (1, theRatings.getId());
            preparedStmt.setInt (2, theRatings.getRid());
            preparedStmt.setInt (3, theRatings.getStar());
            preparedStmt.setDate(4, theRatings.getDate());
            preparedStmt.setString(5, theRatings.getComments());
            preparedStmt.execute();
            JOptionPane.showMessageDialog(null, "Ratings succesfully added!",
                    "Add Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
			e.printStackTrace(); //for debugging
            JOptionPane.showMessageDialog(null, "Unable to add ratings",
                    "Add failed", JOptionPane.WARNING_MESSAGE);
            return "Error adding ratings: " + e.getMessage();
        }
        return "Added Ratings Successfully";
    }
    public List<Rating> getRatings() throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "SELECT * " + "FROM RATINGS ";

        myRatingList = new ArrayList<>();
        try {
            if (myConnection == null) {
                return null;
            }
            stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("RatingsID");
                int star = rs.getInt("Star");
                int rid = rs.getInt("ReservationID");
                java.sql.Date date = rs.getDate("DateRented");
                String comments = rs.getString("Comments");
                Rating rating = null;
                rating = new Rating(id, rid, star, date, comments);
                myRatingList.add(rating);
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
        return myRatingList;
    }
    
}
