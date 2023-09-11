package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Product.*;

public class InsertItemGUI extends JPanel{

    private JButton back;
    private JTextField itemName;
    private JLabel itemNameLabel;
    private JTextField itemPrice;
    private JLabel itemPriceLabel;
    private JTextField itemStock;
    private JLabel itemStockLabel;
    private JButton add;
    private String itemNameData;
    private double itemPriceData;
    private int itemStockData;
    private JButton clear;
    

    public InsertItemGUI(){

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
        
        //item name
        itemName = new JTextField(5);
        c.gridx++;
        c.gridy++;
        add(itemName, c);

        itemNameLabel = new JLabel("Item name");
        c.gridx--;
        add(itemNameLabel, c);

        //item price

        itemPrice = new JTextField(5);
        c.gridy++;
        c.gridx++;
        add(itemPrice, c);

        itemPriceLabel = new JLabel("Item price");
        c.gridx--;
        add(itemPriceLabel, c);

        //item stock

        itemStock = new JTextField(5);
        c.gridx++;
        c.gridy++;
        add(itemStock, c);

        itemStockLabel = new JLabel("Item stock");
        c.gridx--;
        add(itemStockLabel, c);

        //buttons

        add = new JButton("add");
        c.gridx = 0;
        c.gridy++;
        add(add, c);

        add.addActionListener(new ActionListener(){ // convert date to string and send to sql file
            public void actionPerformed(ActionEvent e){
                itemNameData = itemName.getText();
                itemPriceData = Double.parseDouble(itemPrice.getText());
                itemStockData = Integer.parseInt(itemStock.getText());

                new InsertItem(itemNameData, itemPriceData, itemStockData);
            }
        });

        clear = new JButton("clear");
        c.gridx++;
        add(clear, c);

        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                itemName.setText(null);
                itemPrice.setText(null);
                itemStock.setText(null);
            }
        });

    }
    
}
