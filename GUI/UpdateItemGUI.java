package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Product.UpdateItem;


class UpdateItemGUI extends JPanel{ 

    private JButton back;
    private JTextField itemNameField;
    private JLabel itemNameLabel;
    private JTextField itemPriceField;
    private JLabel itemPriceLabel;
    private JLabel itemStockLabel;
    private JTextField itemStockField;
    private String itemNameData;
    private double itemPriceData;
    private int itemStockData;

    public UpdateItemGUI(int id, String itemName, Double itemPrice, int itemStock){
        
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
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayItemGUI());
            }
        });
        
        // item name

        itemNameField = new JTextField(10);
        itemNameField.setText(itemName);
        c.gridx = 2;
        c.gridy = 1;
        add(itemNameField, c);
        
        itemNameLabel = new JLabel("Item name");
        c.gridx--;
        add(itemNameLabel, c);
        
        // item price
        
        itemPriceField = new JTextField(10);
        itemPriceField.setText(itemPrice.toString());
        c.gridx = 4;
        add(itemPriceField, c);

        itemPriceLabel = new JLabel("Item price");
        c.gridx--;
        add(itemPriceLabel, c);

        // item stock

        itemStockField = new JTextField(20);
        itemStockField.setText(String.valueOf(itemStock));
        c.gridx = 2;
        c.gridy++;
        add(itemStockField, c);

        itemStockLabel = new JLabel("Item stock");
        c.gridx--;
        add(itemStockLabel, c);

        // buttons
        
        JButton add = new JButton("Confirm");
        c.gridx = 2;
        c.gridy++;
        add(add, c);
        
        add.addActionListener(new ActionListener(){ // convert date to string and send to sql file
            public void actionPerformed(ActionEvent e) {
                itemNameData = itemNameField.getText();
                itemPriceData = Double.parseDouble(itemPriceField.getText());
                itemStockData = Integer.parseInt(itemStockField.getText());
                
                new UpdateItem(id, itemNameData, itemPriceData, itemStockData);
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayItemGUI());
            }
        });
        
        JButton clear = new JButton("clear");
        c.gridx++;
        add(clear, c);


        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                itemNameField.setText(null);
                itemPriceField.setText(null);
                itemStockField.setText(null);
            }
        });

    }
    
}
