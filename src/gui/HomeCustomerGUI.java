package gui;

import javax.swing.*;
import java.sql.SQLException;

/**
 * The GUI for Home with features for Customer
 * @author Nico Tandyo
 */
public class HomeCustomerGUI {

    /**
     * Width of Home GUI.
     */
    public static final int WIDTH = 1200;

    /**
     * Height of Home GUI.
     */
    public static final int HEIGHT = 700;

    /**
     * JFrame of the GUI
     */
    private static JFrame myFrame;

    /**
     * Tab Pane for the GUI.
     */
    private JTabbedPane myTabbedPane;
    
    private String myUser;
    /**
     * This constructors calls the method to create the GUI.
     *
     * @throws SQLException if error occur.
     */
    public HomeCustomerGUI(final String theUser) throws SQLException {
    	myUser = theUser;
        myFrame = new JFrame("Home: Customer");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        myFrame.setSize(WIDTH, HEIGHT);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }

    /**
     * This method is used for closing the GUI.
     */
    public static void close() {
        myFrame.dispose();
    }

    /**
     * This method creates several tabs for different features for user to use.
     *
     * @throws SQLException if error occur.
     */
    private void createComponents() throws SQLException {
        myTabbedPane = new JTabbedPane();
        JComponent custData = makeTextPanel("Personal Information");
        myTabbedPane.addTab("Personal Information", custData);
        JComponent reservePanel = makeTextPanel("Reservation");
        myTabbedPane.addTab("Reservation", reservePanel);
        JComponent carPanel = makeTextPanel("Car");
        myTabbedPane.addTab("Car", carPanel);
        JComponent brandPanel = makeTextPanel("Brand");
        myTabbedPane.addTab("Brand", brandPanel);
        JComponent ratingsPanel = makeTextPanel("Ratings");
        myTabbedPane.addTab("Ratings", ratingsPanel);
        JComponent officePanel = makeTextPanel("Office Information");
        myTabbedPane.addTab("Office Information", officePanel);
        JComponent logOutPanel = makeTextPanel("Log Out");
        myTabbedPane.addTab("Log out", logOutPanel);
        myFrame.add(myTabbedPane);
    }

    /**
     * Create the the particular part to add to the tab based on the type.
     *
     * @param theType the tab's type.
     * @return the panel.
     * @throws SQLException if error occur.
     */
    private JComponent makeTextPanel(final String theType) throws SQLException {
        JPanel panel = new JPanel();
        if (theType.equalsIgnoreCase("Reservation")) {
            panel.add(new ReservationGUI(myUser));
        } else if (theType.equalsIgnoreCase("Car")) {
            panel.add(new CarGUICustomer());
        } else if (theType.equalsIgnoreCase("Personal Information")) {
            panel.add(new CustInformationGUI(myUser));
        } else if (theType.equalsIgnoreCase("Ratings")) {
            panel.add(new RatingsGUI());
        } else if(theType.equalsIgnoreCase("Office Information")) {
        	panel.add(new OfficeInformationGUI());
        } else if (theType.equalsIgnoreCase("Log Out")) {
            panel.add(new LogoutGUI());
        } else if(theType.equalsIgnoreCase("Brand")) {
        	panel.add(new BrandGUI());
        }
        return panel;
    }
}