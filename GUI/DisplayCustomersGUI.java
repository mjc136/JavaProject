package GUI;

import javax.swing.*;

import Customer.DeleteCustomer;

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
    private JComboBox<String> customerList = new JComboBox<>();
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

        idLabel = new JLabel("ID");
        c.gridy+=2;
        add(idLabel,c);
        
        firstnameLabel = new JLabel("First name");
        c.gridy++;
        add(firstnameLabel,c);
        
        lastnameLabel = new JLabel("Last name");
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

        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT * FROM Customers");
            // query data in the table
            resultSet = pstat.executeQuery();
            // process query results
            while (resultSet.next()) {
                String customerFirstName = resultSet.getString("first_name");
                String customerLastName = resultSet.getString("last_name");
                customerList.addItem(customerFirstName + " " + customerLastName); // add the customer name to the combobox
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx++;
        c.gridy = 0;

        // buttons

        JButton addCustomer = new JButton("Add");
        add(addCustomer, c);
        
        addCustomer.addActionListener(new ActionListener(){ // Takes users to add customer screen
            public void actionPerformed(ActionEvent e) {
                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new InsertCustomerGUI());
            }
        });
        
        c.gridy++;
        add(customerList, c);

        customerList.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent e) {
                try{
                    remove(addCustomer);
                    remove(customerList);

                    String selectedCustomer = (String) customerList.getSelectedItem();
                    if(selectedCustomer != null){
                        PreparedStatement pstat2 = connection.prepareStatement("SELECT * FROM Customers WHERE first_name = ? AND last_name=?");
                        int space = selectedCustomer.indexOf(" ");
                        pstat2.setString(1, selectedCustomer.substring(0, space));
                        pstat2.setString(2, selectedCustomer.substring(space+1, selectedCustomer.length()));
                        ResultSet resultSet2 = pstat2.executeQuery();
                        if(resultSet2.next()) {
                            idData = resultSet2.getString("customer_id");
                            firstnameData = resultSet2.getString("first_name");
                            lastnameData = resultSet2.getString("last_name");
                            addressData = resultSet2.getString("address");
                            emailData = resultSet2.getString("email");
                            phoneNumData = resultSet2.getString("phone_number");
                            dobData = resultSet2.getDate("date_of_birth");
                        }
                        id = new JLabel(idData);
                        c.gridy++;
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
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new DisplayCustomersGUI());
                            }
                        });

                        GUIHandler.handler.revalidate(); // tell the container to re-layout its components
                        GUIHandler.handler.repaint(); // tell the container to repaint itself
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                finally{
                    try{
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (pstat != null) {
                            pstat.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        });
    }
}
