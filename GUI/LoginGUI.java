package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Users.*;

public class LoginGUI extends JPanel{

    public LoginGUI(){

        GridBagConstraints c = new GridBagConstraints(); 
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);
        
        //user id

        JTextField userId = new JTextField(5);
        c.gridx = 1;
        c.gridy = 0;
        add(userId, c);

        JLabel userIdLabel = new JLabel("User ID");
        c.gridx--;
        add(userIdLabel, c);

        //password

        JTextField password = new JTextField(5);
        c.gridx++;
        c.gridy++; 
        add(password, c);

        JLabel passwordLabel = new JLabel("Password");
        c.gridx--;
        add(passwordLabel, c);

        //buttons

        JButton login = new JButton("Login");
        c.gridx--;
        c.gridy++;
        add(login, c);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int userIdData = Integer.parseInt(userId.getText());
                String passwordData = password.getText();
                Verify v = new Verify(userIdData, passwordData);
                if(v.verifier(userIdData, passwordData)){
                    UserSession.setUser(userIdData);
                    GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
                }
            }
        });

        
        JButton clear = new JButton("clear");
        c.gridx+=2;
        add(clear, c);
        
        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                userId.setText(null);
                password.setText(null);
            }
        });
        
        JButton addCustomer = new JButton("Register");
        c.gridy++;
        add(addCustomer, c);
        
        addCustomer.addActionListener(new ActionListener(){ // Takes users to add customer screen
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new InsertCustomerGUI());
            }
        });
        
    }

}
