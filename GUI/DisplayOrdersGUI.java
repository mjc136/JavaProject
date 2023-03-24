package GUI;

import javax.swing.*;

import Orders.DeleteOrders;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayOrdersGUI extends JPanel{

    private String idData;
    private String firstnameData;
    private String lastnameData;
    private String addressData;
    private String itemNameData;
    private int orderQuantityData;
    private double totalPriceData;
    private Date dateData;
    private JLabel id;
    private JLabel firstname;
    private JLabel lastname;
    private JLabel address;
    private JLabel itemName;
    private JLabel orderQuantity;
    private JLabel totalPrice;
    private JLabel date;
    private JLabel idLabel;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel itemNameLabel;
    private JLabel addressLabel;
    private JLabel orderQuantityLabel;
    private JLabel totalPriceLabel;
    private JLabel dateLabel;
    private JButton editOrder;
    private JButton deleteOrder;
    private JButton addOrder;
    private JButton back;
    
    private JComboBox<String> itemList = new JComboBox<>();
    private final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
    private Connection connection = null;
    private PreparedStatement pstat= null;
    private ResultSet resultSet = null;

    public DisplayOrdersGUI(){
         
        GridBagConstraints c = new GridBagConstraints();  
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);

        // back button

        back = new JButton("Back");
        c.gridx = 0;
        c.gridy = 0;
        add(back, c);

        back.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
            }
        });

        idLabel = new JLabel("ID");
        c.gridy+=2;
        add(idLabel,c);

        firstnameLabel = new JLabel("First name");
        c.gridy++;
        add(firstnameLabel,c);
        
        lastnameLabel = new JLabel("Last name");
        c.gridy++;
        add(lastnameLabel,c);
        
        addressLabel = new JLabel("Address");
        c.gridy++;
        add(addressLabel,c);
        
        itemNameLabel = new JLabel("Item name");
        c.gridy++;
        add(itemNameLabel,c);
        
        orderQuantityLabel = new JLabel("Order Quantity");
        c.gridy++;
        add(orderQuantityLabel,c);
        
        totalPriceLabel = new JLabel("Total Price");
        c.gridy++;
        add(totalPriceLabel,c);

        dateLabel = new JLabel("Date of Purchase");
        c.gridy++;
        add(dateLabel,c);

        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT * FROM orders");
            // query data in the table
            resultSet = pstat.executeQuery();
            // process query results
            while (resultSet.next()) {
                String productsItemName = resultSet.getString("item_name");
                String customerFirstName = resultSet.getString("first_name");
                itemList.addItem(productsItemName + " " + customerFirstName); // add the item name to the combobox
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx++;
        c.gridy = 0;

        // buttons

        addOrder = new JButton("Add");
        add(addOrder, c);
        
        addOrder.addActionListener(new ActionListener(){ // Takes users to add item screen screen
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new InsertOrdersGUI());
            }
        });
        
        c.gridy++;
        add(itemList, c);

        itemList.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent e){
                try{
                    remove(addOrder);
                    remove(itemList);

                    String selectedOrder = (String) itemList.getSelectedItem();
                    if(selectedOrder != null){
                        PreparedStatement pstat = connection.prepareStatement("SELECT * FROM orders WHERE item_name = ? AND first_name = ?");
                        int space = selectedOrder.indexOf(" ");
                        pstat.setString(1, selectedOrder.substring(0, space));
                        pstat.setString(2, selectedOrder.substring(space+1, selectedOrder.length()));
                        ResultSet resultSet2 = pstat.executeQuery();
                        if(resultSet2.next()) {
                            idData = resultSet2.getString("item_id");
                            firstnameData = resultSet2.getString("first_name");
                            lastnameData = resultSet2.getString("last_name");
                            addressData = resultSet2.getString("address");
                            itemNameData = resultSet2.getString("item_name");
                            orderQuantityData = resultSet2.getInt("order_quantity");
                            totalPriceData = resultSet2.getDouble("total_price");
                            dateData = resultSet2.getDate("date_of_purchase");
                        }
                        id = new JLabel(idData);
                        c.gridy++;
                        add(id, c);
                        c.gridy++;
                        if(firstnameData != null){  // if null skip 
                            firstname = new JLabel(firstnameData);
                            add(firstname, c);
                        }
                        c.gridy++;
                        if(lastnameData != null){
                            lastname = new JLabel(lastnameData);
                            add(lastname, c);
                        }
                        c.gridy++;
                        if(addressData != null){
                            address = new JLabel(addressData);
                            add(address, c);
                        }
                        c.gridy++;
                        if(itemNameData != null){
                            itemName = new JLabel(itemNameData);
                            add(itemName, c);
                        }
                        c.gridy++;
                        if(orderQuantityData != 0){
                            orderQuantity = new JLabel(String.valueOf(orderQuantityData));
                            add(orderQuantity, c);
                        }
                        c.gridy++;
                        if(totalPriceData != 0){
                            totalPrice = new JLabel(String.valueOf(totalPriceData));
                            add(totalPrice, c);
                        }
                        c.gridy++;
                        if(dateData != null){
                            date = new JLabel(dateData.toString());
                            add(date, c);
                        }
                        c.gridy++;

                        editOrder = new JButton("Edit");
                        c.gridy++;
                        c.gridx++;
                        add(editOrder, c);

                        editOrder.addActionListener(new ActionListener(){   // Takes users to edit item screen
                            public void actionPerformed(ActionEvent e) {
                                //GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new UpdateOrderGUI(Integer.parseInt(idData), itemNameData, itemPriceData, itemStockData));
                            }
                        });

                        deleteOrder = new JButton("Delete");
                        c.gridy++;
                        add(deleteOrder, c);

                        deleteOrder.addActionListener(new ActionListener(){   // Deletes selected item
                            public void actionPerformed(ActionEvent e) {
                                new DeleteOrders(Integer.parseInt(idData));
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayItemGUI());
                            }
                        });

                        GUIHandler.handler.revalidate(); // tell the container to re-layout its components
                        GUIHandler.handler.repaint(); // tell the container to repaint itself
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                finally{
                    try{
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (pstat != null) {
                            pstat.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        });
    }
    
}
