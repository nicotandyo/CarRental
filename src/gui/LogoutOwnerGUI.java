package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the GUI for logging out
 * and a button to show things about this program
 *
 * @author Nico Tandyo
 */
public class LogoutOwnerGUI extends JPanel implements ActionListener {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7655286335054538989L;
    /**
     * Log out button and about button.
     */
    private JButton myBtnLogout, myBtnAbout;

    /**
     * Button panel that put log out and about button.
     */
    private JPanel myBtnPanel;

    /**
     * The constructor to call the method to create all of the components
     */
    public LogoutOwnerGUI() {
        setLayout(new BorderLayout());
        createComponents();
        setVisible(true);
    }

    /**
     * This method creates the panel for log out button and about button
     */
    private void createComponents() {
        myBtnPanel = new JPanel();
        myBtnLogout = new JButton("Log Out");
        myBtnLogout.addActionListener(this);
        myBtnAbout = new JButton("About This Program");
        myBtnAbout.addActionListener(this);
        myBtnPanel.add(myBtnAbout);
        myBtnPanel.add(myBtnLogout);
        add(myBtnPanel, BorderLayout.NORTH);
    }

    /**
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myBtnLogout) {
        	HomeOwnerGUI.close();
            new MainGUI();
            
        } else if (theEvent.getSource() == myBtnAbout) {
        	String about = "This is the final project for TCSS 445 - Database"
                    + "\nBy: Nico Tandyo - nicot@uw.edu";

            JOptionPane.showMessageDialog(null, about,
                    "About This Program", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
