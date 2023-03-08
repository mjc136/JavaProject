package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Product.*;

public class InsertItemGUI {

    public InsertItemGUI(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();  
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        panel.setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        panel.setBackground(Color.cyan);
        
        //item name

        JTextField itemName = new JTextField(5);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(itemName, c);

        JLabel itemNameLabel = new JLabel("Item name");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(itemNameLabel, c);

        //item price

        JTextField itemPrice = new JTextField(5);
        c.gridx = 1;
        c.gridy = 1; 
        panel.add(itemPrice, c);

        JLabel itemPriceLabel = new JLabel("Item price");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(itemPriceLabel, c);

        //item stock

        JTextField itemStock = new JTextField(5);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(itemStock, c);

        JLabel itemStockLabel = new JLabel("Item stock");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(itemStockLabel, c);

        //buttons

        JButton add = new JButton("add");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(add, c);

        add.addActionListener(new ActionListener(){ // convert date to string and send to sql file
            public void actionPerformed(ActionEvent e) {
                String itemNameData = itemName.getText();
                double itemPriceData = Double.parseDouble(itemPrice.getText());
                int itemStockData = Integer.parseInt(itemStock.getText());

                new InsertItem(itemNameData, itemPriceData, itemStockData);
            }
        });

        JButton clear = new JButton("clear");
        c.gridx = 1;
        c.gridy = 3;
        panel.add(clear, c);

        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                itemName.setText(null);
                itemPrice.setText(null);
                itemStock.setText(null);
            }
        });


        // setting frame

        frame.setSize(500, 500);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Item Details");
        frame.pack();
        frame.setVisible(true);

    }
    
}
