package GUI;

import javax.swing.*;
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
    
    private JComboBox<String> customerList = new JComboBox<>();
    private final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
    private Connection connection = null;
    private PreparedStatement pstat= null;
    private ResultSet resultSet = null;
     
    public DisplayCustomersGUI(){
         
        GridBagConstraints c = new GridBagConstraints();  
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30)); // set grid size
        setLayout(new GridBagLayout()); 
        c.insets = new Insets(5, 5, 5, 5); // set margin
        setBackground(Color.cyan);

        try{
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT * FROM Customers");
            // query data in the table
            resultSet = pstat.executeQuery();
            // process query results
            while (resultSet.next()) {
                String customerName = resultSet.getString("first_name");
                customerList.addItem(customerName); // add the customer name to the combobox
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
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

        customerList.addActionListener(new ActionListener(){   // when clear is pressed everything is set to null
            public void actionPerformed(ActionEvent e) {
                try{
                    String selectedCustomer = (String) customerList.getSelectedItem();
                    if(selectedCustomer != null){
                        PreparedStatement pstat2 = connection.prepareStatement("SELECT * FROM Customers WHERE first_name = ?");
                        pstat2.setString(1, selectedCustomer);
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
                        if(firstnameData != null){
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

                        JButton addCustomer = new JButton("Add");
                        c.gridy++;
                        add(addCustomer, c);

                        addCustomer.addActionListener(new ActionListener(){ // Takes users to add customer screen
                            public void actionPerformed(ActionEvent e) {
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new InsertCustomerGUI());
                            }
                        });

                        JButton editCustomer = new JButton("Edit");
                        c.gridx++;
                        add(editCustomer, c);

                        editCustomer.addActionListener(new ActionListener(){   // Takes users to edit customer screen
                            public void actionPerformed(ActionEvent e) {
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
                            }
                        });

                        JButton deleteCustomer = new JButton("Delete");
                        c.gridx++;
                        add(deleteCustomer, c);

                        deleteCustomer.addActionListener(new ActionListener(){   // Takes users to delete customer screen
                            public void actionPerformed(ActionEvent e) {
                                GUIHandler.replacePanel(GUIHandler.handler, GUIHandler.panel, new MenuGUI());
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
