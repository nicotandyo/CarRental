package gui;

import account.AccountCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * This class creates a GUI for the login process
 * and goes to the HomeGUI after the user has logged in.
 *
 * @author Nico Tandyo
 */
public class MainGUI implements ActionListener {
    /**
     * Role of Owner.
     */
    public static final int OWNER = 1;

    /**
     * Role of Customer.
     */
    public static final int CUSTOMER = 2;
    

    /**
     * Width size.
     */
    private static final int WIDTH = 380;

    /**
     * Height size.
     */
    private static final int HEIGHT = 125;

    /**
     * GUI frame.
     */
    private JFrame myFrame;

    /**
     * Login panel and buttons panel.
     */
    private JPanel myLoginPnl, myLoginBtnPnl;

    /**
     * Textfield for input Username.
     */
    private JTextField myUserName;

    /**
     * Textfield for input Password.
     */
    private JTextField myPassword;

    /**
     * Labels for Username and Password.
     */
    private JLabel myLabel, myLabelP;

    /**
     * Buttons for employee login, student login, and about.
     */
    private JButton myLoginBtn, myRegisterBtn, myBtnAbout;

    /**
     * This constructor calls the method to create the login GUI.
     */
    public MainGUI() {
        myFrame = new JFrame("Log in");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());
        myFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        createLogin();

        myFrame.setVisible(true);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
    }

    /**
     * The main method for running the GUI
     *
     * @param args arguments input.
     */
    public static void main(String[] args) {
        new MainGUI();
    }

    /**
     * This method creates the login panel with buttons.
     */
    public void createLogin() {
        myLoginPnl = new JPanel(new GridLayout(2, 2));
        myLabel = new JLabel("Enter Username:");
        myLabel.setSize(myLabel.getPreferredSize());
        myLoginPnl.add(myLabel);

        myUserName = new JTextField();
        myUserName.setSize(myUserName.getPreferredSize());
        myUserName.setToolTipText("Enter Username");
        myUserName.setVisible(true);
        myLoginPnl.add(myUserName);

        myLabelP = new JLabel("Enter Password:");
        myLabelP.setSize(myLabelP.getPreferredSize());
        myLoginPnl.add(myLabelP);

        myPassword = new JTextField();
        myPassword.setSize(myPassword.getPreferredSize());
        myPassword.setToolTipText("Enter Password");
        myPassword.setVisible(true);
        myLoginPnl.add(myPassword);

        myLoginBtnPnl = new JPanel();
        myLoginBtn = new JButton("Log in");
        myLoginBtn.addActionListener(this);
        myLoginBtnPnl.add(myLoginBtn);

        myRegisterBtn = new JButton("Register");
        myRegisterBtn.addActionListener(this);
        myBtnAbout = new JButton("About This Program");
        myBtnAbout.addActionListener(this);
        myLoginBtnPnl.add(myRegisterBtn);
        myLoginBtnPnl.add(myBtnAbout);

        myFrame.add(myLoginPnl, BorderLayout.NORTH);
        myFrame.add(myLoginBtnPnl, BorderLayout.SOUTH);
    }

    /**
     * This method makes the buttons work.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myRegisterBtn) {
            new RegisterGUI();
            //myFrame.add(regGui);
            myFrame.dispose();
        } else if (e.getSource() == myLoginBtn) {

            String user = myUserName.getText();
            String pass = myPassword.getText();
            try {
                AccountCollection.login(user, pass, OWNER);
            } catch (SQLException err) {
//                err.printStackTrace(); //For debugging!
                JOptionPane.showMessageDialog(null, "Unable to connect to the server!"
                                + "\nPlease check your internet connection and restart the program!",
                        "Login failed", JOptionPane.WARNING_MESSAGE);
            }
            myFrame.dispose();
        } else if (e.getSource() == myBtnAbout) {
            String about = "This is the final project for TCSS 445 - Database"
                    + "\nBy: Nico Tandyo - nicot@uw.edu";


            JOptionPane.showMessageDialog(null, about,
                    "About This Program", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}