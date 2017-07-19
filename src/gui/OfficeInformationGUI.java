package gui;

import account.AccountCollection;
import account.CustomerCollection;
import account.OfficeCollection;
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
public class OfficeInformationGUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 2373258184918962762L;
	private JLabel[] myTxfLabel = new JLabel[5];
	private JLabel[] myLabel = new JLabel[5];
	private JPanel myCustPnl;
	private String[] myOffice;
	public OfficeInformationGUI() throws SQLException {
		myOffice = new String[5];
		myOffice = OfficeCollection.getOffice(1);
        setLayout(new BorderLayout());
        createComponents();
        add(myCustPnl, BorderLayout.CENTER);
        setVisible(true);
    }
	private void createComponents() {
		myCustPnl = new JPanel();
		myCustPnl.setLayout(new GridLayout(9, 0));
		String labelNames[] = {"Office ID: ", "Address: ", "Phone: ", "Email: "};
		for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myLabel[i] = new JLabel(myOffice[i]);
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