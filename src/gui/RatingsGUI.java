package gui;

import design_pattern.Iterator;
import reservation.Brand;
import reservation.BrandCollection;
import reservation.Car;
import reservation.CarCollection;
import reservation.Rating;
import reservation.RatingCollection;
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
public class RatingsGUI extends JPanel implements ActionListener,
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
    private JButton myBtnList, myBtnAdd, myAddBtn;

    /**
     * All panels that will use in this frame.
     */
    private JPanel myPnlButtons, myPnlAdd, myPnlContent;

    /**
     * All textfield's label.
     */
    private JLabel[] myTxfLabel = new JLabel[8];

    /**
     * All Textfield input.
     */
    private JTextField[] myTxfField = new JTextField[8];

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
    private String[] myColumnNames = {"RatingsID", "ReservationID", "Star", "DateRented", "Comments"};

    private List<Rating> myList;


    
    private RatingCollection myRatingCollection;
    /**
     * This constructor calls the method to create all of the components
     */
    public RatingsGUI() {
        setLayout(new BorderLayout());
        myRatingCollection = new RatingCollection();
        myList = new ArrayList<>();
        getData();
        createComponents();
        setVisible(true);
    }

    /**
     * Retrieve the list of car to display
     *
     * @param theSearchKey the key to search for a car.
     * @return list of student
     */
    private void getData() {
        myList.clear();
        Iterator iter;
        iter = myRatingCollection.getIterator(null);

        int size = myRatingCollection.getSize();

        if (iter != null) {
            myData = new Object[size][myColumnNames.length];
            int i = 0;
            for (; iter.hasNext(); ) {
                Rating rating = (Rating) iter.next();
                myList.add(rating);
                myData[i][0] = rating.getId();
                myData[i][1] = rating.getRid();
                myData[i][2] = rating.getStar();
                myData[i][3] = rating.getDate();
                myData[i][4] = rating.getComments();
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
        myBtnList = new JButton("Rating List");
        myBtnList.addActionListener(this);


        myBtnAdd = new JButton("Add Rating");
        myBtnAdd.addActionListener(this);

        myPnlButtons.add(myBtnList);
        myPnlButtons.add(myBtnAdd);

        addListPanel();


        add(myPnlButtons, BorderLayout.NORTH);
        add(myPnlContent, BorderLayout.CENTER);
    }

    /**
     * Create the list panel for displaying the list of car information.
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
     * Create the add panel for adding a car information.
     */
    public void addPanel() {
        // Add Panel
        myPnlAdd = new JPanel();
        myPnlAdd.setLayout(new GridLayout(9, 0));
        String labelNames[] = {"Enter Reservation ID: *", "Star: *",  "Comments: *"};
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
            performAddReview();
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
        } 
    }

    
    private void performAddReview() {
        String ridStr = myTxfField[0].getText();
        int rid = 0;

        if (ridStr.length() != 0) {
            try {
            	rid = Integer.parseInt(ridStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter reservation id an integer");
                myTxfField[0].setFocusable(true);
                return;
            }
        }
        String starStr = myTxfField[1].getText();
        int star = 0;

        if (starStr.length() != 0) {
            try {
            	star = Integer.parseInt(starStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter Star an integer 0-5");
                myTxfField[1].setFocusable(true);
                return;
            }
        }
        String comments = myTxfField[2].getText();
        if (comments.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Comments:");
            myTxfField[2].setFocusable(true);
            return;
        }
        
        
        

        if (comments.length() > 100 || star > 5 || star < 0) {
            if (star > 5 || star < 0) {
                JOptionPane.showMessageDialog(null, "Invalid input! " +
                                "star should be between 0-5",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } else if (comments.length() > 100) {
            	JOptionPane.showMessageDialog(null, "Invalid input! " +
                        "Comments must not exceed 100 chars!",
                "Add failed", JOptionPane.WARNING_MESSAGE);
            }
        } else {
        	Random rn = new Random();
        	int n = 10000000 - 0 + 1;
        	int i = rn.nextInt() % n;
        	int randomNum =  0 + i;
        	java.sql.Date convertedDate = ReservationCollection.getReservationDate(rid);		
            Rating rating = new Rating(randomNum, rid, star, convertedDate, comments);
            String message = null;
            if (RatingCollection.addReview(rating)) {
                message = "Rating added successfully!";
                JOptionPane.showMessageDialog(null, message);
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
        } 
    }
}