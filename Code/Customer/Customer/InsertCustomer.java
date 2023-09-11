package Customer;

import java.sql.*;
import javax.swing.JOptionPane;

public class InsertCustomer{

    
    public InsertCustomer(String firstName, String lastName, String address, String email, String phoneNum, Date dob, String password){
        
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/purchases";
        Connection connection = null ;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        int customerId = 0;
        
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "Sydpuppy2016");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO Customers (first_name, last_name, address, email, phone_number, date_of_birth, password) VALUES (?,?,?,?,?,?,?)");
            pstat.setString(1, firstName );
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNum);
            pstat.setDate(6, dob);
            pstat.setString(7, password);

            // insert data into table
            pstat.executeUpdate();
            pstat.close();

            //display user id to user
            pstat = connection.prepareStatement("Select customer_id From Customers WHERE first_name = ? AND last_name = ? AND address = ? AND email = ? AND phone_number = ? AND date_of_birth = ? AND password = ?;");
            pstat.setString(1, firstName );
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNum);
            pstat.setDate(6, dob);
            pstat.setString(7, password);

            resultSet = pstat.executeQuery();
            while(resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }

            // If the insert was successful, display a success message to the user
            JOptionPane.showMessageDialog(null, "Registration successful! your id is " + customerId);
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                pstat.close() ;
                connection.close () ;
                resultSet.close();
            }
            catch (Exception exception){
                exception.printStackTrace () ;
            }
        }
    }
} // end class


