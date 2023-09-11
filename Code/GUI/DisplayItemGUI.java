package GUI;

import javax.swing.*;

import Product.DeleteItem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class DisplayItemGUI extends JPanel{
    
    private String idData;
    private String itemNameData;
    private double itemPriceData;
    private int itemStockData;
    private JLabel id;
    private JLabel itemName;
    private JLabel itemPrice;
    private JLabel itemStock;
    private JLabel idLabel;
    private JLabel itemNameLabel;
    private JLabel itemPriceLabel;
    private JLabel itemStockLabel;
    private JButton editItem;
    private JButton deleteItem;
    private JButton addItem;
    private JButton back;
    
    private JComboBox<String> itemList = new JComboBox<>();
    private final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
    private Connection connection = null;
    private PreparedStatement pstat= null;
    private ResultSet resultSet = null;    

     
    public DisplayItemGUI(){
         
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

        idLabel = new JLabel("Item ID");
        c.gridy+=2;
        add(idLabel,c);
        
        itemNameLabel = new JLabel("Item name");
        c.gridy++;
        add(itemNameLabel,c);
        
        itemPriceLabel = new JLabel("Item Price");
        c.gridy++;
        add(itemPriceLabel,c);
        
        itemStockLabel = new JLabel("Items Left");
        c.gridy++;
        add(itemStockLabel,c);

        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT * FROM products");
            // query data in the table
            resultSet = pstat.executeQuery();
            // process query results
            while (resultSet.next()) {
                String productsItemName = resultSet.getString("item_name");
                itemList.addItem(productsItemName); // add the item name to the combobox
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

        addItem = new JButton("Add");
        add(addItem, c);
        
        addItem.addActionListener(new ActionListener(){ // Takes users to add item screen screen
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new InsertItemGUI());
            }
        });
        
        c.gridy++;
        add(itemList, c);

        itemList.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent e){
                try{
                    connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
                    if(id != null){ // refreshes display when new item is chosen
                        remove(id);
                        remove(itemName);
                        remove(itemPrice);
                        remove(itemStock);
                        remove(editItem);
                        remove(deleteItem);
                        c.gridx = 2;
                        c.gridy = 1;
                    }
                    String selectedItem = (String) itemList.getSelectedItem();
                    if(selectedItem != null){
                        PreparedStatement pstat = connection.prepareStatement("SELECT * FROM Products WHERE item_name = ?");
                        pstat.setString(1, selectedItem);
                        ResultSet resultSet2 = pstat.executeQuery();
                        if(resultSet2.next()) {
                            idData = resultSet2.getString("item_id");
                            itemNameData = resultSet2.getString("item_name");
                            itemPriceData = resultSet2.getDouble("item_price");
                            itemStockData = resultSet2.getInt("item_stock");
                        }
                        id = new JLabel(idData);
                        c.gridy++;
                        add(id, c);
                        if(itemNameData != null){  // if null skip 
                            itemName = new JLabel(itemNameData);
                            c.gridy++;
                            add(itemName, c);
                        }
                        if(itemPriceData != 0){ // if null skip 
                            itemPrice = new JLabel(String.valueOf(itemPriceData));
                            c.gridy++;
                            add(itemPrice, c);
                        }
                        if(itemStockData != 0){ // if null skip 
                            itemStock = new JLabel(String.valueOf(itemStockData));
                            c.gridy++;
                            add(itemStock, c);
                        }

                        editItem = new JButton("Edit");
                        c.gridy++;
                        c.gridx++;
                        add(editItem, c);

                        editItem.addActionListener(new ActionListener(){   // Takes users to edit item screen
                            public void actionPerformed(ActionEvent e) {
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new UpdateItemGUI(Integer.parseInt(idData), itemNameData, itemPriceData, itemStockData));
                            }
                        });

                        deleteItem = new JButton("Delete");
                        c.gridy++;
                        add(deleteItem, c);

                        deleteItem.addActionListener(new ActionListener(){   // Deletes selected item
                            public void actionPerformed(ActionEvent e) {
                                new DeleteItem(Integer.parseInt(idData));
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
