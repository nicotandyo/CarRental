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


public class OfficeDB {

    /**
     * It's a connection for connecting to the database.
     */
    private Connection myConnection;


    
    
    
    public String[] getOffice(final int theId) throws SQLException {
    	if (myConnection == null) {
    		myConnection = DataConnection.getConnection();
    	}
    	Statement stmt = null;
    	try {
    		String sql = "Select * from OFFICE where OfficeID ='" +
    				theId + "'";
    		if (myConnection == null) {
    			return null;
    		}
    		String[] result = new String[4];
    		stmt = myConnection.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			//in this case enter when at least one result comes it means user is valid
    			result[0] = rs.getString("OfficeID");
    			result[1] = rs.getString("Address");
    			result[2] = rs.getString("Phone");
    			result[3] = rs.getString("Email");
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
    
    
}
