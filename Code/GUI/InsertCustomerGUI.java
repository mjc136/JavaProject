package GUI;

import javax.swing.*;

import Customer.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;


class InsertCustomerGUI extends JPanel{ 

    private JButton back;
    private JTextField firstName;
    private JLabel firstNameLabel;
    private JTextField lastName;
    private JLabel lastNameLabel;
    private JTextField address;
    private JLabel addressLabel;
    private JTextField email;
    private JLabel emailLabel;
    private JTextField phoneNumber;
    private JLabel phoneNumberLabel;
    private Date dob;
    private JLabel dobLabel;
    private JPasswordField password;
    private JLabel passwordLabel;
    private JPasswordField conPassword;
    private JLabel conPasswordLabel;
    private JButton add;
    private String firstNameData;
    private String lastNameData;
    private String addressData;
    private String emailData;
    private String phoneNumberData;
    private Date dobData;
    private String passwordData;
    private String conPasswordData;

    public InsertCustomerGUI(){
        
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
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new LoginGUI());
            }
        });
        
        // first name

        firstName = new JTextField(10);
        c.gridx = 2;
        c.gridy = 1;
        add(firstName, c);
        
        firstNameLabel = new JLabel("First Name");
        c.gridx--;
        add(firstNameLabel, c);
        
        // last name
        
        lastName = new JTextField(10);
        c.gridx = 4;
        add(lastName, c);

        lastNameLabel = new JLabel("Last Name");
        c.gridx--;
        add(lastNameLabel, c);

        // address

        address = new JTextField(20);
        c.gridx = 2;
        c.gridy++;
        add(address, c);

        addressLabel = new JLabel("Address");
        c.gridx--;
        add(addressLabel, c);

        // email

        email = new JTextField(15);
        c.gridx = 2;
        c.gridy++;
        add(email, c);

        emailLabel = new JLabel("Email Address");
        c.gridx--;
        add(emailLabel, c);
        
        // phone number
        
        phoneNumber = new JTextField(10);
        c.gridx = 4;
        c.gridy--;
        add(phoneNumber, c);

        phoneNumberLabel = new JLabel("Phone Number");
        c.gridx--;
        add(phoneNumberLabel, c);

        // date of birth

        dob = new Date(System.currentTimeMillis());
        SpinnerDateModel dobModel = new SpinnerDateModel(dob, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dobModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        c.gridx = 2;
        c.gridy+=2;
        add(dateSpinner, c);
        
        dobLabel = new JLabel("Date of Birth");
        c.gridx--;
        add(dobLabel, c);

        //password

        password = new JPasswordField(10);
        c.gridx = 4;
        c.gridy--;
        add(password, c);

        passwordLabel = new JLabel("Password");
        c.gridx--;
        add(passwordLabel, c);

        conPassword = new JPasswordField(10);
        c.gridx = 4;
        c.gridy++;
        add(conPassword, c);

        conPasswordLabel = new JLabel("Confirm Password");
        c.gridx--;
        add(conPasswordLabel, c);

        // buttons

        add = new JButton("add");
        c.gridx = 2;
        c.gridy+=2;
        add(add, c);

        add.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
                firstNameData = firstName.getText();
                lastNameData = lastName.getText();
                addressData = address.getText();
                emailData = email.getText();
                phoneNumberData = phoneNumber.getText();
                dobData = new java.sql.Date(dobModel.getDate().getTime());
                passwordData = String.valueOf(password.getPassword());
                conPasswordData = String.valueOf(conPassword.getPassword());
                
                if(passwordData.equals(conPasswordData)){
                    new InsertCustomer(firstNameData, lastNameData, addressData, emailData, phoneNumberData, dobData, passwordData);
                    GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new LoginGUI());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Password does not match confirm password");
                }
            }
        });
        
        JButton clear = new JButton("clear");
        c.gridx++;
        add(clear, c);


        clear.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                firstName.setText(null);
                lastName.setText(null);
                address.setText(null);
                email.setText(null);
                phoneNumber.setText(null);
                password.setText(null);
            }
        });

    }
    
}
