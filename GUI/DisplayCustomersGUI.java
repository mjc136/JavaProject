package GUI;

import javax.swing.*;

import Customer.DeleteCustomer;
import Users.UserSession;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class DisplayCustomersGUI extends JPanel{
    
    private String idData;
    private String firstnameData;
    private String lastnameData;
    private String addressData;
    private String emailData;
    private String phoneNumData;
    private Date dobData;
    private JLabel id;
    private JLabel firstname;
    private JLabel lastname;
    private JLabel address;
    private JLabel email;
    private JLabel phoneNum;
    private JLabel dob;
    private JLabel idLabel;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel phoneNumLabel;
    private JLabel dobLabel;
    private final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
    private Connection connection = null;
    private PreparedStatement pstat= null;
    private ResultSet resultSet = null;

    private JButton back;
     
    public DisplayCustomersGUI(){
         
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
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
            }
        });

        idLabel = new JLabel("Customer ID");
        c.gridy+=2;
        add(idLabel,c);
        
        firstnameLabel = new JLabel("First Name");
        c.gridy++;
        add(firstnameLabel,c);
        
        lastnameLabel = new JLabel("Last Name");
        c.gridy++;
        add(lastnameLabel,c);
        
        addressLabel = new JLabel("Address");
        c.gridy++;
        add(addressLabel,c);
        
        emailLabel = new JLabel("Email");
        c.gridy++;
        add(emailLabel,c);
        
        phoneNumLabel = new JLabel("Phone number");
        c.gridy++;
        add(phoneNumLabel,c);
        
        dobLabel = new JLabel("Date of birth");
        c.gridy++;
        add(dobLabel,c);
        
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx++;
        c.gridy = 0;

        // buttons

        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            PreparedStatement pstat = connection.prepareStatement("SELECT * FROM Customers WHERE customer_id=?");
            pstat.setInt(1, UserSession.getUser());
            ResultSet resultSet = pstat.executeQuery();
            if(resultSet.next()) {
                idData = resultSet.getString("customer_id");
                firstnameData = resultSet.getString("first_name");
                lastnameData = resultSet.getString("last_name");
                addressData = resultSet.getString("address");
                emailData = resultSet.getString("email");
                phoneNumData = resultSet.getString("phone_number");
                dobData = resultSet.getDate("date_of_birth");
            }
            id = new JLabel(idData);
            c.gridy = 2;
            add(id, c);
            if(firstnameData != null){  // if null skip 
                firstname = new JLabel(firstnameData);
                c.gridy++;
                add(firstname, c);
            }
            if(lastnameData != null){
                lastname = new JLabel(lastnameData);
                c.gridy++;
                add(lastname, c);
            }
            if(addressData != null){
                address = new JLabel(addressData);
                c.gridy++;
                add(address, c);
            }
            if(emailData != null){
                email = new JLabel(emailData);
                c.gridy++;
                add(email, c);
            }
            if(phoneNumData != null){
                phoneNum = new JLabel(phoneNumData);
                c.gridy++;
                add(phoneNum, c);
            }
            if(dobData != null){
                dob = new JLabel(dobData.toString());
                c.gridy++;
                add(dob, c);
            }

            JButton editCustomer = new JButton("Edit");
            c.gridy++;
            c.gridx++;
            add(editCustomer, c);

            editCustomer.addActionListener(new ActionListener(){   // Takes users to edit customer screen
                public void actionPerformed(ActionEvent e) {
                    GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new UpdateCustomerGUI(Integer.parseInt(idData), firstnameData, lastnameData, addressData, emailData, phoneNumData, dobData));
                }
            });

            JButton deleteCustomer = new JButton("Delete");
            c.gridy++;
            add(deleteCustomer, c);

            deleteCustomer.addActionListener(new ActionListener(){   // Deletes selected user
                public void actionPerformed(ActionEvent e) {
                    new DeleteCustomer(Integer.parseInt(idData));
                    GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new LoginGUI());
                    UserSession.setUser(0);
                }
            });

            GUIHandler.handler.revalidate(); // tell the container to re-layout its components
            GUIHandler.handler.repaint(); // tell the container to repaint itself
        } catch(SQLException sqlException){
        sqlException.printStackTrace();
        }
        finally{
            try{
                if(resultSet != null) {
                    resultSet.close();
                }
                if(pstat != null) {
                    pstat.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}

