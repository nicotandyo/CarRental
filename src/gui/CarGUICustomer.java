package gui;

import design_pattern.Iterator;
import reservation.Car;
import reservation.CarCollection;
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
public class CarGUICustomer extends JPanel implements ActionListener,
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
    private JButton myBtnList;

    /**
     * All panels that will use in this frame.
     */
    private JPanel myPnlButtons, myPnlAdd, myPnlContent;

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
    private String[] myColumnNames = {"VIN", "PriceDay", "IsAvailable"};

    private JComboBox<String> myVinComboBox;
    private List<Car> myList;


    
    private CarCollection myCarCollection;
    /**
     * This constructor calls the method to create all of the components
     */
    public CarGUICustomer() {
        setLayout(new BorderLayout());
        myCarCollection = new CarCollection();
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
        iter = myCarCollection.getIterator(null);

        int size = myCarCollection.getSize();

        if (iter != null) {
            myData = new Object[size][myColumnNames.length];
            int i = 0;
            for (; iter.hasNext(); ) {
                Car car = (Car) iter.next();
                myList.add(car);
                myData[i][0] = car.getVIN();
                myData[i][1] = car.getPrice();
                myData[i][2] = car.getAvailability();
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
        myBtnList = new JButton("Car List");
        myBtnList.addActionListener(this);



        myPnlButtons.add(myBtnList);

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
     * Make the buttons work!
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myBtnList) {
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