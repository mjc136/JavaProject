package GUI;


import javax.swing.*;

import Users.UserSession;

import java.awt.*;
import java.awt.event.*;

public class MenuGUI extends JPanel{

    public MenuGUI(){

        GridBagConstraints c = new GridBagConstraints();  
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);

        //buttons

        JButton customer = new JButton("Profile");
        c.gridx=0;
        add(customer, c);

        customer.addActionListener(new ActionListener(){ // Takes users to customer menu
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayCustomersGUI());
            }
        });

        JButton items = new JButton("Products");
        c.gridx++;
        add(items, c);

        items.addActionListener(new ActionListener(){   // Takes users to products menu
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayItemGUI());
            }
        });

        JButton orders = new JButton("Orders");
        c.gridx++;
        add(orders, c);

        orders.addActionListener(new ActionListener(){   // Takes users to orders menu
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayOrdersGUI());
            }
        });

        JButton logout = new JButton("Log out");
        c.gridx++;
        add(logout, c);

        logout.addActionListener(new ActionListener(){ // Takes users to customer menu
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new LoginGUI());
                UserSession.setUser(0);
            }
        });

    }
    
}
