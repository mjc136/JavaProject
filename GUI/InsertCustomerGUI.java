package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import Customer.*;


class InsertCustomerGUI extends JPanel{ 

    public InsertCustomerGUI(){
        
        GridBagConstraints c = new GridBagConstraints();  
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);
        
        // first name

        JTextField firstName = new JTextField(10);
        c.gridx = 5;
        c.gridy = 5;
        add(firstName, c);
        
        JLabel firstNameLabel = new JLabel("Firstname");
        c.gridx = 4;
        c.gridy = 5;
        add(firstNameLabel, c);
        
        // last name
        
        JTextField lastName = new JTextField(10);
        c.gridx = 20;
        c.gridy = 5;
        add(lastName, c);

        JLabel lastNameLabel = new JLabel("Lastname");
        c.gridx = 19;
        c.gridy = 5;
        add(lastNameLabel, c);

        // address

        JTextField address = new JTextField(20);
        c.gridx = 5;
        c.gridy = 10;
        add(address, c);

        JLabel addressLabel = new JLabel("Address");
        c.gridx = 4;
        c.gridy = 10;
        add(addressLabel, c);

        // email

        JTextField email = new JTextField(15);
        c.gridx = 5;
        c.gridy = 15;
        add(email, c);

        JLabel emailLabel = new JLabel("Email Address");
        c.gridx = 4;
        c.gridy = 15;
        add(emailLabel, c);
        
        // phone number
        
        JTextField phoneNumber = new JTextField(10);
        c.gridx = 20;
        c.gridy = 10;
        add(phoneNumber, c);

        JLabel phoneNumberLabel = new JLabel("Phone Number");
        c.gridx = 19;
        c.gridy = 10;
        add(phoneNumberLabel, c);

        // date of birth

        Date dob = new Date(System.currentTimeMillis());
        SpinnerDateModel dobModel = new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dobModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        c.gridx = 20;
        c.gridy = 15;
        add(dateSpinner, c);
        
        JLabel dobLabel = new JLabel("Date of Birth");
        c.gridx = 19;
        c.gridy = 15;
        add(dobLabel, c);

        // buttons

        JButton add = new JButton("add");
        c.gridx = 10;
        c.gridy = 30;
        add(add, c);

        add.addActionListener(new ActionListener(){ // convert date to string and send to sql file
            public void actionPerformed(ActionEvent e) {
                String firstNameData = firstName.getText();
                String lastNameData = lastName.getText();
                String addressData = address.getText();
                String emailData = email.getText();
                String phoneNumberData = phoneNumber.getText();
                Date dobData = dob;

                new InsertCustomer(firstNameData, lastNameData, addressData, emailData, phoneNumberData, dobData);
            }
        });
        
        JButton clear = new JButton("clear");
        c.gridx = 11;
        c.gridy = 30;
        add(clear, c);

        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                firstName.setText(null);
                lastName.setText(null);
                address.setText(null);
                email.setText(null);
                phoneNumber.setText(null);
            }
        });

    }
    
}
