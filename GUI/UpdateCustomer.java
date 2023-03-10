package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import Customer.*;


class UpdateCustomerGUI extends JPanel{ 

    private JButton back;
    private JTextField firstNameField;
    private JLabel firstNameLabel;
    private JTextField lastNameField;
    private JLabel lastNameLabel;
    private JLabel addressLabel;
    private JTextField addressField;
    private JTextField emailField;
    private JLabel emailLabel;
    private JTextField phoneNumberField;
    private JLabel phoneNumberLabel;
    private Date dobField;
    private String firstNameData;
    private String lastNameData;
    private String addressData;
    private String emailData;
    private String phoneNumberData;

    public UpdateCustomerGUI(int id, String firstname, String lastname, String address, String email, String phoneNum, java.sql.Date dob){
        
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
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayCustomersGUI());
            }
        });
        
        // first name

        firstNameField = new JTextField(10);
        firstNameField.setText(firstname);
        c.gridx = 2;
        c.gridy = 1;
        add(firstNameField, c);
        
        firstNameLabel = new JLabel("Firstname");
        c.gridx--;
        add(firstNameLabel, c);
        
        // last name
        
        lastNameField = new JTextField(10);
        lastNameField.setText(lastname);
        c.gridx = 4;
        add(lastNameField, c);

        lastNameLabel = new JLabel("Lastname");
        c.gridx--;
        add(lastNameLabel, c);

        // address

        addressField = new JTextField(20);
        addressField.setText(address);
        c.gridx = 2;
        c.gridy++;
        add(addressField, c);

        addressLabel = new JLabel("Address");
        c.gridx--;
        add(addressLabel, c);

        // email

        emailField = new JTextField(15);
        emailField.setText(email);
        c.gridx = 2;
        c.gridy++;
        add(emailField, c);

        emailLabel = new JLabel("Email Address");
        c.gridx--;
        add(emailLabel, c);
        
        // phone number
        
        phoneNumberField = new JTextField(10);
        phoneNumberField.setText(phoneNum);
        c.gridx = 4;
        add(phoneNumberField, c);

        phoneNumberLabel = new JLabel("Phone Number");
        c.gridx--;
        add(phoneNumberLabel, c);

        // date of birth
        SpinnerDateModel dobModel = new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dobModel);
        
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        c.gridx = 2;
        c.gridy++;
        add(dateSpinner, c);
        
        JLabel dobLabel = new JLabel("Date of Birth");
        c.gridx--;
        add(dobLabel, c);

        // buttons
        
        JButton add = new JButton("Confirm");
        c.gridx = 2;
        c.gridy++;
        add(add, c);
        
        add.addActionListener(new ActionListener(){ // convert date to string and send to sql file
            public void actionPerformed(ActionEvent e) {
                firstNameData = firstNameField.getText();
                lastNameData = lastNameField.getText();
                addressData = addressField.getText();
                emailData = emailField.getText();
                phoneNumberData = phoneNumberField.getText();
                dobField = new java.sql.Date(dobModel.getDate().getTime());
                
                new UpdateCustomer(id, firstNameData, lastNameData, addressData, emailData, phoneNumberData, dobField);
            }
        });
        
        JButton clear = new JButton("clear");
        c.gridx++;
        add(clear, c);


        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                firstNameField.setText(null);
                lastNameField.setText(null);
                addressField.setText(null);
                emailField.setText(null);
                phoneNumberField.setText(null);
            }
        });

    }
    
}