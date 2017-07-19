package gui;

import design_pattern.Iterator;
import reservation.Brand;
import reservation.BrandCollection;
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
public class CarGUI extends JPanel implements ActionListener,
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
    private String[] myColumnNames = {"VIN", "PriceDay", "IsAvailable"};

    private JComboBox<String> myVinComboBox;
    private List<Car> myList;


    
    private CarCollection myCarCollection;
    /**
     * This constructor calls the method to create all of the components
     */
    public CarGUI() {
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


        myBtnAdd = new JButton("Add Car");
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
        String labelNames[] = {"Enter VIN of Car: *", "Price a day: *", "Model: *", "Make: *", "Bodystyle: *", "Capacity: *"};
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
            performAddCar();
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

    
    private void performAddCar() {
        String vin = myTxfField[0].getText();
        if (vin.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter VIN of Car:");
            myTxfField[0].setFocusable(true);
            return;
        }
        String priceStr = myTxfField[1].getText();
        Double price = 0.0;

        if (priceStr.length() != 0) {
            try {
            	price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter price an integer");
                myTxfField[1].setFocusable(true);
                return;
            }
        }
        String model = myTxfField[2].getText();
        if (model.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter model of Car:");
            myTxfField[2].setFocusable(true);
            return;
        }
        String make = myTxfField[3].getText();
        if (make.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter make of Car:");
            myTxfField[3].setFocusable(true);
            return;
        }
        String bodystyle = myTxfField[4].getText();
        if (bodystyle.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter bodystyle of Car:");
            myTxfField[4].setFocusable(true);
            return;
        }
        String capStr = myTxfField[5].getText();
        int cap = 0;

        if (capStr.length() != 0) {
            try {
            	cap = Integer.parseInt(capStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter capacity as an integer");
                myTxfField[5].setFocusable(true);
                return;
            }
        }
        

        if (vin.length() > 17 || price < 0) {
            if (vin.length() > 17) {
                JOptionPane.showMessageDialog(null, "Invalid input! " +
                                "VIN should be less than 17 characters!",
                        "Add failed", JOptionPane.WARNING_MESSAGE);
            } else if (price < 0) {
            	JOptionPane.showMessageDialog(null, "Invalid input! " +
                        "Price a day cannot be negative!",
                "Add failed", JOptionPane.WARNING_MESSAGE);
            } 
        } else {
        	
        			
            Car car = new Car(vin, price, true);
            Brand brand = new Brand(model, make, bodystyle, cap, vin);
            String message = null;
            if (CarCollection.addCar(car)) {
                message = "Car added successfully!";
                JOptionPane.showMessageDialog(null, message);

                
            }
            if (BrandCollection.addBrand(brand)) {
                message = "Brand added successfully!";
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