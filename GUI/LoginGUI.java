package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Users.*;

public class LoginGUI extends JPanel{

    public LoginGUI(){

        GridBagConstraints c = new GridBagConstraints();  
        setBorder(BorderFactory.createEmptyBorder(200,200,200,200)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);
        
        //user id

        JTextField userId = new JTextField(5);
        c.gridx = 1;
        c.gridy = 0;
        add(userId, c);

        JLabel userIdLabel = new JLabel("User ID");
        c.gridx = 0;
        c.gridy = 0;
        add(userIdLabel, c);

        //password

        JTextField password = new JTextField(5);
        c.gridx = 1;
        c.gridy = 1; 
        add(password, c);

        JLabel passwordLabel = new JLabel("Password");
        c.gridx = 0;
        c.gridy = 1;
        add(passwordLabel, c);

        //buttons

        JButton login = new JButton("Login");
        c.gridx = 0;
        c.gridy = 3;
        add(login, c);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int userIdData = Integer.parseInt(userId.getText());
                String passwordData = password.getText();
                Verify v = new Verify(userIdData, passwordData);
                if(v.verifier(userIdData, passwordData)){
                    GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
                }
            }
        });

        JButton clear = new JButton("clear");
        c.gridx = 1;
        c.gridy = 3;
        add(clear, c);

        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                userId.setText(null);
                password.setText(null);
            }
        });
        
    }

}
