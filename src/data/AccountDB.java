package data;

import gui.HomeCustomerGUI;
import gui.HomeOwnerGUI;
//import gui.HomeGUI;
import gui.MainGUI;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class contains methods to access Account table. 
 * @author Nico Tandyo
 */
public class AccountDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;


    /**
     * It login to the system by given Account information.
     *
     * @param theUser the login username.
     * @param thePass the login password
     * @param theRole if 1, it's Owner. If 2, it's Customer.
     * @return A string message that telling user success or not.
     */
    public String login(final String theUser, final String thePass,
                        final int theRole) throws SQLException {
        if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        try {
            if (theUser != null && thePass != null) {
                String sql = "Select * from ACCOUNT where Username ='" +
                        theUser + "' and Pw='" + thePass + "'";
                if (myConnection == null) {
                    return null;
                }
                stmt = myConnection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    //in this case enter when at least one result comes it means user is valid
                    int role = rs.getInt("Role");
                    //HomeGUI home = new HomeGUI();
                    if (role == 1) { // owner
                    	new HomeOwnerGUI(theUser);
                        JOptionPane.showMessageDialog(null, "You are logged in as an Owner",
                                "Login success", JOptionPane.INFORMATION_MESSAGE);
                        return "Sucess";
                    } else if (role == 2) { // customer
                    	new HomeCustomerGUI(theUser);
                        JOptionPane.showMessageDialog(null, "You are logged in as a Customer",
                                "Login success", JOptionPane.INFORMATION_MESSAGE);
                        return "Sucess";
                    }
                } else {
                    //in this case enter when  result size is zero  it means user is invalid
                    JOptionPane.showMessageDialog(null, "Invalid username or password",
                            "Login failed", JOptionPane.INFORMATION_MESSAGE);
                    new MainGUI();
                    return null;
                }
            } 

        } catch (SQLException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                            + "\nPlease check your internet connection and restart the program!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
    public String register(final String theUser, final String thePass,
            final int theRole) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO ACCOUNT (Username, Pw, Role) VALUES (?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setString (1, theUser);
            preparedStmt.setString (2, thePass);
            preparedStmt.setInt   (3, theRole);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Account succesfully registered!",
            "Register Success", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                            + "\nPlease check your internet connection and restart the program!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
        }
    	return null;
    }
}
