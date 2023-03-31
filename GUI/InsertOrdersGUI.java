package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Orders.*;

public class InsertOrdersGUI extends JPanel{
    
    private JButton back;
    private JTextField itemName;
    private JLabel itemLabel;
    private JTextField orderQuantity;
    private JLabel orderQuantityLabel;
    private JButton add;
    private String itemNameData;
    private int orderQuantityData;
    private int itemId;

    private JComboBox<String> itemList = new JComboBox<>();
    private final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
    private Connection connection = null;
    private PreparedStatement pstat= null;
    private ResultSet resultSet = null;

    public InsertOrdersGUI(){
        
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
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayOrdersGUI());
            }
        });

        // item

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
        finally{
            try{
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }

        c.gridy++;
        c.gridx++;
        add(itemList, c);

        itemList.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent e){
                try{
                    connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
                    String selectedItem = (String) itemList.getSelectedItem();
                    if(selectedItem != null){
                        pstat = connection.prepareStatement("SELECT item_name FROM Products WHERE item_name = ?");
                        pstat.setString(1, selectedItem);
                        resultSet = pstat.executeQuery();
                        if(resultSet.next()) {
                            itemNameData = resultSet.getString(1);
                        }
                        
                        GUIHandler.handler.revalidate(); // tell the container to re-layout its components
                        GUIHandler.handler.repaint(); // tell the container to repaint itself
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    try{
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (pstat != null) {
                            pstat.close();
                        }
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        });

        itemLabel = new JLabel("Item");
        c.gridx--;
        add(itemLabel , c);
        
        // order quantity
        
        orderQuantity = new JTextField(10);
        c.gridx++;
        c.gridy++;
        add(orderQuantity, c);

        orderQuantityLabel = new JLabel("Order Quantity");
        c.gridx--;
        add(orderQuantityLabel, c);

        // buttons

        add = new JButton("add");
        c.gridy++;
        add(add, c);

        add.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
                orderQuantityData = Integer.parseInt(orderQuantity.getText());
                // get item id
                try{
                    connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
                    String selectedItem = (String) itemList.getSelectedItem();
                    if(selectedItem != null){
                        pstat = connection.prepareStatement("SELECT item_id FROM Products WHERE item_name = ?");
                        pstat.setString(1, itemNameData);
                        resultSet = pstat.executeQuery();
                        if(resultSet.next()) {
                            itemId = resultSet.getInt(1);
                        }
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

                new InsertOrders(orderQuantityData, itemId);
            }
        });
        
        JButton clear = new JButton("clear");
        c.gridx++;
        add(clear, c);


        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                itemName.setText(null);
                orderQuantity.setText(null);
            }
        });

    }

}
