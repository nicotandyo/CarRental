package data;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This class has the information to connect to the cssgate server.
 * to customize for your own project.
 *
 * @author Nico Tandyo
 */

public class DataConnection {

    /**
     * Connection to the database.
     */
    private static Connection myConnection;

    // Creates once instance of the connection to be reused in the different places in the
    // system.
    private static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();

        // Login to the SQL's username.
        String userName = "nicot";
        // Login to the SQL's password
        String password = "ownoid";
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        // Server address.
        String serverName = "cssgate.insttech.washington.edu";
        myConnection = DriverManager
                .getConnection("jdbc:mysql://" + serverName + "/"
                        + userName + "?user=" + userName + "&password=" + password);

        //For debugging - System.out.println("Connected to database");
    }

    /**
     * Returns a connection to the database so that queries can be executed.
     *
     * @return Connection to the database
     */
    public static Connection getConnection() {
        if (myConnection == null) {
            try {
                createConnection();
            } catch (SQLException e) {
//				e.printStackTrace(); //For debugging.
                JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                                + "\nPlease check your internet connection and restart the program!",
                        "Login failed", JOptionPane.WARNING_MESSAGE);
            }
        }
        return myConnection;
    }

    /**
     * Close the connection to the database when done.
     *
     * @throws SQLException it throws exception if errors occur.
     */
    public static void closeConnection() throws SQLException {
        if (myConnection != null && !myConnection.isClosed()) {
            myConnection.close();
        }
    }
}
