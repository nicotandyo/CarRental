package data;



import javax.swing.*;

import gui.HomeCustomerGUI;
import gui.HomeOwnerGUI;
import gui.MainGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;


    
    public String addCustomer(final String theName, final String theAddress,
                        final String theEmail, final String thePhone, final String theUser) throws SQLException {
    	if (myConnection == null) {
            myConnection = DataConnection.getConnection();
        }
        try {
        	String query = "INSERT INTO CUSTOMER (CustName, CustAddress, CustEmail, CustPhone, Username) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement preparedStmt = myConnection.prepareStatement(query);
        	preparedStmt.setString (1, theName);
            preparedStmt.setString (2, theAddress);
            preparedStmt.setString (3, theEmail);
            preparedStmt.setString (4, thePhone);
            preparedStmt.setString (5, theUser);
            preparedStmt.execute();
            //myConnection.close();
            JOptionPane.showMessageDialog(null, "Customer succesfully added!",
            "Add success!", JOptionPane.WARNING_MESSAGE);
            return "success";
        } catch (SQLException e) {
//            e.printStackTrace();
        	System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                            + "\nPlease check your internet connection and restart the program!",
                    "Failed Warning", JOptionPane.WARNING_MESSAGE);
            return "failed";
        }
    }
    
    public String[] getCustomer(final String theUser) throws SQLException {
    	if (myConnection == null) {
    		myConnection = DataConnection.getConnection();
    	}
    	Statement stmt = null;
    	try {
    		String sql = "Select * from CUSTOMER where Username ='" +
    				theUser + "'";
    		if (myConnection == null) {
    			return null;
    		}
    		String[] result = new String[4];
    		stmt = myConnection.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			//in this case enter when at least one result comes it means user is valid
    			result[0] = rs.getString("CustName");
    			result[1] = rs.getString("CustAddress");
    			result[2] = rs.getString("CustEmail");
    			result[3] = rs.getString("CustPhone");
    			return result;

    		} else {
    			//in this case enter when  result size is zero  it means user is invalid
    			JOptionPane.showMessageDialog(null, "Invalid username or password",
    					"Login failed", JOptionPane.INFORMATION_MESSAGE);
    			new MainGUI();
    			return null;
    		}
    	} catch (SQLException e) {
    		//e.printStackTrace();
    		JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
    				+ "\nPlease check your internet connection and restart the program!",
    				"Failed Warning", JOptionPane.WARNING_MESSAGE);
    	}
    	return null;
    }
    
    public int getCustomerID(final String theUser) throws SQLException {
    	if (myConnection == null) {
    		myConnection = DataConnection.getConnection();
    	}
    	Statement stmt = null;
    	try {
    		String sql = "Select CustomerID from CUSTOMER where Username ='" +
    				theUser + "'";
    		if (myConnection == null) {
    			return 0;
    		}
    		int result = 0;
    		stmt = myConnection.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			//in this case enter when at least one result comes it means user is valid
    			result = rs.getInt("CustomerID");
    			return result;

    		} else {
    			return 0;
    		}
    	} catch (SQLException e) {
    		//e.printStackTrace();
    		JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
    				+ "\nPlease check your internet connection and restart the program!",
    				"Failed Warning", JOptionPane.WARNING_MESSAGE);
    	}
    	return 0;
    }
}
