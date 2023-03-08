package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;

class CustomerGUI { 

    public CustomerGUI(){

        // panel

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();  
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        panel.setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        panel.setBackground(Color.cyan);
        
        // first name

        JTextField firstName = new JTextField(10);
        c.gridx = 5;
        c.gridy = 5;
        panel.add(firstName, c);

        JLabel firstNameLabel = new JLabel("Firstname");
        c.gridx = 4;
        c.gridy = 5;
        panel.add(firstNameLabel, c);

        // last name
        
        JTextField lastName = new JTextField(10);
        c.gridx = 20;
        c.gridy = 5;
        panel.add(lastName, c);

        JLabel lastNameLabel = new JLabel("Lastname");
        c.gridx = 19;
        c.gridy = 5;
        panel.add(lastNameLabel, c);

        // address

        JTextField address = new JTextField(20);
        c.gridx = 5;
        c.gridy = 10;
        panel.add(address, c);

        JLabel addressLabel = new JLabel("Address");
        c.gridx = 4;
        c.gridy = 10;
        panel.add(addressLabel, c);

        // email

        JTextField email = new JTextField(15);
        c.gridx = 5;
        c.gridy = 15;
        panel.add(email, c);

        JLabel emailLabel = new JLabel("Email Address");
        c.gridx = 4;
        c.gridy = 15;
        panel.add(emailLabel, c);

        // phone number
        
        JTextField phoneNumber = new JTextField(10);
        c.gridx = 20;
        c.gridy = 10;
        panel.add(phoneNumber, c);

        JLabel phoneNumberLabel = new JLabel("Phone Number");
        c.gridx = 19;
        c.gridy = 10;
        panel.add(phoneNumberLabel, c);

        // date of birth

        Date dob = new Date(System.currentTimeMillis());
        SpinnerDateModel dobModel = new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dobModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        c.gridx = 20;
        c.gridy = 15;
        panel.add(dateSpinner, c);
        
        JLabel dobLabel = new JLabel("Date of Birth");
        c.gridx = 19;
        c.gridy = 15;
        panel.add(dobLabel, c);
        // buttons
        
        JButton ok = new JButton("ok");
        c.gridx = 10;
        c.gridy = 30;
        panel.add(ok, c);
        
        JButton cancel = new JButton("cancel");
        c.gridx = 20;
        c.gridy = 30;
        panel.add(cancel, c);
        
        // setting frame

        frame.setSize(500, 500);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Customer Details");
        frame.pack();
        frame.setVisible(true);



    }

    
}
