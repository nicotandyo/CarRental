package gui;

import design_pattern.Iterator;
import reservation.Reservation;
import reservation.ReservationCollection;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import account.CustomerCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Nico Tandyo
 */
public class ReservationGUI extends JPanel implements ActionListener,
        TableModelListener {

    /**
     * Panel's Width.
     */
    protected static final int WIDTH = 1100;

    /**
     * Panel's Height.
     */
    protected static final int HEIGHT = 550;

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7520370128176444786L;

    /**
     * All Buttons that in the panel.
     */
    private JButton myBtnList, myBtnAdd, myAddBtn, myBtnDel, myDeleteBtn;

    /**
     * All panels that will use in this frame.
     */
    private JPanel myPnlButtons, myPnlAdd, myPnlContent, myPnlDel;

    /**
     * All textfield's label.
     */
    private JLabel[] myTxfLabel = new JLabel[4];

    /**
     * All Textfield input.
     */
    private JTextField[] myTxfField = new JTextField[4];

    /**
     * Data for making a table.
     */
    private Object[][] myData;

    /**
     * The table contains all information.
     */
    private JTable myTable;

    /**
     * Scroll pane that use in the table.
     */
    private JScrollPane myScrollPane;

    /**
     * All column name that store in a string array.
     */
    private String[] myColumnNames = {"ReservationID", "CustomerID", "VIN", "Date"};

    private JComboBox<String> myVinComboBox;
    private List<Reservation> myList;


    


    /**
     * It allows access to SudentCollection class.
     */
    private ReservationCollection myReservationCollection;
    private String myUser;
    /**
     * This constructor calls the method to create all of the components
     */
    public ReservationGUI(String theUser) {
    	myUser = theUser;
        setLayout(new BorderLayout());
        myReservationCollection = new ReservationCollection();
        myList = new ArrayList<>();
        getData();
        createComponents();
        setVisible(true);
    }

    /**
     * Retrieve the list of student to display
     *
     * @param theSearchKey the key to search for a student.
     * @return list of student
     */
    private void getData() {
        myList.clear();
        Iterator iter;
        iter = myReservationCollection.getIterator(null);

        int size = myReservationCollection.getSize();

        if (iter != null) {
            myData = new Object[size][myColumnNames.length];
            int i = 0;
            for (; iter.hasNext(); ) {
                Reservation reservation = (Reservation) iter.next();
                myList.add(reservation);
                myData[i][0] = reservation.getReservationID();
                myData[i][1] = reservation.getCustomerID();
                myData[i][2] = reservation.getVIN();
                myData[i][3] = reservation.getDate();
                i++;
            }
        }
    }

    /**
     * Create the three panels to add to this GUI. One for list, one for search,
     * one for add.
     */
    private void createComponents() {
        // A button panel at the top for list, add

        myPnlContent = new JPanel();
        myPnlButtons = new JPanel();
        myBtnList = new JButton("Reservation List");
        myBtnList.addActionListener(this);


        myBtnAdd = new JButton("Make Reservation");
        myBtnAdd.addActionListener(this);
        
        myBtnDel = new JButton("Cancel Reservation");
        myBtnDel.addActionListener(this);

        myPnlButtons.add(myBtnList);
        myPnlButtons.add(myBtnAdd);
        myPnlButtons.add(myBtnDel);

        addListPanel();


        add(myPnlButtons, BorderLayout.NORTH);
        add(myPnlContent, BorderLayout.CENTER);
    }

    /**
     * Create the list panel for displaying the list of reservation information.
     */
    public void addListPanel() {
        // List Panel
        myPnlContent = new JPanel();
        myTable = new JTable(myData, myColumnNames);
        myScrollPane = new JScrollPane(myTable);
        myScrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        myPnlContent.add(myScrollPane);
        myTable.getModel().addTableModelListener(this);
    }

    /**
     * Create the add panel for adding a reservation information.
     */
    public void addPanel() {
        // Add Panel
        myPnlAdd = new JPanel();
        myPnlAdd.setLayout(new GridLayout(9, 0));
        String labelNames[] = {"Enter VIN of Car to Rent: *", "Reservation Year: YYYY *", 
        		"Reservation Month: MM *", "Reservation Day: DD *"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAdd.add(panel);
        }


        JPanel panel = new JPanel();
        myAddBtn = new JButton("Add");
        myAddBtn.addActionListener(this);
        JLabel label = new JLabel("(*) = Required Fields");
        panel.add(myAddBtn);
        panel.add(label);
        myPnlAdd.add(panel);
        myPnlContent.add(myPnlAdd);
    }
    
    public void deletePanel() {
        // Add Panel
        myPnlDel = new JPanel();
        myPnlDel.setLayout(new GridLayout(9, 0));
        String labelNames[] = {"Enter Reservation ID: *", "Enter Customer ID: *"};
        for (int i = 0; i < labelNames.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlDel.add(panel);
        }


        JPanel panel = new JPanel();
        myDeleteBtn = new JButton("Delete");
        myDeleteBtn.addActionListener(this);
        JLabel label = new JLabel("(*) = Required Fields");
        panel.add(myDeleteBtn);
        panel.add(label);
        myPnlDel.add(panel);
        myPnlContent.add(myPnlDel);
    }

    /**
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myBtnAdd) {
            myPnlContent.removeAll();
            addPanel();
            myPnlContent.add(myPnlAdd);
            myPnlContent.revalidate();
            this.repaint();
        } else if (theEvent.getSource() == myAddBtn) {
            performAddReservation();
        } else if (theEvent.getSource() == myBtnList) {
            getData();
            myPnlContent.removeAll();
            myTable = new JTable(myData, myColumnNames);
            myTable.getModel().addTableModelListener(this);
            myScrollPane = new JScrollPane(myTable);
            myScrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            myPnlContent.add(myScrollPane);
            myPnlContent.revalidate();
            myPnlContent.setVisible(true);
            this.repaint();
        } else if (theEvent.getSource() == myBtnDel) {
        	myPnlContent.removeAll();
            deletePanel();
            myPnlContent.add(myPnlDel);
            myPnlContent.revalidate();
            this.repaint();
        } else if (theEvent.getSource() == myDeleteBtn) {
            try {
				performDeleteReservation();
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

    private void performDeleteReservation() throws HeadlessException, SQLException {
    	
        String idStr = myTxfField[0].getText();
        int id = 0;

        if (idStr.length() != 0) {
            try {
            	id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter an integer id");
                myTxfField[0].setFocusable(true);
                return;
            }
        }
        String cidStr = myTxfField[1].getText();
        int cid = 0;

        if (cidStr.length() != 0) {
            try {
            	cid = Integer.parseInt(cidStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter an integer id");
                myTxfField[1].setFocusable(true);
                return;
            }
        }
        if (ReservationCollection.cancelReservation(id, cid)) {
        	String message = "Reservation canceled successfully!";
        	JOptionPane.showMessageDialog(null, message);

        	// Clear all text fields.
        	for (int j = 0; j < myTxfField.length; j++) {
        		if (myTxfField[j].getText().length() != 0) {
        			myTxfField[j].setText("");
        		}
        	}
        }
    }

    private void performAddReservation() {
        String vin = myTxfField[0].getText();
        if (vin.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter VIN of Car to Rent:");
            myTxfField[0].setFocusable(true);
            return;
        }
        String y = myTxfField[1].getText();
        if (y.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Year: YYYY");
            myTxfField[1].setFocusable(true);
            return;
        }
        String m = myTxfField[2].getText();
        if (m.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Month: MM");
            myTxfField[12].setFocusable(true);
            return;
        }
        String d = myTxfField[3].getText();
        if (d.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Day: DD");
            myTxfField[3].setFocusable(true);
            return;
        }
        
        

        

        if (vin.length() > 17 || y.length() != 4 || m.length() != 2|| d.length() != 2) {
            if (vin.length() > 17) {
                JOptionPane.showMessageDialog(null, "Invalid input! " +
                                "VIN should be less than 17 characters!",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } else if (y.length() != 4) {
                JOptionPane.showMessageDialog(null, "Invalid input! " +
                                "Year should be 4 digits.",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } else if (m.length() != 2) {
                JOptionPane.showMessageDialog(null, "Invalid input!" +
                        "Month should be 2 digits.",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } else if (d.length() != 2) {
                JOptionPane.showMessageDialog(null, "Invalid input!"+
                        "Day should be 2 digits.",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } 
        } else {
        	Random rn = new Random();
        	int n = 10000000 - 0 + 1;
        	int i = rn.nextInt() % n;
        	int randomNum =  0 + i;
        	int customerID=0;
			try {
				customerID = CustomerCollection.getCustomerID(myUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			StringBuilder sb = new StringBuilder();
			sb.append(y);
			sb.append("-");
			sb.append(m);
			sb.append("-");
			sb.append(d);
        	java.sql.Date convertedDate = java.sql.Date.valueOf(sb.toString());
        			
            Reservation reservation = new Reservation(randomNum, customerID, vin, convertedDate);

            String message = null;
            if (ReservationCollection.addReservation(reservation)) {
                message = "Reservation added successfully!";
                JOptionPane.showMessageDialog(null, message);

                // Clear all text fields.
                for (int j = 0; j < myTxfField.length; j++) {
                    if (myTxfField[j].getText().length() != 0) {
                        myTxfField[j].setText("");
                    }
                }
            }
        }
    }

    /**
     * Listen to the cell changes on the myTable.
     */
    @Override
    public void tableChanged(final TableModelEvent theEvent) {
        int row = theEvent.getFirstRow();
        int column = theEvent.getColumn();
        TableModel model = (TableModel) theEvent.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (!columnName.matches("email")) {
            JOptionPane.showMessageDialog(null,
                    "Update failed, " + columnName + " CANNOT BE EDITED!!!");
        } else if (data != null && ((String) data).length() != 0) {
            Reservation reservation = myList.get(row);
            if (!ReservationCollection.updateReservation(reservation, columnName, data)) {
                JOptionPane.showMessageDialog(null,
                        "Update failed, Please check your input!");
            }
        }
    }
}