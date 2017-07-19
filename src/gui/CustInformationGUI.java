package gui;

import account.AccountCollection;
import account.CustomerCollection;
import account.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Nico Tandyo
 */
public class CustInformationGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 2373258184918962762L;
	private JLabel[] myTxfLabel = new JLabel[5];
	private JLabel[] myLabel = new JLabel[5];
	private JPanel myCustPnl;
	private String myUser;
	private String[] myCustomer;
	public CustInformationGUI(String theUser) throws SQLException {
		myUser = theUser;
		myCustomer = new String[5];
		myCustomer = CustomerCollection.getCustomer(myUser);
        setLayout(new BorderLayout());
        createComponents();
        add(myCustPnl, BorderLayout.CENTER);
        setVisible(true);
    }
	private void createComponents() {
		myCustPnl = new JPanel();
		myCustPnl.setLayout(new GridLayout(9, 0));
		String labelNames[] = {"Name: ", "Address: ", "Email: ", "Phone: "};
		for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myLabel[i] = new JLabel(myCustomer[i]);
            panel.add(myTxfLabel[i]);
            panel.add(myLabel[i]);
            myCustPnl.add(panel);
        }
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}