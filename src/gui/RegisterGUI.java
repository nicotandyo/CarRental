package gui;

//import request.Request;
//import request.RequestCollection;

import account.AccountCollection;
import account.CustomerCollection;
import account.OwnerCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * This class creates the GUI for making a request to edit student's employment information.
 *
 * @author Nico Tandyo
 */
public class RegisterGUI extends JPanel implements ActionListener {
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

	private static final long serialVersionUID = -793646904153351058L;

	/**
     * Instruction button and add button.
     */
    private JButton myRegisterBtn, myBackBtn;

    /**
     * Main panel, information panel.
     */
    private JPanel myInfoPnl, myPnlContent, myInfoPanel;

    /**
     * Textfield's labels.
     */
    private JLabel[] myTxfLabel = new JLabel[7];

    /**
     * Text input fields.
     */
    private JTextField[] myTxfField = new JTextField[7];


    /**
     * Information label.
     */
    private JLabel myInfoLabel;

    /**
     * Combo box for choosing role.
     */
    private JComboBox<String> myRoleComboBox;

    /**
     * This constructor calls the method to create all of the components.
     */
    public RegisterGUI() {
    	myFrame = new JFrame("Home");
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        myFrame.setSize(WIDTH, HEIGHT);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        //setLayout(new BorderLayout());
        //createComponents();
        //setVisible(true);
    }

    /**
     * Create the three panels to add to this GUI. One for making a request,
     * one for the instruction for making a request
     */
    private void createComponents() {
        myPnlContent = new JPanel();
        myPnlContent.setLayout(new BorderLayout());


        addPanel();
        add(myPnlContent, BorderLayout.CENTER);
        myFrame.add(myPnlContent);
    }

    /**
     * Create the add panel for making a request.
     */
    public void addPanel() {
        // Add Panel
        myInfoPnl = new JPanel();
        myInfoPnl.setLayout(new GridLayout(9, 0));
        String labelNames[] = {"Username: *", "Password: *", "Name: *", "Address: *", "Email: *", "Phone: *"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myInfoPnl.add(panel);
        }



        myInfoPanel = new JPanel();
        String s = "Note: If you want to register as an owner (rent your car here) select role as an owner, if you want "
        		+ "to rent a car here as a customer, select role as a customer.";

        //this is for wrapping the text in the JLabel
        String html1 = "<html><body style='width: ";
        String html2 = "px'>";

        int width = 500, height = 175, size = 17;
        myInfoLabel = new JLabel(html1 + "400" + html2 + s);
        myInfoLabel.setFont(new Font("DialogInput", Font.BOLD, size));
        myInfoPanel.add(myInfoLabel);
        myInfoPanel.setPreferredSize(new Dimension(width, height));


        JPanel panel = new JPanel();
        myRegisterBtn = new JButton("Register User");
        myRegisterBtn.addActionListener(this);
        myBackBtn = new JButton("Back to Login");
        myBackBtn.addActionListener(this);
        panel.add(myBackBtn);
        panel.add(myRegisterBtn);
        

        JPanel comboPanel4 = new JPanel();
        comboPanel4.setLayout(new GridLayout(1, 2));
        String[] types = {"Owner", "Customer"};
        myRoleComboBox = new JComboBox<>(types);
        myRoleComboBox.setSelectedIndex(0);
        comboPanel4.add(new JLabel("Role(Owner or Customer): * "));
        comboPanel4.add(myRoleComboBox);
        myInfoPnl.add(comboPanel4);

       

        myPnlContent.add(myInfoPanel, BorderLayout.NORTH);
        myPnlContent.add(myInfoPnl, BorderLayout.CENTER);
        myPnlContent.add(panel, BorderLayout.SOUTH);
        //myFrame.add(myPnlContent);
    }

    /**
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myRegisterBtn) {
            try {
				performAddRequest();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        if (theEvent.getSource() == myBackBtn) {
        	new MainGUI();
        	myFrame.dispose();
        }
    }

    /**
     * Perform adding the request to the database.
     * @throws SQLException 
     */
    public void performAddRequest() throws SQLException {
        String username = myTxfField[0].getText();
        if (username.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Username");
            myTxfField[0].setFocusable(true);
            return;
        }
        String password = myTxfField[1].getText();
        if (password.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Password");
            myTxfField[1].setFocusable(true);
            return;
        }
        
        
        String name = myTxfField[2].getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Name");
            myTxfField[2].setFocusable(true);
            return;
        }
        String address = myTxfField[3].getText();
        if (address.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Address");
            myTxfField[3].setFocusable(true);
            return;
        }
        
        String email = myTxfField[4].getText();
        if (email.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Email");
            myTxfField[4].setFocusable(true);
            return;
        }
        String phone = myTxfField[5].getText();
        if (phone.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Phone");
            myTxfField[5].setFocusable(true);
            return;
        }
        String role = (String) myRoleComboBox.getSelectedItem();
        int roleInt = 0;
        if(role.equals("Owner")) {
        	roleInt = 1;
        } else if(role.equals("Customer")) {
        	roleInt = 2;
        }
        AccountCollection.register(username, password, roleInt);
        if(roleInt == 1) {
        	OwnerCollection.addOwner(name, address, email, phone, username);
        } else if(roleInt == 2) {
        	CustomerCollection.addCustomer(name, address, email, phone, username);
        }
        
        
        
//        for (int i = 0; i < myTxfField.length; i++) {
//            if (myTxfField[i].getText().length() != 0) {
//                myTxfField[i].setText("");
//            }
//        }
    }
}
